package com.example.member.application.port.in;

import com.example.member.domain.vo.IdName;
import com.example.member.framework.httpadapter.dto.MemberOutputDTO;

public interface UsePointUsecase {
    MemberOutputDTO usePoint(IdName idName, long point);
}
