package com.example.Backend.repository.jpa.member;

import com.example.Backend.entity.member.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long> {
}
