package com.cg.model.dto;


import com.cg.model.User;
import com.cg.model.dto.product.ProductUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.swing.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    private Long id;

    @NotBlank(message = "Email cannot be blank!")

    @Size(min = 5, max = 50, message = "Email length between 5-50 characters!")
    private String username;

    @NotBlank(message = "Please enter a password!")
    @Size(max = 30, message =  "Password length up to 30 characters!")
    private String password;

    public UserLoginDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public User toUser() {
        return new User()
                .setId(id)
                .setUsername(username)
                .setPassword(password);
    }


}