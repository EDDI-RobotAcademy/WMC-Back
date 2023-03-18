package com.example.Backend.service.member;

import com.example.Backend.entity.member.Authentication;
import com.example.Backend.entity.member.BasicAuthentication;
import com.example.Backend.entity.member.Member;
import com.example.Backend.repository.member.AuthenticationRepository;
import com.example.Backend.repository.member.MemberRepository;
import com.example.Backend.service.member.request.MemberRegisterRequest;
import com.example.Backend.service.security.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    final private MemberRepository memberRepository;
    final private AuthenticationRepository authenticationRepository;
    final private RedisService redisService;

    @Override
    public Boolean emailValidation(String email) {
        Optional<Member> maybeMember = memberRepository.findByEmail(email);

        if (maybeMember.isPresent()) {
            return false;
        }

        return true;
    }


    @Override
    public Boolean signUp(MemberRegisterRequest memberRegisterRequest) {
        final Member member = memberRegisterRequest.toMember();
        memberRepository.save(member);

        final BasicAuthentication authentication = new BasicAuthentication(
                member,
                Authentication.BASIC_AUTH,
                memberRegisterRequest.getPassword()
        );

        authenticationRepository.save(authentication);

        return true;
    }

    }