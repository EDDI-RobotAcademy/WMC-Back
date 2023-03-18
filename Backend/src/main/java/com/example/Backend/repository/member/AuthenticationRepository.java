package com.example.Backend.repository.member;

import com.example.Backend.entity.member.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

    @Query("select a from Authentication a join fetch a.member m where m.email = :email")
    Optional<Authentication> findByEmail(String email);
}