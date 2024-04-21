package com.thuctaptotnghiem.thuctaptotnghiep.entity;

import com.thuctaptotnghiem.thuctaptotnghiep.enums.HistoryStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "histories")
public class HistoryEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "history_date")
    private LocalDateTime historyDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private HistoryStatusEnum status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;

}
