package com.workintech.twitter_clone_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TweetUpdateRequest {

    @NotBlank
    @Size(max = 240)
    private String content;
}
