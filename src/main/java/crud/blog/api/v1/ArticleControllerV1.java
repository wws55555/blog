package crud.blog.api.v1;

import crud.blog.dao.Article;
import crud.blog.dao.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleControllerV1 {
    private final ArticleService articleService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponseV1> articles = articleService.readAll().stream()
                .map(ArticleListViewResponseV1::new)
                .toList();
        model.addAttribute("articles", articles);
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = articleService.readOne(id);
        model.addAttribute("article", new ArticleViewResponseV1(article));
        return "article";
    }
}
