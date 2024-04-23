package com.thuctaptotnghiem.thuctaptotnghiep.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thuctaptotnghiem.thuctaptotnghiep.entity.BookingDetailEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.entity.UserEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.BookingStatusEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.LocationEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.TimeEnum;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {

    private long bookingId;

    private LocalDate bookingDate;

    private TimeEnum bookingTime;

    private LocationEnum location;

    private String color;

    private BookingStatusEnum status;

    private String image;

    private String licensePlate;

    private String brand;

    private long totalPrice;

    @JsonIgnore
    private UserEntity users;

    private Set<BookingDetailEntity> bookingDetails;

}
