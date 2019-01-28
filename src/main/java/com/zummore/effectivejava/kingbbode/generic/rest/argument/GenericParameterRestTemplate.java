package com.zummore.effectivejava.kingbbode.generic.rest.argument;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class GenericParameterRestTemplate {

    private final RestTemplate restTemplate;

    public <T> T getForClassDirectParameterized(String url, Class<T> clazz) {
        return getForParameterizedByClass(url, clazz);
    }

    public <T> T getForParameterizedByClass(String url, Class<T> clazz) {
        return getForParameterized(url, new ParameterizedTypeReference<T>() {
        });
    }

    public <T> T getForParameterized(String url, ParameterizedTypeReference<T> parameterizedTypeReference) {
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
