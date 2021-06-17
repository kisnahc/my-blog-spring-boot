package com.project.myblog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMemberResponseDto {

    private Long userId;
    private String email;

    public CreateMemberResponseDto(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
