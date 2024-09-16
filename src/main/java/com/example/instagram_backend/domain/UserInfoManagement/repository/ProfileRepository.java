package com.example.instagram_backend.domain.UserInfoManagement.repository;

import com.example.instagram_backend.domain.UserInfoManagement.dao.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
