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

    // 정상루트만 표현, 변수루트는 controller까지 가져가지말고, 여기서 오류처리
    public Article readOne(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
    //
//    public void updateArticle(Article article, String title, String content) {
//        article.setTitle(title);
//        article.setContent(content);
//    }
//
}
