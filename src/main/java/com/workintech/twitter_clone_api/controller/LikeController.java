package com.workintech.twitter_clone_api.controller;

import com.workintech.twitter_clone_api.dto.LikeRequest;
import com.workintech.twitter_clone_api.entity.Like;
import com.workintech.twitter_clone_api.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Like> likeTweet(@Valid @RequestBody LikeRequest likeRequest) {
        Like like = likeService.likeTweet(likeRequest);
        return ResponseEntity.status(201).body(like);
    }

    @PostMapping("/dislike")
    public ResponseEntity<String> dislikeTweet(@Valid @RequestBody LikeRequest request) {
        likeService.dislikeTweet(request);
        return ResponseEntity.ok("Like removed successfully.");
    }
}
