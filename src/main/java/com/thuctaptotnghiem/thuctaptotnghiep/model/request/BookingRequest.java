package com.thuctaptotnghiem.thuctaptotnghiep.model.request;

import com.thuctaptotnghiem.thuctaptotnghiep.enums.BookingStatusEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.LocationEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.TimeEnum;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    private LocalDate bookingDate;

    private TimeEnum bookingTime;

    private LocationEnum location;

    private String color;

    private BookingStatusEnum status;

    private String image;

    private String licensePlate;

    private String brand;

    private long totalPrice;

    private long userId;

    private List<BookingDetailRequest> bookingDetails;

}
