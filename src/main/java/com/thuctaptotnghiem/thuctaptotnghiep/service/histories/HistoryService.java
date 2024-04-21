package com.thuctaptotnghiem.thuctaptotnghiep.service.histories;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.HistoryEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.model.response.HistoryResponse;

import java.util.List;

public interface HistoryService {

    List<HistoryResponse> getAllHistories();

    HistoryEntity getHistoryByBookingId(long bookingId);

    void deleteHistory(long id);

}
