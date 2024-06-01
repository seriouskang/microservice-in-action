package com.example.member.application.service;

import com.example.member.application.port.in.AddMemberUsecase;
import com.example.member.application.port.out.MemberOutputPort;
import com.example.member.domain.Member;
import com.example.member.domain.vo.Email;
import com.example.member.domain.vo.User;
import com.example.member.domain.vo.Password;
import com.example.member.framework.httpadapter.dto.MemberInfoDTO;
import com.example.member.framework.httpadapter.dto.MemberOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddMemberService implements AddMemberUsecase {
    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberOutputDTO addMember(MemberInfoDTO memberInfoDTO) {
        User user = new User(memberInfoDTO.getId(), memberInfoDTO.getName());
        Password pwd = new Password(memberInfoDTO.getPassword());
        Email email = new Email(memberInfoDTO.getEmail());
        Member member = new Member(user, pwd, email);

        Member saved = memberOutputPort.save(member);
        return MemberOutputDTO.of(saved);
    }
}
