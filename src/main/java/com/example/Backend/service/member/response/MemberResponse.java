package com.example.Backend.service.member.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberResponse {
    private String email;
    private String username;
    private int birthdate;
    private String PhoneNumber;
    private String city;
    private String street;
    private String addressDetail;
    private String zipcode;


}
