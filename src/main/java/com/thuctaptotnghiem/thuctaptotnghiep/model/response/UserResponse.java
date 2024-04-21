package com.thuctaptotnghiem.thuctaptotnghiep.model.response;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.BookingEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.entity.RoleEntity;
import lombok.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String phone;

    private long citizen_id;

    private List<BookingEntity> bookings;

    private Collection<RoleEntity> roles = new HashSet<>();

}
