package com.candidate.test.service;

import com.candidate.test.dto.AddUserDTO;
import com.candidate.test.entity.User;
import com.candidate.test.exception.CustomException;
import com.candidate.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getByUserName(String userName){
        return userRepository.getUserByUserName(userName)
                .orElseThrow(() -> new CustomException("User with this username : " + userName + " not found"));
    }

    private void validateUsername(String userName){
        if(Objects.nonNull(userRepository.validateUserName(userName))){
            throw new CustomException("User with this username: " + userName + " already exists");
        }
    }

    @Override
    public User registerUser(AddUserDTO addUserDTO) {
        this.validateUsername(addUserDTO.getUsername());
        return userRepository.save(User.builder()
                .userName(addUserDTO.getUsername())
                .password(passwordEncoder.encode(addUserDTO.getPassword()))
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.getByUserName(username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), user.getPassword(), authorities
        );
    }
}
