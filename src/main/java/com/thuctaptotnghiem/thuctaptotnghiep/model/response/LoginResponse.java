package com.thuctaptotnghiem.thuctaptotnghiep.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {

    private Long id;

    private String citizen_id;

    private String token;

    private String type = "Bearer";

    private List<String> roles;

    public LoginResponse(Long id, String citizen_id, String token, List<String> roles) {
        this.id = id;
        this.citizen_id = citizen_id;
        this.token = token;
        this.roles = roles;
    }

}

