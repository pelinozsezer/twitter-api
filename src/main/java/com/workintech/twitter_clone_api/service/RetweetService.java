package com.workintech.twitter_clone_api.service;

import com.workintech.twitter_clone_api.dto.RetweetRequest;
import com.workintech.twitter_clone_api.entity.Retweet;

public interface RetweetService {
    Retweet retweet(RetweetRequest request);
    void deleteRetweet(Integer retweetId, String username);

}
