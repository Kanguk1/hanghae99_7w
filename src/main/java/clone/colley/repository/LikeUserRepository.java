package clone.colley.repository;

import clone.colley.model.LikeUser;
import clone.colley.model.Posts;
import clone.colley.security.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeUserRepository extends JpaRepository<LikeUser, Long> {

}
