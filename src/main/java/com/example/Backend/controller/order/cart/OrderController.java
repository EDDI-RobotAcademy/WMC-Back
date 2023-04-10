package com.example.Backend.controller.order.cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class OrderController {

    @PostMapping("/kakaoPay")
    public String kakaoPay() throws IOException {
        URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "KakaoAK 52f93d87ec4e46149a6c978cf9f6d606");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setDoOutput(true);
            String params = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=초코파이&quantity=1&total_amount=2200&vat_amount=200&tax_free_amount=0&approval_url=http://localhost:8080/order/kakaoPaySuccess&cancel_url=http://localhost:8080/order/kakaoPayCancel&fail_url=http://localhost:8080/order/kakaoPaySuccessFail";
            OutputStream os = conn.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeBytes(params);
            dos.close();

            int responseCode = conn.getResponseCode();

            InputStream is;
            if(responseCode == 200) {
                is = conn.getInputStream();
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

}
