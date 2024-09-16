package com.example.instagram_backend.domain.UserInfoManagement.controller;

import com.example.instagram_backend.domain.UserInfoManagement.dao.User;
import com.example.instagram_backend.domain.UserInfoManagement.dto.GetUserProfileResponseDto;
import com.example.instagram_backend.domain.UserInfoManagement.dto.UpdateUserProfileRequestDto;
import com.example.instagram_backend.domain.UserInfoManagement.service.UserService;
import com.example.instagram_backend.global.util.ApiUtils.ResponseDto;
import com.example.instagram_backend.global.util.ApiUtils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    //키워드를 포함하는 유저 객체 찾기
    @GetMapping("/")
    public List<User> CheckForUsers(@RequestParam String keyword){
        return userService.findUsersByNickname(keyword);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDto<GetUserProfileResponseDto>> getUserProfile(@PathVariable("userId") Long userId) {
        GetUserProfileResponseDto responseDTO = userService.getUserProfile(userId);
        return ResponseEntity.ok(ResponseUtil.SUCCESS("",responseDTO));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ResponseDto<?>> updateUserProfile(@PathVariable("userId") Long userId, @RequestBody UpdateUserProfileRequestDto requestDTO){
        userService.updateUserProfile(userId, requestDTO);
        return ResponseEntity.ok(ResponseUtil.SUCCESS("프로필 업데이트를 완료하였습니다.", null));
    }

}
