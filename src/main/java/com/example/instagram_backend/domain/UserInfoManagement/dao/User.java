package com.example.instagram_backend.domain.UserInfoManagement.dao;


import com.example.instagram_backend.domain.ContentManagement.dao.Post;
import com.example.instagram_backend.domain.SocialRelations.dao.Relation;
import com.example.instagram_backend.domain.UserInteractions.dao.Chat;
import com.example.instagram_backend.domain.UserInteractions.dao.Like;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED) //기본생성자
@AllArgsConstructor // 모든 필드를 초기화하는 생성자
@Builder // 빌더 패턴
@Table(name = "users")
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private String userName;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String provider;

    @Column(nullable = true)
    private String nickname;

    @Column(nullable = false )
    private String role;

    //    @OneToMany(mappedBy="users", fetch=FetchType.LAZY)
//    private List<Relation> relations = new ArrayList<>();
    //일대일
    @OneToOne(mappedBy="users", fetch=FetchType.LAZY)
    private Profile profile;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

//    //users에 의해 매핑 되었다.
//    @OneToMany(mappedBy ="user", fetch=FetchType.LAZY)
//    private List<Like> likes = new ArrayList<Like>();

//    @OneToMany(mappedBy ="user", fetch=FetchType.LAZY)
//    private List<Chat> chats = new ArrayList<Chat>();

//    @OneToMany(mappedBy ="user", fetch=FetchType.LAZY)
//    private List<Post> posts = new ArrayList<Post>();

    // 프로필을 빌더에서 제외하고 싶은 경우

    public User update(String nickname){
        this.nickname = nickname;
        return this;
    }

}
