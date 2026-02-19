package com.caplock.booking.util;

import com.caplock.booking.entity.object.User;
import com.caplock.booking.entity.dto.UserDTO;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
