package com.caplock.booking.controller;

import com.caplock.booking.service.IBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookings/details")
public class BookingDetailsController {
    private final IBookingService bookingService;

    public BookingDetailsController(IBookingService iBookingService) {
        this.bookingService = iBookingService;
    }

    @GetMapping("/{id}")
    public String getDetails(@PathVariable long id) {

        return "bookings/Details";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteBooking(@PathVariable long id) {
        bookingService.cancelBooking(id);
        return "redirect:/bookings/Bookings";
    }
}
