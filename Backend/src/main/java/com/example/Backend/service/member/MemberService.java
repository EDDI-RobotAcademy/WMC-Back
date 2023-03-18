package com.example.Backend.service.member;

import com.example.Backend.service.member.request.MemberRegisterRequest;

public interface MemberService {
    Boolean emailValidation(String email);
    Boolean signUp(MemberRegisterRequest memberRegisterRequest);
}