package com.thuctaptotnghiem.thuctaptotnghiep.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "password", length = 300)
    private String password;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Email(message = "Email is invalid")
    @Column(name = "email", length = 50, unique = true)
    private String email;

    @Column(name = "address", length = 300)
    private String address;

    @Column(name = "phone", length = 20, unique = true)
    private String phone;

    @Column(name = "citizen_id", length = 20, unique = true)
    private long citizen_id;

    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<BookingEntity> bookings;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<RoleEntity> roles = new HashSet<>();

}
