package com.project.myblog.repository;

import com.project.myblog.domain.board.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
