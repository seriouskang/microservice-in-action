package com.example.member.domain;

import com.example.member.domain.vo.*;

import java.util.List;

public class Member {
    private Long id;
    private IdName idName;
    private Password password;
    private Email email;
    private List<Authority> authorities;
    private Point point;
}
