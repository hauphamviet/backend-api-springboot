package com.thuctaptotnghiem.thuctaptotnghiep.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking_detail")
public class BookingDetailEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private long price;

    @Column(name = "sub_total")
    private long subTotal;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;

}
