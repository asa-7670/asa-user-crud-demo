package com.asa.user.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@Entity
@Table(name = "T_USERS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USR_ID")
    private long id;
    @Column(name = "USR_CIVILITY", length = 3, nullable = false)
    @NotBlank(message="error.user.civility.required")
    private String civility;
    @Column(name = "USER_FIRSTNAME", length = 50, nullable = false)
    @NotBlank(message="error.user.firstname.required")
    @Size(min = 3, max = 50,  message = "error.user.firstname.length")
    private String firstname;
    @Column(name = "USR_LASTNAME", length = 50, nullable = false)
    @NotBlank(message="error.user.lastname.required")
    @Size(min = 3, max = 50,  message = "error.user.lastname.length")
    private String lastname;
    @Column(name = "USR_BIRTHDAY", nullable = false)
    @NotNull(message="error.user.birthday.required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @Column(name = "USR_USERNAME", length = 50, nullable = false, unique = true)
    @NotBlank(message="error.user.email.required")
    @Email(message="error.user.email.not.valid")
    @JsonProperty("mail")
    private String email;
    @Transient
    private int age;
    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }
    @Transient
    private String fullName;
    public String getFullName() {
        return new StringBuilder()
                .append(civility)
                .append(" ")
                .append(firstname)
                .append(" ")
                .append(lastname).toString();

    }
}
