package com.example.Backend.service.member;

import com.example.Backend.entity.member.Authentication;
import com.example.Backend.entity.member.BasicAuthentication;
import com.example.Backend.entity.member.ManagerCode;
import com.example.Backend.entity.member.Member;
import com.example.Backend.repository.member.AuthenticationRepository;
import com.example.Backend.repository.member.ManagerCodeRepository;
import com.example.Backend.repository.member.MemberRepository;
import com.example.Backend.service.member.request.MemberLoginRequest;
import com.example.Backend.service.member.request.MemberRegisterRequest;
import com.example.Backend.service.security.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    final private MemberRepository memberRepository;
    final private ManagerCodeRepository managerCodeRepository;
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
    public Boolean managerCodeValidation(String managerCode) {
        Optional<ManagerCode> maybeManager = managerCodeRepository.findByCode(managerCode);
        if (maybeManager.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean signUp(MemberRegisterRequest request) {
        log.info("매니저코드: " + request.getManagerCode());
        if (request.getManagerCode() == null || request.getManagerCode().isEmpty()) {
            log.info("일반회원 가입");
            final Member member = request.toMember();
            memberRepository.save(member);

            final BasicAuthentication auth = new BasicAuthentication(member,
                    Authentication.BASIC_AUTH, request.getPassword());

            authenticationRepository.save(auth);

        } else {
            log.info("관리자회원 가입");
            final Member member = request.toManager();
            memberRepository.save(member);

            final BasicAuthentication auth = new BasicAuthentication(member,
                    Authentication.BASIC_AUTH, request.getPassword());

            authenticationRepository.save(auth);
        }

        return true;
    }

    @Override
    public String signIn(MemberLoginRequest memberLoginRequest) {
        Optional<Member> maybeMember =
                memberRepository.findByEmail(memberLoginRequest.getEmail());

        System.out.println("loginRequest: " + memberLoginRequest);
        System.out.println("maybeMember.isPresent(): " + maybeMember.isPresent());

        if (maybeMember.isPresent()) {
            Member member = maybeMember.get();

            System.out.println("사용자가 입력한 비번: " + memberLoginRequest.getPassword());
            System.out.println("비밀번호 일치 검사: " + member.isRightPassword(memberLoginRequest.getPassword()));

            if (!member.isRightPassword(memberLoginRequest.getPassword())) {
                System.out.println("잘 들어오나 ?");
                throw new RuntimeException("이메일 및 비밀번호 입력이 잘못되었습니다!");
            }

            UUID userToken = UUID.randomUUID();

            // redis 처리 필요
            redisService.deleteByKey(userToken.toString());
            redisService.setKeyAndValue(userToken.toString(), member.getId());

            return userToken.toString();
        }

        throw new RuntimeException("가입된 사용자가 아닙니다!");
    }

    }