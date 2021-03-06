package com.project.myblog.api;

import com.project.myblog.domain.Member;
import com.project.myblog.domain.memberDto.*;
import com.project.myblog.service.MemberService;
import lombok.Data;
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

    @GetMapping("/api/members/")
    public Result memberList() {
        List<Member> memberList = memberService.findMembers();
        List<FindMembersResponseDto> collect = memberList.stream()
                .map(member -> new FindMembersResponseDto(member.getEmail(), member.getUsername()))
                .collect(Collectors.toList());

        return new Result( collect.size(), collect);
    }


    @PostMapping("/api/members/")
    public CreateMemberResponseDto joinMember(@RequestBody @Valid CreateMemberRequestDto requestDto) {
        Long id = memberService.join(requestDto);
        return new CreateMemberResponseDto(id, requestDto.getEmail(), LocalDateTime.now());
    }

    @GetMapping("/api/members/email")
    public FindByEmailResponseDto findByEmail(@RequestBody @Valid FindByEmailRequestDto requestDto) {
        Member findMember = memberService.findByEmail(requestDto);
        return new FindByEmailResponseDto(requestDto.getEmail(), findMember.getUsername());
    }

    @PatchMapping("/api/members/{id}")
    public UpdateMemberResponseDto updateMember(@PathVariable("id") Long id,
                                                @RequestBody @Valid UpdateMemberRequestDto requestDto) {
        memberService.updateMember(id, requestDto.getEmail());
        Member member = memberService.findById(id);
        return new UpdateMemberResponseDto(member.getId(), requestDto.getEmail(), LocalDateTime.now());
    }

    @DeleteMapping("/api/members/{id}")
    public String deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return "delete successful";
    }

    @Data
    public class Result<T> {

        private int count;
        private T data;

        public Result( int count, T data) {
            this.count = count;
            this.data = data;
        }
    }
}
