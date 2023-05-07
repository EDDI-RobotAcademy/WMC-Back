package com.example.Backend.service.member;

import com.example.Backend.controller.member.form.CheckPasswordForm;
import com.example.Backend.controller.member.form.PasswordUpdateForm;
import com.example.Backend.entity.member.*;
import com.example.Backend.entity.product.Product;
import com.example.Backend.repository.jpa.member.AuthenticationRepository;
import com.example.Backend.repository.jpa.member.ManagerCodeRepository;
import com.example.Backend.repository.jpa.member.MemberProfileRepository;
import com.example.Backend.repository.jpa.member.MemberRepository;
import com.example.Backend.repository.jpa.order.OrderRepository;
import com.example.Backend.service.member.request.MemberLoginRequest;
import com.example.Backend.service.member.request.MemberRegisterRequest;
import com.example.Backend.service.member.request.MemberUpdateAddressRequest;
import com.example.Backend.service.member.response.MemberResponse;
import com.example.Backend.service.security.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    final private MemberRepository memberRepository;
    final private ManagerCodeRepository managerCodeRepository;
    final private AuthenticationRepository authenticationRepository;
    final private MemberProfileRepository memberProfileRepository;
    final private OrderRepository orderRepository;
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
    @Transactional
    public Boolean delete(Long memberId) {
        Optional<Member> maybeMember = memberRepository.findById(memberId);
        if (maybeMember.isPresent()) {
            Member member = maybeMember.get();

            orderRepository.deleteAllByBuyer(member);

            memberProfileRepository.delete(member.getMemberProfile());
            authenticationRepository.deleteAll(member.getAuthentications());
            memberRepository.delete(member);
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
            redisService.setKeyAndValue(userToken.toString(), member.getId(), member.getAuthorityName());

            return userToken.toString();
        }

        throw new RuntimeException("가입된 사용자가 아닙니다!");
    }

    @Override
    @Transactional
    public Boolean passwordCheck(CheckPasswordForm checkPasswordForm) {
        Long memberId = checkPasswordForm.getMemberId();
        Optional<Member> maybeMember = memberRepository.findById(memberId);

        if (maybeMember.isPresent()) {
            Member member = maybeMember.get();
            String password = checkPasswordForm.getPassword();
            return member.isRightPassword(password);
        }
        return false;
    }


    @Override
    @Transactional
    public MemberResponse read(Long memberId) {

        Optional<Member> maybeMember = memberRepository.findByIdWithProfile(memberId);
        if (maybeMember.isPresent()) {
            Member member = maybeMember.get();
            MemberProfile memberProfile = memberProfileRepository.getReferenceById(memberId);
            Address address = memberProfile.getAddress();
            MemberResponse res = new MemberResponse(
                    member.getEmail(), member.getUsername(), member.getBirthdate(),
                    memberProfile.getPhoneNumber(), address.getCity(), address.getStreet(),
                    address.getAddressDetail(), address.getZipcode());
            return res;
        }

        throw new RuntimeException("가입된 사용자가 아닙니다!");
    }

    @Override
    public Member getMemberById(Long memberId) {
        Optional<Member> maybeMember = memberRepository.findById(memberId);
        if (maybeMember.isPresent()) {
            return maybeMember.get();
        }
        throw new RuntimeException("가입된 사용자가 아닙니다!");
    }

    @Override
    @Transactional
    public Boolean passwordUpdate(PasswordUpdateForm passwordUpdateForm) {
        Optional<Member> maybeMember = memberRepository.findById(passwordUpdateForm.getMemberId());
        Optional<Authentication> maybeAuthentication = authenticationRepository.findByMember_id(passwordUpdateForm.getMemberId());

        if (maybeMember.isPresent()) {
            Member member = maybeMember.get();

            final BasicAuthentication authentication = new BasicAuthentication(
                    member,
                    Authentication.BASIC_AUTH,
                    passwordUpdateForm.getNewPassword()
            );

            authentication.setId(maybeAuthentication.get().getId());
            authenticationRepository.save(authentication);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean addressUpdate(MemberUpdateAddressRequest memberUpdateAddressRequest) {
        Optional<Member> optionalMember = memberRepository.findById(memberUpdateAddressRequest.getMemberId());
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            MemberProfile memberProfile = member.getMemberProfile();
            Address address = Address.of(memberUpdateAddressRequest.getNewCity(),
                    memberUpdateAddressRequest.getNewStreet(),
                    memberUpdateAddressRequest.getNewAddressDetail(),
                    memberUpdateAddressRequest.getNewZipcode());
            memberProfile.setAddress(address);
            memberProfileRepository.save(memberProfile);
            return true;
        } else {
            // member가 존재하지 않을 경우에 대한 처리
            return false;
        }
    }

}
