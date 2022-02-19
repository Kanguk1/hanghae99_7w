package clone.colley.service;


import clone.colley.dto.Request.PostRequestDto;
import clone.colley.dto.Response.PostResponseDto;
import clone.colley.model.Posts;
import clone.colley.repository.PostRepository;
import clone.colley.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    //게시글 작성
    @Transactional
    public Long postRegister(PostRequestDto requestDto, UserDetailsImpl userDetails) {
        Posts posts=new Posts(requestDto,userDetails.getUser());
        return postRepository.save(posts).getPostId();
    }

    //게시글 수정
    @Transactional
    public Boolean postUpdate(PostRequestDto requestDto, Long postId,UserDetailsImpl userDetails) {
        Posts posts=postRepository.findById(postId).orElseThrow(
                ()->new NullPointerException("수정할 게시글이 없습니다.")
        );
        if(posts.getUser().equals(userDetails.getUser())){
            posts.setTitle(requestDto.getTitle());
            posts.setContent(requestDto.getContent());
            posts.setImgUrl(requestDto.getImgUrl());
            return true;
        } else{
            return false;
        }

    }

    //게시글 삭제
    @Transactional
    public Boolean deletePost(Long postId,UserDetailsImpl userDetails) {
        Posts posts=postRepository.findById(postId).orElseThrow(
                ()->new NullPointerException("삭제할 게시글이 없습니다.")
        );
        if(posts.getUser().equals(userDetails.getUser())){
            postRepository.deleteById(posts.getPostId());
            return true;
        } else{
            return false;
        }

    }

    //게시글 상세조회
    @Transactional
    public PostResponseDto postDetail(Long postId) {
        Posts posts=postRepository.findById(postId).orElseThrow(
                ()->new NullPointerException("게시글이 없습니다.")
        );
        PostResponseDto responseDto=new PostResponseDto();
        responseDto.setPostId(posts.getPostId());
        responseDto.setTitle(posts.getTitle());
        responseDto.setContent(posts.getContent());
        responseDto.setPostDate(posts.getCreatedAt());
        responseDto.setLikeCnt(posts.getLikeCnt());
        responseDto.setCommentCnt(posts.getCommentCnt());
        responseDto.setImaUrl(posts.getImgUrl());
        responseDto.setUsername(posts.getUser().getUsername());
        responseDto.setProfileUrl(posts.getUser().getProfileUrl());
        responseDto.setNickname(posts.getUser().getNickname());
        return  responseDto;

    }
}
