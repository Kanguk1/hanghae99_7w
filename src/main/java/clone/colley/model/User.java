package clone.colley.model;

import clone.colley.dto.MyPostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column
    private String profileUrl;

    @Column
    private String introduce;

    @OneToMany(mappedBy = "user")
    private List<Posts> postList;

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<LikeUser> likeUserList;

    public User(String username, String password, String nickname) {

        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }


    public void updateUser(MyPostDto myPostDto) {

        this.profileUrl = myPostDto.getUser_profile();
        this.nickname = myPostDto.getNickname();
        this.introduce = myPostDto.getIntroduce();
    }

}
