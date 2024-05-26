package com.example.member.application.port.in;

import com.example.member.domain.vo.IdName;
import com.example.member.framework.httpadapter.dto.MemberOutputDTO;

public interface SavePointUsecase {
    MemberOutputDTO savePoint(IdName idName, Long point);
}
