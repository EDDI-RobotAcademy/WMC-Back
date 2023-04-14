package com.example.Backend.service.member;

import com.example.Backend.entity.member.Member;
import com.example.Backend.service.member.request.MemberLoginRequest;
import com.example.Backend.service.member.request.MemberRegisterRequest;
import com.example.Backend.service.member.response.MemberResponse;

public interface MemberService {
    Boolean emailValidation(String email);
    Boolean signUp(MemberRegisterRequest request);
    String signIn(MemberLoginRequest memberLoginRequest);
    Boolean managerCodeValidation(String managerCode);
    Boolean delete(Long memberId);
    MemberResponse read(Long memberId);

    Member getMemberById(Long memberId);
}