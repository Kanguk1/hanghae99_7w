package clone.colley.controller;

import clone.colley.dto.MyPostDto;
import clone.colley.dto.MyResponse;
import clone.colley.dto.Response.MyPostResponse;
import clone.colley.security.UserDetailsImpl;
import clone.colley.service.MyPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class MyPostController {

    private final MyPostService myPostService;



    // 본인 작성 게시리스트
    @GetMapping("/user/mypost")
    public ResponseEntity<MyPostResponse> myPost(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(myPostService.myPost(userDetails));
    }



    // 유저정보 수정.
    @PutMapping("/user/mypost/update")
    public MyResponse updateUser(
            @Validated @RequestBody MyPostDto myPostDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        myPostService.updateUser(myPostDto, userDetails);

        MyResponse myResponse = new MyResponse();
        myResponse.setResult(true);
        return myResponse;
    }

}