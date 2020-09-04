## JDBC 

## 9/04

### JDBC (Java Database Connectivity)

* #### 자바에서 DB에 접근할 수 있게 해주는 API

* #### 플랫폼에 종속되지 않는다.

### JDBC 설치 방법

* #### JDBC  jar파일을 다운받는다 --> src의 build path -> config build path -> Libraries 탭 -> Classpath에 있는 파일 선택 후 Add JAR 클릭 --> Apply 

### JDBC 절차

1. #### Driver 등록

   *  **DriverManager에 해당 DBMS Driver 등록**

   * ##### `Class.forname(db.driver)`

2. #### DBMS와 연결

   * ##### DB 연결을 위한 Connection 객체 생성

   * **해당 Driver로부터 Connection instance를 획득**

   * ##### Connection 변수=  DriverManager.getConnection(url, user, password)

   * ````java
     ex)
     String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
     String user = "hr";
     String password = "hr";
     
     Connection conn = DriverManager.getConnection(url, user, password);
     ````

3. #### Statement 생성

   * ##### sql문을 전송하는 역할인 Statement 객체 생성

   * ##### **Connection instance로부터 Statement instance획득**

   * **Statement 변수 = (Connection 변수).createStatement();** 

   * ```java
     Statement stmt = conn.createStatement();
     ```

   * 

4. #### SQL 전송

   *  **Statement method를 이용하여 SQL 실행**

   * **select가 있는 쿼리문일 때 : Result 변수 = (State변수).executeQuery(sql);**

   * ##### selcet가 없는 쿼리문일 때 : 리턴 변수 = (state변수).excuteUpdate(sql);

5. #### 결과 받기

   * **실행후 결과를 ResultSet(SELECT) 혹은 int형 변수(DML)로 받아 처리**

6. #### 닫기

   * .**close()**

### prepareStatement

* **같은 Query를 반복 수행해야 하는 경우 성능이 좋다 (loop 이용이 용이하다.)**
* **하지만 코드가 길어질 수 있다.**
* **?를 사용하는 query문에 사용이 용이하다.**
* **sql 변수를 prepareStatement가 사용한다.** **(prepare : 먼저 사용한다고 생각한다.)**
* **prepareStatement는 Statement의 자식이다. Statement 타입이 사용 가능하다.**

```
PreparedStatement stmt = connection.prepareStatement(sql);
```

