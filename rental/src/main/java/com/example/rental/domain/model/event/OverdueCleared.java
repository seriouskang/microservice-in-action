package com.example.rental.domain.model.event;

import com.example.rental.domain.model.vo.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OverdueCleared {
    private User user;
    private long point;
}
