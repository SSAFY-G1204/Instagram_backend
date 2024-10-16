//package com.example.instagram_backend;
//
//
//import com.example.instagram_backend.domain.ContentManagement.controller.PostController;
//import com.example.instagram_backend.domain.ContentManagement.dto.FeedResponseDto;
//import com.example.instagram_backend.domain.ContentManagement.dto.MediaResponseDto;
//import com.example.instagram_backend.domain.ContentManagement.service.PostService;
//import com.example.instagram_backend.domain.UserInfoManagement.controller.UserController;
//import com.example.instagram_backend.domain.UserInfoManagement.dao.Profile;
//import com.example.instagram_backend.domain.UserInteractions.dto.CommentResponseDto;
//import com.example.instagram_backend.domain.UserInteractions.dto.LikeResponseDto;
//import jakarta.persistence.*;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@AutoConfigureMockMvc
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@RunWith(SpringJUnit4ClassRunner.class)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
//    }
//
//    @Test
//    public void contextLoads(){}
//
//    @Test
//    public void testsearchUser() throws Exception {
//
//        Long userId;
//        String email;
//        String password;
//        String name;
//        String provider;
//        String nickname;
//        Profile profile;
//
//        long userId = 1L;
//        // 테스트용 데이터 생성
//        MediaResponseDto mediaDto = new MediaResponseDto("media-url");
//        LikeResponseDto likeDto = new LikeResponseDto(1L, 2L);
//        // 날짜 포맷 설정
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        Date rgtDate = new Date();
//        CommentResponseDto commentDto = new CommentResponseDto(1L, "This is a comment", rgtDate);
//        FeedResponseDto dto = new FeedResponseDto(
//                1L,
//                "Post content",
//                "2024-09-06T12:00:00",
//                userId,
//                Collections.singletonList(mediaDto),
//                Collections.singletonList(likeDto),
//                Collections.singletonList(commentDto)
//        );
//
//        List<FeedResponseDto> feedList = Arrays.asList(dto);
//        // 서비스가 특정 userId에 대해 반환할 데이터를 모의합니다.
//        //given(postService.findFolollowingPostsByUserId(userId)).willReturn(feedList);
//        PostService mock = org.mockito.Mockito.mock(PostService.class);
//        when(mock.findFolollowingPostsByUserId(userId)).thenReturn(feedList);
//
//        // GET 요청을 수행하고 응답을 검증합니다.
//        mockMvc.perform(MockMvcRequestBuilders.get("/home/feeds")
//                        .param("userId", String.valueOf(userId))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(print())
//                .andReturn();
//
//    }
//}