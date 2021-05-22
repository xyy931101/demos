package com.examplexyy.demo;

import com.examplexyy.demo.model.Movie;
import com.examplexyy.demo.repo.MovieRepository;
import com.examplexyy.demo.service.MailService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("xyy test");
    }

//    @Autowired
//    JestClient jestClient;
    @Autowired
    MovieRepository repository;

    @Autowired
    MailService mailService;

    /**
     * springData ES
     */
    /*@Test
    public void testElasticSearchJestPost() throws IOException {
        Movie movie = new Movie(1, "xyy的电影", 2020, null);
        Index index = new Index.Builder(movie).index("xyy").type("movie").build();
//        repository.index(movie);
        jestClient.execute(index);
    }

    @Test
    public void testElasticSearchJestGet() throws IOException {
       String query = "{\n" +
               "    \"query\" : {\n" +
               "        \"match\" : {\n" +
               "            \"title\" : \"xyy的电影\"\n" +
               "        }\n" +
               "    }\n" +
               "}";

        Search search = new Search.Builder(query).addIndex("xyy").addType("movie").build();
        SearchResult searchResult = jestClient.execute(search);
        System.out.println(searchResult.getJsonString());
    }*/

    /**
     * spring data 索引
     */
    @Test
    public void testElasticsearch(){
        Movie movie = new Movie(1, "xyy的电影", 2020, null);
        Movie index = repository.index(movie);
        System.out.println(movie);
    }


    @Test
    public void testSearch(){

        List<Movie> movies = repository.findMovieByTitleLike("x");
        System.out.println(movies);
    }


    @Test
    public void sendMail() throws MessagingException {
        mailService.sendMail();
    }
}
