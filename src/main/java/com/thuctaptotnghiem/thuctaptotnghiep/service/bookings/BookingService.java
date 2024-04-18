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

    void cancelBooking(Long bookingId);

    BookingEntity findByBookingUserId(Long userId);

    BookingEntity saveBooking(MultipartFile file, BookingRequest bookingRequest) throws SQLException, IOException;



}
