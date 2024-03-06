package com.mjmj.review.service;

import com.mjmj.review.model.TestEntity;
import com.mjmj.review.repository.TestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestService {

    private final TestRepository testRepository; // DIP 생성자 만들거나 cmd + N

    public void create(String name, Integer age){
        TestEntity testEntity = new TestEntity(name, age);
        testRepository.save(testEntity);
    }


    public void delete(Long id){
        TestEntity testEntity = testRepository.findById(id).get();
        testRepository.delete(testEntity);
    }

    public void update(Long id, String name, Integer age){
        TestEntity testEntity = testRepository.findById(id).orElseThrow();

        testEntity.changeNameAndAge(name,age);

        testRepository.save(testEntity);
    }


    public List<TestEntity> findAllByNameJPA(String name){
        return testRepository.findAllByName(name);
    }

    public List<TestEntity> findAllByNameByQuerydsl(String name){
        return testRepository.findAllByNameByQuerydsl(name);
    }
}
