package com.caplock.booking.entity.dto.flow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SeatAvailabilityRequestDto {
    private Long eventId;
    private Integer quantity;
}
