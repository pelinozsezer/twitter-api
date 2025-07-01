package com.workintech.twitter_clone_api.service.impl;

import com.workintech.twitter_clone_api.dto.CommentRequest;
import com.workintech.twitter_clone_api.dto.CommentUpdateRequest;
import com.workintech.twitter_clone_api.entity.Comment;
import com.workintech.twitter_clone_api.entity.Tweet;
import com.workintech.twitter_clone_api.entity.User;
import com.workintech.twitter_clone_api.exception.ResourceNotFoundException;
import com.workintech.twitter_clone_api.exception.UnauthorizedActionException;
import com.workintech.twitter_clone_api.repository.CommentRepository;
import com.workintech.twitter_clone_api.repository.TweetRepository;
import com.workintech.twitter_clone_api.repository.UserRepository;
import com.workintech.twitter_clone_api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;

    @Override
    public Comment createComment(CommentRequest commentRequest) {
        User user = userRepository.findById(commentRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + commentRequest.getUserId()));

        Tweet tweet = tweetRepository.findById(commentRequest.getTweetId())
                .orElseThrow(() -> new ResourceNotFoundException("Tweet not found with ID: " + commentRequest.getTweetId()));

        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setUser(user);
        comment.setTweet(tweet);

        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long commentId, Long userId, CommentUpdateRequest updateRequest) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with ID: " + commentId));

        if (!comment.getUser().getId().equals(userId)) {
            throw new UnauthorizedActionException("You are not allowed to update this comment.");
        }

        comment.setContent(updateRequest.getContent());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with ID: " + commentId));

        Long commentOwnerId = comment.getUser().getId();
        Long tweetOwnerId = comment.getTweet().getUser().getId();

        if (!userId.equals(commentOwnerId) && !userId.equals(tweetOwnerId)) {
            throw new UnauthorizedActionException("You are not authorized to delete this comment.");
        }

        commentRepository.delete(comment);
    }
}
