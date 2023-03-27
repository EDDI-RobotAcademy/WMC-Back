package com.example.Backend.entity.member;

import com.example.Backend.entity.member.MemberProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Member {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(nullable = false)
    private String email;

    @Getter
    @Column(nullable = false)
    private String username;

    @Getter
    @Column(nullable = false)
    private int birthdate;

    @Getter
    @Column
    private boolean managerCheck;

    @Getter
    @JsonIgnore
    @JoinColumn(name ="authority_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Authority authority;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private MemberProfile memberProfile;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private Set<Authentication> authentications = new HashSet<>();


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
}