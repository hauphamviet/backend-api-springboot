package com.thuctaptotnghiem.thuctaptotnghiep.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thuctaptotnghiem.thuctaptotnghiep.entity.BookingEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.entity.HistoryEntity;
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

    private String citizen_id;

    @JsonIgnore
    private List<BookingEntity> bookings;

    @JsonIgnore
    private List<HistoryEntity> historyEntities;

    private Collection<RoleEntity> roles = new HashSet<>();

}
