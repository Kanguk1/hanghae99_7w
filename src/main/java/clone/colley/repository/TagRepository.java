package clone.colley.repository;

import clone.colley.model.Posts;
import clone.colley.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {
    List<Tag> findTagByPosts(Posts posts);
//    List<String> findByPostId(Long postId);

//    List<String> findByPosts(Posts posts);

//    List<String> findTagByPosts(Posts posts);

//    List<String> findTagByPostsId(Long postId);

//    List<String> findByPosts(Posts posts);

//    List<String> findTagsByPosts(Posts posts);
}
