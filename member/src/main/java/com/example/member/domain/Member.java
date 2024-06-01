package com.example.member.domain;

import com.example.member.domain.vo.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long no;
    @Embedded
    private User user;
    @Embedded
    private Password pwd;
    @Embedded
    private Email email;
    @ElementCollection
    private List<Authority> authorities;
    @Embedded
    private Point point;

    public Member(User user, Password pwd, Email email) {
        this.user = user;
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
    public Member login(User user, Password pwd) {
        return this;
    }

    // @TODO
    public void logout(User user) {
    }
}
