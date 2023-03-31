package com.example.Backend.repository.member;

import com.example.Backend.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m join fetch m.authentications join fetch m.authority where m.email = :email")
    Optional<Member> findByEmail(String email);
}