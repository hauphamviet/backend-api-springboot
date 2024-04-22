package com.thuctaptotnghiem.thuctaptotnghiep.service.users;

import com.thuctaptotnghiem.thuctaptotnghiep.common.Constants;
import com.thuctaptotnghiem.thuctaptotnghiep.entity.RoleEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.entity.UserEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.RoleEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.exception.NotFoundException;
import com.thuctaptotnghiem.thuctaptotnghiep.model.request.RegisterRequest;
import com.thuctaptotnghiem.thuctaptotnghiep.model.request.UserRequest;
import com.thuctaptotnghiem.thuctaptotnghiep.model.response.UserResponse;
import com.thuctaptotnghiem.thuctaptotnghiep.repository.RoleRepository;
import com.thuctaptotnghiem.thuctaptotnghiep.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(RegisterRequest registerRequest) {
        var userEntity = new UserEntity();
        userEntity.setUsername(registerRequest.getUsername());
        userEntity.setEmail(registerRequest.getEmail());
        userEntity.setAddress(registerRequest.getAddress());
        userEntity.setCitizen_id(registerRequest.getCitizen_id());
        userEntity.setFirstName(registerRequest.getFirstName());
        userEntity.setLastName(registerRequest.getLastName());
        userEntity.setPhone(registerRequest.getPhone());
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        Set<String> strRoles = registerRequest.getRole();
        Set<RoleEntity> roles = new HashSet<>();

        if (strRoles == null) {
            var userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        var adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        var modRole = roleRepository.findByName(RoleEnum.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        var userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        userEntity.setRoles(roles);
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.USER_ID_NOT_EXIST, id)));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        var usersList = userRepository.findAll();
        return usersList.stream().map(this::mapToUserResponse).toList();
    }

    private UserResponse mapToUserResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .address(userEntity.getAddress())
                .phone(userEntity.getPhone())
                .citizen_id(userEntity.getCitizen_id())
                .bookings(userEntity.getBookings())
                .roles(userEntity.getRoles())
                .build();
    }

    @Override
    public void deleteUser(Long id) {
        var userEntity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.USER_ID_NOT_EXIST, id)));
        userRepository.delete(userEntity);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.USER_EMAIL_NOT_EXIST, email)));
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.USER_NAME_NOT_EXIST, username)));
    }

    @Override
    public UserEntity updateUser(Long id, UserRequest userRequest) {
        var userTemp = userRepository.findById(id)
                .map(userEntity -> buildUsersEntity(userEntity, userRequest))
                .orElse(null);

        if (Objects.isNull(userTemp)) {
            log.error("error update user id = {}", id);
            return null;
        }
        return userRepository.save(userTemp);
    }

    private UserEntity buildUsersEntity(UserEntity userEntity, UserRequest userRequest) {
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setFirstName(userRequest.getFirstName());
        userEntity.setLastName(userRequest.getLastName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setAddress(userRequest.getAddress());
        userEntity.setPhone(userRequest.getPhone());
        userEntity.setCitizen_id(userRequest.getCitizen_id());

        return userEntity;
    }
}
