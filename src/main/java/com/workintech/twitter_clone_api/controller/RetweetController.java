package com.workintech.twitter_clone_api.controller;

import com.workintech.twitter_clone_api.dto.RetweetRequest;
import com.workintech.twitter_clone_api.entity.Retweet;
import com.workintech.twitter_clone_api.service.RetweetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retweet")
@RequiredArgsConstructor
public class RetweetController {

    private final RetweetService retweetService;

    @PostMapping
    public ResponseEntity<Retweet> retweet(@Valid @RequestBody RetweetRequest request) {
        Retweet created = retweetService.retweet(request);
        return ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRetweet(@PathVariable Long id,
                                                @RequestParam Long userId) {
        retweetService.deleteRetweet(id, userId);
        return ResponseEntity.ok("Retweet successfully deleted.");
    }
}
