package com.thuctaptotnghiem.thuctaptotnghiep.model.request;

import com.thuctaptotnghiem.thuctaptotnghiep.enums.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull(message = "Username is required ")
    @Size(max = 20, message = "The length of User Name is less than 20 characters")
    private String username;

    @Size(max = 200)
    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    private String password;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Size(max = 20)
    @Email(message = "Email is invalid")
    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Address is required")
    @Size(max = 500, message = "The length of Address is less than 500 characters")
    private String address;

    @NotNull(message = "Phone is required")
    @Size(max = 20)
    private String phone;

    @NotNull(message = "Citizen_id is required")
    //@Size(max = 20)
    private long citizen_id;

    private Set<String> role;

}
