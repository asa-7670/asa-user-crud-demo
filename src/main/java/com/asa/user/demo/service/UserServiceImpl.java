package com.asa.user.demo.service;

import com.asa.user.demo.dto.UserDto;
import com.asa.user.demo.model.UserEntity;
import com.asa.user.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    /**
     * userRepository
     */
    private final UserRepository userRepository;

    /**
     * Constructor
     * @param userRepository UserRepository
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @return Set<UserDto>
     */
    @Override
    public Set<UserDto> getUsers() {
        List<UserEntity> users = this.userRepository.findAll();
        log.info("Users size: {}", users.size());
        return new HashSet<>(users.stream().map(user -> {
            return UserDto.builder()
                    .fullName(user.getFullName())
                    .id(user.getId())
                    .build();
            }).toList()
        );
    }

    /**
     * @param userDto UserEntity
     * @return UserEntity
     */
    @Override
    public UserEntity addUser(UserEntity userDto) {
        Assert.notNull(userDto, "userDto is required");
        log.info("Add user: {}", userDto.getFullName());
        return this.userRepository.save(userDto);
    }

    /**
     * @param id Long
     * @return Optional<UserEntity>
     */
    @Override
    public Optional<UserEntity> getUserById(Long id) {
        Assert.notNull(id, "user id is required");
        log.info("Search user by id: {}", id);
        return foundUserById(id);
    }

    /**
     * @param id Long
     */
    @Override
    public void deleteUserById(Long id) {
        Assert.notNull(id, "user id is required");
        //Check user exist
        foundUserById(id);
        //deleteById user
        log.info("Delete user with id: {}", id);
        this.userRepository.deleteById(id);
    }

    /**
     * @param userEntity UserEntity
     */
    @Override
    public void updateUser(Long id, UserEntity userEntity) {
        Assert.notNull(id, "userId is required");
        Assert.notNull(userEntity, "userEntity is required");
        //Check exist user
        Optional<UserEntity> foundUser = foundUserById(id);
        //check user email
        if(foundUser.isPresent() && !foundUser.get().getEmail().equals(userEntity.getEmail())) {
            throw new IllegalStateException("The user's email address cannot be modified");
        }
        //update user
        log.info("Update user {}", userEntity);
        this.userRepository.save(userEntity);
    }

    /**
     * @param email String
     * @return Optional<UserEntity>
     */
    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        Assert.hasText(email, "email is required");
        Optional<UserEntity> foundUser =  this.userRepository.findByEmail(email);
        if(foundUser.isEmpty()){
            throw new EntityNotFoundException(String.format("No users found with email [%s]", email));
        }
        return foundUser;
    }

    /**
     * foundUserById
     * @param id Long
     * @return Optional<UserEntity>
     */
    private Optional<UserEntity> foundUserById(Long id) {
        Optional<UserEntity> foundUser = this.userRepository.findById(id);
        if(foundUser.isEmpty()){
            throw new EntityNotFoundException(String.format("No users found with id [%d]", id));
        }
        return foundUser;
    }
}
