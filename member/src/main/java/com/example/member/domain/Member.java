package com.example.member.domain;

import com.example.member.domain.vo.*;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private Long id;
    private IdName idName;
    private Password pwd;
    private Email email;
    private List<Authority> authorities;
    private Point point;

    private Member(IdName idName, Password pwd, Email email) {
        this.idName = idName;
        this.pwd = pwd;
        this.email = email;

        this.point = new Point();
        this.authorities = new ArrayList<>();
        addAuthority(Authority.of(UserRole.USER));
    }

    public void addAuthority(Authority auth) {
        this.authorities.add(auth);
    }

    public void addPoint(Long point) {
        this.point.add(point);
    }

    public void usePoint(Long point) {
        this.point.deduct(point);
    }

    // @TODO
    public Member login(IdName idName, Password pwd) {
        return this;
    }

    // @TODO
    public void logout(IdName idName) {
    }
}
