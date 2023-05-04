package com.example.Backend.controller.member;

import com.example.Backend.controller.member.form.*;
import com.example.Backend.service.member.MemberService;
import com.example.Backend.service.member.request.MemberUpdateAddressRequest;
import com.example.Backend.service.member.response.MemberResponse;
import com.example.Backend.service.security.RedisService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.example.Backend.controller.order.OrderController.getaLong;


@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
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

    @PostMapping("/passwordCheck")
    public Boolean passwordCheck(@RequestBody CheckPasswordForm checkPasswordForm) {
        return memberService.passwordCheck(checkPasswordForm);
    }

    @PutMapping("/passwordUpdate")
    public Boolean passwordUpdate(@RequestBody PasswordUpdateForm passwordUpdateForm) {
        log.info("PasswordUpdate : " + passwordUpdateForm);

        return memberService.passwordUpdate(passwordUpdateForm);
    }

    @PutMapping("/addressUpdate")
    public Boolean addressUpdate(@RequestBody MemberUpdateAddressRequest memberUpdateAddressRequest) {
        log.info("addressUpdate : " + memberUpdateAddressRequest);
        return memberService.addressUpdate(memberUpdateAddressRequest);
    }



    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody String token) {
        try {
            token = token.substring(0, token.length() - 1);
            log.info("logout(): " + token);

            redisService.deleteByKey(token);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error during logout: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/account")
    public MemberResponse account(@RequestBody String token) {
        token = token.substring(0, token.length() - 1);
        log.info("account(): " + token);
        Long memberId = null;
        String memberValue = redisService.getValueByKey(token);
        if (memberValue != null) {
            String[] value = memberValue.split(":");
            if (value.length > 0) {
                memberId = Long.valueOf(value[0]);
            }
        }
        return memberService.read(memberId);
    }

    private Long getMemberIdByToken(String token) {
        return getaLong(token, log, redisService);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestBody Map<String, String> map) {
        log.info("Hello");
        log.info("delete(): " + map);
        Long memberId = null;

        String token = map.get("token");
        String memberValue = redisService.getValueByKey(token);
        log.info("memberValue : " + memberValue);
        if (memberValue != null) {
            String[] value = memberValue.split(":");
            if (value.length > 0) {
                memberId = Long.valueOf(value[0]);
            }
        }
        log.info("memberId: " + memberId);

        if (memberId == null) {
            log.info("token: " + map);
            log.info("memberValue: " + memberValue);
            return false;
        }

        return memberService.delete(memberId);
    }


    @PostMapping("/ismanager")
    public boolean isManager(@RequestBody String token) {
        token = token.substring(0, token.length() - 1);
        log.info("ismanager(): " + token);
        log.info("logout(): " + token);
        String authorityName = null;
        String memberValue = redisService.getValueByKey(token);
        if (memberValue != null) {
            String[] value = memberValue.split(":");
            if (value.length > 0) {
                authorityName = value[1];
                log.info("authorityName: " + authorityName);
            }
        }
        log.info(String.valueOf(authorityName.equals("MANAGER")));
        return authorityName.equals("MANAGER");
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}