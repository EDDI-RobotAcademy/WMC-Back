package com.example.Backend.repository.jpa.member;

import com.example.Backend.entity.member.ManagerCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ManagerCodeRepository extends JpaRepository<ManagerCode, Long> {

    @Query("select mc from ManagerCode mc where mc.code = :managerCode")
    Optional<ManagerCode> findByCode(String managerCode);

}
