package com.project.myblog.service;

import com.project.myblog.domain.board.Post;
import com.project.myblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    /**
     * 게시글_등록
     */
    @Transactional
    public Long createPost(Post post) {
        postRepository.save(post);
        return post.getId();
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
    public void deletePost(Post post) {
        postRepository.delete(post);
    }
}
