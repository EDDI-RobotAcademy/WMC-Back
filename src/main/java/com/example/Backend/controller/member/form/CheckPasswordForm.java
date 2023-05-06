package com.example.Backend.controller.member.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CheckPasswordForm {
    Long memberId;
    String password;
}