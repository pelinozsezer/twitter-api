package com.workintech.twitter_clone_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LikeRequest {
    @NotNull
    private Integer userId;

    @NotNull
    private Integer tweetId;
}
