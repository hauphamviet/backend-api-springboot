package com.thuctaptotnghiem.thuctaptotnghiep.service.users;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.RoleEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.entity.UserEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.RoleEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.model.request.RegisterRequest;
import com.thuctaptotnghiem.thuctaptotnghiep.repository.RoleRepository;
import com.thuctaptotnghiem.thuctaptotnghiep.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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
}
