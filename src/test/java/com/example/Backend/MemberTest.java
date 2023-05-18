package com.example.Backend;

import com.example.Backend.entity.member.AuthorityType;
import com.example.Backend.entity.member.Member;
import com.example.Backend.repository.jpa.member.MemberRepository;
import com.example.Backend.service.member.MemberService;
import com.example.Backend.service.member.request.MemberRegisterRequest;
import com.example.Backend.service.member.response.MemberResponse;
import com.example.Backend.service.security.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 없는_이메일에대한_유효성_검증() {
        assertTrue(memberService.emailValidation("sdfshdkfshkfhsk@sds.com"));
    }

    @Test
    public void 회원가입_확인() {
        assertTrue(memberService.signUp(new MemberRegisterRequest(
                "test@test.com", "test", "asdf", 19950228, AuthorityType.MEMBER, true,
                "집", "집", "집",
                "01234", "010-2345-1234"
        )));
    }

    @Test
    public void 회원탈퇴() {
        memberRepository.deleteAll();
        assertEquals(0, memberRepository.count());
        assertTrue(memberService.signUp(new MemberRegisterRequest(
                "test@test.com", "test", "asdf", 19950228, AuthorityType.MEMBER, true,
                "집", "집", "집",
                "01234", "010-2345-1234"
        )));
        assertEquals(1, memberRepository.count());
        List<Member> members = memberRepository.findAll();
        Member member = members.get(0);
        memberService.delete(member.getId());
        assertEquals(0, memberRepository.count());
    }

//    @Transactional
//    @Test
//    public void 회원조회() {
//        memberRepository.deleteAll();
//        assertTrue(memberService.signUp(new MemberRegisterRequest(
//                "test@test.com", "test", "asdf", 19950228, AuthorityType.MEMBER, true,
//                "집", "집", "집",
//                "01234", "010-2345-1234"
//        )));
//        assertEquals(1, memberRepository.count());
//        MemberResponse res = memberService.read(1L);
//        assertTrue(res.getAddressDetail().equals("집"));
//        assertTrue(res.getCity().equals("집"));
//        assertTrue(res.getPhoneNumber().equals("010-2345-1234"));
//        assertTrue(res.getEmail().equals("test@test.com"));
//        assertTrue(res.getUsername().equals("asdf"));
//        assertTrue(res.getBirthdate()==19950228);
//    }
}