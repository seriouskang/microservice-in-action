package com.example.member.framework.httpadapter;

import com.example.member.application.port.in.AddMemberUsecase;
import com.example.member.application.port.in.InquiryMemberUsecase;
import com.example.member.framework.httpadapter.dto.MemberInfoDTO;
import com.example.member.framework.httpadapter.dto.MemberOutputDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@Api(tags = {"member controller"})
@RequiredArgsConstructor
public class MemberController {
    private final AddMemberUsecase addMemberUsecase;
    private final InquiryMemberUsecase inquiryMemberUsecase;

    @PostMapping
    public ResponseEntity<MemberOutputDTO> addMember(@RequestBody MemberInfoDTO memberInfoDTO) {
        MemberOutputDTO added = addMemberUsecase.addMember(memberInfoDTO);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }

    @GetMapping("/{no}")
    public ResponseEntity<MemberOutputDTO> findByNo(@PathVariable long no) {
        MemberOutputDTO found = inquiryMemberUsecase.findByMemberNo(no);
        return found != null ? ResponseEntity.ok(found) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
