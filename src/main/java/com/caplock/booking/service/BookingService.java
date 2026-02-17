package com.caplock.booking.service;

import com.caplock.booking.entity.dao.BookingDao;
import com.caplock.booking.entity.dto.BookingDto;
import com.caplock.booking.repository.IBookingRepository;
import com.caplock.booking.repository.IEventRepository;
import com.caplock.booking.util.Mapper;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private IBookingRepository bookingRepo;
    @Autowired
    private IEventRepository eventRepo;

    @Override
    public BookingDto getBookingById(long id) {
        var dao = bookingRepo.getBookingById(id);
        return (BookingDto) Mapper.mapDaoToDto(dao, BookingDto.class);
    }

    @Override
    public Collection<BookingDto> getAllUserBookings(long userId) {
        return bookingRepo.getAllUserBookings(userId).stream()
                .map(dao -> (BookingDto) Mapper.mapDaoToDto(dao, BookingDto.class))
                .toList();
    }

    @Override
    public Pair<Boolean, String> setNewBooking(BookingDto bookingDto) {
        var event = eventRepo.getEventById(bookingDto.getEventId());

        if (event == null) return Pair.with(false, "Event not found");

        // The logic that triggers your Controller redirect
        if (event.getBookedSeats() + bookingDto.getQty() > event.getCapacity()) {
            return Pair.with(false, "Booking full");
        }

        BookingDao dao = (BookingDao) Mapper.mapDtoToDao(bookingDto, BookingDao.class);
        boolean success = bookingRepo.setNewBooking(dao);
        return Pair.with(success, success ? "Success" : "Error");
    }

    @Override
    public boolean checkBookingExists(BookingDto booking) {
        return bookingRepo.checkBookingExists((BookingDao) Mapper.mapDtoToDao(booking, BookingDao.class));
    }

    @Override
    public boolean cancelBooking(long bookingId) {
        return bookingRepo.cancelBooking(bookingId);
    }
}
