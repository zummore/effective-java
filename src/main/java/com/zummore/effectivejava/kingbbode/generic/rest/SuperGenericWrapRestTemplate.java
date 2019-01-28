package com.zummore.effectivejava.kingbbode.generic.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;

@RequiredArgsConstructor
public class SuperGenericWrapRestTemplate<T> {

    private final SuperGenericRestTemplate<T> superGenericRestTemplate;
    private final ParameterizedTypeReference<T> parameterized;

    public SuperGenericWrapRestTemplate(SuperGenericRestTemplate<T> superGenericRestTemplate) {
        this.superGenericRestTemplate = superGenericRestTemplate;
        this.parameterized = new ParameterizedTypeReference<T>() {};
    }

    public T getForParameterizedByGeneric() {
        return superGenericRestTemplate.getForParameterized(new ParameterizedTypeReference<T>() {});
    }

    public T getForParameterizedByCreatorGeneric() {
        return superGenericRestTemplate.getForParameterized(parameterized);
    }
}
