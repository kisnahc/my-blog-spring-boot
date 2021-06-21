package com.project.myblog.dto;

import com.project.myblog.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class FindByEmailRequestDto {

    @NotEmpty
    @Email
    private String email;

    public Member toFindByEmail() {
        return Member.builder()
                .email(getEmail())
                .build();
    }



}
