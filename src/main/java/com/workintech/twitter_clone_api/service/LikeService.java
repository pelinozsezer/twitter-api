package com.workintech.twitter_clone_api.service;

import com.workintech.twitter_clone_api.dto.LikeRequest;
import com.workintech.twitter_clone_api.entity.Like;

public interface LikeService {
    Like likeTweet(LikeRequest likeRequest);
    void dislikeTweet(LikeRequest request);
}
