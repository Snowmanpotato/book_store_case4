package com.cg.model.dto;

import com.cg.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO  {
    private Long id;

    @NotBlank(message = "Please enter your name!")
    @Size(min = 5, max = 50, message = "Names should be between 5-50 characters long!")
    private String fullName;

    @NotBlank(message = "Please enter email!")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email invalidate!")
    @Size(min = 5, max = 50, message = "Email length between 5-50 characters!")
    private String username;

    @NotBlank(message = "Please enter a password!")
    @Size(max = 30, message = "Password length up to 30 characters!")
    private String password;

//    @Valid
//    private RoleDTO role;

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public User toUser() {
        return new User()
                .setId(id)
                .setFullName(fullName)
                .setUsername(username)
                .setPassword(password);
//                .setRole(role.toRole());
    }

//    @Override
//    public boolean supports(Class<?> clazz) {
//        return UserLoginDTO.class.isAssignableFrom(clazz);
//
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//
//        UserLoginDTO userLoginDTO =  (UserLoginDTO) target;
//        String username = userLoginDTO.getUsername();
//        if(!username.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
//            errors.rejectValue("username","username.regexp", "Email không đúng định dạng");
//        }
//    }
}