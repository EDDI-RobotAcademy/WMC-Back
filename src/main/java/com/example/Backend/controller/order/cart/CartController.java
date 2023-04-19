package com.example.Backend.controller.order.cart;

import com.example.Backend.service.security.RedisServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://52.79.176.94/", allowedHeaders = "*")
public class CartController {

    private final RedisServiceImpl redisService;

    @PostMapping("/validate")
    public String userValidation(@RequestBody String token) {
        token = token.substring(0, token.length() - 1);
        log.info("logout(): " + token);

        return redisService.getValueByKey(token);
    }

}
