package com.thuctaptotnghiem.thuctaptotnghiep.controller;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.BookingEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.model.request.BookingRequest;
import com.thuctaptotnghiem.thuctaptotnghiep.model.response.BookingResponse;
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

    @GetMapping("/{bookingId}")
    public BookingEntity getBookingById(@PathVariable long bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @GetMapping("/userId/{userId}")
    public List<BookingEntity> findBookingByUserId(@PathVariable long userId) {
        return bookingService.findBookingByUserId(userId);
    }

    @PostMapping("/create")
    public void createBookings(@RequestParam("file") MultipartFile file,
                               @ModelAttribute @Valid BookingRequest bookingRequest) throws SQLException, IOException {
        bookingService.saveBooking(file, bookingRequest);
    }

    @PutMapping("/update/{bookingId}")
    public BookingEntity updateBooking(@PathVariable long bookingId,
                                       @ModelAttribute @Valid BookingRequest bookingRequest,
                                       @RequestParam MultipartFile file) {
        return bookingService.updateBooking(bookingId, bookingRequest, file);
    }

    @DeleteMapping("/{bookingId}")
    public void cancelBooking(@PathVariable long bookingId) {
        bookingService.cancelBooking(bookingId);
    }

}
