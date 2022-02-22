package clone.colley.controller;

import clone.colley.dto.MyPostDto;
import clone.colley.dto.MyResponse;
import clone.colley.dto.Response.MyPostResponse;
import clone.colley.security.UserDetailsImpl;
import clone.colley.service.MyPostService;
import clone.colley.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
public class MyPostController {

    private final MyPostService myPostService;
    private final S3Uploader s3Uploader;


    // 본인 작성 게시리스트
    @GetMapping("/user/mypost")
    public ResponseEntity<MyPostResponse> myPost(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(myPostService.myPost(userDetails));
    }



    // 유저정보 수정.
    @PutMapping("/user/mypost/update")
    public MyResponse updateUser(
            @RequestPart("image") MultipartFile multipartFile,
            MyPostDto myPostDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) throws IOException {
        String image = s3Uploader.uploadFile(multipartFile,"profileUrl");
        myPostDto.setProfileUrl(image);

        myPostService.updateUser(myPostDto, userDetails);

        MyResponse myResponse = new MyResponse();
        myResponse.setResult(true);
        return myResponse;
    }

}