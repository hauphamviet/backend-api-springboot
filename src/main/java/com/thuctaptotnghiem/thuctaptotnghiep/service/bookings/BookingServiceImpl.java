package com.thuctaptotnghiem.thuctaptotnghiep.service.bookings;

import com.thuctaptotnghiem.thuctaptotnghiep.common.Constants;
import com.thuctaptotnghiem.thuctaptotnghiep.entity.BookingEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.BookingStatusEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.exception.BookingLimitExceededException;
import com.thuctaptotnghiem.thuctaptotnghiep.exception.NotFoundException;
import com.thuctaptotnghiem.thuctaptotnghiep.model.request.BookingRequest;
import com.thuctaptotnghiem.thuctaptotnghiep.model.response.BookingResponse;
import com.thuctaptotnghiem.thuctaptotnghiep.repository.BookingRepository;
import com.thuctaptotnghiem.thuctaptotnghiep.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Log4j2
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
                .status(bookingEntity.getStatus())
                .image(bookingEntity.getImage())
                .licensePlate(bookingEntity.getLicensePlate())
                .brand(bookingEntity.getBrand())
                .users(bookingEntity.getUsers())
                .build();
    }

    @Override
    public void cancelBooking(long bookingId) {
        var bookingEntity = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.BOOKING_ID_NOT_EXIST, bookingId)));
        bookingRepository.delete(bookingEntity);
    }

    @Override
    public List<BookingEntity> findBookingByUserId(long userId) {
        return bookingRepository.findBookingByUserId(userId);
    }

    @Override
    public BookingEntity updateBooking(long bookingId, BookingRequest bookingRequest, MultipartFile file) {
        var bookingTemp = bookingRepository.findById(bookingId)
                .map(bookingEntity -> {
                    try {
                        return buildBookingEntity(bookingEntity, bookingRequest, file);
                    } catch (IOException e) {
                        log.error("Error occurred while processing file for booking update", e);
                        return null;
                    }
                })
                .orElse(null);

        if (Objects.isNull(bookingTemp)) {
            log.error("error update booking id = {}", bookingId);
            return null;
        }

        return bookingRepository.save(bookingTemp);
    }

    private BookingEntity buildBookingEntity(BookingEntity bookingEntity, BookingRequest bookingRequest, MultipartFile file) throws IOException {
        bookingEntity.setBookingDate(bookingRequest.getBookingDate());
        bookingEntity.setBookingTime(bookingRequest.getBookingTime());
        bookingEntity.setLocation(bookingRequest.getLocation());
        bookingEntity.setColor(bookingRequest.getColor());
        bookingEntity.setLicensePlate(bookingRequest.getLicensePlate());
        bookingEntity.setBrand(bookingRequest.getBrand());
        bookingEntity.setStatus(bookingRequest.getStatus());

        String originalFilename = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(UPLOAD_DIR, originalFilename);
        Files.write(fileNameAndPath, file.getBytes());
        bookingEntity.setImage(originalFilename);

        var userEntity = userRepository.findById(bookingRequest.getUsers())
                .orElseThrow(() -> new NotFoundException(Constants.USER_ID_NOT_EXIST));
        bookingEntity.setUsers(userEntity);

        return bookingEntity;
    }

    @Override
    public BookingEntity saveBooking(MultipartFile file, BookingRequest bookingRequest) throws IOException {
//        LocalDateTime bookingTime = LocalDateTime.parse(bookingRequest.getBookingDate() + "T" + bookingRequest.getBookingTime());
//
//        if (isBookingLimitReached(bookingTime)) {
//            // Nếu giới hạn đã đạt, bạn có thể xử lý thông báo hoặc đề xuất khoảng thời gian khác
//            throw new BookingLimitExceededException("Booking limit for this hour has been reached. Please choose another time.");
//        }

        var booking = new BookingEntity();

        booking.setBookingDate(bookingRequest.getBookingDate());
        booking.setBookingTime(bookingRequest.getBookingTime());
        booking.setLocation(bookingRequest.getLocation());
        booking.setColor(bookingRequest.getColor());
        booking.setStatus(BookingStatusEnum.Pending);
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

//    @Override
//    public boolean isBookingLimitReached(LocalDateTime bookingTime) {
//        // tinh toan khoang thoi gian 1 tieng
//        LocalDateTime endTime = bookingTime.plusHours(1);
//
//        // Lay ds cac booking trong khoang thoi gian do
//        List<BookingEntity> bookingsWithinHour = bookingRepository.findByBookingTimeBetween(bookingTime, endTime);
//
//        // Verify so luong booking
//        return bookingsWithinHour.size() >= 5;
//    }

}
