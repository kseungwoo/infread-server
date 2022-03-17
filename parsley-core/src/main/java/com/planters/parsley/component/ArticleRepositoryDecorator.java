package com.planters.parsley.component;

import com.planters.parsley.constant.ResponseCode;
import com.planters.parsley.domain.Account;
import com.planters.parsley.domain.Article;
import com.planters.parsley.exception.CommonException;
import com.planters.parsley.repository.AccountRepository;
import com.planters.parsley.repository.ArticleRepository;
import com.planters.parsley.vo.ArticleVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Component
public class ArticleRepositoryDecorator {

    private final ArticleRepository articleRepository;

    public List<Article> saveAllFromArticleVoList(final List<ArticleVo> articleVoList) {

        final List<Article> articles = articleVoList.stream().map(vo -> new Article(
                vo.getTitle(),
                vo.getDescription(),
                vo.getUrl(),
                vo.getThumbnail(),
                vo.getPublishedAt(),
                vo.getBrand(),
                vo.getCategory())).collect(Collectors.toList());

        return articleRepository.saveAll(articles);
    }
}
