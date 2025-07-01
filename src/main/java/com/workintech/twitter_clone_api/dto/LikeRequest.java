package com.workintech.twitter_clone_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LikeRequest {
    @NotNull
    private Long userId;

    @NotNull
    private Long tweetId;
}
