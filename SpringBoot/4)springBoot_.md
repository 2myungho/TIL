## 웹 계층 개발

* 홈 화면과 레이아웃
* 회원 등록
* 회원 목록 조회
* 상품 등록
* 상품 목록
* 상품 수정
* 변경 감지와 병합 (jpa에서 어떻게 데이터를 수정하는게 올바른 방법인가? 에 대해서)
* 상품 주문
* 주문 목록 검색, 취소



* HomeController
  * Logging : 애플리케이션 실행에 대한 추적을 기록하기 위해 어딘가에 메세지를 작성하는 것
    * Log4J 
    * Logback : Log4J의 후속 버전
    * **SLF4J (Simple Logging Facade for Java**): Log4J  또는 Logback 과 같은 백엔드 Logger의 프레임워크



* 

* **Thymeleaf**

* 

* 부트스트랩 CDN 주소 https://getbootstrap.com/docs/4.4/getting-started/introduction/

  ```html
  ex) <head th:replace="fragments/header :: header"> <!-- fragments폴더의 header를 사용-->
  ```



* javax.validation 참고하기.



### 공부중 생겻던 에러

```JAVA
unexpected token: member near line 1, column 15 [select m from member m]
//모든 memberList를 불러오려고 했는데 이 에러가 났다 원인은 JPQL 이었다
//MemberRepository에서 
    return em.createQuery("select m from member m", Member.class)
    //이걸
    return em.createQuery("select m from Member m", Member.class) //Member로 고쳐주어야 한다.
    //JPQL은 자바코드를 기본으로 따라갑니다.
```

