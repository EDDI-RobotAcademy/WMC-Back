package com.example.Backend.service.like;

import com.example.Backend.entity.like.Like;
import com.example.Backend.entity.member.Member;
import com.example.Backend.entity.product.Product;
import com.example.Backend.repository.jpa.like.LikeRepository;
import com.example.Backend.repository.jpa.member.MemberRepository;
import com.example.Backend.repository.jpa.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    final private MemberRepository memberRepository;
    final private ProductRepository productRepository;
    final private LikeRepository likeRepository;

    @Override
    @Transactional
    public boolean likeProduct(Long memberId, Long productId) {
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Product> product = productRepository.findById(productId);

        if (member.isPresent() && product.isPresent()) {
            Optional<Like> existingLike = likeRepository.findByMemberAndProduct(member.get(), product.get());
            if (existingLike.isPresent()) {
                throw new IllegalArgumentException("이미 좋아요를 누른 상품입니다.");
            }

            Like like = new Like(member.get(), product.get());
            member.get().getLikes().add(like);
            product.get().getLikes().add(like);
            likeRepository.save(like);
            return true;
        }

        return false;
    }


    @Override
    @Transactional
    public boolean unlikeProduct(Long memberId, Long productId) {
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Product> product = productRepository.findById(productId);

        if (member.isPresent() && product.isPresent()) {
            Optional<Like> existingLike = likeRepository.findByMemberAndProduct(member.get(), product.get());
            if (existingLike.isPresent()) {
                Like like = existingLike.get();
                member.get().getLikes().remove(like);
                product.get().getLikes().remove(like);
                memberRepository.save(member.get());
                productRepository.save(product.get());
                return true;
            }
        }

        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkIfMemberLikedProduct(Long memberId, Long productId) {
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Product> product = productRepository.findById(productId);

        if (member.isPresent() && product.isPresent()) {
            Optional<Like> existingLike = likeRepository.findByMemberAndProduct(member.get(), product.get());
            return existingLike.isPresent();
        }

        return false;
    }
}
