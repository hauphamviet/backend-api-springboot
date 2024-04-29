package com.thuctaptotnghiem.thuctaptotnghiep.model.request;

import com.thuctaptotnghiem.thuctaptotnghiep.enums.BookingStatusEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.LocationEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.TimeEnum;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    @NotNull(message = "Booking date must not be null")
    @FutureOrPresent(message = "Booking date must be in the future or present")
    private LocalDate bookingDate;

    @NotNull(message = "Booking time must not be null")
    private TimeEnum bookingTime;

    @NotNull(message = "Location must not be null")
    private LocationEnum location;

    @NotEmpty(message = "Color must not be null")
    private String color;

    private BookingStatusEnum status;

    private String image;

    @NotEmpty(message = "Type must not be null")
    private String type;

    @NotEmpty(message = "Brand must not be null")
    private String brand;

    private long userId;

 //   private List<BookingDetailRequest> bookingDetails;

}
