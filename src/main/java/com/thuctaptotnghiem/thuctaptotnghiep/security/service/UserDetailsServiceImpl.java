package com.thuctaptotnghiem.thuctaptotnghiep.security.service;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.UserEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.exception.NotFoundException;
import com.thuctaptotnghiem.thuctaptotnghiep.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));
        return UserDetailsImpl.buildUserDetails(userEntity);
    }
}
