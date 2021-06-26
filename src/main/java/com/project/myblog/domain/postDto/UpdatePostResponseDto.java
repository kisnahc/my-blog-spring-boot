package com.project.myblog.domain.postDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdatePostResponseDto {

    private Long postId;
    private String changedTitle;
    private String changedContent;
    private LocalDateTime lastModifiedDate;

    public UpdatePostResponseDto(Long postId, String changedTitle, String changedContent, LocalDateTime lastModifiedDate) {
        this.postId = postId;
        this.changedTitle = changedTitle;
        this.changedContent = changedContent;
        this.lastModifiedDate = lastModifiedDate;
    }
}
