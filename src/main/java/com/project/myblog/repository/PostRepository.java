package com.project.myblog.repository;

import com.project.myblog.domain.board.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findPostByTitle(String title);

}
