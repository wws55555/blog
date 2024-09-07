package crud.blog.dto;

import crud.blog.domain.Article;
import lombok.Data;

@Data
public class ArticleListViewResponse {
    Long id;
    String title;
    String content;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
