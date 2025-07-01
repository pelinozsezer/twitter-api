package com.workintech.twitter_clone_api.service;

import com.workintech.twitter_clone_api.dto.TweetRequest;
import com.workintech.twitter_clone_api.dto.TweetUpdateRequest;
import com.workintech.twitter_clone_api.entity.Tweet;

import java.util.List;

public interface TweetService {
    Tweet createTweet(TweetRequest tweetRequest);
    List<Tweet> getTweetsByUserId(Long userId);
    Tweet getTweetById(Long id);
    Tweet updateTweet(Long id, TweetUpdateRequest updateRequest);
    void deleteTweet(Long tweetId, Long userId);




}
