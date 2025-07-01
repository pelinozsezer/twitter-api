package com.workintech.twitter_clone_api.service.impl;

import com.workintech.twitter_clone_api.dto.RetweetRequest;
import com.workintech.twitter_clone_api.entity.Retweet;
import com.workintech.twitter_clone_api.entity.Tweet;
import com.workintech.twitter_clone_api.entity.User;
import com.workintech.twitter_clone_api.exception.AlreadyExistsException;
import com.workintech.twitter_clone_api.exception.ResourceNotFoundException;
import com.workintech.twitter_clone_api.exception.UnauthorizedActionException;
import com.workintech.twitter_clone_api.repository.RetweetRepository;
import com.workintech.twitter_clone_api.repository.TweetRepository;
import com.workintech.twitter_clone_api.repository.UserRepository;
import com.workintech.twitter_clone_api.service.RetweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetweetServiceImpl implements RetweetService {

    private final RetweetRepository retweetRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    @Override
    public Retweet retweet(RetweetRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + request.getUserId()));

        Tweet tweet = tweetRepository.findById(request.getTweetId())
                .orElseThrow(() -> new ResourceNotFoundException("Tweet not found with ID: " + request.getTweetId()));

        boolean alreadyRetweeted = retweetRepository.existsByUserAndTweet(user, tweet);
        if (alreadyRetweeted) {
            throw new AlreadyExistsException("You already retweeted this tweet.");
        }

        Retweet retweet = new Retweet();
        retweet.setUser(user);
        retweet.setTweet(tweet);

        return retweetRepository.save(retweet);
    }

    @Override
    public void deleteRetweet(Long retweetId, Long userId) {
        Retweet retweet = retweetRepository.findById(retweetId)
                .orElseThrow(() -> new ResourceNotFoundException("Retweet not found with ID: " + retweetId));

        if (!retweet.getUser().getId().equals(userId)) {
            throw new UnauthorizedActionException("You are not allowed to delete this retweet.");
        }

        retweetRepository.delete(retweet);
    }
}
