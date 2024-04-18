package com.thuctaptotnghiem.thuctaptotnghiep.service.bookings;

import com.thuctaptotnghiem.thuctaptotnghiep.common.Constants;
import com.thuctaptotnghiem.thuctaptotnghiep.entity.BookingEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.exception.BadRequestException;
import com.thuctaptotnghiem.thuctaptotnghiep.exception.InternalServerException;
import com.thuctaptotnghiem.thuctaptotnghiep.exception.NotFoundException;
import com.thuctaptotnghiem.thuctaptotnghiep.model.request.BookingRequest;
import com.thuctaptotnghiem.thuctaptotnghiep.model.response.BookingResponse;
import com.thuctaptotnghiem.thuctaptotnghiep.repository.BookingRepository;
import com.thuctaptotnghiem.thuctaptotnghiep.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/photos/";

    @Override
    public List<BookingResponse> getAllBookings() {
        var bookingList = bookingRepository.findAll(Sort.by("bookingId").descending());
        return bookingList.stream().map(this::mapToBookingResponse).toList();
    }

    @Override
    public BookingEntity getBookingById(long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.BOOKING_ID_NOT_EXIST, bookingId)));
    }

    private BookingResponse mapToBookingResponse(BookingEntity bookingEntity) {
        return BookingResponse.builder()
                .bookingId(bookingEntity.getBookingId())
                .bookingDate(bookingEntity.getBookingDate())
                .bookingTime(bookingEntity.getBookingTime())
                .location(bookingEntity.getLocation())
                .color(bookingEntity.getColor())
                .image(bookingEntity.getImage())
                .licensePlate(bookingEntity.getLicensePlate())
                .brand(bookingEntity.getBrand())
                .users(bookingEntity.getUsers())
                .build();
    }

    @Override
    public void cancelBooking(Long bookingId) {

    }

    @Override
    public BookingEntity findByBookingUserId(Long userId) {
        return null;
    }

    @Override
    public BookingEntity saveBooking(MultipartFile file, BookingRequest bookingRequest) throws IOException {
        var booking = new BookingEntity();

        booking.setBookingDate(bookingRequest.getBookingDate());
        booking.setBookingTime(bookingRequest.getBookingTime());
        booking.setLocation(bookingRequest.getLocation());
        booking.setColor(bookingRequest.getColor());
        booking.setLicensePlate(bookingRequest.getLicensePlate());
        booking.setBrand(bookingRequest.getBrand());

        String originalFilename = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(UPLOAD_DIR, originalFilename);
        Files.write(fileNameAndPath, file.getBytes());
        booking.setImage(originalFilename);

        var userEntity = userRepository.findById(bookingRequest.getUsers())
                .orElseThrow(() -> new NotFoundException(Constants.USER_ID_NOT_EXIST));
        booking.setUsers(userEntity);

        return bookingRepository.save(booking);

    }

}
