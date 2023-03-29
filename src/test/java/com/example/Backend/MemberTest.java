package com.example.Backend;

import com.example.Backend.entity.member.AuthorityType;
import com.example.Backend.service.member.MemberService;
import com.example.Backend.service.member.request.MemberRegisterRequest;
import com.example.Backend.service.security.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MemberTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RedisService redisService;

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


}