package com.caplock.booking.service.impl;

import lombok.RequiredArgsConstructor;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SeatReservationServiceImpl {

    public Pair<Boolean, String> assignSeats() {
        if (false)
            return Pair.with(false, "Booking full only " + "" + "can be only added");


        if (false)
            return Pair.with(false, "Some seat is reserved");


        return Pair.with(true, "Reservation has been successfully assigned");
    }

    public boolean clearReservationOfSeats() {
        boolean fail = false;

        return !fail;
    }
}
