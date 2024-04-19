package com.asa.user.demo.api;

import com.asa.user.demo.dto.UserDto;
import com.asa.user.demo.service.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
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
     * @param userDto UserDto
     * @return ResponseEntity<UserDto>
     */
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(this.userService.addUser(userDto));
    }

    /**
     * users
     * @return ResponseEntity<Set<UserDto>>
     */
    @GetMapping
    public ResponseEntity<Set<UserDto>> users() {
        return ResponseEntity.ok().body(this.userService.getUsers());
    }
}
