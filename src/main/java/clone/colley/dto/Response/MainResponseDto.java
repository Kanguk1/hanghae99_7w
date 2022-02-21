package clone.colley.dto.Response;

import clone.colley.model.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class MainResponseDto {

    private Long postId;

    private String imgUrl;

    private String title;

    private Integer commentCnt;

    private Integer likeCnt;

    private String nickname;

    private String profileUrl;

    //글 작성 시간
    private LocalDateTime postDate;





//    public MainResponseDto(Posts posts, int likeCnt, int commentCnt)
//    {
//        this.postId = posts.getPostId();
//        this.title =posts.getTitle();
//        this.imgUrl =posts.getImgUrl();
//        this.likeCnt = likeCnt;
//        this.commentCnt = commentCnt;
//        this.nickname =posts.getUser().getNickname();
//    }

    //////////////////////////////////
    public MainResponseDto(Posts posts)
    {
        this.postId = posts.getPostId();
        this.title =posts.getTitle();
        this.imgUrl =posts.getImgUrl();
        this.nickname =posts.getUser().getNickname();
    }
}
