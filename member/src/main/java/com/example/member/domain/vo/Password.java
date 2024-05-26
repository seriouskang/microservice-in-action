package com.example.member.domain.vo;

import lombok.Getter;

@Getter
public class Password {
    private String currentPwd;
    private String pastPwd;

    public Password(String currentPwd) {
        this.currentPwd = currentPwd;
    }
}
