package com.thuctaptotnghiem.thuctaptotnghiep.controller;

import com.thuctaptotnghiem.thuctaptotnghiep.common.Constants;
import com.thuctaptotnghiem.thuctaptotnghiep.entity.BookingEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.exception.NotFoundException;
import com.thuctaptotnghiem.thuctaptotnghiep.model.request.BookingRequest;
import com.thuctaptotnghiem.thuctaptotnghiep.model.response.BookingResponse;
import com.thuctaptotnghiem.thuctaptotnghiep.repository.UserRepository;
import com.thuctaptotnghiem.thuctaptotnghiep.service.bookings.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*",maxAge = 3600)
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/")
    public List<BookingResponse> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public BookingEntity getBookingById(@PathVariable Long bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @PostMapping("/create")
    public BookingEntity createBookings(@RequestParam("file") MultipartFile file,
                                         @ModelAttribute BookingRequest bookingRequest) throws SQLException, IOException {
        return bookingService.saveBooking(file, bookingRequest);
    }
}
