package com.caplock.booking.controller.view;

import com.caplock.booking.entity.dto.flow.PaymentRequestDto;
import com.caplock.booking.entity.dto.flow.SeatSelectionDto;
import com.caplock.booking.service.flow.CheckoutFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui/checkout")
@RequiredArgsConstructor
public class CheckoutViewController {
    private final CheckoutFlowService checkoutFlowService;

    @GetMapping("/events")
    public String listEvents(Model model) {
        return null;
    }

    @GetMapping("/events/{eventId}")
    public String eventDetails(@PathVariable Long eventId, Model model) {
        return null;
    }

    @GetMapping("/events/{eventId}/availability")
    public String availability(@PathVariable Long eventId, Model model) {
        return null;
    }

    @GetMapping("/events/{eventId}/buy")
    public String buyForm(@PathVariable Long eventId, Model model) {
        return null;
    }

    @PostMapping("/events/{eventId}/confirm")
    public String confirmSelection(@PathVariable Long eventId, @ModelAttribute SeatSelectionDto selection) {
        return null;
    }

    @GetMapping("/payments/{bookingId}")
    public String paymentForm(@PathVariable Long bookingId, Model model) {
        return null;
    }

    @PostMapping("/payments/{bookingId}")
    public String submitPayment(@PathVariable Long bookingId, @ModelAttribute PaymentRequestDto payment) {
        return null;
    }

    @GetMapping("/bookings/{bookingId}/summary")
    public String ticketAndInvoice(@PathVariable Long bookingId, Model model) {
        return null;
    }
}
