package com.example.member.domain.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Authority {
    private UserRole userRole;

    public static Authority of(UserRole userRole) {
        return new Authority(userRole);
    }
}
