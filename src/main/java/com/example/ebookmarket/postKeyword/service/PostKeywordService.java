package com.example.ebookmarket.postKeyword.service;

import com.example.ebookmarket.postKeyword.entity.PostKeyword;
import com.example.ebookmarket.postKeyword.repository.PostKeywordRepository;
import javassist.compiler.ast.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostKeywordService {

    private final PostKeywordRepository postKeywordRepository;

    public PostKeyword save(String keywordContent) {

        Optional<PostKeyword> optKeyword = postKeywordRepository.findByContent(keywordContent);

        if ( optKeyword.isPresent() ) {
            return optKeyword.get();
        }

        PostKeyword keyword = PostKeyword
                .builder()
                .content(keywordContent)
                .build();

        postKeywordRepository.save(keyword);

        return keyword;

    }

}
