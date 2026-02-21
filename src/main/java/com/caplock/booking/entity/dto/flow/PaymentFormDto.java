package com.caplock.booking.entity.dto.flow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaymentFormDto {
    private Long bookingId;
    private BigDecimal amount;
    private String method;
}
