package com.sarayeva.cybercubebetask.service;

import com.sarayeva.cybercubebetask.domain.User;
import com.sarayeva.cybercubebetask.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto addUser(UserDto userDto);

    UserDto getUser(Long id);
    User getUserInternal(Long id);

    UserDto updateUser(UserDto userDto, Long userId);
    User updateUser(User user);
}
