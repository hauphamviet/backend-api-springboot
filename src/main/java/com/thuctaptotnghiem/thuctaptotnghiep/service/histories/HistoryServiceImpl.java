package com.thuctaptotnghiem.thuctaptotnghiep.service.histories;

import com.thuctaptotnghiem.thuctaptotnghiep.common.Constants;
import com.thuctaptotnghiem.thuctaptotnghiep.entity.HistoryEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.HistoryStatusEnum;
import com.thuctaptotnghiem.thuctaptotnghiep.exception.NotFoundException;
import com.thuctaptotnghiem.thuctaptotnghiep.model.request.HistoryRequest;
import com.thuctaptotnghiem.thuctaptotnghiep.model.response.HistoryResponse;
import com.thuctaptotnghiem.thuctaptotnghiep.repository.BookingRepository;
import com.thuctaptotnghiem.thuctaptotnghiep.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final BookingRepository bookingRepository;

    @Override
    public List<HistoryResponse> getAllHistories() {
        var historyList = historyRepository.findAll(Sort.by("id").descending());
        return historyList.stream().map(this::mapToHistoryResponse).toList();
    }

    private HistoryResponse mapToHistoryResponse(HistoryEntity historyEntity) {
        return HistoryResponse.builder()
                .id(historyEntity.getId())
                .status(historyEntity.getStatus())
                .historyDate(historyEntity.getHistoryDate())
                .booking(historyEntity.getBookings())
                .build();
    }

    @Override
    public HistoryEntity getHistoryByBookingId(long bookingId) {
        return historyRepository.getHistoryByBookingId(bookingId)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.BOOKING_ID_NOT_EXIST, bookingId)));
    }

    @Override
    public HistoryEntity saveHistory(HistoryRequest historyRequest) {
        var historyEntity = new HistoryEntity();

        historyEntity.setHistoryDate(historyRequest.getHistoryDate());
        historyEntity.setStatus(HistoryStatusEnum.Pending);

        var bookingEntity = bookingRepository.findById(historyRequest.getBookings())
                .orElseThrow(() -> new NotFoundException(String.format(Constants.BOOKING_ID_NOT_EXIST, historyRequest.getBookings())));
        historyEntity.setBookings(bookingEntity);

        return historyRepository.save(historyEntity);
    }

    private HistoryEntity buildHistoriesEntity(HistoryEntity historyEntity, HistoryRequest historyRequest) {
        historyEntity.setHistoryDate(historyRequest.getHistoryDate());
        historyEntity.setStatus(historyRequest.getStatus());

        var bookingEntity = bookingRepository.findById(historyRequest.getBookings())
                .orElseThrow(() -> new NotFoundException(String.format(Constants.BOOKING_ID_NOT_EXIST, historyRequest.getBookings())));
        historyEntity.setBookings(bookingEntity);

        return historyEntity;
    }

    @Override
    public HistoryEntity updateHistory(long id, HistoryRequest historyRequest) {
        var historyTemp = historyRepository.findById(id)
                .map(historyEntity -> buildHistoriesEntity(historyEntity, historyRequest))
                .orElse(null);

        if (Objects.isNull(historyTemp)) {
            log.error("error update history id = {}", id);
            return null;
        }
        return historyRepository.save(historyTemp);
    }

    @Override
    public void deleteHistory(long id) {
        var historyEntity = historyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.HISTORY_ID_NOT_EXIST, id)));

        // Xoa lien ket giua history va booking
        if (historyEntity.getBookings() != null) {
            historyEntity.setBookings(null);
            historyRepository.save(historyEntity);
        }

        historyRepository.delete(historyEntity);
    }
}
