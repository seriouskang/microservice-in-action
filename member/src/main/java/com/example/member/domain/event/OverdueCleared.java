package com.example.member.domain.event;

import com.example.member.domain.vo.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OverdueCleared {
    private User user;
    private long point;
}
