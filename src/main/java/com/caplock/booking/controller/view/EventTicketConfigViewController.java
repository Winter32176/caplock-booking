package com.caplock.booking.controller.view;

import com.caplock.booking.entity.dto.EventTicketConfigDto;
import com.caplock.booking.service.EventTicketConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui/event-ticket-configs")
@RequiredArgsConstructor
public class EventTicketConfigViewController {
    private final EventTicketConfigService eventTicketConfigService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", eventTicketConfigService.getAll());
        return "ui/event-ticket-configs/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("item", new EventTicketConfigDto());
        model.addAttribute("formAction", "/ui/event-ticket-configs");
        return "ui/event-ticket-configs/form";
    }

    @PostMapping
    public String create(@ModelAttribute("item") EventTicketConfigDto dto) {
        eventTicketConfigService.create(dto);
        return "redirect:/ui/event-ticket-configs";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        EventTicketConfigDto dto = eventTicketConfigService.getById(id).orElseThrow();
        model.addAttribute("item", dto);
        model.addAttribute("formAction", "/ui/event-ticket-configs/" + id);
        return "ui/event-ticket-configs/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("item") EventTicketConfigDto dto) {
        eventTicketConfigService.update(id, dto);
        return "redirect:/ui/event-ticket-configs";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        eventTicketConfigService.delete(id);
        return "redirect:/ui/event-ticket-configs";
    }
}
