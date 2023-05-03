package com.example.Backend.service.member.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class MemberUpdateAddressRequest {

    private Long memberId;
    private String newCity;
    private String newStreet;
    private String newAddressDetail;
    private String newZipcode;
}
