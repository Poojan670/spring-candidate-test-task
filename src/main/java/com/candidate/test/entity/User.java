package com.candidate.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**

 Represents a user table for authentication.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    /**

     The unique identifier of the permission.
     */
    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "id"
    )
    @ReadOnlyProperty
    private Long id;

    /**

     The username of the user.
     */
    @Column(unique = true, name = "user_name")
    @NotBlank(message = "userName is required")
    @NotNull(message = "userName can't be null")
    private String userName;

    /**
     The password of the user
     */
    @Column(name = "password")
    @Size(min = 4, message = "password should have at least 4 characters")
    @JsonIgnore
    private String password;

}
