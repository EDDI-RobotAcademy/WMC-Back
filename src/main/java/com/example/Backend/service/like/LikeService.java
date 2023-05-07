package com.example.Backend.service.like;

import com.example.Backend.entity.like.Like;

import java.util.Optional;

public interface LikeService {
    boolean likeProduct(Long memberId, Long productId);

    boolean unlikeProduct(Long memberId, Long productId);

    boolean checkIfMemberLikedProduct(Long memberId, Long productId);

}
