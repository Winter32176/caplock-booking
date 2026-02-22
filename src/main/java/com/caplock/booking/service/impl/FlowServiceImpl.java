package com.caplock.booking.service.impl;

import com.caplock.booking.entity.StatusBookingEnum;
import com.caplock.booking.entity.TicketType;
import com.caplock.booking.entity.dto.*;
import com.caplock.booking.event.PaymentSucceededEvent;
import com.caplock.booking.service.*;
import com.caplock.booking.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.javatuples.Pair;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FlowServiceImpl implements FlowService {
    private final UserService userService;
    private final EventService eventService;
    private final InvoiceService invoiceService;
    private final PaymentService paymentService;
    private final TicketService ticketService;
    private final BookingItemService bookingItemService;
    private final BookingService bookingService;
    private final ObjectProvider<SeatReservationService> seatReservationServiceProvider;

    @Override
    public Pair<Boolean, String> processBookingFlow(Long userId,
                                                    EventDetailsDto eventDetails,
                                                    BookingDto bookingDto,
                                                    List<BookingItemDto> bookingItemDtos,
                                                    PaymentDto paymentDto) {
        // 1. Validate input data
        // 2. Check seat availability
        // 3. Create booking and booking items
        // 4. Process payment
        // 5. Update booking status based on payment result
        // 6. Return result

        // -----------Input validation logic-----------
/*        if (bookingDto.getUserId() == null || eventDetails.getEvent().getId() == null
                || eventDetails.getTicketConfig().isEmpty() || paymentDto.getAmount().compareTo(BigDecimal.ZERO) <= 0
                || bookingItemDtos.stream().anyMatch(x -> x.getTicketType() == null)
                || bookingItemDtos.stream().anyMatch(x -> x.getBookingId() == null)
                || !Objects.equals(bookingDto.getUserId(), userId) || !Objects.equals(bookingDto.getId(), paymentDto.getBookingId())) {
            return Pair.with(false, "Invalid input data");
        }*/
        // -----------Seat reservation logic-----------
        List<Pair<String, TicketType>> selectedSeats = eventDetails.getSelectedSeats();
        var result = seatReservationServiceProvider.getObject().assignSeatsTemp(eventDetails.getEvent().getId(), selectedSeats, bookingDto.getId());
        if (result.getValue0() == false) return result;

        // -----------Booking logic-----------
        bookingDto.setStatus(StatusBookingEnum.WaitingPayment);

        var bookResult = bookingService.create(bookingDto);
        bookingDto = bookResult.getValue0().orElseThrow();
        if (bookResult.getValue1() == false) {
            return Pair.with(false, "Booking creation failed: " + bookingService.create(bookingDto).getValue2());
        }

        //------------Payment processing logic-----------
/*
        if (paymentService.create(paymentDto) != null) {
            bookingDto.setStatus(StatusBookingEnum.Cancelled);
            bookingService.update(bookingDto.getId(), bookingDto);
            seatReservationServiceProvider
                    .getObject().clearReservationOfSeats(eventDetails.getEvent().getId(), bookingDto.getId()); // not implemented yet, but should be implemented in future
            return Pair.with(false, "Payment processing failed");
        }
*/

        // -----------Invoice generation logic-----------
        var uid = invoiceService.getNewInvoiceNumber();
        var user = userService.getById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        var invoiceDto = new InvoiceDto(null, bookingDto.getId(),
                paymentDto.getId(), uid, user.getName(),
                user.getEmailHash(), BigDecimal.ZERO, BigDecimal.ZERO,
                paymentDto.getAmount(), LocalDateTime.now(), null);
        var invoice=invoiceService.create(invoiceDto);
        if ( invoice== null) {
            return Pair.with(false, "Invoice creation failed");
        }
        invoiceService.generateInvoice(invoice.getId());


        bookingDto.setStatus(StatusBookingEnum.Processed);
        bookingService.update(bookingDto.getId(), bookingDto);

        return Pair.with(true, "Booking processed successfully");
    }
}
