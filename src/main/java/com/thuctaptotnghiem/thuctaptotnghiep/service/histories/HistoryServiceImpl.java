package com.thuctaptotnghiem.thuctaptotnghiep.service.histories;

import com.thuctaptotnghiem.thuctaptotnghiep.common.Constants;
import com.thuctaptotnghiem.thuctaptotnghiep.entity.HistoryEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.exception.NotFoundException;
import com.thuctaptotnghiem.thuctaptotnghiep.model.response.HistoryResponse;
import com.thuctaptotnghiem.thuctaptotnghiep.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService{

    private final HistoryRepository historyRepository;

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
                .booking(historyEntity.getBooking())
                .build();
    }

    @Override
    public HistoryEntity getHistoryByBookingId(long bookingId) {
        return null;
    }

    @Override
    public void deleteHistory(long id) {
        var historyEntity = historyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.HISTORY_ID_NOT_EXIST, id)));
    }
}
