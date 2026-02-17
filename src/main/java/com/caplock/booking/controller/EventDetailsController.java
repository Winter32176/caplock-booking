package com.caplock.booking.controller;

import com.caplock.booking.entity.dto.EventDto;
import com.caplock.booking.service.IEventService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/events/details")
public class EventDetailsController {

    private final IEventService eventService;

    public EventDetailsController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("{id}")
    public String getDetails(@PathVariable long id) {

        return "events/Details";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBooking(@PathVariable long id) {
        eventService.deleteEvent(id);
        return "redirect:/events/Events";
    }

    @PutMapping("/update/{id}")
    public String putEvent(@ModelAttribute EventDto event, @PathVariable long id) {
        // get user id from jwt
        eventService.updateEvent(id, event);
        return "redirect:events/Events";
    }
}
