package com.example.rental.framework.httpadapter.dto;

import com.example.rental.domain.model.vo.SubmitItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class SubmitItemOutputDTO {
    private final long id;
    private final String title;
    private final LocalDate submitDate;

    public static SubmitItemOutputDTO of(SubmitItem submitItem) {
        return new SubmitItemOutputDTO(
                submitItem.id(),
                submitItem.title(),
                submitItem.submitDate()
        );
    }
}
