package com.example.instagram_backend.global.Oauth;

public interface OAuth2Response {
    //제공자(구글인지/네이버인지)
    String getProvider();
    //제공자에서 발급해주는 아이디
    String getProviderId();
    //리소스 서버에서 응답받은 email
    String getEmail();
    //리소스 서버에서 응답받은 name
    String getName();
}
