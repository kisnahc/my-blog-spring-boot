package com.project.myblog.service;

import com.project.myblog.domain.Member;
import com.project.myblog.domain.board.Post;
import com.project.myblog.dto.CreatePostRequestDto;
import com.project.myblog.repository.MemberRepository;
import com.project.myblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    /**
     * 게시글_등록
     */
    @Transactional
    public Long createPost(CreatePostRequestDto requestDto) {

        Member member = memberRepository.findByEmail(requestDto.getAuthorEmail())
                .orElseThrow(() -> new IllegalArgumentException(""));

        Post savedPost = postRepository.save(requestDto.toPost(member));
        return savedPost.getId();
    }

    /**
     * 게시글_전체_조회
     */
    public List<Post> PostList() {
        return postRepository.findAll();
    }

    /**
     * 게시글_단건_조회
     */
    public Post findPost(Long postId) {
        return postRepository.findById(postId).get();
    }

    /**
     * 게시글_수정
     */
    @Transactional
    public void updatePost(Long postId, String title, String content) {
        Post post = postRepository.findById(postId).get();
        post.lastModifiedPost(title, content);
    }

    /**
     * 게시글_삭제
     */
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
