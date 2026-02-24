package com.caplock.booking.controller;

import com.caplock.booking.entity.dto.*;
import com.caplock.booking.exception.SeatNotAssignedException;
import com.caplock.booking.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("api/v1/handle-booking")
@RequiredArgsConstructor
public class FlowController {

    private final FlowService flowService;

    @PostMapping
    public String handleBooking(@ModelAttribute BookingRequestDTO request) {
        log.info("Started processing booking with id: {}", request.getEventId());

        try {
            flowService.handleBooking(request);
        } catch (SeatNotAssignedException e) {
            log.error(e.getMessage());
        }

        return "redirect:/ui/bookings";
    }

}
