package com.caplock.booking.service;

import com.caplock.booking.entity.dto.BookingDetailsDto;
import com.caplock.booking.entity.dto.BookingDto;
import com.caplock.booking.entity.dto.BookingFormDto;
import com.caplock.booking.entity.dto.EventDetailsDto;
import org.javatuples.Pair;

import java.util.Collection;

public interface IBookingService {
    BookingFormDto getBookingFormById(String id, long userId);

    BookingDetailsDto getDetails(String id);

    BookingDto getBookingById(String id);

    Collection<BookingDto> getAllUserBookings(long userId);

    Pair<Boolean, String> setNewBooking(BookingFormDto booking);

    boolean cancelBooking(String bookingId);
}
