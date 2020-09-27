# JPA

## 9/22

### JPA (Java Persistence API)

* 자바 진영의 ORM 기술 표준
* 애플리케이션과 JDBC 사이에서 동작

### ORM (Object-relational mapping)

* 객체 관계 매핑
* 객체는 객체대로 설계
* 관계형 데이터베이스는 관계형 데이터베이스대로 설계
* ORM 프레임워크가 중간에서 매핑

### JPA를 사용하는 이유

* 생산성
  * 저장 : `jap.persist(member)`
  * 조회 : `Member member = jpa.find(memberId)`
  * 수정 : `member.setName("변경할 이름")`
  * 삭제 : `jpa.remove(member)`

* 유지보수
  * 필드만 추가하면된다. 
  * SQL은 JAP가 처리.
  * MyBatis는 xml을 수정해 주어야 했다.

* JPA와 패러다임의 불일치 해결
  * JPA와 상속
    * 저장 - 개발자가 할 일 : `jpa.persist(album)`
    * 조회 - 개발자가 할 일 : `Album album = jpa.find(Album.class, albumId)`

* JPA의 성능 최적화 기능
  * **트랙잭션**을 지원하는 쓰기 지원

* 지연 로딩과 즉시 로딩
  * 지연 로딩 : 객체가 실제 사용될 때 로딩
  * 즉시 로딩 : JOIN SQL로 한번에 연관된 개체까지 미리 조회

### JPA 설정하기

* JPA 설정 파일

* /META-IMF/ persistence.xml 위치

* persistence-unit name으로 이름 지정

* javax.persistence로 시작 : JPA 표준 속서

* hibernate로 시작 : 하이버네이트 전용 속성

* ```xml
  persistence-unit name="hello">
  <properties> <!-- 필수 속성 --> <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/> <property name="javax.persistence.jdbc.user" value="sa"/> <property name="javax.persistence.jdbc.password" value=""/> <property name="javax.persistence.jdbc.url" value="jdbc:h2:tcp://localhost/~/test"/> <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>
  <!-- 옵션 --> <property name="hibernate.show_sql" value="true"/> <property name="hibernate.format_sql" value="true"/> <property name="hibernate.use_sql_comments" value="true"/> <!--<property name="hibernate.hbm2ddl.auto" value="create" />--> </properties> </persistence-unit>
  ```



### 연관관계

* 예제 시나리오
  * 회원과 팀이 있다.
  * 회원은 하나의 팀에만 소속될 수 있다.
  * 회언과 팀은 다대일 관계이다.