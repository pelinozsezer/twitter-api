package com.workintech.twitter_clone_api.controller;

import com.workintech.twitter_clone_api.dto.RetweetRequest;
import com.workintech.twitter_clone_api.entity.Retweet;
import com.workintech.twitter_clone_api.service.RetweetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retweet")
@RequiredArgsConstructor
public class RetweetController {

    private final RetweetService retweetService;

    @PostMapping
    public ResponseEntity<Retweet> retweet(@Valid @RequestBody RetweetRequest request) {
        Retweet createdRetweet = retweetService.retweet(request);
        return ResponseEntity.status(201).body(createdRetweet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRetweet(@PathVariable Integer id, Authentication authentication) {
        String username = authentication.getName();
        retweetService.deleteRetweet(id, username);
        return ResponseEntity.ok("Retweet successfully deleted.");
    }

}
