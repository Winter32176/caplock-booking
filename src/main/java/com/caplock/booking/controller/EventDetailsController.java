package com.caplock.booking.controller;

import com.caplock.booking.controller.helper.FormShower;
import com.caplock.booking.entity.dto.EventDto;
import com.caplock.booking.service.IEventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.caplock.booking.controller.helper.FormShower.showForm;

@Controller
@RequestMapping("/events/details")
public class EventDetailsController {

    private final IEventService eventService;

    public EventDetailsController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/view/{id}")
    public String getDetails(@PathVariable long id, Model model) {
        model.addAttribute("event-details", eventService.getDetails(id));
        return "events/Details";
    }

    @PostMapping("/delete/{id}")
    public String deleteBooking(@PathVariable long id) {
        eventService.deleteEvent(id);
        return "redirect:/events/Events";
    }

    @GetMapping({"/event-form", "/event-form/{id}"})
    public String form(Model model, @PathVariable(required = false) Long id) {
        long safeId = (id == null) ? -1 : id;
        return FormShower.showForm(model, safeId, eventService::getEventById, EventDto.class);
    }

    @PutMapping("/update/{id}")
    public String putEvent(@ModelAttribute EventDto event, @PathVariable long id) {
        // get user id from jwt
        eventService.updateEvent(id, event);
        return "redirect:events/Events";
    }
}
