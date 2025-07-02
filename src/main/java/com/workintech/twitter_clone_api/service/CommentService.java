package com.workintech.twitter_clone_api.service;

import com.workintech.twitter_clone_api.dto.CommentRequest;
import com.workintech.twitter_clone_api.dto.CommentUpdateRequest;
import com.workintech.twitter_clone_api.entity.Comment;

public interface CommentService {
    Comment createComment(CommentRequest commentRequest);
    Comment updateComment(Integer commentId, CommentUpdateRequest updateRequest, String username);
    void deleteComment(Integer commentId, String username);
}
