package xyz.lxie.feign.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * FeignClientTests
 *
 * @author xieg
 * @since 2016/11/4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = FeignClientTests.Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        value = "api.github=https://api.github.com")
@DirtiesContext
public class FeignClientTests {
    @Configuration
    @EnableAutoConfiguration
    @EnableFeignClients
    public static class Application {
        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }
    }

    @Autowired
    private GitHub github;

    @Autowired
    private Wikipedia wikipedia;

    @Test
    public void testGithub() {
        github.contributors("xiegang", "spring-boot-starter-feign").forEach(contributor -> {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        });
    }

    @Test
    public void testWikipedia() {
        wikipedia.search("java").forEach(System.out::println);
    }
}