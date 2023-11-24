package com.candidate.test.service;

import com.candidate.test.entity.User;
import com.candidate.test.dto.AddUserDTO;

public interface UserService {
    User getByUserName(String userName);

    User registerUser(AddUserDTO addUserDTO);
}
