package com.sy.drawsyncserver.user.dto;

import com.sy.drawsyncserver.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    private String id;

    public static UserResponse from(User user) {
        return new UserResponse(user.getId());
    }
}
