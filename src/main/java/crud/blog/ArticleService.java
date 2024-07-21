package crud.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Article readOne(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }
//
//    public void updateArticle(Article article, String title, String content) {
//        article.setTitle(title);
//        article.setContent(content);
//    }
//
//    public void deleteArticle(Article article) {
//        articleRepository.delete(article);
//    }
}
