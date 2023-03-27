package com.example.Backend.entity.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MemberProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String PhoneNumber;

    @Embedded
    private Address address;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    private MemberProfile(Address address, String phoneNumber) {
        this.address = address;
        this.PhoneNumber = phoneNumber;
    }

    public static MemberProfile of(String city, String street, String addressDetail, String zipcode, String phoneNumber) {
        final Address address = Address.of(city, street, addressDetail, zipcode);
        return new MemberProfile(address, phoneNumber);
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
