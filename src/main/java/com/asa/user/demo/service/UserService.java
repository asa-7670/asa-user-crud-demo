package com.asa.user.demo.service;

import com.asa.user.demo.dto.UserDto;
import com.asa.user.demo.model.UserEntity;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    Set<UserDto> getUsers();

    UserEntity addUser(UserEntity userEntity);

    Optional<UserEntity> getUserById(Long id);

    void deleteUserById(Long id);

    void updateUser(Long id, UserEntity userEntity);

    Optional<UserEntity> findUserByEmail(String email);
}
