package com.project.myblog.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FindByEmailResponseDto {

    private String email;
    private String name;

    public FindByEmailResponseDto(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
