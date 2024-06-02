package com.example.member.application.port.out;

import com.example.member.domain.Member;
import com.example.member.domain.vo.User;

public interface MemberOutputPort {
    Member save(Member member);
    Member findByNo(long no);
    Member findByUser(User user);
}
