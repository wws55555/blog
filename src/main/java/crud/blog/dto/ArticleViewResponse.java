package crud.blog.dto;

import crud.blog.domain.Article;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleViewResponse {
    Long id;
    String title;
    String content;
    LocalDateTime createdAt;

    public ArticleViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}
