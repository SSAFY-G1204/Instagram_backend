package com.example.instagram_backend.global.Oauth;

import com.example.instagram_backend.domain.UserInfoManagement.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
    private final UserDTO userDTO;
    public CustomOAuth2User(UserDTO userDTO){
        this.userDTO = userDTO;
    }
    //인증서버?로부터 받은 리소스를 가져오는 방법?
    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }
    //role값을 return해준다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userDTO.getRole();
            }
        });
        return collection;
    }
    @Override
    public String getName() {
        return userDTO.getName();
    }
    /*
    public String getUsername(){
        return userDTO.getUsername();
    }*/
}
