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
    public void postRegister(PostRequestDto requestDto, UserDetailsImpl userDetails) {
        Posts posts=new Posts(requestDto,userDetails.getUser());
        postRepository.save(posts);
    }

    //게시글 수정
    @Transactional
    public void postUpdate(PostRequestDto requestDto, Long postId) {
        Posts posts=postRepository.findById(postId).orElseThrow(
                ()->new NullPointerException("수정할 게시글이 없습니다.")
        );
        posts.setTitle(requestDto.getTitle());
        posts.setContent(requestDto.getContent());
        posts.setImgUrl(requestDto.getImgUrl());
    }

    //게시글 삭제
    @Transactional
    public void deletePost(Long postId) {
        Posts posts=postRepository.findById(postId).orElseThrow(
                ()->new NullPointerException("삭제할 게시글이 없습니다.")
        );
        postRepository.deleteById(posts.getPostId());
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
        responseDto.setImaUrl(posts.getImgUrl());
        responseDto.setProfileUrl(posts.getUser().getProfileUrl());
        responseDto.setNickname(posts.getUser().getNickname());
        return  responseDto;

    }
}
