# spring-boot-starter-feign
- [spring boot](https://spring.io/spring-boot)
- [feign](https://github.com/OpenFeign/feign)

## dependency
```xml
<dependency>
    <groupId>xyz.lxie</groupId>
    <artifactId>spring-boot-starter-feign</artifactId>
    <version>${starter-feign.version}</version>
</dependency>
```

> search [newest version](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22xyz.lxie%22%20a%3A%22spring-boot-starter-feign%22)


## usage
```java
// 1. EnableFeignClients
@SpringBootApplication
@EnableFeignClients
public class Main {
    public static void main(String[] args) {
       SpringApplication.run(Application.class, args);
   }
}

// 2. @FeignClient
// or @FeignClient("https://api.github.com")
@FeignClient("${api.github}")
public interface GitHub {
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);

    @Data
    class Contributor {
        String login;
        int contributions;
    }
}
```

```java
// 3. usage
@Autowired
private GitHub github;

@Test
public void testGithub() {
   github.contributors("xiegang", "spring-boot-starter-feign").forEach(contributor -> {
       System.out.println(contributor.login + " (" + contributor.contributions + ")");
   });
}
```
See details: [FeignAutoConfiguration](https://github.com/xiegang/spring-boot-starter-feign/blob/master/src/main/java/xyz/lxie/feign/springboot/FeignAutoConfiguration.java)




