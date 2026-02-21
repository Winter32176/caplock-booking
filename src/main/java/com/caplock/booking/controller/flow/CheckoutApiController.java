package com.caplock.booking.controller.flow;

import com.caplock.booking.entity.dto.BookingDto;
import com.caplock.booking.entity.dto.EventDto;
import com.caplock.booking.entity.dto.EventTicketConfigDto;
import com.caplock.booking.entity.dto.flow.PaymentRequestDto;
import com.caplock.booking.entity.dto.flow.PaymentStatusDto;
import com.caplock.booking.entity.dto.flow.SeatAvailabilityRequestDto;
import com.caplock.booking.entity.dto.flow.SeatHoldRequestDto;
import com.caplock.booking.entity.dto.flow.SeatHoldResponseDto;
import com.caplock.booking.entity.dto.flow.TicketInvoiceDto;
import com.caplock.booking.service.flow.CheckoutFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckoutApiController {
    private final CheckoutFlowService checkoutFlowService;

    @GetMapping("/events")
    public ResponseEntity<List<EventDto>> getAllEvents() {
        return null;
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<EventDto> getEvent(@PathVariable Long eventId) {
        return null;
    }

    @GetMapping("/events/{eventId}/configs")
    public ResponseEntity<List<EventTicketConfigDto>> getEventConfigs(@PathVariable Long eventId) {
        return null;
    }

    @PostMapping("/events/{eventId}/availability")
    public ResponseEntity<SeatHoldResponseDto> getFreeSeats(
            @PathVariable Long eventId,
            @RequestBody SeatAvailabilityRequestDto request
    ) {
        return null;
    }

    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long bookingId) {
        return null;
    }

    @PostMapping("/bookings/{bookingId}/hold-seats")
    public ResponseEntity<SeatHoldResponseDto> holdSeats(
            @PathVariable Long bookingId,
            @RequestBody SeatHoldRequestDto request
    ) {
        return null;
    }

    @GetMapping("/bookings/{bookingId}/wait-payment")
    public ResponseEntity<PaymentStatusDto> waitForPayment(@PathVariable Long bookingId) {
        return null;
    }

    @PostMapping("/bookings/{bookingId}/payment/check")
    public ResponseEntity<PaymentStatusDto> checkPayment(@PathVariable Long bookingId) {
        return null;
    }

    @PostMapping("/bookings/{bookingId}/payment/finalize")
    public ResponseEntity<TicketInvoiceDto> finalizeBooking(@PathVariable Long bookingId) {
        return null;
    }

    @PostMapping("/bookings/{bookingId}/payment")
    public ResponseEntity<BookingDto> postNewBooking(
            @PathVariable Long bookingId,
            @RequestBody PaymentRequestDto payment
    ) {
        return null;
    }
}
