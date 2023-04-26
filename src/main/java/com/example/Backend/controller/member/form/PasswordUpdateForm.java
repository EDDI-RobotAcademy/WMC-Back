package com.example.Backend.controller.member.form;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PasswordUpdateForm {
    String newPassword;
    Long memberId;
}
