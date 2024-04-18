package com.thuctaptotnghiem.thuctaptotnghiep.service.users;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.UserEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.model.request.RegisterRequest;

public interface UserService {

    void registerUser(RegisterRequest registerRequest);

    UserEntity getUserById(long id);

}
