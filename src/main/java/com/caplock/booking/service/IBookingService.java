package com.caplock.booking.service;

import com.caplock.booking.entity.dto.BookingDto;
import org.javatuples.Pair;

import java.util.Collection;

public interface IBookingService {
    BookingDto getBookingById(long id);

    Collection<BookingDto> getAllUserBookings(long userId);

    Pair<Boolean, String> setNewBooking(BookingDto booking);

    boolean checkBookingExists(BookingDto booking);

    boolean cancelBooking(long bookingId);
}
