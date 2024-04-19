package com.asa.user.demo.repository;

import com.asa.user.demo.dto.UserDto;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.*;

@Repository
public class UserRepository {

    /**
     * USERS_DATA_BASE
     */
    private static final Set<UserDto> USERS_DATA_BASE = new HashSet<>();

    /**
     * addUser
     * @param userDto UserDto
     * @return UserDto
     */
    public UserDto addUser(UserDto userDto) {
        Assert.notNull(userDto, "userDto is required");
       for(UserDto dto: USERS_DATA_BASE) {
           if(dto.getEmail().equals(userDto.getEmail())) {
               throw new IllegalStateException(String.format("User [%s] exists.", userDto.getEmail()));
           }
       }
       userDto.setId(UUID.randomUUID().toString());
       USERS_DATA_BASE.add(userDto);
       return userDto;
    }

    /**
     * getUsers
     * @return Set<UserDto>
     */
    public Set<UserDto> getUsers() {
        return USERS_DATA_BASE;
    }
}
