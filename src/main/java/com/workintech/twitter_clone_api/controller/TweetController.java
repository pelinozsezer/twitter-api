package com.workintech.twitter_clone_api.controller;

import com.workintech.twitter_clone_api.dto.TweetRequest;
import com.workintech.twitter_clone_api.dto.TweetUpdateRequest;
import com.workintech.twitter_clone_api.entity.Tweet;
import com.workintech.twitter_clone_api.service.TweetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/findByUserId")
    public ResponseEntity<List<Tweet>> getTweetsByUserId(@RequestParam Long userId) {
        List<Tweet> tweets = tweetService.getTweetsByUserId(userId);
        return ResponseEntity.ok(tweets);
    }

    @GetMapping("/findById")
    public ResponseEntity<Tweet> getTweetById(@RequestParam Long id) {
        Tweet tweet = tweetService.getTweetById(id);
        return ResponseEntity.ok(tweet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tweet> updateTweet(@PathVariable Long id,
                                             @Valid @RequestBody TweetUpdateRequest updateRequest) {
        Tweet updatedTweet = tweetService.updateTweet(id, updateRequest);
        return ResponseEntity.ok(updatedTweet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTweet(@PathVariable Long id,
                                              @RequestParam Long userId) {
        tweetService.deleteTweet(id, userId);
        return ResponseEntity.ok("Tweet successfully deleted.");
    }
}
