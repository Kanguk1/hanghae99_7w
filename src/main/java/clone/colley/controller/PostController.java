package clone.colley.controller;


import clone.colley.dto.Request.PostRequestDto;
import clone.colley.dto.Response.PostResponseDto;
import clone.colley.security.UserDetailsImpl;
import clone.colley.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    //게시글 상세 조회
    @GetMapping("/post/{postId}")
    public PostResponseDto postDetail(@PathVariable Long postId){
        return postService.postDetail(postId);
    }

    //게시글 작성
    @PostMapping("/post")
    public Boolean postRegister(@RequestBody PostRequestDto requestDto,
                             @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.postRegister(requestDto,userDetails);
        return true;
    }


    //게시글 수정
    @PutMapping("/post/{postId}")
    public Boolean postUpdate(@PathVariable Long postId,
                           @RequestBody PostRequestDto requestDto){
       postService.postUpdate(requestDto,postId);
       return true;
    }

    //게시글 삭제
    @DeleteMapping("/post/{postId}")
    public Boolean postDelete(@PathVariable Long postId){
        postService.deletePost(postId);
        return true;
    }
}
