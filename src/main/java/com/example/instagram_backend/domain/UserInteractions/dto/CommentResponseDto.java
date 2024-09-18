package com.example.instagram_backend.domain.UserInteractions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private Long commentId;
    private String content;
//    private Date rgtDate;
    private String rgtDate;
}
