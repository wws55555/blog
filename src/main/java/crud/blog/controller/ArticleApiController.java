package crud.blog.controller;

import crud.blog.dto.ArticleResponse;
import crud.blog.dto.CreateRequest;
import crud.blog.dto.UpdateRequest;
import crud.blog.domain.Article;
import crud.blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v0")
public class ArticleApiController {
    
    private final ArticleService articleService;

    // 생성할때는 request객체 이용(@RequestBody)
    // request객체에 request객체 -> entity객체로 변환하는 함수 만들어둠
    // 생성하는 경우에도 응답을 보낸다
    @PostMapping("/article")
    public ResponseEntity<Article> requestCreateArticle(@RequestBody CreateRequest request) {
        Article article = articleService.create(request.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(article);
    }

    // 조회할때는 response객체 이용
    // response객체에 entity객체 -> response객체 변환 함수 만들어둠
    @GetMapping("/articles")
    public ResponseEntity<List<ArticleResponse>> requestReadAll() {
        List<ArticleResponse> articles = articleService.readAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(articles);
    }

    // request객체보단 url경로에서 데이터 이용(@PathVariable)
    // 변수일땐 {id} 처리
    @GetMapping("/article/{id}")
    public ResponseEntity<ArticleResponse> requestReadOne(@PathVariable Long id) {
        Article article = articleService.readOne(id);
        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    // 삭제하는 경우에도 응답을 보낸다
    @DeleteMapping("/article/{id}")
    public ResponseEntity<Void> requestDeleteArticle(@PathVariable Long id) {
        articleService.delete(id);
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/article/{id}")
    public ResponseEntity<Article> requestUpdateArticle(@PathVariable Long id,
                                                        @RequestBody UpdateRequest request) {
        Article updatedArticle = articleService.update(id, request.getTitle(), request.getContent());
        return ResponseEntity.ok()
                .body(updatedArticle);
    }
}
