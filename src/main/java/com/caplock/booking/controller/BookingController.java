package com.caplock.booking.controller;

import com.caplock.booking.entity.dto.BookingDto;
import com.caplock.booking.service.IBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    private final IBookingService bookingService;

    public BookingController(IBookingService iBookingService) {
        this.bookingService = iBookingService;
    }

    @GetMapping("/submit-edit-form/{id}")
    public String getBookingForm(Model model, @PathVariable long id) {
        if (bookingService.getBookingById(id) != null) {
            model.addAttribute("Form-name", "Edit");
            model.addAttribute("Form-button", "Update");
        } else {
            model.addAttribute("Form-name", "Add");
            model.addAttribute("Form-button", "Place booking");
        }
        return "bookings/Booking-form";
    }

    @GetMapping("details/{id}")
    public String getBookingDetails(@PathVariable long id, Model model) {
        // check user rights
        model.addAttribute("booking-details", bookingService.getBookingById(id));
        return "bookings/Booking-details";
    }


    @GetMapping
    public String getAllBookings(Model model) {
        // get user id from jwt
        long userId = -1;
        model.addAttribute("booking-list", bookingService.getAllUserBookings(userId));
        return "bookings/Bookings";
    }

    @PostMapping("/submit-form")
    public String setBooking(@ModelAttribute("booking") BookingDto booking) {
        var result = bookingService.setNewBooking(booking);

        boolean isSuccess = result.getValue0();
        String message = result.getValue1();

        if (!isSuccess && message.contains("Booking full")) {
            int userId = (int) -1;
            return "redirect:/waitList/waitList/submit-edit-form/" + userId;
        } else {
            return "redirect:/bookings/Bookings";
        }
    }

}
