package com.workintech.twitter_clone_api.service.impl;

import com.workintech.twitter_clone_api.dto.LikeRequest;
import com.workintech.twitter_clone_api.entity.Like;
import com.workintech.twitter_clone_api.entity.Tweet;
import com.workintech.twitter_clone_api.entity.User;
import com.workintech.twitter_clone_api.exception.AlreadyExistsException;
import com.workintech.twitter_clone_api.exception.ResourceNotFoundException;
import com.workintech.twitter_clone_api.repository.LikeRepository;
import com.workintech.twitter_clone_api.repository.TweetRepository;
import com.workintech.twitter_clone_api.repository.UserRepository;
import com.workintech.twitter_clone_api.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @Override
    public Like likeTweet(LikeRequest likeRequest) {
        User user = userRepository.findById(likeRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + likeRequest.getUserId()));
        Tweet tweet = tweetRepository.findById(likeRequest.getTweetId())
                .orElseThrow(() -> new ResourceNotFoundException("Tweet not found with id: " + likeRequest.getTweetId()));

        boolean alreadyLiked = likeRepository.existsByUserAndTweet(user, tweet);
        if (alreadyLiked) {
            throw new AlreadyExistsException("This tweet is already liked by this user.");
        }

        Like like = new Like();
        like.setUser(user);
        like.setTweet(tweet);

        return likeRepository.save(like);
    }

    @Override
    public void dislikeTweet(LikeRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        Tweet tweet = tweetRepository.findById(request.getTweetId())
                .orElseThrow(() -> new ResourceNotFoundException("Tweet not found with id: " + request.getTweetId()));

        Like like = likeRepository.findByUserAndTweet(user, tweet)
                .orElseThrow(() -> new ResourceNotFoundException("Like not found for this user and tweet"));

        likeRepository.delete(like);
    }
}
