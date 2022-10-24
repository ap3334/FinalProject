package com.example.ebookmarket.post.service;

import com.example.ebookmarket.member.entity.Member;
import com.example.ebookmarket.post.dto.PostDetailDto;
import com.example.ebookmarket.post.dto.PostFormDto;
import com.example.ebookmarket.post.dto.PostListDto;
import com.example.ebookmarket.post.entity.Post;
import com.example.ebookmarket.postHashTag.entity.PostHashTag;
import com.example.ebookmarket.postHashTag.service.PostHashTagService;
import com.example.ebookmarket.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    private final PostHashTagService postHashTagService;


    public List<PostListDto> getAllPost() {

        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));

        List<PostListDto> postListDtos = posts.stream().map(post -> postToListDto(post)).collect(Collectors.toList());

        return postListDtos;
    }

    private PostListDto postToListDto(Post post) {

        PostListDto dto = PostListDto.builder()
                .id(post.getId())
                .subject(post.getSubject())
                .username(post.getAuthor().getUsername())
                .createDate(post.getCreateDate())
                .updateDate(post.getUpdateDate())
                .build();

        return dto;
    }

    public PostDetailDto getPostDetailDto(Long id) {

        Post post = postRepository.findById(id).orElseThrow(() ->
             new IllegalArgumentException("no such data")
        );

        getHashTagFromPost(post);

        PostDetailDto postDetailDtos = postToDetailDto(post);

        return postDetailDtos;
    }

    private PostDetailDto postToDetailDto(Post post) {

        PostDetailDto dto = PostDetailDto.builder()
                .id(post.getId())
                .subject(post.getSubject())
                .username(post.getAuthor().getUsername())
                .createDate(post.getCreateDate())
                .updateDate(post.getUpdateDate())
                .contentHtml(post.getContentHtml())
                .contentMarkdown(post.getContent())
                .hashTagContents(post.getExtra_inputValue_hashTagContents())
                .build();

        return dto;
    }

    private void getHashTagFromPost(Post post) {
        List<PostHashTag> postHashTags = postHashTagService.getHashTags(post);

        post.getExtra().put("postHashTags", postHashTags);
    }


    public Post writePost(PostFormDto post, Long memberId) {
        return writePost(post, new Member(memberId));
    }

    @Transactional
    public Post writePost(PostFormDto postFormDto, Member member) {

        Post post = Post.builder()
                .author(member)
                .subject(postFormDto.getSubject())
                .content(postFormDto.getContent())
                .contentHtml(postFormDto.getContentHtml())
                .build();

        postRepository.save(post);

        postHashTagService.applyHashTags(post, postFormDto.getKeywords());

        return post;


    }

    public List<Post> getLatestPost(int cnt) {

        Pageable pageable = PageRequest.of(0, cnt, Sort.by("createDate"));

        return postRepository.findAll(pageable).stream().toList();

    }

    @Transactional
    public void delete(Long id) {

        postHashTagService.deleteByPostId(id);

        postRepository.deleteById(id);

    }

    @Transactional
    public void modify(Long postId, PostFormDto postFormDto) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException());

        post.setSubject(postFormDto.getSubject());
        post.setContent(post.getContent());
        post.setContentHtml(postFormDto.getContentHtml());

        postRepository.save(post);

        log.info(postFormDto.getKeywords());

        postHashTagService.applyHashTags(post, postFormDto.getKeywords());

    }

}
