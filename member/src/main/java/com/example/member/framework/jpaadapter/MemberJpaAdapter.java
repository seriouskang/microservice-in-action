package com.example.member.framework.jpaadapter;

import com.example.member.application.port.out.MemberOutputPort;
import com.example.member.domain.Member;
import com.example.member.domain.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberJpaAdapter implements MemberOutputPort {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(member);
    }

    @Override
    public Member findByNo(long no) {
        return memberJpaRepository.findById(no).get();
    }

    @Override
    public Member findByUser(User user) {
        return memberJpaRepository.findByUser(user).get();
    }
}
