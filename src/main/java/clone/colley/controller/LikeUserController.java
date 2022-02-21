package clone.colley.controller;

import clone.colley.dto.Request.LikeUserRequestDto;
import clone.colley.model.User;
import clone.colley.security.UserDetailsImpl;
import clone.colley.service.LikeUserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeUserController {

    private final LikeUserService likeUserService;

    public LikeUserController(LikeUserService likeUserService) {
        this.likeUserService = likeUserService;
    }

    @PutMapping("/like/{postId}")
    public void likeCheck(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody LikeUserRequestDto requestDto){

        User user = userDetails.getUser();
        likeUserService.likeCheck(postId, user, requestDto);
    }
}
