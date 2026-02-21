package com.caplock.booking.entity.dto.flow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SeatHoldResponseDto {
    private List<String> freeSeats;
    private List<String> heldSeats;
}
