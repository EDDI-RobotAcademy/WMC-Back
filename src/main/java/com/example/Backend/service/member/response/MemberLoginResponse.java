package com.example.Backend.service.member.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class MemberLoginResponse {
    private String token;
    private Long id;
    private String username;
}