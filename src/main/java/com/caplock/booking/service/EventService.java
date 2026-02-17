package com.caplock.booking.service;

import com.caplock.booking.entity.StatusEventEnum;
import com.caplock.booking.entity.dao.EventDao;
import com.caplock.booking.entity.dto.EventDetailsDto;
import com.caplock.booking.entity.dto.EventDto;
import com.caplock.booking.repository.IEventRepository;
import com.caplock.booking.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class EventService implements IEventService {

    @Autowired
    private IEventRepository eventRepo;

    @Override
    public EventDetailsDto getDetails(long id) {
        return null;
    }

    @Override
    public Collection<EventDto> getAllEvents() {
        return eventRepo.getAllEvents().stream()
                .map(dao -> (EventDto) Mapper.mapDaoToDto(dao, EventDto.class))
                .toList();
    }

    @Override
    public EventDto getEventByTitle(String title) {
        return null;
    }

    @Override
    public Collection<EventDto> getEventByCategory(String cat) {
        return List.of();
    }

    @Override
    public EventDto getEventById(long id) {
        var dao = eventRepo.getEventById(id);
        return (EventDto) Mapper.mapDaoToDto(dao, EventDto.class);
    }

    @Override
    public Collection<EventDto> getEventsByDate(LocalDate date) {
        return List.of();
    }

    @Override
    public Collection<EventDto> getEventsByLocation(String location) {
        return List.of();
    }

    @Override
    public boolean setEvent(EventDto dto) {
        return eventRepo.setEvent((EventDao) Mapper.mapDtoToDao(dto, EventDao.class));
    }

    @Override
    public boolean updateEvent(long id, EventDto dto) {
        // Ensure ID from path is set on the object
        dto.setId(id);
        return eventRepo.updateEvent(id,(EventDao) Mapper.mapDtoToDao(dto, EventDao.class));
    }

    @Override
    public boolean deleteEvent(long id) {
        return eventRepo.deleteEvent(id);
    }

    @Override
    public boolean deleteByTitle(String title) {
        return false;
    }

    // Example of other filter methods
    @Override
    public Collection<EventDto> getEventsByStatus(StatusEventEnum status) {
        return eventRepo.getEventsByStatus(status).stream()
                .map(dao -> (EventDto) Mapper.mapDaoToDto(dao, EventDto.class))
                .toList();
    }
}