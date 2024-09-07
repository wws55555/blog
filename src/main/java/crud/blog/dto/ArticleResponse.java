package crud.blog.dto;

import crud.blog.domain.Article;
import lombok.Data;

@Data
public class ArticleResponse {
    String title;
    String content;

    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
