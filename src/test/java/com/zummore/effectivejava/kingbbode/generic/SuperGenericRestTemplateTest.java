package com.zummore.effectivejava.kingbbode.generic;

import com.zummore.effectivejava.kingbbode.generic.rest.SuperGenericRestTemplate;
import com.zummore.effectivejava.kingbbode.generic.rest.SuperGenericWrapRestTemplate;
import com.zummore.effectivejava.kingbbode.generic.rest.argument.GenericParameterRestTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuperGenericRestTemplateTest {

    private GenericParameterRestTemplate genericRestTemplate = new GenericParameterRestTemplate(new RestTemplateBuilder().build());
    private SuperGenericRestTemplate<TestClass> superGenericRestTemplate;
    private SuperGenericWrapRestTemplate<TestClass> superGenericWrapRestTemplate;
    private SuperRestTemplate superRestTemplate;
    private SuperWrapRestTemplate superWrapRestTemplate;

    private SuperGenericRestTemplate<List<TestClass>> superGenericRestTemplateForList;
    private SuperGenericWrapRestTemplate<List<TestClass>> superGenericWrapRestTemplateForList;
    private SuperRestTemplateForList superRestTemplateForList;
    private SuperWrapRestTemplateForList superWrapRestTemplateForList;

    @BeforeEach
    void setUp() {
        superGenericRestTemplate = new SuperGenericRestTemplate<>(new RestTemplateBuilder().build(), "https://jsonplaceholder.typicode.com/todos/1");
        superGenericWrapRestTemplate = new SuperGenericWrapRestTemplate<>(superGenericRestTemplate);
        superRestTemplate = new SuperRestTemplate(new RestTemplateBuilder().build(), "https://jsonplaceholder.typicode.com/todos/1");
        superWrapRestTemplate = new SuperWrapRestTemplate(superGenericRestTemplate);

        superGenericRestTemplateForList = new SuperGenericRestTemplate<>(new RestTemplateBuilder().build(), "https://jsonplaceholder.typicode.com/posts");
        superGenericWrapRestTemplateForList = new SuperGenericWrapRestTemplate<>(superGenericRestTemplateForList);
        superRestTemplateForList = new SuperRestTemplateForList(new RestTemplateBuilder().build(), "https://jsonplaceholder.typicode.com/posts");
        superWrapRestTemplateForList = new SuperWrapRestTemplateForList(superGenericRestTemplateForList);
    }

    public static class SuperRestTemplate extends SuperGenericRestTemplate<TestClass> {
        public SuperRestTemplate(RestTemplate restTemplate, String url) {
            super(restTemplate, url);
        }
    }

    public static class SuperWrapRestTemplate extends SuperGenericWrapRestTemplate<TestClass> {

        public SuperWrapRestTemplate(SuperGenericRestTemplate<TestClass> superGenericRestTemplate) {
            super(superGenericRestTemplate);
        }
    }

    public static class SuperRestTemplateForList extends SuperGenericRestTemplate<List<TestClass>> {
        public SuperRestTemplateForList(RestTemplate restTemplate, String url) {
            super(restTemplate, url);
        }
    }

    public static class SuperWrapRestTemplateForList extends SuperGenericWrapRestTemplate<List<TestClass>> {

        public SuperWrapRestTemplateForList(SuperGenericRestTemplate<List<TestClass>> superGenericRestTemplate) {
            super(superGenericRestTemplate);
        }
    }

    @Test
    @DisplayName("Generic <T> -> new ParameterizedTypeReference<T> 는 Runtime 에서 T 를 알 수 있는 방법이 없다.")
    void getForClassDirectParameterized() {
        assertThrows(ClassCastException.class , () -> {
            TestClass testClass = genericRestTemplate.getForClassDirectParameterized("https://jsonplaceholder.typicode.com/todos/1", TestClass.class);
            System.out.println(testClass);
        });

        assertThrows(ClassCastException.class , () -> {
            TestClass testClass = genericRestTemplate.getForParameterizedByClass("https://jsonplaceholder.typicode.com/todos/1", TestClass.class);
            System.out.println(testClass);
        });

        assertThrows(ClassCastException.class , () -> {
            TestClass testClass = superGenericRestTemplate.getForParameterizedByGeneric();
            System.out.println(testClass);
        });

        assertThrows(ClassCastException.class , () -> {
            TestClass testClass = superGenericRestTemplate.getForParameterizedByCreatorGeneric();
            System.out.println(testClass);
        });

        assertThrows(ClassCastException.class , () -> {
            TestClass testClass = superGenericWrapRestTemplate.getForParameterizedByGeneric();
            System.out.println(testClass);
        });

        assertThrows(ClassCastException.class , () -> {
            TestClass testClass = superGenericWrapRestTemplate.getForParameterizedByCreatorGeneric();
            System.out.println(testClass);
        });

        assertThrows(ClassCastException.class , () -> {
            TestClass testClass = superRestTemplate.getForParameterizedByGeneric();
            System.out.println(testClass);
        });

        assertThrows(ClassCastException.class , () -> {
            TestClass testClass = superWrapRestTemplate.getForParameterizedByGeneric();
            System.out.println(testClass);
        });

        assertThrows(ClassCastException.class , () -> {
            List<TestClass> testClass = superGenericRestTemplateForList.getForParameterizedByGeneric();
            testClass.forEach(System.out::println);
        });

        assertThrows(ClassCastException.class , () -> {
            List<TestClass> testClass = superGenericWrapRestTemplateForList.getForParameterizedByGeneric();
            testClass.forEach(System.out::println);
        });

        assertThrows(ClassCastException.class , () -> {
            List<TestClass> testClass = superRestTemplateForList.getForParameterizedByGeneric();
            testClass.forEach(System.out::println);
        });

        assertThrows(ClassCastException.class , () -> {
            List<TestClass> testClass = superWrapRestTemplateForList.getForParameterizedByGeneric();
            testClass.forEach(System.out::println);
        });
    }

    public static class SuperRestTemplateWithParameterized extends SuperGenericRestTemplate<TestClass> {
        public SuperRestTemplateWithParameterized(RestTemplate restTemplate, String url) {
            super(restTemplate, url, new ParameterizedTypeReference<TestClass>() {});
        }
    }

    public static class SuperRestTemplateWithParameterizedForList extends SuperGenericRestTemplate<List<TestClass>> {
        public SuperRestTemplateWithParameterizedForList(RestTemplate restTemplate, String url) {
            super(restTemplate, url, new ParameterizedTypeReference<List<TestClass>>() {});
        }
    }

    private SuperRestTemplateWithParameterized superRestTemplateWithParameterized = new SuperRestTemplateWithParameterized(new RestTemplateBuilder().build(), "https://jsonplaceholder.typicode.com/todos/1");
    private SuperRestTemplateWithParameterizedForList superRestTemplateWithParameterizedForList = new SuperRestTemplateWithParameterizedForList(new RestTemplateBuilder().build(), "https://jsonplaceholder.typicode.com/posts");

    @Test
    @DisplayName("new ParameterizedTypeReference<T> 으로 Compile 에서부터 T 를 추론할 수 있도록 해야한다.")
    void getForParameterized() {
        assertDoesNotThrow(() -> {
            TestClass testClass = superGenericRestTemplate.getForParameterized(new ParameterizedTypeReference<TestClass>() {
            });
            System.out.println(testClass);
        });

        assertDoesNotThrow(() -> {
            List<TestClass> testClass = superGenericRestTemplateForList.getForParameterized(new ParameterizedTypeReference<List<TestClass>>() {
            });
            testClass.forEach(System.out::println);
        });

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

        assertThrows(ClassCastException.class , () -> {
            TestClass testClass = superRestTemplateWithParameterized.getForParameterizedByGeneric();
            System.out.println(testClass);
        });

        assertDoesNotThrow(() -> {
            TestClass testClass = superRestTemplateWithParameterized.getForParameterizedByCreatorGeneric();
            System.out.println(testClass);
        });

        assertThrows(ClassCastException.class , () -> {
            List<TestClass> testClass = superRestTemplateWithParameterizedForList.getForParameterizedByGeneric();
            testClass.forEach(System.out::println);
        });

        assertDoesNotThrow(() -> {
            List<TestClass> testClass = superRestTemplateWithParameterizedForList.getForParameterizedByCreatorGeneric();
            testClass.forEach(System.out::println);
        });
    }
}
