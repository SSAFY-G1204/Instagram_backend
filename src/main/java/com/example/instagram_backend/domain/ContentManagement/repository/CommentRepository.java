package com.example.instagram_backend.domain.ContentManagement.repository;

import com.example.instagram_backend.domain.UserInteractions.dao.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
