package com.mjmj.review.api;

import com.mjmj.review.model.TestEntity;
import com.mjmj.review.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestQueryAPI {
    private final TestService testService;

    @GetMapping("/test/query/jpa")
    public List<TestEntity> queryJpa(){
        return testService.findAllByNameJPA("mj");
    }

    @GetMapping("/test/query/jpa")
    public List<TestEntity> queryQuerydsl(){
        return testService.findAllByNameByQuerydsl("mj");
    }

}
