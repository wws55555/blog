package crud.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {

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

    @Test
    void requestReadAll() throws Exception{
        //given
        String url = "/v0/article";
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
}