package crud.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v0")
public class ArticleController {
    
    private final ArticleService articleService;

    @PostMapping("/article")
    public ResponseEntity<Article> requestCreateArticle(@RequestBody CreateRequest request) {
        Article article = articleService.create(request.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(article);
    }

    @GetMapping("/article")
    public ResponseEntity<List<ArticleResponse>> requestReadAll() {
        List<ArticleResponse> articles = articleService.readAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(articles);
    }

//    @GetMapping("/article")
//    public ResponseEntity<Article> requestReadArticle(@RequestBody ReadRequest request) {
//        Optional<Article> article = articleService.readOne(request.getId());
//        if (article.isPresent()) {
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(article.get());
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST);
//        }
//    }
}
