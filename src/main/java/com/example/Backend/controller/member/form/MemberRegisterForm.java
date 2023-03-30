package com.example.Backend.controller.member.form;

import com.example.Backend.entity.member.AuthorityType;
import com.example.Backend.service.member.request.MemberRegisterRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberRegisterForm {

    private String email;
    private String password;
    private String username;
    private int birthdate;
    private AuthorityType authorityName;
    private boolean managerCheck;
    private String managerCode;
    private String city;
    private String street;
    private String addressDetail;
    private String zipcode;
    private String phoneNumber;

    public MemberRegisterRequest toMemberRegisterRequest () {
        return new MemberRegisterRequest( email, password, username, birthdate, authorityName,managerCheck, city,street,addressDetail,zipcode, phoneNumber);
    }

    public MemberRegisterRequest toManagerRegisterRequest() {
        return new MemberRegisterRequest( email, password, username, birthdate, authorityName, managerCheck, managerCode, phoneNumber);
    }
}