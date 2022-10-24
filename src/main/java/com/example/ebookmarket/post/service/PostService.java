package com.example.ebookmarket.post.service;

import com.example.ebookmarket.member.entity.Member;
import com.example.ebookmarket.post.dto.PostForm;
import com.example.ebookmarket.post.dto.PostListDto;
import com.example.ebookmarket.post.entity.Post;
import com.example.ebookmarket.postHashTag.entity.PostHashTag;
import com.example.ebookmarket.postHashTag.service.PostHashTagService;
import com.example.ebookmarket.postKeyword.entity.PostKeyword;
import com.example.ebookmarket.postHashTag.repository.PostHashTagRepository;
import com.example.ebookmarket.postKeyword.repository.PostKeywordRepository;
import com.example.ebookmarket.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostKeywordRepository postKeywordRepository;
    private final PostHashTagRepository postHashTagRepository;

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

    public Post getPost(Long id) {

        Post post = postRepository.findById(id).orElseThrow(() ->
             new IllegalArgumentException("no such data")
        );

        getPostForPrint(post);

        return post;
    }

    private void getPostForPrint(Post post) {
        List<PostHashTag> postHashTags = postHashTagService.getHashTags(post);

//        post.getExtra().put
    }

    public Post writePost(PostForm postForm, Member member) {

        Post post = Post.builder()
                .author(member)
                .subject(postForm.getSubject())
                .content(postForm.getContent())
                .contentHtml(postForm.getContentHtml())
                .build();

        postRepository.save(post);

        String keywords[] = postForm.getKeywords().split("#");

        for (int i = 1; i < keywords.length; i++) {
            PostKeyword postKeywords = PostKeyword.builder()
                    .content(keywords[i].trim())
                    .build();

            postKeywordRepository.save(postKeywords);

            PostHashTag postHashTag = PostHashTag.builder()
                    .member(member)
                    .post(post)
                    .postKeyword(postKeywords)
                    .build();

            postHashTagRepository.save(postHashTag);

        }

        return post;


    }

    public List<Post> getLatestPost(int cnt) {

        Pageable pageable = PageRequest.of(0, cnt, Sort.by("createDate"));

        return postRepository.findAll(pageable).stream().toList();

    }
}
