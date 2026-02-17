package com.caplock.booking.repository;

import com.caplock.booking.entity.StatusBookingEnum;
import com.caplock.booking.entity.StatusWaitListEnum;
import com.caplock.booking.entity.dao.BookingDao;
import com.caplock.booking.entity.dao.WaitListEntryDao;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Repository
public class WaitRepo implements IWaitListEntryRepository {
    private static long idCounter = 1;
    private final List<WaitListEntryDao> mockWaitList = new ArrayList<>(List.of(
            new WaitListEntryDao(4, StatusWaitListEnum.Pending, "1", LocalDateTime.now(), 2L, 100L)
    ));

    @Override
    public Collection<WaitListEntryDao> getAllWaitList() {
        return new ArrayList<>(mockWaitList);
    }

    @Override
    public Collection<WaitListEntryDao> getAllWaitListByUser(long userId) {
        return mockWaitList.stream()
                .filter(entry -> entry.getUserId() == userId)
                .toList();
    }

    @Override
    public boolean setWaitListToUser(long userId, WaitListEntryDao waitListEntryDao) {
        // Set the userId and specific waitlist fields
        waitListEntryDao.setUserId(userId);
        waitListEntryDao.setId(idCounter++); // Handle the long ID
        waitListEntryDao.setTimestamp(LocalDateTime.now());

        // Example logic for position: check how many are already waiting for this event
        long currentQueueSize = mockWaitList.stream()
                .filter(e -> e.getEventId() == waitListEntryDao.getEventId())
                .count();
        waitListEntryDao.setPositionInQ("POS-" + (currentQueueSize + 1));

        return mockWaitList.add(waitListEntryDao);
    }

    @Override
    public boolean moveToBooking(WaitListEntryDao waitListEntryDao, BookingDao bookingDao) {
        // Remove from waitlist using the long ID
        return mockWaitList.removeIf(entry -> entry.getId() == waitListEntryDao.getId());
    }
}
