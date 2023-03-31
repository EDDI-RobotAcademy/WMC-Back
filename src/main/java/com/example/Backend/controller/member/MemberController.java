package com.example.Backend.controller.member;

import com.example.Backend.controller.member.form.MemberLoginForm;
import com.example.Backend.controller.member.form.MemberRegisterForm;
import com.example.Backend.service.member.MemberService;
import com.example.Backend.service.security.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class MemberController {

    final private MemberService memberService;
    final private RedisService redisService;

    @PostMapping("/check-email/{email}")
    public Boolean emailValidation(@PathVariable("email") String email) {
        log.info("emailValidation(): " + email);

        return memberService.emailValidation(email);
    }

    @PostMapping("/sign-up")
    public Boolean signUp(@RequestBody MemberRegisterForm form) {
        log.info("signUp(): " + form);

        return memberService.signUp(form.toMemberRegisterRequest());
    }

    @PostMapping("/check-manager/{managerCode}")
    public Boolean managerCodeValidation(@PathVariable("managerCode") String managerCode) {
        log.info("managerCodeValidation(): " + managerCode);

        return memberService.managerCodeValidation(managerCode);
    }

    @PostMapping("/sign-in")
    public String signIn(@RequestBody MemberLoginForm form) {
        log.info("signIn(): " + form);

        return memberService.signIn(form.toMemberLoginRequest());
    }

    @PostMapping("/logout")
    public void logout(@RequestBody String token) {
        token = token.substring(0, token.length() - 1);
        log.info("logout(): " + token);

        redisService.deleteByKey(token);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody String token) {
        token = token.substring(0, token.length() - 1);
        log.info("logout(): " + token);
        Long memberId = null;
        String memberValue = redisService.getValueByKey(token);
        if (memberValue != null) {
            String[] value = memberValue.split(":");
            if (value.length > 0) {
                memberId = Long.valueOf(value[0]);
            }
        }
        memberService.delete(memberId);
    }

}