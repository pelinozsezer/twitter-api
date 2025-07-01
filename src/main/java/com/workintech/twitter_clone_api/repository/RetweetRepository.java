package com.workintech.twitter_clone_api.repository;

import com.workintech.twitter_clone_api.entity.Retweet;
import com.workintech.twitter_clone_api.entity.Tweet;
import com.workintech.twitter_clone_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {
    boolean existsByUserAndTweet(User user, Tweet tweet);
    Optional<Retweet> findByUserAndTweet(User user, Tweet tweet);
}
