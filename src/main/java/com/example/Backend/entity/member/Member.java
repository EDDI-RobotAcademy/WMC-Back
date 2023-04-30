package com.example.Backend.entity.member;

import com.example.Backend.entity.member.MemberProfile;
import com.example.Backend.entity.order.Order;

import com.example.Backend.entity.review.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private int birthdate;

    @Column
    private boolean managerCheck;

    @JsonIgnore
    @JoinColumn(name ="authority_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Authority authority;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private MemberProfile memberProfile;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Authentication> authentications = new HashSet<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();



    public Member(String email, String username, int birthdate, Authority authority, boolean managerCheck, MemberProfile memberProfile) {
        this.email = email;
        this.username = username;
        this.birthdate = birthdate;
        this.authority = authority;
        this.managerCheck = managerCheck;
        this.memberProfile = memberProfile;
        memberProfile.setMember(this);
    }

    public Member(String email, String username, int birthdate, Authority authority, boolean managerCheck) {
        this.email = email;
        this.username = username;
        this.birthdate = birthdate;
        this.authority = authority;
        this.managerCheck = managerCheck;
    }

    public Member(Long id, Authority authority) {
        this.id = id;
        this.authority = authority;
    }

    public boolean isRightPassword(String plainToCheck) {
        final Optional<Authentication> maybeBasicAuth = findBasicAuthentication();

        if (maybeBasicAuth.isPresent()) {
            final BasicAuthentication authentication = (BasicAuthentication) maybeBasicAuth.get();
            return authentication.isRightPassword(plainToCheck);
        }

        return false;
    }

    private Optional<Authentication> findBasicAuthentication () {
        return authentications
                .stream()
                .filter(authentication -> authentication instanceof BasicAuthentication)
                .findFirst();
    }

    public String getAuthorityName() {
        return authority.getAuthorityName().name();
    }

}
