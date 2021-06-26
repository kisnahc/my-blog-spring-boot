package com.project.myblog.domain.memberDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateMemberResponseDto {

    private Long userId;
    private String email;
    private LocalDateTime createDate;

    public CreateMemberResponseDto(Long userId, String email, LocalDateTime createDate) {
        this.userId = userId;
        this.email = email;
        this.createDate = createDate;
    }
}
