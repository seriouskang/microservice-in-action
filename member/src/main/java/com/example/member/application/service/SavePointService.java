package com.example.member.application.service;

import com.example.member.application.port.in.SavePointUsecase;
import com.example.member.application.port.out.MemberOutputPort;
import com.example.member.domain.Member;
import com.example.member.domain.vo.User;
import com.example.member.framework.httpadapter.dto.MemberOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SavePointService implements SavePointUsecase {
    private final MemberOutputPort memberOutputPort;

    @Override
    public MemberOutputDTO savePoint(User user, Long point) {
        Member found = memberOutputPort.findByUser(user);
        found.addPoint(point);

        return MemberOutputDTO.of(found);
    }
}
