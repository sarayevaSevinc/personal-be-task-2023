package com.sarayeva.cybercubebetask.service.impl;

import com.sarayeva.cybercubebetask.domain.User;
import com.sarayeva.cybercubebetask.dto.UserDto;
import com.sarayeva.cybercubebetask.exception.UserNotFoundException;
import com.sarayeva.cybercubebetask.mapper.UserMapper;
import com.sarayeva.cybercubebetask.repository.UserRepository;
import com.sarayeva.cybercubebetask.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto addUser(UserDto userDto) {
        log.info("adding the person with name={}", userDto.getName());
        User user = UserMapper.INSTANCE.mapToUser(userDto);
        log.info("mapped user = {}", user);
        User saved = userRepository.save(user);
        log.info("saved user = {}", user);
        return UserMapper.INSTANCE.mapToDto(saved);
    }

    @Override
    public UserDto getUser(Long id) {
        User user = getUserInternal(id);
        UserDto userDto = UserMapper.INSTANCE.mapToDto(user);
        log.info("retrieved user with userId = {}", id);
        return userDto;
    }

    @Override
    public User getUserInternal(Long id) {
        log.info("Getting user with userId={}", id);
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        log.info("user found with userId={}", id);
        return user;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = getUserInternal(userId);
        user = UserMapper.INSTANCE.mapToUser(user, userDto);
        log.info("updating user with userId={}", userId);
        User saved = userRepository.save(user);
        log.info("updated user with userId={}", userId);
        return UserMapper.INSTANCE.mapToDto(saved);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
