package com.asa.user.demo.api;

import com.asa.user.demo.model.UserEntity;
import com.asa.user.demo.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
     * GET: <host>:<port>/api/v1/users/<user name>
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
     * POST: <host>:<port>/api/v1/users
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
     * GET: <host>:<port>/api/v1/users
     * @return ResponseEntity<Set<UserEntity>>
     */
    @GetMapping
    public ResponseEntity<Set<UserEntity>> users() {
        return ResponseEntity.ok().body(this.userService.getUsers());
    }

    /**
     * getUserById
     * GET: <host>:<port>/api/v1/users/user/<user id>
     * @param id Long
     * @return ResponseEntity<UserEntity>
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ofNullable(this.userService.getUserById(id).get());
    }

    /**
     * deleteUserById
     * DELETE: <host>:<port>/api/v1/users/user/<user id>
     * @param id Long
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable @NotNull Long id) {
        this.userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * updateUser
     * PATCH: <host>:<port>/api/v1/users/user/<user id>
     * @param userDto UserEntity
     * @return ResponseEntity<Void>
     */
    @PatchMapping("/user/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable @NotNull Long id, @RequestBody @Valid UserEntity userDto) {
        this.userService.updateUser(id, userDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * findUserByEmail
     * GET: <host>:<port>/api/v1/users/user?email=<email>
     * @param email String
     * @return ResponseEntity<UserEntity>
     */
    @GetMapping("/user")
    public ResponseEntity<UserEntity> findUserByEmail(@RequestParam @NotBlank String email) {
       return ResponseEntity.ok().body(this.userService.findUserByEmail(email).get());
    }

}
