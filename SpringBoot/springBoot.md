### 인프런 Spring Boot  강의



## 환경설정

### 1). https://start.spring.io/ 에서 Gradle 프로젝트로 시작한다.

![image-20201001212351110](C:\Users\SAMSUNG\AppData\Roaming\Typora\typora-user-images\image-20201001212351110.png)

### 2). 압축파일 해제 후 이클립스에서 Gradle 프로젝트로 import 한다.

### 3). 서버가 제대로 실행 되는지 확인한다

* 만약 기본 8080 포트 번호가 오류 난다면 application.properties 에서 포트 번호를 따로 정해준다.
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



| 라이브러리           | 내용                                                         |
| -------------------- | ------------------------------------------------------------ |
| Spring Web           |                                                              |
| Thymeleaf            | Thymeleaf 템플릿을 사용할 수 있게 해준다.                    |
| Spring Dara JPA      |                                                              |
| Lombok               | setter getter toString 등을 생략할 수 있게 해주는 라이브러리이다. |
| spring-boot-devtools | 번거롭게 서버 재시작을 하지 않아도 된다.                     |



| 어노테이션 | 내용                                                         |
| ---------- | ------------------------------------------------------------ |
| Model      | model에 데이터를 실어서 데이터를 Controller에서 View로 넘길 수 있다. |

