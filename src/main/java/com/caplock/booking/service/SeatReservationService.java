package com.caplock.booking.service;

import com.caplock.booking.repository.IBookingRepository;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatReservationService implements ISeatReservationService {
    IEventService eventService;
    IBookingRepository bookingRepository;

    @Autowired
    public SeatReservationService(IEventService eventService, IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        this.eventService = eventService;
    }

    @Override
    public Pair<Boolean, String> assignSeats(String bookId, long eventId,List<String> seats) {
        var eventDet = eventService.getDetails(eventId);

        if (eventDet == null) return Pair.with(false, "Event not found");

        if (bookingRepository.checkBookingExists(bookId, eventId)) return Pair.with(false, "Booking already exists");

        if (eventDet.getBookedSeats() + seats.size() > eventDet.getCapacity()) {
            return Pair.with(false, "Booking full only " + (eventDet.getCapacity() - eventDet.getBookedSeats()) + "can be only added");
        }

        boolean bookedSeats = false;
        for (int i = 0; i < seats.size(); i++) {
            if (eventService.assignSeat(eventId, bookId, seats.get(i))) {
                bookedSeats = true;
                break;
            }
        }
        if (bookedSeats || seats.size() < 1)
            return Pair.with(false, "Some seat is reserved");


        return Pair.with(true, "Reservation has been successfully assigned");
    }

    @Override
    public boolean clearReservationOfSeats(String bookId, long eventId) {
        boolean fail = false;
        if (eventService.unassignSeat(eventId, bookId))
            fail = true;

        return !fail;
    }
}
