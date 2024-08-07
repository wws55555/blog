package crud.blog.api.v1;

import crud.blog.dao.Article;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleViewResponseV1 {
    Long id;
    String title;
    String content;
    LocalDateTime createdAt;

    public ArticleViewResponseV1(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}
