package com.caplock.booking.repository;

import com.caplock.booking.entity.dao.*;

import java.util.Collection;

public interface IBookingRepository {
    BookingDao getBookingById(String id);

    Collection<BookingDao> getAllUserBookings(long userId);

    boolean setNewBooking(BookingDao booking);

    boolean checkBookingExists(String bookingId, long eventId);

    boolean cancelBooking(String bookingId);

    String genBookingId();
}
