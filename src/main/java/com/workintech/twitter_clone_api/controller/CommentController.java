package com.workintech.twitter_clone_api.controller;

import com.workintech.twitter_clone_api.dto.CommentRequest;
import com.workintech.twitter_clone_api.dto.CommentUpdateRequest;
import com.workintech.twitter_clone_api.entity.Comment;
import com.workintech.twitter_clone_api.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@Valid @RequestBody CommentRequest commentRequest) {
        Comment createdComment = commentService.createComment(commentRequest);
        return ResponseEntity.status(201).body(createdComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer id,
                                                 @Valid @RequestBody CommentUpdateRequest updateRequest,
                                                 Authentication authentication) {
        String username = authentication.getName(); // security
        Comment updatedComment = commentService.updateComment(id, updateRequest, username);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id, Authentication authentication) {
        String username = authentication.getName();
        commentService.deleteComment(id, username);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
