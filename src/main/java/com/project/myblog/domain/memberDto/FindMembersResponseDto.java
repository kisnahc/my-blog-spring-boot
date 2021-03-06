package com.project.myblog.domain.memberDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindMembersResponseDto {

    private String email;
    private String name;

    public FindMembersResponseDto(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
