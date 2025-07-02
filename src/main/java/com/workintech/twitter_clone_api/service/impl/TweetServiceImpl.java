package com.workintech.twitter_clone_api.service.impl;

import com.workintech.twitter_clone_api.dto.TweetRequest;
import com.workintech.twitter_clone_api.dto.TweetUpdateRequest;
import com.workintech.twitter_clone_api.entity.Tweet;
import com.workintech.twitter_clone_api.entity.User;
import com.workintech.twitter_clone_api.exception.ResourceNotFoundException;
import com.workintech.twitter_clone_api.repository.TweetRepository;
import com.workintech.twitter_clone_api.repository.UserRepository;
import com.workintech.twitter_clone_api.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @Override
    public Tweet createTweet(TweetRequest tweetRequest) {
        User user = userRepository.findById(tweetRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + tweetRequest.getUserId()));

        Tweet tweet = new Tweet();
        tweet.setContent(tweetRequest.getContent());
        tweet.setUser(user);

        return tweetRepository.save(tweet);
    }

    @Override
    public List<Tweet> getTweetsByUserId(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        return tweetRepository.findByUserId(userId);
    }

    @Override
    public Tweet getTweetById(Integer id) {
        return tweetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet not found with id: " + id));
    }

    @Override
    public Tweet updateTweet(Integer id, TweetUpdateRequest updateRequest) {
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet not found with id: " + id));

        tweet.setContent(updateRequest.getContent());
        return tweetRepository.save(tweet);
    }

    @Override
    public void deleteTweet(Integer tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new ResourceNotFoundException("Tweet not found with id: " + tweetId));

        tweetRepository.delete(tweet);
    }

}
