package com.thuctaptotnghiem.thuctaptotnghiep.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotNull(message = "Email is required")
    @NotEmpty(message="Username rỗng")
//    @Email(message = "Email is invalid")
//    private String email;
    private String citizen_id;

    @NotNull(message = "Mật khẩu rỗng")
    @NotEmpty(message = "Mật khẩu rỗng")
    @Size(min=6,max=30,message="Mật khẩu có từ 6-30 ký tự")
    private String password;

}
