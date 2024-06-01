package com.example.book.domain.model.event;

import lombok.*;

import javax.persistence.Embeddable;

@EqualsAndHashCode
@Embeddable
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Item {
    private Long itemId;
    private String itemTitle;
}
