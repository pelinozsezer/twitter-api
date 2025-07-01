package com.workintech.twitter_clone_api.repository;

import com.workintech.twitter_clone_api.entity.Like;
import com.workintech.twitter_clone_api.entity.Tweet;
import com.workintech.twitter_clone_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndTweet(User user, Tweet tweet);
    Optional<Like> findByUserAndTweet(User user, Tweet tweet);

}
