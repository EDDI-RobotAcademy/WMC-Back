package com.example.Backend.controller.order;

import com.example.Backend.entity.member.Member;
import com.example.Backend.entity.order.Order;
import com.example.Backend.service.member.MemberService;
import com.example.Backend.service.order.OrderService;
import com.example.Backend.service.order.request.KakaoPayRequest;
import com.example.Backend.service.order.request.OrderItemRequest;
import com.example.Backend.service.order.response.ManagerOrderResponse;
import com.example.Backend.service.product.ProductService;
import com.example.Backend.service.security.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.example.Backend.exception.ManagerOrderNotAllowedException;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    final private ProductService productService;
    final private OrderService orderService;
    final private MemberService memberService;
    final private RedisService redisService;

    @PostMapping("/kakaoPay")
    public String kakaoPay(@RequestBody KakaoPayRequest payload) throws IOException {
        String token = payload.getToken();
        log.info("token: " + token);
        String memberValue = redisService.getValueByKey(token);
        log.info("Member value from Redis: " + memberValue);

        String authName = null;
        if (memberValue != null) {
            String[] value = memberValue.split(":");
            if (value.length > 0) {
                authName = value[1];
            }
        }
        if (authName.equals("MANAGER")) {
            throw new ManagerOrderNotAllowedException("관리자는 주문할 수 없습니다.");
        }
        List<OrderItemRequest> orderItems = payload.getOrderItems();
        for (OrderItemRequest orderItem : orderItems) {
            if (!orderService.isProductEnough(orderItem.getProductId(), orderItem.getQuantity())) {
                throw new RuntimeException("재고가 충분치 않습니다! : " + orderItem.getProductId());
            }
        }
        URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "KakaoAK 52f93d87ec4e46149a6c978cf9f6d606");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setDoOutput(true);
            String params = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=초코파이&quantity=1&total_amount=2200&vat_amount=200&tax_free_amount=0&approval_url=http://wemakecode.co.kr/order/kakaoPaySuccess&cancel_url=http://wemakecode.co.kr/order/kakaoPayCancel&fail_url=http://wemakecode.co.kr/order/kakaoPaySuccessFail";
            OutputStream os = conn.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeBytes(params);
            dos.close();

            int responseCode = conn.getResponseCode();

            InputStream is;
            if (responseCode == 200) {
                is = conn.getInputStream();
                for (OrderItemRequest orderItem : orderItems) {
                    productService.decreaseProductStock(orderItem.getProductId(), orderItem.getQuantity());
                }

                Long memberId = getMemberIdByToken(token);
                log.info("memberId: " + memberId.toString());
                orderService.createOrder(memberId, orderItems);
            } else {
                is = conn.getErrorStream();
            }
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Long getMemberIdByToken(String token) {
        return getaLong(token, log, redisService);
    }

    public static Long getaLong(String token, Logger log, RedisService redisService) {
//        token = token.substring(0, token.length() - 1);
        log.info("account(): " + token);


        Long memberId = null;
        String memberValue = redisService.getValueByKey(token);
        log.info("Member value from Redis: " + memberValue);

        if (memberValue != null) {
            String[] value = memberValue.split(":");
            if (value.length > 0) {
                memberId = Long.valueOf(value[0]);
            }
        }
        return memberId;
    }

//    @PostMapping("/kakaoPaySuccess")

//    @PostMapping("/myorderlist")
//    public List<Order> getMemberOrderList(@RequestBody String token) {
//        Long memberId = getMemberIdByToken(token);
//        return orderService.getMyOrderList(memberId);
//    }

    @PostMapping("/managerorderlist")
    public List<ManagerOrderResponse> getManagerOrderList(@RequestBody String token) {
        log.info("token:" + token);
        token = token.substring(0, token.length() - 1);
        log.info(token);
        String value = redisService.getValueByKey(token);
        log.info("value:" + value);
        String[] values = value.split(":");
        String authority = values[1];
        log.info("authority:" + authority);
        if(authority.equals("MANAGER")) {
            return orderService.getManagerOrderList();
        }
        throw new RuntimeException("권한이 없습니다.");
    }
}
