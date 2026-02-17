package com.caplock.booking.service;

import com.caplock.booking.entity.dao.BookingDao;
import com.caplock.booking.entity.dao.WaitListEntryDao;
import com.caplock.booking.entity.dto.WaitListEntryDto;
import com.caplock.booking.repository.IWaitListEntryRepository;
import com.caplock.booking.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class WaitListEntryService implements IWaitListEntryService {

    @Autowired
    private IWaitListEntryRepository waitRepo;

    @Override
    public Collection<WaitListEntryDto> getAllWaitList() {
        return waitRepo.getAllWaitList().stream()
                .map(dao -> (WaitListEntryDto) Mapper.mapDaoToDto(dao,WaitListEntryDto.class))
                .toList();
    }

    @Override
    public Collection<WaitListEntryDto> getAllWaitListByUser(long userId) {
        return waitRepo.getAllWaitListByUser(userId).stream()
                .map(dao -> (WaitListEntryDto) Mapper.mapDaoToDto(dao,WaitListEntryDto.class ))
                .toList();
    }

    @Override
    public boolean setWaitListToUser(long userId, WaitListEntryDto waitListEntryDto) {
        WaitListEntryDao dao = (WaitListEntryDao) Mapper.mapDtoToDao(waitListEntryDto,WaitListEntryDao.class);
        return waitRepo.setWaitListToUser(userId, dao);
    }

    @Override
    public boolean moveToBooking(WaitListEntryDto waitDto, BookingDao bookingDao) {
        WaitListEntryDao waitDao = (WaitListEntryDao) Mapper.mapDtoToDao(waitDto,WaitListEntryDao.class);
        return waitRepo.moveToBooking(waitDao, bookingDao);
    }
}
