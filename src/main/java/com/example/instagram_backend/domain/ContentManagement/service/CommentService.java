package com.example.instagram_backend.domain.ContentManagement.service;

import com.example.instagram_backend.domain.ContentManagement.dao.Post;
import com.example.instagram_backend.domain.ContentManagement.dto.AddCommentRequestDto;
import com.example.instagram_backend.domain.ContentManagement.repository.CommentRepository;
import com.example.instagram_backend.domain.ContentManagement.repository.PostRepository;
import com.example.instagram_backend.domain.UserInfoManagement.dao.User;
import com.example.instagram_backend.domain.UserInfoManagement.repository.UserRepository;
import com.example.instagram_backend.domain.UserInteractions.dao.Comment;
import com.example.instagram_backend.domain.response.CustomException;
import com.example.instagram_backend.domain.response.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    public void addComment(Long postId,AddCommentRequestDto addCommentRequestDto) {
        if (postId == null) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }
        // 사용자 존재 여부 확인
        Optional<User> user = userRepository.findById(addCommentRequestDto.getUserId());
        if (!user.isPresent()) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
        // 게시물 존재 여부 확인
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        // 댓 저장
        Comment comment = Comment.builder()
                .post(post)
                .content(addCommentRequestDto.getContent())
                .rgtDate(String.valueOf(LocalDateTime.now()))
                .build();

        commentRepository.save(comment);
    }


}
