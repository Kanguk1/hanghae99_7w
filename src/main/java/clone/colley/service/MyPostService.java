package clone.colley.service;

import clone.colley.dto.MyPostDto;
import clone.colley.dto.Response.MainResponseDto;
import clone.colley.dto.Response.MyPostResponse;
import clone.colley.model.Posts;
import clone.colley.model.User;
import clone.colley.repository.PostRepository;
import clone.colley.repository.UserRepository;
import clone.colley.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPostService {


    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public MyPostResponse myPost(UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        // userDetails 값과 동일한 작성자를 가진 게시글들을 db에서 찾아옴 = postList
        List<Posts> postList = postRepository.findAllByUser(userDetails.getUser());

        // 리턴할 값( MainResponseDto 배열 ) 을 정의함
        List<MainResponseDto> mainResponseDtoList = new ArrayList<>();

        // db 에서 조회한 게시글들을 리턴할 값으로 정의한 List에 담음
        for (Posts posts : postList) {
            MainResponseDto mainResponseDto = new MainResponseDto(posts);
            mainResponseDtoList.add(mainResponseDto);
        }

          MyPostResponse myPostResponse = new MyPostResponse();

          myPostResponse.setStatus(200);
        myPostResponse.setNickName(user.getNickname());
        myPostResponse.setProfileUrl(user.getProfileUrl());
        myPostResponse.setIntroduce(user.getIntroduce());
        myPostResponse.setMainResponseDtoList(mainResponseDtoList);

        // 해당 list 리턴
        return myPostResponse;
    }


    @Transactional
    public User updateUser(MyPostDto myPostDto, UserDetailsImpl userDetails) {

        User user = userDetails.getUser();
        String nickname = myPostDto.getNickname();

        if (nickname == null) {
            throw new NullPointerException("닉네임을 입력해주세요");
        }
        user.updateUser(myPostDto);
        userRepository.save(user);
        return user;
    }
}