## finalizer와 cleaner사용을 피하라

---

### 요약
`cleaner는 안전망 역할이나 중요하지 않은 네이티브 자원회수용으로만 사용하자.
물론 이런 경우라도 불확실성과 성능저하에 주의해야한다.`

---

### 주의
 - 실행을 예측할 수 없고, 느리고, 일반적으로 불필요하다.