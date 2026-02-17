package com.caplock.booking.controller;

import com.caplock.booking.service.IBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookings/details")
public class BookingDetailsController {
    private final IBookingService bookingService;

    public BookingDetailsController(IBookingService iBookingService) {
        this.bookingService = iBookingService;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBooking(@PathVariable long id) {
        bookingService.cancelBooking(id);
        return "redirect:/bookings/Bookings";
    }
}
