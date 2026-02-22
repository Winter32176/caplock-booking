package com.caplock.booking.controller.old;

import com.caplock.booking.entity.dto.TicketDto;
import com.caplock.booking.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/")
    public String findAll(Model model) {
        List<TicketDto> tickets = ticketService.getAll();

        model.addAttribute("tickets", tickets);

        return "users/tickets/list";
    }

    @ResponseBody
    @GetMapping("/{holderName}")
    public List<TicketDto> findByHolderName(@PathVariable String holderName) {
        return ticketService.findByHolderName(holderName);
    }

    @GetMapping("/create-form")
    public String getCreateTicketForm() {
        return "tickets/create-form";
    }

    @ResponseBody
    @PostMapping("/create")
    public TicketDto createTicket(@RequestBody TicketDto newTicket) {
        return ticketService.create(newTicket);
    }

    @GetMapping("/edit-form")
    public String getEditTicketForm() {
        return "tickets/edit-form";
    }

    @ResponseBody
    @PutMapping("/edit/{id}")
    public TicketDto editTicket(@PathVariable Long id, @RequestBody TicketDto updatedTicket) {
        return ticketService.update(id, updatedTicket);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.delete(id);
    }

}
