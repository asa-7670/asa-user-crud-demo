package com.asa.user.demo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

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
}
