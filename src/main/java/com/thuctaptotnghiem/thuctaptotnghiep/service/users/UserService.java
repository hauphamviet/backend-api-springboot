package com.thuctaptotnghiem.thuctaptotnghiep.service.users;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.UserEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.model.request.RegisterRequest;
import com.thuctaptotnghiem.thuctaptotnghiep.model.request.UserRequest;
import com.thuctaptotnghiem.thuctaptotnghiep.model.response.UserResponse;

import java.util.List;

public interface UserService {

    void registerUser(RegisterRequest registerRequest);

    UserEntity getUserById(Long id);

    List<UserResponse> getAllUsers();

    UserEntity getUserByEmail(String email);

    UserEntity getUserByUsername(String username);

    UserEntity updateUser(Long id, UserRequest userRequest);

    void deleteUser(Long id);



}
