package com.caplock.booking.service;

import com.caplock.booking.entity.dao.BookingDao;
import com.caplock.booking.repository.IBookingRepository;
import com.caplock.booking.repository.IEventRepository;
import com.caplock.booking.util.Mapper;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatReservationService {
    IEventService eventService;
    IBookingRepository bookingRepository;
    @Autowired
    public SeatReservationService(IEventService eventService, IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        this.eventService= eventService;
    }
    public Pair<Boolean, String> assignSeat(String bookId, long eventId,long qty){
        var eventDet = eventService.getDetails(eventId);

        if (eventDet == null) return Pair.with(false, "Event not found");

        if (bookingRepository.checkBookingExists(bookId,eventId)) return Pair.with(false, "Booking already exists");

        if (eventDet.getBookedSeats() + qty > eventDet.getCapacity()) {
            return Pair.with(false, "Booking full");
        }

        if(eventService.)
            return Pair.with(true, "Seat is reserved");


        return Pair.with(true, "Reservation has been successfully assigned");
    }
}
