package com.example.instagram_backend.domain.UserInfoManagement.controller;

import com.example.instagram_backend.domain.UserInfoManagement.dao.User;
import com.example.instagram_backend.domain.UserInfoManagement.dto.UserRelationDto;
import com.example.instagram_backend.domain.UserInfoManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    //키워드를 포함하는 유저 객체 찾기
    @GetMapping("/")
    public List<User> CheckForUsers(@RequestParam String keyword){
        return
                userService.findUsersByNickname(keyword);
    }

    @GetMapping("/login")
    @ResponseBody
    public String mainAPI(){
        return  "main route";
    }


}
