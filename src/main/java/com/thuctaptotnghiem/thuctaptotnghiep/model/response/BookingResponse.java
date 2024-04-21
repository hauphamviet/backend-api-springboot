package com.thuctaptotnghiem.thuctaptotnghiep.model.response;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.UserEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.BookingStatusEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.LocationEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.TimeEnum;
import lombok.*;

import java.sql.Blob;
import java.time.LocalDate;

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

    private UserEntity users;

}
