package com.project.myblog.domain.postDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UpdatePostRequestDto {

    @NotEmpty
    private String title;

    private String content;

}
