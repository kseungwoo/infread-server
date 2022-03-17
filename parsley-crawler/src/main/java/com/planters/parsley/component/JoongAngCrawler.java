package com.planters.parsley.component;

import com.planters.parsley.constant.NewsBrand;
import com.planters.parsley.constant.NewsCategory;
import com.planters.parsley.vo.ArticleVo;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Component
public class JoongAngCrawler {

    private final ArticleRepositoryDecorator articleRepository;
    private final NewsBrand brand = NewsBrand.JoongAng;

    @Scheduled(cron = "0 * * * * *")
    public void saveNewArticlesInOneMinuteForEveryCategory() {

        articleRepository.saveAllFromArticleVoList(
                Arrays.stream(NewsCategory.values())
                        .map(wrapper(this::getNewArticlesInOneMinutePerCategory))
                        .flatMap(articleVoList -> articleVoList.stream())
                        .collect(Collectors.toList()));
    }

    public List<ArticleVo> getNewArticlesInOneMinutePerCategory(final NewsCategory category) throws IOException {

        final ZonedDateTime publishedAt =
                ZonedDateTime.of(
                        ZonedDateTime.now().getYear(),
                        ZonedDateTime.now().getMonthValue(),
                        ZonedDateTime.now().getDayOfMonth(),
                        ZonedDateTime.now().getHour(),
                        ZonedDateTime.now().getMinute(),
                        0,
                        0,
                        ZoneId.of("Asia/Seoul")).minusMinutes(1);

        return getAllArticlesPerCategory(category)
                .stream()
                .filter(vo -> vo.getPublishedAt().isEqual(publishedAt))
                .collect(Collectors.toList());
    }

    public List<ArticleVo> getAllArticlesPerCategory(final NewsCategory category) throws IOException {

        final Document document = Jsoup.connect(brand.getMainUri() + category.getSubUri(brand)).get();

        final int size =
                document.select("#story_list > li > div.card_body > h2 > a").size();

        return IntStream.rangeClosed(1, size).mapToObj(n -> getOneArticlePerCategory(n, document, category))
                .filter(articleVo -> articleVo.getDescription().length() < 1500)
                .collect(Collectors.toList());
    }

    public ArticleVo getOneArticlePerCategory(final int n, final Document document, NewsCategory category) {

        return ArticleVo.builder()
                .title(document.select("#story_list > li:nth-child(" + n + ") > div.card_body > h2 > a").text())
                .description(document.select("#story_list > li:nth-child(" + n + ") > div.card_body > p > a").text())
                .url(document.select("#story_list > li:nth-child(" + n + ") > div.card_body > h2 > a").attr("href"))
                .thumbnail(document.select("#story_list > li:nth-child(" + n + ") > div.card_image > figure > a > img").attr("src"))
                .publishedAt(ZonedDateTime.of(LocalDateTime.parse(
                                document.select("#story_list > li:nth-child(" + n + ") > div.card_body > div > p").text(),
                                DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")),
                        ZoneId.of("Asia/Seoul")))
                .brand(brand)
                .category(category)
                .build();
    }

    @FunctionalInterface
    public interface FunctionWithException<T, R, E extends Exception> {

        R apply(T t) throws E;
    }

    private <T, R, E extends Exception>
    Function<T, R> wrapper(FunctionWithException<T, R, E> fe) {

        return arg -> {
            try {
                return fe.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

}
