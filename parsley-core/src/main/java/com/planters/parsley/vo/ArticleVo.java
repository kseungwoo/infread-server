package com.planters.parsley.vo;

import com.planters.parsley.constant.NewsBrand;
import com.planters.parsley.constant.NewsCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Builder
@Getter
@ToString
public class ArticleVo {

    private final Long id;
    private final String title;
    private final String description;
    private final String url;
    private final String thumbnail;
    private final ZonedDateTime publishedAt;
    private final NewsBrand brand;
    private final NewsCategory category;
}
