package clone.colley.service;

import clone.colley.dto.Request.LikeUserRequestDto;
import clone.colley.model.LikeUser;
import clone.colley.model.Posts;
import clone.colley.model.User;
import clone.colley.repository.LikeUserRepository;
import clone.colley.repository.PostRepository;
import clone.colley.repository.UserRepository;
import clone.colley.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LikeUserService {
    private final PostRepository postRepository;
    private final LikeUserRepository likeUserRepository;
    private final UserRepository userRepository;

    @Transactional
    public Boolean likeCheck(Long postId, UserDetailsImpl userDetails, LikeUserRequestDto requestDto){
//        LikeUserRequestDto likeUserRequestDto = new LikeUserRequestDto();
//
        Posts posts = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("좋아요 할 게시글이 없습니다")
        );
        User user = userRepository.findById(userDetails.getUser().getUserId()).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다")
        );

        LikeUser likesCheck = likeUserRepository.findByUserAndPosts(userDetails.getUser(), posts).orElse(null);


        if (likesCheck == null) {
            log.info("널일경우");
            LikeUser likeUser = new LikeUser(posts, user, requestDto);
            likeUserRepository.save(likeUser);
        } else {
            log.info("널 아닐 경우");
            if (likesCheck.getIsLike()==true) {
                likesCheck.setIsLike(false);

            } else if(likesCheck.getIsLike()==false) {
                likesCheck.setIsLike(true);
            }
        }

        return requestDto.getIsLike();

    }

}
