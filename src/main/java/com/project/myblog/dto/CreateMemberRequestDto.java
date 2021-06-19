package com.project.myblog.dto;


import com.project.myblog.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateMemberRequestDto {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String username;

    private int age;


    public Member toMember() {
        return Member.builder()
                .email(getEmail())
                .username(getUsername())
                .age(getAge())
                .build();
    }

}
