package com.project.myblog.domain.memberDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateMemberResponseDto {

    private Long userId;
    private String changedEmail;
    private LocalDateTime lastModifiedDate;

    public UpdateMemberResponseDto(Long userId, String changedEmail, LocalDateTime lastModifiedDate) {
        this.userId = userId;
        this.changedEmail = changedEmail;
        this.lastModifiedDate = lastModifiedDate;
    }
}
