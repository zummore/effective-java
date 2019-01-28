# Super type token

- Generic 은 Erasure 에 의해 Compile 시에 제거된다. 그래서 Generic 만으로는 Type Parameter 를 찾을 수 없다.
- Super Type Token 은 Generic 의 Type 제거 방식을 우회하여 Runtime 에서도 Generic Type 정보를 사용하도록 하는 방식이다.
- 즉 Super Type Token 은 다른 Generic 을 가진 다른 class 가 Runtime 에서도 Type 을 추론할 수 있도록 도와주는 class 로, 명시적으로 선언되어있도록 하는게 올바른 사용법인 class 이다. 이런 class 를 똑같은 Raw Type 을 갖도록 사용하는 것은 잘못된 사용법이다.

**결론 : Generic Type 추론을 돕기 위해 사용되는 Super Type Token 을 똑같은 Generic 사용법으로 사용하는 것은 잘못된 사용법이다.**