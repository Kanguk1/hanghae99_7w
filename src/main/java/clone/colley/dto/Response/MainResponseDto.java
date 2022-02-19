package clone.colley.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
