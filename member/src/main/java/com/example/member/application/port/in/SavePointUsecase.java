package com.example.member.application.port.in;

import com.example.member.domain.vo.User;
import com.example.member.framework.httpadapter.dto.MemberOutputDTO;

public interface SavePointUsecase {
    MemberOutputDTO savePoint(User user, Long point);
}
