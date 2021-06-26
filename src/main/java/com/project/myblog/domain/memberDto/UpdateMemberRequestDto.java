package com.project.myblog.domain.memberDto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UpdateMemberRequestDto {

    @NotEmpty
    @Column(unique = true)
    private String email;
}
