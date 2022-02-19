package clone.colley.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class MainResponseDto {

    private Long postId;

    private String imgUrl;

    private String title;

    private Integer commentCnt;

    private Integer likeCnt;

    private String nickname;

    private String profileUrl;
}
