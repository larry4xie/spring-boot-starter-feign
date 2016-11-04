package xyz.lxie.feign.springboot;

import feign.Param;
import feign.RequestLine;
import lombok.Data;

import java.util.List;

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
