package com.workintech.twitter_clone_api.service;

import com.workintech.twitter_clone_api.dto.TweetRequest;
import com.workintech.twitter_clone_api.dto.TweetUpdateRequest;
import com.workintech.twitter_clone_api.entity.Tweet;

import java.util.List;

public interface TweetService {
    Tweet createTweet(TweetRequest tweetRequest);
    List<Tweet> getTweetsByUserId(Integer userId);
    Tweet getTweetById(Integer id);
    Tweet updateTweet(Integer id, TweetUpdateRequest updateRequest);
    void deleteTweet(Integer tweetId);
}
