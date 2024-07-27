package crud.blog.api.v0;

import crud.blog.dao.Article;
import lombok.Data;

@Data
public class ArticleResponseV0 {
    String title;
    String content;

    public ArticleResponseV0(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
