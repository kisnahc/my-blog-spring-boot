package com.project.myblog.domain.postDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FindPostResponseDto {

    private String title;
    private String author;
    private LocalDateTime createDate;

    public FindPostResponseDto(String title, String author, LocalDateTime createDate) {
        this.title = title;
        this.author = author;
        this.createDate = createDate;

    }
}
