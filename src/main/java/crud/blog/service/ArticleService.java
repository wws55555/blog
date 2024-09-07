package crud.blog.service;

import crud.blog.domain.Article;
import crud.blog.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article create(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> readAll() {
        return articleRepository.findAll();
    }

    // 정상루트만 표현, 변수루트는 controller까지 가져가지말고, 여기서 오류처리
    public Article readOne(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    // update 함수는 entity쪽에서 함수를 만들어놓는다
    @Transactional
    public Article update(Long id, String title, String content) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        article.update(title, content);
        return article;
    }
}
