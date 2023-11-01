package com.sarayeva.cybercubebetask.service.impl;

import com.sarayeva.cybercubebetask.domain.User;
import com.sarayeva.cybercubebetask.dto.UserDto;
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
        log.info("getting the person with id={}", id);
        User user = userRepository.findUserById(id);
        log.info("got the person ={}", user);
        UserDto userDto = UserMapper.INSTANCE.mapToDto(user);
        log.info("personDto = {}", userDto);
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        log.info("adding the person with name={}", userDto.getName());
        User user = UserMapper.INSTANCE.mapToUser(userDto);
        log.info("mapped user = {}", user);
        User saved = userRepository.save(user);
        log.info("saved user = {}", user);
        return UserMapper.INSTANCE.mapToDto(saved);
    }
}
