package com.example.Backend.service.member;

import com.example.Backend.service.member.request.MemberLoginRequest;
import com.example.Backend.service.member.request.MemberRegisterRequest;

public interface MemberService {
    Boolean emailValidation(String email);
    Boolean signUp(MemberRegisterRequest request);
    String signIn(MemberLoginRequest memberLoginRequest);
    Boolean managerCodeValidation(String managerCode);
    Boolean delete(Long memberId);

}