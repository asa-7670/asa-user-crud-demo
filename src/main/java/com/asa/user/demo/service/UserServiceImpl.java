package com.asa.user.demo.service;

import com.asa.user.demo.dto.UserDto;
import com.asa.user.demo.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    /**
     * userRepository
     */
    private final UserRepository userRepository;

    /**
     * Constructor
     * @param userRepository
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @return Set<UserDto>
     */
    @Override
    public Set<UserDto> getUsers() {
        return this.userRepository.getUsers();
    }

    /**
     * @param userDto
     * @return UserDto
     */
    @Override
    public UserDto addUser(UserDto userDto) {
        Assert.notNull(userDto, "userDto is required");
        return this.userRepository.addUser(userDto);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<User> getUserById(String id) {
        //TODO
        return Optional.empty();
    }

    /**
     * @param id
     */
    @Override
    public void deleteUserById(String id) {
        //TODO
    }
}
