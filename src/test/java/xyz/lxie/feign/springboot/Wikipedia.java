package xyz.lxie.feign.springboot;

import feign.Param;
import feign.RequestLine;
import lombok.Data;

import java.util.ArrayList;

@FeignClient("https://en.wikipedia.org")
public interface Wikipedia {

    @RequestLine("GET /w/api.php?action=query&continue=&generator=search&prop=info&format=json&gsrsearch={search}")
    Response<Page> search(@Param("search") String search);

    @RequestLine("GET /w/api.php?action=query&continue=&generator=search&prop=info&format=json&gsrsearch={search}&gsroffset={offset}")
    Response<Page> resumeSearch(@Param("search") String search, @Param("offset") long offset);

    @Data
    class Page {
        long id;
        String title;
    }

    class Response<X> extends ArrayList<X> {
        /**
         * when present, the position to resume the list.
         */
        Long nextOffset;
    }
}