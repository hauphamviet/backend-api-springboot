package com.thuctaptotnghiem.thuctaptotnghiep.service.bookings;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.BookingEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.model.request.BookingRequest;
import com.thuctaptotnghiem.thuctaptotnghiep.model.response.BookingResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface BookingService {

    List<BookingResponse> getAllBookings();

    BookingEntity getBookingById(long bookingId);

    void cancelBooking(long bookingId);

    List<BookingEntity> findBookingByUserId(long userId);

    BookingEntity updateBooking(long bookingId, BookingRequest bookingRequest, MultipartFile file);

    void saveBooking(MultipartFile file, BookingRequest bookingRequest) throws SQLException, IOException;

//    boolean isBookingLimitReached(LocalDateTime bookingTime);



}
