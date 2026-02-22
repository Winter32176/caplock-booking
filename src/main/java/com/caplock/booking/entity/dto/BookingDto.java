package com.caplock.booking.entity.dto;

import com.caplock.booking.entity.StatusBookingEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookingDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String confirmationCode;
    private Long eventId;
    private Long userId;
    private BigDecimal totalPrice;
    private String discountCode;
    private BigDecimal discountAmount;
    private StatusBookingEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime canceledAt;
    private LocalDateTime expiresAt;
}
