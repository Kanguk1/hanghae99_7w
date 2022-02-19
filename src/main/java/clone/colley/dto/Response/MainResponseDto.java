package clone.colley.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
public class MainResponseDto {

    private Long postId;

    private String imgUrl;

    private String title;

    private Integer commentCnt;

    private Integer likeCnt;

    private String nickname;

    private String profileUrl;

    public MainResponseDto(Long postId, String imgUrl, String title, String nickname, String profileUrl) {
        this.postId=postId;
        this.imgUrl=imgUrl;
        this.title=title;
        this.nickname=nickname;
        this.profileUrl=profileUrl;
    }
}
