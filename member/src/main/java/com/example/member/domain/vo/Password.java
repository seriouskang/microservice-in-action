package com.example.member.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {
    private String currentPwd;
    private String pastPwd;

    public Password(String currentPwd) {
        this.currentPwd = currentPwd;
    }
}
