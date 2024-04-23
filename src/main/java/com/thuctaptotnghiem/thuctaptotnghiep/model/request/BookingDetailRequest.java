package com.thuctaptotnghiem.thuctaptotnghiep.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetailRequest {

    @NotNull(message = "So luong san pham rong")
    @NotEmpty(message = "So luong san pham rong")
    @Size(min = 1, message = "So luong san pham tu 1 tro len")
    private int quantity;

    @NotNull(message = "Gia san pham rong")
    @NotEmpty(message = "Gia san pham rong")
    @Size(min = 0, message = "Gia san pham tu 0 tro len")
    private long price;

    private long subTotal;

}
