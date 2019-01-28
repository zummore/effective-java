package com.zummore.effectivejava.kingbbode.generic.argument;

import com.zummore.effectivejava.kingbbode.generic.TestClass;
import com.zummore.effectivejava.kingbbode.generic.rest.argument.GenericParameterRestTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GenericRestTemplateTest {

    private GenericParameterRestTemplate genericRestTemplate;

    @BeforeEach
    void setUp() {
        genericRestTemplate = new GenericParameterRestTemplate(new RestTemplateBuilder().build());
    }

    @Test
    @DisplayName("Class<T> -> new ParameterizedTypeReference<T> 는 안됨.")
    void getForClassDirectParameterized() {
        assertThrows(ClassCastException.class , () -> {
            TestClass testClass = genericRestTemplate.getForClassDirectParameterized("https://jsonplaceholder.typicode.com/todos/1", TestClass.class);
            System.out.println(testClass);
        });


        assertThrows(ClassCastException.class , () -> {
            TestClass testClass = genericRestTemplate.getForParameterizedByClass("https://jsonplaceholder.typicode.com/todos/1", TestClass.class);
            System.out.println(testClass);
        });
    }

    @Test
    @DisplayName("new ParameterizedTypeReference<T> 으로 바로 하면 됨.")
    void getForParameterized() {
        assertDoesNotThrow(() -> {
            TestClass testClass = genericRestTemplate.getForParameterized("https://jsonplaceholder.typicode.com/todos/1", new ParameterizedTypeReference<TestClass>() {
            });
            System.out.println(testClass);
        });

        assertDoesNotThrow(() -> {
            List<TestClass> testClasses = genericRestTemplate.getForParameterized("https://jsonplaceholder.typicode.com/posts", new ParameterizedTypeReference<List<TestClass>>() {
            });
            testClasses.forEach(System.out::println);
        });
    }

}
