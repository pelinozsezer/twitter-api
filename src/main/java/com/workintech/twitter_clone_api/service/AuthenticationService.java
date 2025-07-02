package com.workintech.twitter_clone_api.service;

import com.workintech.twitter_clone_api.entity.User;

public interface AuthenticationService {
    User register(String fullName, String email, String password);
}
