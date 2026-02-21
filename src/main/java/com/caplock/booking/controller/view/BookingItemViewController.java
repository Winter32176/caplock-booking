package com.caplock.booking.controller.view;

import com.caplock.booking.entity.dto.BookingItemDto;
import com.caplock.booking.service.BookingItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui/booking-items")
@RequiredArgsConstructor
public class BookingItemViewController {
    private final BookingItemService bookingItemService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", bookingItemService.getAll());
        return "ui/booking-items/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("item", new BookingItemDto());
        model.addAttribute("formAction", "/ui/booking-items");
        return "ui/booking-items/form";
    }

    @PostMapping
    public String create(@ModelAttribute("item") BookingItemDto dto) {
        bookingItemService.create(dto);
        return "redirect:/ui/booking-items";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        BookingItemDto dto = bookingItemService.getById(id).orElseThrow();
        model.addAttribute("item", dto);
        model.addAttribute("formAction", "/ui/booking-items/" + id);
        return "ui/booking-items/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("item") BookingItemDto dto) {
        bookingItemService.update(id, dto);
        return "redirect:/ui/booking-items";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        bookingItemService.delete(id);
        return "redirect:/ui/booking-items";
    }
}
