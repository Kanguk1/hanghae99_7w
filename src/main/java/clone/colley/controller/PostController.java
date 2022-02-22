package clone.colley.controller;


import clone.colley.dto.Request.PostRequestDto;
import clone.colley.dto.Response.PostResponseDto;
import clone.colley.security.UserDetailsImpl;
import clone.colley.service.PostService;
import clone.colley.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;
    private final S3Uploader s3Uploader;

    //게시글 상세 조회
    @GetMapping("/post/{postId}")
    public PostResponseDto postDetail(@PathVariable Long postId,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.postDetail(postId,userDetails);
    }

    //    게시글 작성
    @PostMapping("/post")
    public Long postRegister(
            @RequestPart("image") MultipartFile multipartFile,
            PostRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails)
            throws IOException {

        String image = s3Uploader.uploadFile(multipartFile, "postImage");
        requestDto.setImgUrl(image);

        return postService.postRegister(requestDto,userDetails);
    }


    //게시글 수정
    @PatchMapping("/post/{postId}")
    public Long postUpdate(
            @RequestPart("image") MultipartFile multipartFile,
            @PathVariable Long postId,
            PostRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        if (multipartFile.equals("")) {
            return postService.PostUpdateNoImage(requestDto, postId, userDetails);
        } else {
            String image = s3Uploader.uploadFile(multipartFile, "postImage");
            requestDto.setImgUrl(image);
            return  postService.postUpdate(requestDto,postId,userDetails);
        }
    }
//    try {
//        String image = s3Uploader.uploadFile(multipartFile, "postImage");
//        requestDto.setImgUrl(image);
//        return  postService.postUpdate(requestDto,postId,userDetails);
//    } catch (Exception e) {
//        return  postService.PostUpdateNoImage(requestDto,postId,userDetails);
//    }
//}
    //게시글 수정 시 이미지를 안바꾸면 이미지가 안옴 << image :


    //게시글 삭제
    @DeleteMapping("/post/{postId}")
    public boolean postDelete(@PathVariable Long postId,
                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        return  postService.deletePost(postId,userDetails);
    }
}
