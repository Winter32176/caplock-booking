package com.caplock.booking.service.flow;

import com.caplock.booking.entity.dto.BookingDto;
import com.caplock.booking.entity.dto.EventDto;
import com.caplock.booking.entity.dto.EventTicketConfigDto;
import com.caplock.booking.entity.dto.flow.PaymentFormDto;
import com.caplock.booking.entity.dto.flow.PaymentRequestDto;
import com.caplock.booking.entity.dto.flow.PaymentStatusDto;
import com.caplock.booking.entity.dto.flow.SeatAvailabilityRequestDto;
import com.caplock.booking.entity.dto.flow.SeatHoldRequestDto;
import com.caplock.booking.entity.dto.flow.SeatHoldResponseDto;
import com.caplock.booking.entity.dto.flow.SeatSelectionDto;
import com.caplock.booking.entity.dto.flow.TicketInvoiceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutFlowServiceImpl implements CheckoutFlowService {
    @Override
    public List<EventDto> getAllEvents() {
        return null;
    }

    @Override
    public EventDto getEvent(Long eventId) {
        return null;
    }

    @Override
    public List<EventTicketConfigDto> getEventConfigs(Long eventId) {
        return null;
    }

    @Override
    public SeatHoldResponseDto getFreeSeats(SeatAvailabilityRequestDto request) {
        return null;
    }

    @Override
    public PaymentFormDto getBuyForm(Long eventId, Integer quantity) {
        return null;
    }

    @Override
    public PaymentFormDto confirmSelection(SeatSelectionDto selection) {
        return null;
    }

    @Override
    public PaymentStatusDto waitForPayment(Long bookingId) {
        return null;
    }

    @Override
    public BookingDto postNewBooking(PaymentRequestDto payment) {
        return null;
    }

    @Override
    public TicketInvoiceDto getTicketAndInvoice(Long bookingId) {
        return null;
    }

    @Override
    public BookingDto getBookingById(Long bookingId) {
        return null;
    }

    @Override
    public SeatHoldResponseDto holdSeats(SeatHoldRequestDto request) {
        return null;
    }

    @Override
    public PaymentStatusDto checkPayment(Long bookingId) {
        return null;
    }

    @Override
    public void notifySeatReservation(Long bookingId) {
    }

    @Override
    public TicketInvoiceDto createTicketAndInvoice(Long bookingId) {
        return null;
    }
}
