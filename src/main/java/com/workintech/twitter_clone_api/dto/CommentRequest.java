package com.workintech.twitter_clone_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentRequest {

    @NotBlank
    @Size(max = 240)
    private String content;

    @NotNull
    private Long userId;

    @NotNull
    private Long tweetId;
}
