package com.example.member.framework.httpadapter.dto;

import com.example.member.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberOutputDTO {
    private Long id;
    private String name;
    private String password;
    private String email;
    private long point;

    public static MemberOutputDTO of(Member member) {
        return new MemberOutputDTO(
                member.getNo(),
                member.getUser().getName(),
                member.getPwd().getCurrentPwd(),
                member.getEmail().getAddress(),
                member.getPoint().getPoint()
        );
    }
}
