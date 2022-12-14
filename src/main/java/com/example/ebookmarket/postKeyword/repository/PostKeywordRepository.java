package com.example.ebookmarket.postKeyword.repository;

import com.example.ebookmarket.postKeyword.entity.PostKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostKeywordRepository extends JpaRepository<PostKeyword, Long> {
    Optional<PostKeyword> findByContent(String keywordContent);

}
