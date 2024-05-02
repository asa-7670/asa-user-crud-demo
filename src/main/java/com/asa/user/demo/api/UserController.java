package com.asa.user.demo.api;

import com.asa.user.demo.model.UserEntity;
import com.asa.user.demo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    /**
     * userService
     */
    private final UserService userService;

    /**
     * Constructor
     * @param userService UserController
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * sayHello
     * @param username String
     * @return ResponseEntity<String>
     */
    @GetMapping("hello/{username}")
    public ResponseEntity<String> sayHello(@PathVariable String username) {

        //Check if username is nul or empty string
        //Will return error system : 500 status with custom error message
        Assert.hasText(username, "username is required");

        //Success response with ok (200) status
        return ResponseEntity.ok().body(String.format("Hello %s !!", username));
    }

    /**
     * addUser
     * @param userDto UserEntity
     * @return ResponseEntity<UserEntity>
     */
    @PostMapping
    public ResponseEntity<UserEntity> addUser(@RequestBody @Valid UserEntity userDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.userService.addUser(userDto));
    }

    /**
     * users
     * @return ResponseEntity<Set<UserEntity>>
     */
    @GetMapping
    public ResponseEntity<Set<UserEntity>> users() {
        return ResponseEntity.ok().body(this.userService.getUsers());
    }

    /**
     * getUserById
     * @param id Long
     * @return ResponseEntity<UserEntity>
     */
    @GetMapping("{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ofNullable(this.userService.getUserById(id).get());
    }

    /**
     * deleteUserById
     * @param id Long
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable @NotNull Long id) {
        this.userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     *
     * @param userDto UserEntity
     * @return ResponseEntity<Void>
     */
    @PatchMapping
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UserEntity userDto) {
        this.userService.updateUser(userDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
