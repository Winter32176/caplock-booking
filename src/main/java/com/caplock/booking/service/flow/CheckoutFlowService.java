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

import java.util.List;

public interface CheckoutFlowService {
    List<EventDto> getAllEvents();

    EventDto getEvent(Long eventId);

    List<EventTicketConfigDto> getEventConfigs(Long eventId);

    SeatHoldResponseDto getFreeSeats(SeatAvailabilityRequestDto request);

    PaymentFormDto getBuyForm(Long eventId, Integer quantity);

    PaymentFormDto confirmSelection(SeatSelectionDto selection);

    PaymentStatusDto waitForPayment(Long bookingId);

    BookingDto postNewBooking(PaymentRequestDto payment);

    TicketInvoiceDto getTicketAndInvoice(Long bookingId);

    BookingDto getBookingById(Long bookingId);

    SeatHoldResponseDto holdSeats(SeatHoldRequestDto request);

    PaymentStatusDto checkPayment(Long bookingId);

    void notifySeatReservation(Long bookingId);

    TicketInvoiceDto createTicketAndInvoice(Long bookingId);
}
