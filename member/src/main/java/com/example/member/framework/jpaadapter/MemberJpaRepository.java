package com.example.member.framework.jpaadapter;

import com.example.member.domain.Member;
import com.example.member.domain.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUser(User user);
}
