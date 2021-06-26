package com.project.myblog.service;

import com.project.myblog.domain.Member;
import com.project.myblog.domain.board.Board;
import com.project.myblog.domain.board.Post;
import com.project.myblog.domain.postDto.CreatePostRequestDto;
import com.project.myblog.domain.postDto.FindPostByTitleRequestDto;
import com.project.myblog.repository.MemberRepository;
import com.project.myblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

        Member member = memberRepository.findByEmail(requestDto.getAuthor())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 입니다."));

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
     * 게시글_ID_조회
     */
    public Post findById(Long id) {
        Optional<Post> postId = postRepository.findById(id);
        return postId.orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }

    /**
     * 게시글_조회
     */
    public Post findPostByTitle(FindPostByTitleRequestDto requestDto) {
        return postRepository.findPostByTitle(requestDto.toFindPost().getTitle())
                .orElseThrow(() -> new IllegalArgumentException("해당 제목의 게시글이 존재하지 않습니다."));
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
