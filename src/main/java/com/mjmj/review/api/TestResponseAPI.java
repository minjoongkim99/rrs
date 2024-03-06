package com.mjmj.review.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestResponseAPI {

    @GetMapping("/test/response/string")
    public String stringResponse(){
        return "This is String";
    }

    @GetMapping("test/response/json")
    public TestResponseBody jsonResponse(){
        return new TestResponseBody("mj",26);
    }

    public static class TestResponseBody{
        String name;
        Integer age;

        public TestResponseBody(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }
    }
}
