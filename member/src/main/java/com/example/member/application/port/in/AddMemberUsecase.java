package com.example.member.application.port.in;

import com.example.member.framework.httpadapter.dto.MemberInfoDTO;
import com.example.member.framework.httpadapter.dto.MemberOutputDTO;

public interface AddMemberUsecase {
    MemberOutputDTO addMember(MemberInfoDTO memberInfoDTO);
}
