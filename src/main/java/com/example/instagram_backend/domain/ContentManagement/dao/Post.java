package com.example.instagram_backend.domain.ContentManagement.dao;



import com.example.instagram_backend.domain.UserInfoManagement.dao.User;
import com.example.instagram_backend.domain.UserInteractions.dao.Comment;
import com.example.instagram_backend.domain.UserInteractions.dao.Like;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED) //기본생성자
@AllArgsConstructor // 모든 필드를 초기화하는 생성자
@Builder // 빌더 패턴
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId ;

    //내용
    @Column(length = 100)
    private String content;

    //좋아요 수
    @Column(length = 100, nullable = false)
    private String liking;

    //등록일
    @Column(nullable = false)
    private String rgtDate;

    //유저키
    @OneToMany(mappedBy ="post")
    @Column(nullable = false)
    private List<Like> likes = new ArrayList<Like>();

    @OneToMany(mappedBy ="post")
    @Column(nullable = false)
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToMany(mappedBy = "post")
    @Column(nullable=false)
    private List<Media> medias = new ArrayList<Media>();


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}