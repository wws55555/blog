package crud.blog.api.v0;

import crud.blog.dao.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRequestV0 {

    String title;
    String content;

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
