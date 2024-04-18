package com.thuctaptotnghiem.thuctaptotnghiep.model.request;

import com.thuctaptotnghiem.thuctaptotnghiep.enums.LocationEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.TimeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    private LocalDate bookingDate;

    private TimeEnum bookingTime;

    private LocationEnum location;

    private String color;

    private String image;

    private String licensePlate;

    private String brand;

    private long users;

}
