package com.project.myblog.api;

import com.project.myblog.domain.Member;
import com.project.myblog.dto.CreateMemberRequestDto;
import com.project.myblog.dto.CreateMemberResponseDto;
import com.project.myblog.dto.FindMembersDto;
import com.project.myblog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
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

        return new CreateMemberResponseDto(id, member.getEmail());
    }

}
