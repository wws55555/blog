package crud.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

//    public Article readArticle(Long id) {
//        return articleRepository.getById(id);
//    }
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
