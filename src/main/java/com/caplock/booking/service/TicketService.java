package com.caplock.booking.service;

import com.caplock.booking.dto.Response;
import com.caplock.booking.dto.TicketDTO;

import java.util.List;

public interface TicketService {

    Response<List<TicketDTO>> findByHolderName(String holderName);

}
