package com.mjmj.review.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestLombokAPI {

    @GetMapping("/test/lombok")
    public TestLombokResponseBody testLombok(){
        return new TestLombokResponseBody("mj", 26);
    }


    @Getter
    @AllArgsConstructor
    public static class TestLombokResponseBody{
        String name;
        Integer age;
    }
}
