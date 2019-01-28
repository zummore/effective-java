package com.zummore.effectivejava.ryudung.ch04.item24;

public class StaticMemberClass {
    private String name;

    public static class InnerClass{
        private String innerName;

        public String getInnerName() {
            return innerName;
        }
    }
}
