package clone.colley.service;

import clone.colley.dto.Response.MainResponseDto;
import clone.colley.model.Posts;
import clone.colley.repository.MainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {

    private final MainRepository mainRepository;

    @Autowired
    public MainService(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public List<MainResponseDto> getAllPageTimed() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();

        List<Posts> postsList = mainRepository.findAll();
        List<MainResponseDto> mainResponseDtoList = new ArrayList<>();
        for (Posts posts : postsList) {
            MainResponseDto mainResponseDto = new MainResponseDto(
                    posts.getPostId(),
                    posts.getImgUrl(),
                    posts.getTitle(),
                    posts.getComments().size(),
                    posts.getLikeUserList().size(),
                    posts.getUser().getNickname(),
                    posts.getUser().getProfileUrl(),
                    posts.getCreatedAt()
                    );
            mainResponseDtoList.add(mainResponseDto);
        }
        return mainResponseDtoList;
    }

//    public List<MainResponseDto> getAllPageLiked() {
//        List<Posts> postsList = mainRepository.OrderByLikeCntDesc();
//        List<MainResponseDto> mainResponseDtoList = new ArrayList<>();
//        for (Posts posts : postsList) {
//            MainResponseDto mainResponseDto = new MainResponseDto(
//                    posts.getPostId(),
//                    posts.getImgUrl(),
//                    posts.getTitle(),
//                    posts.getComments().size(),
////                    posts.getCommentCnt(),
////                    posts.getLikeCnt(),
//                    posts.getComments().size(),
//                    posts.getUser().getNickname(),
//                    posts.getUser().getProfileUrl(),
//                    posts.getCreatedAt()
//            );
//            mainResponseDtoList.add(mainResponseDto);
//        }
//        return mainResponseDtoList;
//    }
}
