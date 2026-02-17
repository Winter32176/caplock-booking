package com.caplock.booking.repository;

import com.caplock.booking.entity.StatusBookingEnum;
import com.caplock.booking.entity.dao.BookingDao;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class BookingRepo implements IBookingRepository{
    private final List<BookingDao> mockBookings = new ArrayList<>(List.of(
            new BookingDao("bk-9901", StatusBookingEnum.Processed, 2, LocalDateTime.now().minusDays(1), null, 1L, 100L),
            new BookingDao("bk-9902", StatusBookingEnum.Fulfilled, 1, LocalDateTime.now().minusDays(2), null, 3L, 100L)
    ));
    @Override
    public BookingDao getBookingById(long id) {
        return mockBookings.stream()
                .filter(b -> b.getId().equals(String.valueOf(id)))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<BookingDao> getAllUserBookings(long userId) {
        return mockBookings.stream()
                .filter(b -> b.getUserId() == userId)
                .toList();
    }

    @Override
    public boolean setNewBooking(BookingDao booking) {
        if (booking.getId() == null) {
            booking.setId("BK-" + System.currentTimeMillis());
        }
        return mockBookings.add(booking);
    }

    @Override
    public boolean checkBookingExists(BookingDao booking) {
        return mockBookings.stream()
                .anyMatch(b -> b.getUserId() == booking.getUserId() &&
                        b.getEventId() == booking.getEventId());
    }

    @Override
    public boolean cancelBooking(long bookingId) {
        return mockBookings.removeIf(b -> b.getId().equals(String.valueOf(bookingId)));
    }
}
