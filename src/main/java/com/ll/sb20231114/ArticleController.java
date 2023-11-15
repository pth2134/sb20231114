package com.ll.sb20231114;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {

    List<Article> list = new ArrayList<>();

    @GetMapping("/article/write")
    String v1() {
        return "article/write";
    }

    @GetMapping("/article/doWrite")
    @ResponseBody
    RsData v2(String title, String body) {
        Article article = new Article(list.size()+1,title,body);
        list.add(article);
        RsData<Article> rsData = new RsData<>(
                "ok",
                "%d번 article이 등록되었습니다.".formatted(list.size()),
                article
        );
        return rsData;
    }

    @GetMapping("/article/getLastArticle")
    @ResponseBody
    Article v3() {
        Article oa = list.get(list.size()-1);
        return oa;
    }

    @GetMapping("/article/getArticles")
    @ResponseBody
    List<Article> v4() {
        return list;
    }


}

@AllArgsConstructor
@Getter
class RsData<T> {
    private String resultCode;
    private String msg;
    private T data;
}

@AllArgsConstructor
@Getter
class Article {
    private long id;
    private String title;
    private String body;
}
