package com.caplock.booking.controller;

import com.caplock.booking.entity.dto.BookingDto;
import com.caplock.booking.entity.dto.EventDto;
import com.caplock.booking.service.IEventService;
import com.caplock.booking.service.IWaitListEntryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("events/")
public class EventController {
    private final IEventService eventService;

    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/submit-edit-form/{id}")
    public String getEventsForm(Model model, @PathVariable long id) {
        if (eventService.getEventById(id) != null) {
            model.addAttribute("Form-name", "Edit");
            model.addAttribute("Form-button", "Update");
        } else {
            model.addAttribute("Form-name", "Add");
            model.addAttribute("Form-button", "Place event");
        }
        return "events/Event-form";
    }

    @GetMapping("details/{id}")
    public String getEventDetails(@PathVariable long id, Model model) {
        // check user rights
        model.addAttribute("event-details", eventService.getEventById(id));
        return "events/Event-details";
    }


    @GetMapping
    public String getAllEvents(Model model){
        model.addAttribute("event-list", eventService.getAllEvents());
        return "events/Events";
    }


    @PostMapping("/submit-form")
    public String setEvent(@ModelAttribute EventDto event) {
        eventService.setEvent(event);
        return "redirect:events/Events";
    }
}
