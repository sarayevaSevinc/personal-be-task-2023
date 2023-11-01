package com.sarayeva.cybercubebetask.service;

import com.sarayeva.cybercubebetask.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto addUser(UserDto userDto);

    UserDto getUser(Long id);
}
