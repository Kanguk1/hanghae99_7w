package clone.colley.model;

import clone.colley.dto.Request.LikeUserRequestDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity // DB 테이블 역할을 합니다.
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeUser {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Posts posts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Column
    private boolean isLike;

    public LikeUser(Posts posts, User user, LikeUserRequestDto likeUserRequestDto) {
        this.posts = posts;
        this.user = user;
        this.isLike = likeUserRequestDto.getIsLike();
    }
}
