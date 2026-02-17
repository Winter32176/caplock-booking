package com.caplock.booking.controller;

import com.caplock.booking.entity.dto.EventDto;
import com.caplock.booking.entity.dto.WaitListEntryDto;
import com.caplock.booking.entity.object.WaitListEntry;
import com.caplock.booking.service.IWaitListEntryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("waitlist/")
public class WaitListEntryController {
    private final IWaitListEntryService waitListEntryService;

    public WaitListEntryController(IWaitListEntryService WaitListEntryService) {
        this.waitListEntryService = WaitListEntryService;
    }

    @GetMapping("/submit-edit-form/{id}")
    public String getEventsForm(Model model, @PathVariable long id) {
        model.addAttribute("Form-name", "Add");
        model.addAttribute("Form-button", "Place waitList");

        return "waitLists/WaitList-form";
    }

    @GetMapping("{id}")
    public String getAllWaitLists(Model model, @PathVariable long userId) {
        // get user id from jwt
        model.addAttribute("waitList-list", waitListEntryService.getAllWaitListByUser(userId));
        return "waitLists/WaitLists";
    }

    @PostMapping("/submit-form")
    public String setEvent(@ModelAttribute WaitListEntryDto w) {
        //get user using jwt
        long userId = -1;
        waitListEntryService.setWaitListToUser(userId, w);
        return "redirect:bookings/Bookings";
    }

}
