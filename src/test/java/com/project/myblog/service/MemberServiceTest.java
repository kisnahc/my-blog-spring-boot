package com.project.myblog.service;

import com.project.myblog.domain.Member;
import com.project.myblog.dto.CreateMemberRequestDto;
import com.project.myblog.repository.MemberRepository;
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
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @Test
    public void 회원가입() throws Exception {
        //given
        CreateMemberRequestDto requestDto = getCreateMemberRequestDto("spring@naver.com", "kang", 30);

        //when
        Long savedId = memberService.join(requestDto);

        //then
        assertThat(requestDto.get).isEqualTo(savedId);
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

//    @Test
//    @Rollback(value = false)
//    public void 회원_전체_조회() throws Exception {
//        //given
//        CreateMemberRequestDto requestDto1 = getCreateMemberRequestDto("spring@naver.com", "kang", 30);
//        CreateMemberRequestDto requestDto2 = getCreateMemberRequestDto("spring@naver.com", "kang", 30);
//
//        memberService.join(requestDto1);
//        memberService.join(requestDto2);
//
//        //when
//        List<CreateMemberRequestDto> members = memberService.findMembers();
//
//        //then
//
//        assertThat(members.size()).isEqualTo(2);
//    }

//    @Test
//    public void 회원_조회() throws Exception {
//        //given
//        Member memberA = Member.builder()
//                .email("spring@naver.com")
//                .username("memberA")
//                .age(30)
//                .build();
//
//        memberService.join(memberA);
//
//        //when
//        Member findMember = memberService.findOne(memberA.getId());
//
//        //then
//        assertThat(findMember.getId()).isEqualTo(memberA.getId());
//    }

    private CreateMemberRequestDto getCreateMemberRequestDto(String email, String username, int age) {
        CreateMemberRequestDto requestDto = new CreateMemberRequestDto();
        requestDto.setEmail(email);
        requestDto.setUsername(username);
        requestDto.setAge(age);
        return requestDto;
    }

}
