package com.thuctaptotnghiem.thuctaptotnghiep.entity;

import com.thuctaptotnghiem.thuctaptotnghiep.enums.LocationEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.TimeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
@Builder
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "booking_time")
    private TimeEnum bookingTime;

    @Column(name = "location")
    private LocationEnum location;

    @Column(name = "color")
    private String color;

    @Lob
    @Column(name = "image")
    private String image;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "brand")
    private String brand;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private UserEntity users;

}
