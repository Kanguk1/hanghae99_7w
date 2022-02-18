package clone.colley.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity // DB 테이블 역할을 합니다.
public class Comment extends Timestamped{

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long commentId;

    @Column(nullable = false)
    private String comment;

    @JoinColumn(name = "UserId")
    @ManyToOne
    private User user;

    @JsonManagedReference
    @JoinColumn(name = "PostId")
    @ManyToOne
    private Posts posts;
}
