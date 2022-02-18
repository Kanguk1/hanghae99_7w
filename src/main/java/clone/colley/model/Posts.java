package clone.colley.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Posts extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private Integer likeCnt;

    @Column(nullable = false)
    private Integer commentCnt;

    @JoinColumn(name = "username_id")
    @ManyToOne
    private User user;

    @JsonBackReference
    @OneToMany(mappedBy = "posts")
    private List<Comment> comments;

    //    @Column(nullable = false)
//    private Long tagId; 태그기능 보류


}
