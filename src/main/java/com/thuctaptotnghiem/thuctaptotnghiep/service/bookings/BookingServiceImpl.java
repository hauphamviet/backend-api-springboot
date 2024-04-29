package com.thuctaptotnghiem.thuctaptotnghiep.service.bookings;

import com.thuctaptotnghiem.thuctaptotnghiep.common.Constants;
import com.thuctaptotnghiem.thuctaptotnghiep.entity.BookingEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.BookingStatusEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.exception.NotFoundException;
import com.thuctaptotnghiem.thuctaptotnghiep.model.request.BookingRequest;
import com.thuctaptotnghiem.thuctaptotnghiep.model.response.BookingResponse;
import com.thuctaptotnghiem.thuctaptotnghiep.repository.BookingDetailRepository;
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
import java.util.List;
import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final BookingDetailRepository bookingDetailRepository;

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
                .brand(bookingEntity.getBrand())
                .type(bookingEntity.getType())
                .users(bookingEntity.getUsers())
                .histories(bookingEntity.getHistoryEntities())
                .build();
    }

    @Override
    public void cancelBooking(long bookingId) {
        var bookingEntity = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.BOOKING_ID_NOT_EXIST, bookingId)));

        if (bookingEntity.getUsers() != null) {
            bookingEntity.setUsers(null);
            bookingRepository.save(bookingEntity);
        }

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

    @Override
    public BookingEntity updateStatus(long bookingId, BookingStatusEnum status) {
        var bookingEntity = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.BOOKING_ID_NOT_EXIST, bookingId)));
        bookingEntity.setStatus(status);
        return bookingRepository.save(bookingEntity);
    }

    private BookingEntity buildBookingEntity(BookingEntity bookingEntity, BookingRequest bookingRequest, MultipartFile file) throws IOException {
        bookingEntity.setBookingDate(bookingRequest.getBookingDate());
        bookingEntity.setBookingTime(bookingRequest.getBookingTime());
        bookingEntity.setLocation(bookingRequest.getLocation());
        bookingEntity.setColor(bookingRequest.getColor());
        bookingEntity.setBrand(bookingRequest.getBrand());
        bookingEntity.setType(bookingRequest.getType());
        bookingEntity.setStatus(bookingRequest.getStatus());

        String originalFilename = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(UPLOAD_DIR, originalFilename);
        Files.write(fileNameAndPath, file.getBytes());
        bookingEntity.setImage(originalFilename);

        var userEntity = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new NotFoundException(Constants.USER_ID_NOT_EXIST));
        bookingEntity.setUsers(userEntity);

//        long totalPrice = 0;
//        List<BookingDetailRequest> bookingDetails = bookingRequest.getBookingDetails();
//        if (bookingDetails != null) {
//            for (BookingDetailRequest rq : bookingDetails) {
//                var bookingDetail = new BookingDetailEntity();
//                bookingDetail.setQuantity(rq.getQuantity());
//                bookingDetail.setPrice(350);
//                bookingDetail.setSubTotal(rq.getPrice() * rq.getQuantity());
//                bookingDetail.setBooking(bookingEntity);
//                totalPrice += bookingDetail.getSubTotal();
//                bookingDetailRepository.save(bookingDetail);
//            }
//        }

//        bookingEntity.setTotalPrice(totalPrice);
        bookingEntity.setUsers(userEntity);

        return bookingEntity;
    }

    @Override
    public void saveBooking(MultipartFile file, BookingRequest bookingRequest) throws IOException {
        var bookingEntity = new BookingEntity();
        var userEntity = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new NotFoundException(Constants.USER_ID_NOT_EXIST));

        bookingEntity.setBookingDate(bookingRequest.getBookingDate());
        bookingEntity.setBookingTime(bookingRequest.getBookingTime());
        bookingEntity.setLocation(bookingRequest.getLocation());
        bookingEntity.setColor(bookingRequest.getColor());
        bookingEntity.setStatus(BookingStatusEnum.Pending);
        bookingEntity.setBrand(bookingRequest.getBrand());
        bookingEntity.setType(bookingRequest.getType());

        String originalFilename = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(UPLOAD_DIR, originalFilename);
        Files.write(fileNameAndPath, file.getBytes());
        bookingEntity.setImage(originalFilename);

//        long totalPrice = 0;
//        List<BookingDetailRequest> bookingDetails = bookingRequest.getBookingDetails();
//        if (bookingDetails != null) {
//            for (BookingDetailRequest rq : bookingDetails) {
//                var bookingDetail = new BookingDetailEntity();
//                bookingDetail.setQuantity(rq.getQuantity());
//                bookingDetail.setPrice(350);
//                bookingDetail.setSubTotal(rq.getPrice() * rq.getQuantity());
//                bookingDetail.setBooking(bookingEntity);
//                totalPrice += bookingDetail.getSubTotal();
//                bookingDetailRepository.save(bookingDetail);
//            }
//        }
        bookingEntity.setUsers(userEntity);

        bookingRepository.save(bookingEntity);

    }

}
