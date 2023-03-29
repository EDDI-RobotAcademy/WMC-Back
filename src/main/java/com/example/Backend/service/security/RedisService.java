package com.example.Backend.service.security;

public interface RedisService {

    public void setKeyAndValue(String token, Long memId, String authorityName);
    public String getValueByKey(String token);
    public void deleteByKey(String token);
}