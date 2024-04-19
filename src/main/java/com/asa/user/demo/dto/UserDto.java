package com.asa.user.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;
    @NotBlank(message="error.user.firstname.required")
    @Size(min = 3, max = 50,  message = "error.user.firstname.length")
    //@Min(value = 5, message = "error.user.firstname.min.length")
    //@Max(value = 50, message = "error.user.firstname.max.length")
    private String firstname;
    @NotBlank(message="error.user.lastname.required")
    @Size(min = 3, max = 50,  message = "error.user.lastname.length")
//    @Min(value = 5, message = "error.user.lastname.min.length")
//    @Max(value = 50, message = "error.user.lastname.max.length")
    private String lastname;
    @NotNull(message="error.user.birthday.required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @Email(message="error.user.email.required")
    private String email;
}
