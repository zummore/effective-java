package com.zummore.effectivejava.joonghyun.equals;

import java.util.Objects;

public class EqualsSuccessClazz {

    private String name;
    private Long age;

    public EqualsSuccessClazz(String name, Long age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EqualsSuccessClazz)) return false;
        EqualsSuccessClazz that = (EqualsSuccessClazz) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
