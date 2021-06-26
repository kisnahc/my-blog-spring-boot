package com.project.myblog.service;

import com.project.myblog.domain.Member;
import com.project.myblog.domain.memberDto.CreateMemberRequestDto;
import com.project.myblog.domain.memberDto.FindByEmailRequestDto;
import com.project.myblog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(CreateMemberRequestDto requestDto) {
        validateDuplicateMember(requestDto.getEmail());
        Member savedMember = memberRepository.save(requestDto.toMember());
        return savedMember.getId();
    }


    /**
     * 회원_전체_조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원_이메일_조회
     */
    public Member findByEmail(FindByEmailRequestDto requestDto) {
        return memberRepository.findByEmail(requestDto.toFindByEmail().getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    /**
     * 회원_ID_조회
     */
    public Member findById(Long id) {
        Optional<Member> memberId = memberRepository.findById(id);
        return memberId.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    /**
     * 회원_수정
     */
    @Transactional
    public void updateMember(Long id, String email) {
        Member memberId = memberRepository.findById(id).get();
        validateDuplicateMember(email);
        memberId.lastModifiedEmail(email);
    }

    /**
     * 회원_삭제
     */
    @Transactional
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }


    private void validateDuplicateMember(String email) {
        Optional<Member> findMembers = memberRepository.findByEmail(email);
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
