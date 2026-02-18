package com.caplock.booking.service;

import com.caplock.booking.entity.dao.BookingDao;
import com.caplock.booking.entity.dto.BookingDetailsDto;
import com.caplock.booking.entity.dto.BookingDto;
import com.caplock.booking.entity.dto.EventDto;
import com.caplock.booking.repository.IBookingRepository;
import com.caplock.booking.util.Mapper;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private IBookingRepository bookingRepo;
    @Autowired
    private IEventService eventService;
    @Autowired
    private ISeatReservationService seatReservationService;

    @Override
    public BookingDetailsDto getDetails(String id) {
        BookingDao bookingDao = bookingRepo.getBookingById(id);
        if (bookingDao == null) return null;

        // Map bookingDao -> BookingDto (flat -> flat)
        BookingDto bookingDto = Mapper.combine(BookingDto.class, bookingDao);

        // Fetch/map event separately using eventId from booking
        var eventDao = eventService.getEventById(bookingDao.getEventId());
        var eventDto = (eventDao == null) ? null : Mapper.combine(EventDto.class, eventDao);

        return new BookingDetailsDto(bookingDto, eventDto);
    }

    @Override
    public BookingDto getBookingById(String id) {
        var dao = bookingRepo.getBookingById(id);
        return Mapper.combine(BookingDto.class, dao, eventService.getEventById(dao.getEventId()));
    }

    @Override
    public Collection<BookingDto> getAllUserBookings(long userId) {
        var list = bookingRepo.getAllUserBookings(userId).stream()
                .map(dao -> Mapper.combine(BookingDto.class, dao, eventService.getEventById(dao.getEventId())))
                .toList();
        return list;
    }

    @Override
    public Pair<Boolean, String> setNewBooking(BookingDto bookingDto) {
        bookingDto.setId(bookingRepo.genBookingId());
        var val = seatReservationService.assignSeats(bookingDto.getId(), bookingDto.getEventId(), bookingDto.getSeats());
        if (!val.getValue0()) return val;
        BookingDao dao = Mapper.splitOne(bookingDto, BookingDao.class);
        boolean success = bookingRepo.setNewBooking(dao);
        return Pair.with(success, success ? "Success" : "Error");
    }

    @Override
    public boolean cancelBooking(String bookingId) {
        var b= bookingRepo.getBookingById(bookingId);
        var val = seatReservationService.clearReservationOfSeats(b.getId(), b.getEventId());
        if (!val) return val;
        return bookingRepo.cancelBooking(bookingId);
    }
}
