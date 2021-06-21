package com.project.myblog.domain;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty(message = "이메일을 입력해 주세요.")
    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "이름을 입력해 주세요.")
    private String username;
    private int age;

    public void lastModifiedEmail(String email) {
        this.email = email;
    }

    @Builder
    public Member(String email, String username, int age) {
        this.email = email;
        this.username = username;
        this.age = age;
    }
}

