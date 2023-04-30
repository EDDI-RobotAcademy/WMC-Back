package com.example.Backend.repository.jpa.member;

import com.example.Backend.entity.member.Member;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUsername(String username);
    @Query("select m from Member m join fetch m.authentications join fetch m.authority where m.email = :email")
    Optional<Member> findByEmail(String email);


    @Query("SELECT m FROM Member m JOIN FETCH m.memberProfile WHERE m.id = :id")
    Optional<Member> findByIdWithProfile(Long id);

}