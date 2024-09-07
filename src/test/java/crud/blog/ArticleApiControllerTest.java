package crud.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import crud.blog.dto.CreateRequest;
import crud.blog.dto.UpdateRequest;
import crud.blog.domain.Article;
import crud.blog.repository.ArticleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        articleRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
    }

    // http로 전송하기 위해선 objectMapper로 직렬화 작업 필요
    // 직렬화 : 객체 -> json(http에서 필요)
    // 역직렬화 : json -> 객체
    // MediaType.APPLICATION_JSON_VALUE : string
    // MediaType.APPLICATION_JSON : 객체
    @Test
    void requestCreateArticle() throws Exception {
        //given
        String url = "/v0/article";
        String title = "제목";
        String content = "내용";
        CreateRequest createRequest = new CreateRequest(title, content);
        String requestBody = objectMapper.writeValueAsString(createRequest);

        //when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        result.andExpect(status().isCreated());

        List<Article> articles = articleRepository.findAll();
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
    }

    // get으로 가져온것들은 andExpect로 연이어 확인을 한다. get이 아닌 경우는 repository에서 꺼내온 뒤 확인
    @Test
    void requestReadAll() throws Exception{
        //given
        String url = "/v0/articles";
        String title = "제목";
        String content = "내용";
        articleRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when
        ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        //then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));
    }

    @Test
    void readOne() throws Exception {
        //given
        String url = "/v0/article/{id}";
        String title = "제목";
        String content = "내용";
        Article savedArticle = articleRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when
        ResultActions result = mockMvc.perform(get(url, savedArticle.getId()));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));
    }

    @Test
    void deleteArticle() throws Exception {
        //given
        String url = "/v0/article/{id}";
        String title = "제목";
        String content = "내용";
        Article savedArticle = articleRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when
        ResultActions result = mockMvc.perform(delete(url, savedArticle.getId()));

        //then
        result.andExpect(status().isOk());
        List<Article> articles = articleRepository.findAll();
        assertThat(articles).isEmpty();
    }

    @Test
    void updateArticle() throws Exception {
        //given
        String url = "/v0/article/{id}";
        String title = "제목";
        String content = "내용";
        Article savedArticle = articleRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        String updatedTitle = "수정된 제목";
        String updatedContent = "수정된 내용";
        UpdateRequest updateRequest = new UpdateRequest(updatedTitle, updatedContent);
        String request = objectMapper.writeValueAsString(updateRequest);

        //when
        ResultActions result = mockMvc.perform(put(url, savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request));

        //then
        result.andExpect(status().isOk());
        Article findArticle = articleRepository.findById(savedArticle.getId()).get();
        assertThat(findArticle.getTitle()).isEqualTo(updatedTitle);
        assertThat(findArticle.getContent()).isEqualTo(updatedContent);
    }
}