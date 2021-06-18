package com.project.myblog.api;

import com.project.myblog.domain.Member;
import com.project.myblog.dto.*;
import com.project.myblog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/members")
    public List<FindMembersDto> memberList() {
        List<Member> memberList = memberService.findMembers();
        List<FindMembersDto> result = memberList.stream()
                .map(member -> new FindMembersDto(member))
                .collect(Collectors.toList());
        return result;
    }

    @PostMapping("/api/members")
    public CreateMemberResponseDto joinMember(@RequestBody @Valid CreateMemberRequestDto requestDto) {
        Member member = Member.builder()
                .email(requestDto.getEmail())
                .username(requestDto.getUsername())
                .age(requestDto.getAge())
                .build();
        Long id = memberService.join(member);

        return new CreateMemberResponseDto(id, member.getEmail(), LocalDateTime.now());
    }

//    @PutMapping("/api/members/{id}")
//    public UpdateMemberResponseDto updateMember(
//            @PathVariable("id") Long id,
//            @RequestBody @Valid UpdateMemberRequestDto requestDto) {
//
//        memberService.updateMember(id, requestDto.getEmail());
//        Member findMember = memberService.findOne(id);
//        return new UpdateMemberResponseDto(findMember.getId(), findMember.getEmail(), LocalDateTime.now());
//    }
    @PatchMapping("/api/members/{id}")
    public UpdateMemberResponseDto updateMember(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequestDto requestDto) {

        memberService.updateMember(id, requestDto.getEmail());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponseDto(findMember.getId(), findMember.getEmail(), LocalDateTime.now());
    }
}
