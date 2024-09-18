package com.example.instagram_backend.domain.UserInteractions.dao;

import com.example.instagram_backend.domain.ContentManagement.dao.Post;
import com.example.instagram_backend.domain.UserInfoManagement.dao.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본생성자
@AllArgsConstructor // 모든 필드를 초기화하는 생성자
@Builder // 빌더 패턴
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;


//    @ManyToOne
//    @Column(nullable = false)
//    private long userId;
}
