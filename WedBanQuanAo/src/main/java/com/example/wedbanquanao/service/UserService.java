package com.example.wedbanquanao.service;

import com.example.wedbanquanao.entity.User;
import com.example.wedbanquanao.model.request.ChangePasswordReq;
import com.example.wedbanquanao.model.request.CreateUserReq;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUser(CreateUserReq req);

    public void changePassword(User user, ChangePasswordReq req);

//    public User updateProfile(User user, UpdateProfileReq req);
}
