package com.example.ebookmarket.postHashTag.repository;

import com.example.ebookmarket.postHashTag.entity.PostHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostHashTagRepository extends JpaRepository<PostHashTag, Long> {
    List<PostHashTag> findAllByPostId(Long postId);

    Optional<PostHashTag> findByPostIdAndPostKeywordId(Long postId, Long postKeywordId);

    void deleteByPostId(Long postId);
}
