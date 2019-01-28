package com.zummore.effectivejava.kingbbode.generic.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class SuperGenericRestTemplate<T> {

    private final RestTemplate restTemplate;
    private final String url;
    private final ParameterizedTypeReference<T> parameterized;

    public SuperGenericRestTemplate(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
        this.parameterized = new ParameterizedTypeReference<T>() {};
    }

    public T getForParameterizedByCreatorGeneric() {
        return getForParameterized(parameterized);
    }


    public T getForParameterizedByGeneric() {
        return getForParameterized(new ParameterizedTypeReference<T>() {});
    }

    public T getForParameterized(ParameterizedTypeReference<T> parameterizedTypeReference) {
        try {
            ResponseEntity<T> exchange = restTemplate.exchange(url, HttpMethod.GET, null, parameterizedTypeReference);

            if (exchange.getStatusCode() != HttpStatus.OK) {
                throw new IllegalStateException();
            }
            return exchange.getBody();
        } catch(Exception e) {
            throw new IllegalStateException();
        }
    }
}
