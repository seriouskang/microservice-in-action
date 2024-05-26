package com.example.member.application.port.in;

import com.example.member.framework.httpadapter.dto.MemberOutputDTO;

public interface InquiryMemberUsecase {
    MemberOutputDTO findByMemberNo(long no);
}
