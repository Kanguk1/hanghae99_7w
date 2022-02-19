package clone.colley.model;

import clone.colley.dto.Request.PostRequestDto;
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


    @JoinColumn(name = "user_userId")
    @ManyToOne
    private User user;

//    @JsonBackReference
//    @OneToMany(mappedBy = "posts")
//    private List<Comment> comments;

    //    @Column(nullable = false)
//    private Long tagId; 태그기능 보류
    public Posts(PostRequestDto requestDto,User user){
        this.title=requestDto.getTitle();
        this.content=requestDto.getContent();
        this.imgUrl=requestDto.getImgUrl();
        this.user=user;
    }

}
