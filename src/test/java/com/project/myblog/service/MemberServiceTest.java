package com.project.myblog.service;

import com.project.myblog.domain.Member;
import com.project.myblog.dto.CreateMemberRequestDto;
import com.project.myblog.dto.FindByEmailRequestDto;
import com.project.myblog.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void 회원가입() throws Exception {
        //given
        CreateMemberRequestDto requestDto = getCreateMemberRequestDto("spring@naver.com", "kang", 30);

        //when
        Long savedId = memberService.join(requestDto);

        //then
        Assertions.assertThat(savedId.equals(requestDto));
    }


    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        CreateMemberRequestDto requestDto1 = getCreateMemberRequestDto("spring@naver.com", "kang", 30);
        CreateMemberRequestDto requestDto2 = getCreateMemberRequestDto("spring@naver.com", "kang", 30);

        //when
        memberService.join(requestDto1);

        //then
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> memberService.join(requestDto2));
        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());
    }

    @Test
    public void 회원_전체_조회() throws Exception {
        //given
        CreateMemberRequestDto requestDto1 = getCreateMemberRequestDto("spring@naver.com", "kang", 30);
        CreateMemberRequestDto requestDto2 = getCreateMemberRequestDto("jpa@naver.com", "kim", 30);

        memberService.join(requestDto1);
        memberService.join(requestDto2);

        //when
        List<Member> members = memberService.findMembers();

        //then

        assertThat(members.size()).isEqualTo(2);
    }

    @Test
    public void 회원_이메일_조회() throws Exception {
        //given
        CreateMemberRequestDto member = getCreateMemberRequestDto("spring@naver.com", "kang", 30);
        memberService.join(member);

        //when
        Member findMember = memberService.findByEmail(new FindByEmailRequestDto());
        //then
        assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
    }
    
//    @Test
//    public void 회원_수정() throws Exception {
//        //given
//        CreateMemberRequestDto member = getCreateMemberRequestDto("spring@naver.com", "kang", 30);
//        Long memberId = memberService.join(member);
//
//        //when
//        memberService.updateMember(memberId, "jpa@naver.com");
//
//        //then
//        Assertions.assertThat().isEqualTo(member.getEmail()memberService.findByEmail("jpa@naver.com"));
//    }

    private CreateMemberRequestDto getCreateMemberRequestDto(String email, String username, int age) {
        CreateMemberRequestDto requestDto = new CreateMemberRequestDto();
        requestDto.setEmail(email);
        requestDto.setUsername(username);
        requestDto.setAge(age);
        return requestDto;
    }

}
