package com.caplock.booking.controller;

import com.caplock.booking.service.IBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookings/details")
public class BookingDetailsController {
    private final IBookingService bookingService;

    public BookingDetailsController(IBookingService iBookingService) {
        this.bookingService = iBookingService;
    }

    @GetMapping("/view/{id}")
    public String getDetails(@PathVariable long id, Model model) {
        model.addAttribute("booking-details", bookingService.getDetails(id));
        return "bookings/Details";
    }


    @PostMapping("/delete/{id}")
    public String deleteBooking(@PathVariable long id) {
        if (bookingService.cancelBooking(id))
            return "redirect:/bookings/Bookings";
        else {
            //show error
            return "redirect:/bookings/Details";
        }
    }
}
