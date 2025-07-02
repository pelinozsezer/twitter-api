package com.workintech.twitter_clone_api.controller;

import com.workintech.twitter_clone_api.dto.TweetRequest;
import com.workintech.twitter_clone_api.dto.TweetUpdateRequest;
import com.workintech.twitter_clone_api.entity.Tweet;
import com.workintech.twitter_clone_api.service.TweetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
@RequiredArgsConstructor
public class TweetController {

    @Autowired
    private final TweetService tweetService;

    @PostMapping
    public ResponseEntity<Tweet> createTweet(@Valid @RequestBody TweetRequest tweetRequest) {
        Tweet tweet = tweetService.createTweet(tweetRequest);
        return ResponseEntity.status(201).body(tweet);
    }

    @GetMapping("/findByUserId/{userId}")
    public ResponseEntity<List<Tweet>> getTweetsByUserId(@PathVariable Integer userId) {
        List<Tweet> tweets = tweetService.getTweetsByUserId(userId);
        return ResponseEntity.ok(tweets);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Tweet> getTweetById(@PathVariable Integer id) {
        Tweet tweet = tweetService.getTweetById(id);
        return ResponseEntity.ok(tweet);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tweet> updateTweet(@PathVariable Integer id,
                                             @Valid @RequestBody TweetUpdateRequest updateRequest) {
        Tweet updatedTweet = tweetService.updateTweet(id, updateRequest);
        return ResponseEntity.ok(updatedTweet);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@tweetSecurity.isOwner(#id, authentication.name)")
    public ResponseEntity<Void> deleteTweet(@PathVariable Integer id) {
        tweetService.deleteTweet(id);
        return ResponseEntity.noContent().build();
    }
}
