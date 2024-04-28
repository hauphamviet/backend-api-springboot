package com.thuctaptotnghiem.thuctaptotnghiep.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.BookingStatusEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.LocationEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.TimeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "booking_time")
    @Enumerated(EnumType.STRING)
    private TimeEnum bookingTime;

    @Column(name = "location")
    @Enumerated(EnumType.STRING)
    private LocationEnum location;

    @Column(name = "color")
    private String color;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BookingStatusEnum status;

    @Lob
    @Column(name = "image")
    private String image;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "brand")
    private String brand;

    @Column(name = "total_price")
    private Long totalPrice;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private UserEntity users;

    @JsonIgnore
    @OneToMany(mappedBy = "booking")
    @JsonBackReference
    private Set<BookingDetailEntity> bookingDetails;

    @OneToMany(mappedBy = "bookings")
    @JsonBackReference
    private Set<HistoryEntity> historyEntities;

}
