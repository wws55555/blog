package crud.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v0")
public class ArticleController {
    
    private final ArticleService articleService;

    @PostMapping("/article")
    public ResponseEntity<Article> requestCreateArticle(@Validated @RequestBody CreateRequest request) {
        Article article = articleService.createArticle(request.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(article);
    }
}
