package com.thuctaptotnghiem.thuctaptotnghiep.model.request;

import com.thuctaptotnghiem.thuctaptotnghiep.enums.HistoryStatusEnum;
import jakarta.validation.constraints.Positive;
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

    @Positive(message = "Number of bookings must be positive")
    private long bookings;

}
