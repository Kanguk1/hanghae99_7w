package clone.colley.service;


import clone.colley.dto.Request.PostRequestDto;
import clone.colley.dto.Response.PostResponseDto;
import clone.colley.model.Posts;
import clone.colley.model.Tag;
import clone.colley.repository.PostRepository;
import clone.colley.repository.TagRepository;
import clone.colley.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    //게시글 작성
    @Transactional
    public Long postRegister(PostRequestDto requestDto, UserDetailsImpl userDetails) {
        Posts posts = new Posts(requestDto, userDetails.getUser());
        List<String> tags = requestDto.getTags();
        for (String stringTag : tags) {
            Tag tag = new Tag();
            tag.setPosts(posts);
            tag.setTag(stringTag);
            tagRepository.save(tag);
        }
        return postRepository.save(posts).getPostId();
    }

    //게시글 수정
    @Transactional
    public Long postUpdate(PostRequestDto requestDto, Long postId, UserDetailsImpl userDetails) {
        Posts posts = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("수정할 게시글이 없습니다.")
        );
        if (posts.getUser().getUserId().equals(userDetails.getUser().getUserId())) {
            List<String> updateTag = requestDto.getTags();
            List<Tag> dbTagList = posts.getTags();
            List<String> dbTagName = new ArrayList<>();
            for (Tag tag : dbTagList) {
                dbTagName.add(tag.getTag());
            }
            if (updateTag.size()==0) {
                for (Tag tag : dbTagList) {
                    tagRepository.deleteById(tag.getTagId());
                }
                posts.update(requestDto.getContent(), requestDto.getImgUrl(), requestDto.getTitle());
                return posts.getPostId();
            } else if (updateTag.size() > dbTagList.size()) {
                for (String ut : updateTag) {
                    if (!dbTagName.contains(ut)) {
                        Tag tag = new Tag();
                        tag.setPosts(posts);
                        tag.setTag(ut);
                        tagRepository.save(tag);
                    }
                }
                posts.update(requestDto.getContent(), requestDto.getImgUrl(), requestDto.getTitle());
                return posts.getPostId();
            } else if (updateTag.size() == dbTagList.size()) {
                for (String ut : updateTag) {
                    if (!dbTagName.contains(ut)) {
                        Tag tag = new Tag();
                        tag.setPosts(posts);
                        tag.setTag(ut);
                        tagRepository.save(tag);
                    }
                }
                for (String dt : dbTagName) {
                    if (!updateTag.contains(dt)) {
                        Tag tag= tagRepository.findTagByPostsAndTag(posts, dt);
                        tagRepository.deleteById(tag.getTagId());
                    }
                }
                posts.update(requestDto.getContent(), requestDto.getImgUrl(), requestDto.getTitle());
                return posts.getPostId();
            }
        } else {
            return 0L;
        }
        return 0L;
    }

    //게시글 삭제
    @Transactional
    public Boolean deletePost(Long postId, UserDetailsImpl userDetails) {
        Posts posts = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("삭제할 게시글이 없습니다.")
        );
        if (posts.getUser().getUserId().equals(userDetails.getUser().getUserId())) {
            tagRepository.deleteAllByPosts(posts);
            postRepository.deleteById(posts.getPostId());
            return true;
        } else {
            return false;
        }
    }

    //게시글 상세조회
    @Transactional
    public PostResponseDto postDetail(Long postId) {
        Posts posts = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("게시글이 없습니다.")
        );
        PostResponseDto responseDto = new PostResponseDto();
        responseDto.setPostId(posts.getPostId());
        responseDto.setTitle(posts.getTitle());
        responseDto.setContent(posts.getContent());
        responseDto.setPostDate(posts.getCreatedAt());
        responseDto.setLikeCnt(posts.getLikeUserList().size());
        responseDto.setCommentCnt(posts.getComments().size());
        responseDto.setImaUrl(posts.getImgUrl());
        responseDto.setUsername(posts.getUser().getUsername());
        responseDto.setProfileUrl(posts.getUser().getProfileUrl());
        responseDto.setNickname(posts.getUser().getNickname());
        List<Tag> tagList = tagRepository.findTagByPosts(posts);
        List<String> tags = new ArrayList<>();
        for (Tag tag : tagList) {
            tags.add(tag.getTag());
        }
        responseDto.setTags(tags);
        return responseDto;

    }
}
