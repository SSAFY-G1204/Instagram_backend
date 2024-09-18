package com.example.instagram_backend.domain.ContentManagement.service;


import com.example.instagram_backend.domain.ContentManagement.dao.Media;
import com.example.instagram_backend.domain.ContentManagement.dao.Post;
import com.example.instagram_backend.domain.ContentManagement.dto.*;
import com.example.instagram_backend.domain.ContentManagement.repository.LikeRepository;
import com.example.instagram_backend.domain.ContentManagement.repository.PostRepository;
import com.example.instagram_backend.domain.SocialRelations.dao.Relation;
import com.example.instagram_backend.domain.SocialRelations.repository.RelationRepository;
import com.example.instagram_backend.domain.UserInfoManagement.dao.User;
import com.example.instagram_backend.domain.UserInfoManagement.repository.UserRepository;
import com.example.instagram_backend.domain.UserInteractions.dao.Comment;
import com.example.instagram_backend.domain.UserInteractions.dao.Like;
import com.example.instagram_backend.domain.UserInteractions.dto.CommentResponseDto;
import com.example.instagram_backend.domain.UserInteractions.dto.LikeResponseDto;
import com.example.instagram_backend.domain.response.CustomException;
import com.example.instagram_backend.domain.response.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private RelationRepository relationRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeRepository likeRepository;

    public List<Post> findByUserId(Long userId) {
        //System.out.print(postRepository.findByUserUserId(userId).toString());
        return postRepository.findByUserUserId(userId);
    }

    public void addPost(AddPostRequestDto postRequestDto) {
        if(postRequestDto.getUserId() == null){
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        Optional<User> user = userRepository.findByUserId(postRequestDto.getUserId());
        if(!user.isPresent()){
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
        postRepository.save(Post.builder()
                .content(postRequestDto.getContent())
                .user(user.get())
                .liking("0")
                .rgtDate(String.valueOf(LocalDateTime.now()))
                .build());
    }

    public void updatePost(UpdatePostRequestDto postRequestDto) {
        if(postRequestDto.getUserId() == null || postRequestDto.getPostId() == null){
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        Optional<User> user = userRepository.findByUserId(postRequestDto.getUserId());
        if(!user.isPresent()){
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
        Optional<Post> post = postRepository.findById(postRequestDto.getPostId());
        if(!post.isPresent()){
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
        if(post.get().getUser().getUserId() != user.get().getUserId()){
            throw new CustomException(ErrorCode.FORBIDDEN);
        }

        postRepository.save(Post.builder()
                .postId(postRequestDto.getPostId())
                .content(postRequestDto.getContent())
                .user(post.get().getUser())
                .liking(post.get().getLiking())
                .rgtDate(String.valueOf(post.get().getRgtDate()))
                .build());
    }

    public List<FeedResponseDto> findFolollowingPostsByUserId(Long userId){

        // 관계 테이블에서 내가 파리미터로 준 userId를 follower로 두고 있는 관계들을 찾는다.
        List<Relation> relations = relationRepository.findRelationByFollowerId(userId);
        List<Post> tgtpost = new ArrayList<>();

        List<FeedResponseDto> feedResponseDtos = new ArrayList<>();

        for( int idx = 0 ; idx<relations.size(); idx++){
            tgtpost = findByUserId(relations.get(idx).getUsers().getUserId());
        }

        if(!tgtpost.isEmpty()){
            for (int idx = 0; idx< tgtpost.size(); idx++){

                List<Like> likes =  tgtpost.get(idx).getLikes();

                List<Media> medias =  tgtpost.get(idx).getMedias();

                List<Comment> comments = tgtpost.get(idx).getComments();

                List<LikeResponseDto> likeResponseDtos = new ArrayList<>();
                List<MediaResponseDto> mediaReponseDtos = new ArrayList<>();
                List<CommentResponseDto> commentResponseDtos = new ArrayList<>();


                for(int i=0; i<likes.size(); i++){
                    Like like = likes.get(i);
                    LikeResponseDto likeResponseDto = new LikeResponseDto(like.getLikeId(),like.getUser().getUserId());
                    likeResponseDtos.add(likeResponseDto);

                }

                for(int i=0; i<medias.size(); i++){
                    MediaResponseDto mediaReponseDto = new MediaResponseDto(medias.get(i).getImageSrc());
                    mediaReponseDtos.add(mediaReponseDto);
                }

                for(int i=0; i<comments.size(); i++){
                    Comment cmt = comments.get(i);
                    CommentResponseDto commentResponseDto = new CommentResponseDto(cmt.getCommentId(),cmt.getContent(),cmt.getRgtDate());
                    commentResponseDtos.add(commentResponseDto);
                }


                FeedResponseDto feedResponseDto = new FeedResponseDto(tgtpost.get(idx).getPostId(),
                        tgtpost.get(idx).getContent(),
                        tgtpost.get(idx).getRgtDate(),
                        tgtpost.get(idx).getUser().getUserId(),
                        mediaReponseDtos,likeResponseDtos,commentResponseDtos);


                feedResponseDtos.add(feedResponseDto);
            }
        }

        return feedResponseDtos;

    }

    public void addLike(AddLikeRequestDto likeRequestDto) {
        if(likeRequestDto.getPostId() == null){
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }
        // 게시글 존재 여부 확인
        Post post = postRepository.findById(likeRequestDto.getPostId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
        // 사용자 존재 여부 확인
        User user = userRepository.findById(likeRequestDto.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        // 좋아요 중복 체크
        if (likeRepository.existsByUserAndPost(user, post)) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        // 새로운 좋아요 저장
        Like like = Like.builder()
                .user(user)
                .post(post)
                .build();

        likeRepository.save(like);
    }

}

