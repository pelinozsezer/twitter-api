package com.workintech.twitter_clone_api.controller;

import com.workintech.twitter_clone_api.dto.CommentRequest;
import com.workintech.twitter_clone_api.dto.CommentUpdateRequest;
import com.workintech.twitter_clone_api.entity.Comment;
import com.workintech.twitter_clone_api.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Comment> updateComment(@PathVariable Long id,
                                                 @RequestParam Long userId,
                                                 @Valid @RequestBody CommentUpdateRequest updateRequest) {
        Comment updatedComment = commentService.updateComment(id, userId, updateRequest);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id,
                                                @RequestParam Long userId) {
        commentService.deleteComment(id, userId);
        return ResponseEntity.ok("Comment successfully deleted.");
    }


}
