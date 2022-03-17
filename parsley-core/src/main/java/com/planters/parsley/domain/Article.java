package com.planters.parsley.domain;

import com.planters.parsley.constant.NewsBrand;
import com.planters.parsley.constant.NewsCategory;
import com.planters.parsley.vo.ArticleVo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "article")
public class Article extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length=1500)
    private String description;

    @Column(name = "url")
    private String url;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "published_at")
    private ZonedDateTime publishedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand")
    private NewsBrand brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private NewsCategory category;


    public ArticleVo toVo() {
        return ArticleVo.builder()
                .id(id)
                .title(title)
                .description(description)
                .url(url)
                .thumbnail(thumbnail)
                .publishedAt(publishedAt)
                .brand(brand)
                .category(category)
                .build();
    }

    public Article(String title, String description, String url, String thumbnail,
                   ZonedDateTime publishedAt, NewsBrand brand, NewsCategory category) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.thumbnail = thumbnail;
        this.publishedAt = publishedAt;
        this.brand = brand;
        this.category = category;
    }
}
