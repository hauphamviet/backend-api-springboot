package com.thuctaptotnghiem.thuctaptotnghiep.model.response;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.BookingEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.HistoryStatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryResponse {

    private long id;

    private LocalDateTime historyDate;

    private HistoryStatusEnum status;

    private BookingEntity booking;

}
