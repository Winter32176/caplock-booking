package com.caplock.booking.service.impl;

import com.caplock.booking.entity.dto.EventDto;
import com.caplock.booking.entity.dao.EventEntity;
import com.caplock.booking.repository.EventRepository;
import com.caplock.booking.service.EventService;
import com.caplock.booking.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public Triplet<Optional<EventDto>, Boolean, String> create(EventDto dto) {
        if (false) return Triplet.with(Optional.empty(), false, "Event not found");
        if (false) return Triplet.with(Optional.empty(), false, "Booking of event already exists");
        EventEntity saved = eventRepository.save(Mapper.toEntity(dto));
        return Triplet.with(Optional.of(Mapper.toDto(saved)), true, "All good");
    }

    @Override
    public Optional<EventDto> getById(Long id) {
        return eventRepository.findById(id).map(Mapper::toDto);
    }

    @Override
    public List<EventDto> getAll() {
        return eventRepository.findAll().stream().map(Mapper::toDto).toList();
    }

    @Override
    public EventDto update(Long id, EventDto dto) {
        EventEntity entity = Mapper.toEntity(dto);
        entity.setId(id);
        return Mapper.toDto(eventRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}
