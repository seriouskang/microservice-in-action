package com.example.rental.framework.httpadapter.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClearOverdueInfoDTO {
    private final long userId;
    private final String userName;
    private final long point;
}
