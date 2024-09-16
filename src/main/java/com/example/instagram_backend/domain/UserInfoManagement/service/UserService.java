package com.example.instagram_backend.domain.UserInfoManagement.service;


import com.example.instagram_backend.domain.UserInfoManagement.dao.Profile;
import com.example.instagram_backend.domain.UserInfoManagement.dao.User;
import com.example.instagram_backend.domain.UserInfoManagement.dto.GetUserProfileResponseDto;
import com.example.instagram_backend.domain.UserInfoManagement.dto.UpdateUserProfileRequestDto;
import com.example.instagram_backend.domain.UserInfoManagement.dto.UpdateUserProfileResponseDto;
import com.example.instagram_backend.domain.UserInfoManagement.repository.ProfileRepository;
import com.example.instagram_backend.domain.UserInfoManagement.repository.UserRepository;
import com.example.instagram_backend.domain.response.CustomException;
import com.example.instagram_backend.domain.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    public List<User> findUsersByNickname(String keyword) {
        return userRepository.findUsersByNickname(keyword);
    }

    public GetUserProfileResponseDto getUserProfile(Long userId){
        Profile profile = profileRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.PROFILE_NOT_FOUND)
        );

        return GetUserProfileResponseDto.builder()
                .userId(profile.getUsers().getUserId())
                .profileImg(profile.getProfileImg())
                .profileText(profile.getProfileText())
                .followings(profile.getFollowings())
                .followers(profile.getFollowers())
                .build();
    }

    @Transactional
    public UpdateUserProfileResponseDto updateUserProfile(Long userId, UpdateUserProfileRequestDto requestDTO){
        Profile profile = profileRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.PROFILE_NOT_FOUND)
        );

        profile.updateProfile(requestDTO.getProfileImg(), requestDTO.getProfileText());

        return null;
    }


}




