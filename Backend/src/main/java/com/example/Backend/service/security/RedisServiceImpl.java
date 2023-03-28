package com.example.Backend.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    final private StringRedisTemplate redisTemplate;

    @Override
    public void setKeyAndValue(String token, Long memberId, String authorityName) {
        String memberIdAndAuthority = memberId + ":" + authorityName;
        ValueOperations<String, String> value = redisTemplate.opsForValue();
        value.set(token, memberIdAndAuthority, Duration.ofMinutes(60));
    }

    @Override
    public String getValueByKey(String token) {
        ValueOperations<String, String> value = redisTemplate.opsForValue();
        String memberIdAndAuthority = value.get(token);
        return memberIdAndAuthority;
    }

    @Override
    public void deleteByKey(String token) {
        redisTemplate.delete(token);
    }

    public boolean isRefreshTokenExists(String token) {
        return getValueByKey(token) != null;
    }
}