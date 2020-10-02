### 인프런 Spring Boot  강의



## 환경설정

### 1). https://start.spring.io/ 에서 Gradle 프로젝트로 시작한다.

![image-20201001212351110](C:\Users\SAMSUNG\AppData\Roaming\Typora\typora-user-images\image-20201001212351110.png)

### 2). 압축파일 해제 후 이클립스에서 Gradle 프로젝트로 import 한다.

### 3). 서버가 제대로 실행 되는지 확인한다

* 만약 기본 8080 포트 번호가 오류 난다면 application.yml 에서 포트 번호를 따로 정해준다.

```yml
server:
  port: 8086
```

* resources 폴더에 있는 데이터를 수정했을 때는 서버를 재시작 해주어야 한다.
* **spring-boot-devtools 라이브러리를 사용하면 서버 재시작을 하지 않아도 된다.**



### 4). H2 데이터베이스 연결

```
http://192.168.79.1:8082/login.jsp?jsessionid=40cc07f94debcb9015fcf56cebe3b870
이런식으로 되어 있는 주소를
http://localhost:8082/login.do?jsessionid=40cc07f94debcb9015fcf56cebe3b870
localhost:8082로 변경해준다
```

* JDBC URL에 **jdbc:h2:~/jpashop** 로 최소 한 번 연결해주면 ~/jpashop.mv.db 파일이 생성되는 걸 확인해 준다.

![H2(1)](C:\Users\SAMSUNG\LMH\TIL\SpringBoot\image\H2(1).png)

* 왼쪽 상단 연결 끊기로 로그인 창으로 돌아와서 이후부터는 **jdbc:h2:tcp://localhost/~/jpashop** 이 주소로 접속한다.

### 5). application.yml 설정

application.yml

```yml
spring:
  datasource:
    url: jdbc:h2:tcp://localhost/~/jpashop
    username: sa
    driver-class-name: org.h2.Driver #데이터 베이스 설정이 완료 됨
   
  jpa:
    hibernate:
      ddl-auto: create
    properties:
      hibernate:
#        show_sql: true
        format_sql: true
      
logging:
  level:
   org.hibernate.SQL: debug
```



### 6). Entity 클래스 생성

```java
@Entity
@Getter @Setter
public class Member {
	
	@Id @GeneratedValue
	private Long id;
	private String username;
}
```

### 7).  Repository 생성

```java
//레포지토리는 엔티티를 찾아주는 것 ex)DAO랑 비슷함
@Repository
public class MemberRepository {
	
	@PersistenceContext //영속성
	private EntityManager em;
	
	public Long save(Member member) {
		em.persist(member); //em.persist (Entity를 영속성 컨텍스트에 저장하는 것)
		return member.getId();
	}
	
	public Member find(Long id) {
		return em.find(Member.class,id); //DB보다 먼저 1차 캐시를 조회한다. 1차 캐시에 해당 Entity가 존재하면 바로 반환한다.
	}
}
```

### 8). JUnit Test

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
	
	@Autowired MemberRepository memberRepository;

	@Test
	@Transactional
	@Rollback(false)
	public void test() {
		//given
		Member member = new Member();
		member.setUsername("memberA");
		
		//when
		Long saveId = memberRepository.save(member);
		Member findMember = memberRepository.find(saveId);
		
		//then 두 객체를 비교
		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
		Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
	}
}
```

### 쿼리 파라미터 로그 남기기

* JUnit  실행시 value에 ??로 출력 된다.

```
insert 
    into
        member
        (username, id) 
    values
        (?, ?)
```

* Application.yml 라이브러리 추가

```yml
logging:
  level:
   org.hibernate.SQL: debug
   org.hibernate.type: trace #추가
```

* 로그를 출력해준다.

```
2020-10-02 02:05:21.356 TRACE 178324 --- [main] o.h.type.descriptor.sql.BasicBinder:binding parameter [1] as [VARCHAR] - [memberA]
2020-10-02 02:05:21.356 TRACE 178324 --- [main] o.h.type.descriptor.sql.BasicBinder:binding parameter [2] as [BIGINT] - [1]
```

* ??가 보이게 하고 싶으면 외부라이브러리 사용
* https://github.com/gavlyukovskiy/spring-boot-data-source-decorator
* build.gradle dependencies 추가

```groovy
implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.6.1'
```

* 출력문

```
insert into member (username, id) values (?, ?)
insert into member (username, id) values ('memberA', 1);
```





### jar 빌드해서 동작 확인하는 법

```
cd C:\java\springBootZip\jpashop

./gradlew clean build //빌드 초기화

cd build
cd libs
ls
(jpashop-0.0.1-SNAPSHOT.jar jar 파일이 보인다.)
java -jar jpashop-0.0.1-SNAPSHOT.jar //localhost로 접속 가능
```





| 라이브러리           | 내용                                                         |
| -------------------- | ------------------------------------------------------------ |
| Spring Web           |                                                              |
| Thymeleaf            | Thymeleaf 템플릿을 사용할 수 있게 해준다.                    |
| Spring Dara JPA      |                                                              |
| Lombok               | setter getter toString 등을 생략할 수 있게 해주는 라이브러리이다. |
| spring-boot-devtools | 번거롭게 서버 재시작을 하지 않아도 된다.                     |



## Spring Boot 어노테이션 및 용어

| 어노테이션 & 용어 | 내용                                                         |
| ----------------- | ------------------------------------------------------------ |
| Model             | model에 데이터를 실어서 데이터를 Controller에서 View로 넘길 수 있다. |
| @Transactional    | @Test 에서 사용할 때 실행후 DB를 롤백 @Rollback(false)를 사용하면 비활성화 가능 |
|                   |                                                              |
|                   |                                                              |
|                   |                                                              |
|                   |                                                              |
|                   |                                                              |
|                   |                                                              |
|                   |                                                              |



## JPA 어노테이션

| 어노테이션              | 내용                                                         |
| ----------------------- | ------------------------------------------------------------ |
| @Entity                 | 테이블과 링크될 클래스임을 나타낸다.                         |
| @Id                     | 해당 테이블의 PK 필드를 나타냅니다.                          |
| @GeneratedValue         | PK의 생성 규칙을 나타냅니다. `@Id @GeneratedValue`           |
| @Column                 | 테이블의 컬럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 됩니다.<br />사용하는 이유는, 기본 값 외에 추가로 변경이 필요한 옵션이 있으면 사용합니다.<br />ex) VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶다. |
| @PersistenceContext     | Entity를 영구 저장하는 환경이라는 뜻 (영속성 컨텍스트)<br />**EntityManager**를 통해서 영속성 컨텍스트에 접근한다.<br />Spring Boot에서 팩토리와 같은 설정을 자동으로 해준다. |
| @Table                  | 엔티티와 매핑할 테이블을 지정하고, 생략시 매핑한 엔티티 이름을 테이블 이름으로 사용한다. |
| @Embeddable / @Embedded | jpa Entity 안의 Column을 하나의 객체로써 사용하고 싶을 때 사용 |
| @JoinColumn             | @JoinColumn(name = "member_id")  mapping을 무엇으로 할 것인가. |
| @Inheritance            | 상속관계매핑이다. 부모클래스에 설정해준다.<br />단일테이블 전략 (테이블 하나만 사용하는 전략) @Inheritance(strategy = InheritanceType.JOINED) |

| 어노테이션                         | 내용                                                         |
| ---------------------------------- | ------------------------------------------------------------ |
| @DiscriminatorColumn(name="DTYPE") | 상속관계매핑이다. 부모클래스에 설정해준다<br />하위클래스를 구분하는 용도의 컬럼이다. |
| @DiscriminatorValue("XXX")         | 상속관계매핑이다. 하위클래스에 설정해준다<br />어노테이션을 선언하지 않은 경우 기본값으로 클래스 이름이 들어간다. |
| @Enumerated                        | 자바 enum 타입 (열거타입)을 엔티티 클래스의 속성으로 사용할 수 있다.<br />EnumType.ORDINAL : enum 순서 값을 DB에 저장<br />EnumType.STRING : enum 이름을 DB에 저장 |
|                                    |                                                              |
|                                    |                                                              |
|                                    |                                                              |
|                                    |                                                              |
|                                    |                                                              |
|                                    |                                                              |