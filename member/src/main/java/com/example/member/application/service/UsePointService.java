package com.example.member.application.service;

import com.example.member.application.port.in.UsePointUsecase;
import com.example.member.application.port.out.MemberOutputPort;
import com.example.member.domain.Member;
import com.example.member.domain.vo.IdName;
import com.example.member.framework.httpadapter.dto.MemberOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsePointService implements UsePointUsecase {
    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberOutputDTO usePoint(IdName idName, long point) {
        Member found = memberOutputPort.findByIdName(idName);
        found.usePoint(point);

        return MemberOutputDTO.of(found);
    }
}
