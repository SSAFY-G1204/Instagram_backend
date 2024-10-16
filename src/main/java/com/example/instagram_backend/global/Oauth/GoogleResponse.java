package com.example.instagram_backend.global.Oauth;

import java.util.Map;

public class GoogleResponse implements OAuth2Response{
    //Map 타입의 객체 참조, String타입의 키와 Object 타입의 값으로 구성
    private final Map<String, Object> attribute;
    //생성자
    public GoogleResponse(Map<String,Object> attribute){
        this.attribute = attribute;
    }
    @Override
    public String getProvider() {
        return "Google";
    }
    @Override
    public String getProviderId() {
        return attribute.get("sub").toString();
    }
    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }
    @Override
    public String getName() {
        return attribute.get("name").toString();
    }
}
