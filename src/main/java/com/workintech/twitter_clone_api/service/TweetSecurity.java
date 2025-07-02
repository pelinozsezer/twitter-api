package com.workintech.twitter_clone_api.service;

import com.workintech.twitter_clone_api.repository.TweetRepository;
import org.springframework.stereotype.Component;

@Component("tweetSecurity")
public class TweetSecurity {

    private final TweetRepository tweetRepository;

    public TweetSecurity(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public boolean isOwner(Integer tweetId, String username) {
        return tweetRepository.findById(tweetId)
                .map(tweet -> tweet.getUser().getUsername().equals(username))
                .orElse(false);
    }
}

