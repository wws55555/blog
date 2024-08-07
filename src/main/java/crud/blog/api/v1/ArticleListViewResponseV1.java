package crud.blog.api.v1;

import crud.blog.dao.Article;
import lombok.Data;

@Data
public class ArticleListViewResponseV1 {
    Long id;
    String title;
    String content;

    public ArticleListViewResponseV1(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
