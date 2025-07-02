package com.workintech.twitter_clone_api.repository;

import com.workintech.twitter_clone_api.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Integer> {
    List<Tweet> findByUserId(Integer userId);
}
