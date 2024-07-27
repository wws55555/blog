package crud.blog.api.v1;

import crud.blog.api.v0.ArticleResponseV0;
import crud.blog.dao.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleControllerV1 {
    private final ArticleService articleService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleResponseV1> articles = articleService.readAll().stream()
                .map(ArticleResponseV1::new)
                .toList();
        model.addAttribute("articles", articles);
        return "articleList";
    }
}
