package clone.colley.service;

import clone.colley.dto.Request.LikeUserRequestDto;
import clone.colley.model.LikeUser;
import clone.colley.model.Posts;
import clone.colley.model.User;
import clone.colley.repository.LikeUserRepository;
import clone.colley.repository.PostRepository;
import clone.colley.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LikeUserService {
    private final PostRepository postRepository;
    private final LikeUserRepository likeUserRepository;

    @Transactional
    public void likeCheck(Long postId, User user, LikeUserRequestDto requestDto){
        Posts posts = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("좋아요 할 게시글이 없습니다")
        );
        LikeUser likeUser = new LikeUser(posts, user, requestDto);

        if(user.getUserId().equals(likeUser.getUser().getUserId())){

            if (likeUser.isLike()==true) {
                likeUser.setLike(true);
            } else if(likeUser.isLike()==false) {
                likeUser.setLike(false);
            }

        } else {
            likeUserRepository.save(likeUser);
        }

    }

}
