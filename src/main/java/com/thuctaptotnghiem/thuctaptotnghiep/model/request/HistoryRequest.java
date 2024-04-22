package com.thuctaptotnghiem.thuctaptotnghiep.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thuctaptotnghiem.thuctaptotnghiep.entity.BookingEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.enums.HistoryStatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRequest {

    private LocalDateTime historyDate;

    private HistoryStatusEnum status;

    private long bookings;

}
