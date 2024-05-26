package com.example.member.application.service;

import com.example.member.application.port.in.InquiryMemberUsecase;
import com.example.member.application.port.out.MemberOutputPort;
import com.example.member.domain.Member;
import com.example.member.framework.httpadapter.dto.MemberOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryMemberService implements InquiryMemberUsecase {
    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberOutputDTO findByMemberNo(long no) {
        Member found = memberOutputPort.findByNo(no);
        return MemberOutputDTO.of(found);
    }
}
