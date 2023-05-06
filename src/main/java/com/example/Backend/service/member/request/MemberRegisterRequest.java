package com.example.Backend.service.member.request;

import com.example.Backend.entity.member.Authority;
import com.example.Backend.entity.member.AuthorityType;
import com.example.Backend.entity.member.Member;
import com.example.Backend.entity.member.MemberProfile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class MemberRegisterRequest {

    private final String email;
    private final String password;

    private final String username;
    private final int birthdate;

    private final AuthorityType authorityName;

    private boolean   managerCheck;
    private String managerCode;
    private String city;
    private String street;
    private String addressDetail;
    private String zipcode;
    private final String phoneNumber;

    public MemberRegisterRequest(String email, String password, String username, int birthdate, AuthorityType authorityName, boolean managerCheck, String city, String street, String addressDetail, String zipcode, String phoneNumber){
        this.email= email;
        this.password = password;
        this.username= username;
        this.birthdate = birthdate;
        this.authorityName = authorityName;
        this.managerCheck = managerCheck;
        this.city= city;
        this.street = street;
        this.addressDetail = addressDetail;
        this.zipcode = zipcode;
        this.phoneNumber = phoneNumber;
    }

    public MemberRegisterRequest(String email, String password, String username, int birthdate, AuthorityType authorityName, boolean managerCheck, String managerCode, String phoneNumber){
        this.email = email;
        this.password = password;
        this.username= username;
        this.birthdate = birthdate;
        this.authorityName = authorityName;
        this.managerCheck = managerCheck;
        this.managerCode = managerCode;
        this.phoneNumber =phoneNumber;
    }

    public Member toMember () {
        return new Member(
                email,
                username,
                birthdate,
                Authority.ofMember(authorityName),
                managerCheck,
                MemberProfile.of(city, street, addressDetail, zipcode,phoneNumber)
        );
    }

    public Member toManager() {
        return new Member(
                email,
                username,
                birthdate,
                Authority.ofMember(authorityName),
                managerCheck
        );
    }
}