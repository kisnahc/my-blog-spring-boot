package com.project.myblog.dto;

import com.project.myblog.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindMembersDto {

    private String email;
    private int age;

    public FindMembersDto(Member member) {
        email = member.getEmail();
        age = member.getAge();
    }
}
