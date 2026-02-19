// src/main/java/com/caplock/booking/controller/helper/FormShower.java
package com.caplock.booking.controller.helper;

import com.caplock.booking.entity.dto.BookingFormDto;
import com.caplock.booking.entity.dto.BookingDto;
import com.caplock.booking.entity.dto.EventDto;
import org.springframework.ui.Model;

import java.lang.reflect.InvocationTargetException;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FormShower {

    public static <T> String showForm(
            Model model,
            long id,
            Function<Long, T> getByIdLong,
            Class<T> dtoClass
    ) {
        boolean editing = false;

        if (id > 0 && getByIdLong != null) {
            editing = getByIdLong.apply(id) != null;
        }


        // Decide view + model attribute name based on dto class
        String attrName;
        String view;

        if (dtoClass == EventDto.class) {
            attrName = "event";
            view = "events/eventForm";
        } else if (dtoClass == BookingDto.class) {
            attrName = "booking";
            view = "bookings/bookingForm";
        } else {
            attrName = "waitList";
            view = "waitList/waitListForm";
        }

        try {
            T dto;

            if (editing) {

                dto = getByIdLong.apply(id);

                if (dto == null) {
                    dto = dtoClass.getDeclaredConstructor().newInstance();
                }
            } else {
                dto = dtoClass.getDeclaredConstructor().newInstance();
            }

            model.addAttribute(attrName, dto);
            model.addAttribute("formName", editing ? "Edit" : "Add");
            model.addAttribute("formButton", editing ? "Update" : "Place " + attrName);

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException("Failed to prepare form DTO for " + dtoClass.getSimpleName(), e);
        }

        return view;
    }
}
