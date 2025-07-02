package com.workintech.twitter_clone_api.service;

import com.workintech.twitter_clone_api.dto.CommentRequest;
import com.workintech.twitter_clone_api.dto.CommentUpdateRequest;
import com.workintech.twitter_clone_api.entity.Comment;

public interface CommentService {
    Comment createComment(CommentRequest commentRequest);
    Comment updateComment(Long commentId, Long userId, CommentUpdateRequest updateRequest);
    void deleteComment(Long commentId, Long userId);
}
