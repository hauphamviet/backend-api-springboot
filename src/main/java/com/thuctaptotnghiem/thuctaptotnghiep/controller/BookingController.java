package com.thuctaptotnghiem.thuctaptotnghiep.controller;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.BookingEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.BookingStatusEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.model.request.BookingRequest;
import com.thuctaptotnghiem.thuctaptotnghiep.model.response.BookingResponse;
import com.thuctaptotnghiem.thuctaptotnghiep.service.bookings.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createBookings(@RequestParam("file") MultipartFile file,
                                         @ModelAttribute @Valid BookingRequest bookingRequest) throws SQLException, IOException {
        bookingService.saveBooking(file, bookingRequest);
        return ResponseEntity.ok().body("Booking created successfully");
    }

    @PutMapping("/update/{bookingId}")
    public ResponseEntity<BookingEntity> updateBooking(@PathVariable long bookingId,
                                                       @ModelAttribute @Valid BookingRequest bookingRequest,
                                                       @RequestParam MultipartFile file) {
        BookingEntity updatedBooking = bookingService.updateBooking(bookingId, bookingRequest, file);
        return ResponseEntity.ok().body(updatedBooking);
    }

    @PutMapping("/update-status/{bookingId}")
    public BookingEntity updateStatus(@PathVariable long bookingId,
                                      BookingStatusEnum status) {
        return bookingService.updateStatus(bookingId, status);
    }

    @DeleteMapping("/{bookingId}")
    public void cancelBooking(@PathVariable long bookingId) {
        bookingService.cancelBooking(bookingId);
    }

}
