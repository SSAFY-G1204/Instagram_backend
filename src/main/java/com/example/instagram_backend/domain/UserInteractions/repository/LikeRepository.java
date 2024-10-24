package com.example.instagram_backend.domain.UserInteractions.repository;

import com.example.instagram_backend.domain.UserInteractions.dao.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}
