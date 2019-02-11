package com.zummore.effectivejava.joonghyun.equals;

import java.util.Objects;

public class EqualsFailClazz {

    private String name;
    private Long age;

    public EqualsFailClazz(String name, Long age) {
        this.name = name;
        this.age = age;
    }

    public boolean equals(EqualsFailClazz o) {
        if (this == o) return true;
        if (!(o instanceof EqualsFailClazz)) return false;  //묵시적 null 검사
        EqualsFailClazz that = (EqualsFailClazz) o;
        return Objects.equals(name, that.name);
    }

    public int hashCode() {
        return Objects.hash(name);
    }
}
