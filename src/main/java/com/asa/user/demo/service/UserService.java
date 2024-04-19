package com.asa.user.demo.service;

import com.asa.user.demo.dto.UserDto;
import org.apache.catalina.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    Set<UserDto> getUsers();

    UserDto addUser(UserDto userDto);

    Optional<User> getUserById(String id);

    void deleteUserById(String id);
}
