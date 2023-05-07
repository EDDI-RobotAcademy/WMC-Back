package com.example.Backend.repository.jpa.like;

import com.example.Backend.entity.like.Like;
import com.example.Backend.entity.member.Member;
import com.example.Backend.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByMemberAndProduct(Member member, Product product);
}
