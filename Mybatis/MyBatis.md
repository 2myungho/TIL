# MyBatis

## 9/9

### MyBatis의 개요

* 자바 객체와 관계형 데이터베이스 사이의 **자동 Mappping 기능**을 지원하는 **ORM 프레임 워크**이다
* **ORM** (Object Relationam Mapping)
  * 객체 관계 맵핑
  * 개발자가 개발에만 더 집중할 수 있게 도와준다.
  * 재사용 및 유지보수의 편리성의 증가한다.
  * DBMS에 대한 종속성이 줄어든다.
* MyBatis는 SQL을 별도의 파일로 분리해서 관리하게 해준다.
* 도메인 객체나 VO객체를 중심으로 개발이 가능하다.
  * VO (Value object) : 식별자가 없이 값만 들고 있는 것이 특징인 object

### MyBatis의 특징

* 쉬운 접근성과 코드의 간결함
* **SQL문과 프로그래밍(자바) 코드의 분리**
* 다양한 프로그래밍 언어로 구현가능

### MyBatis3의 주요 컴포넌트 역할

* **SqlMapConfig.xml**
  * 데이터베이스의 접속 주소 정보나 Mapping 파일의 경로 등의 고정된 **환경정보**를 설정한다.
  * MyBatis **설정파일** (DataSource)
* **SqlSessionFactoryBuilder**
  * MyBatis 설정을 바탕으로 **SqlSessionFactory**을 생성한다.
* **SqlSessionFactory**
  * SqlSession 생성
* **SqlSession**
  * Sql문 실행
  * 가장 핵심이다.
* **mapping 파일**
  * SQL문과 OR Mapping을 성정한다.
  * SQL문을 포함하고 있는 XML파일 (ex ) user.xml)

### MyBatis3 주요 컴포넌트 호출 순서

* **Application**에서 먼저 **SqlSessionFactoryBuilder**라 하는 인터페이스를 호출한다.
*  **SqlSessionFactoryBuilder** 가 **MyBatisConfigFile** 파일 정보를 읽는다.
*  **SqlSessionFactoryBuilder**가 **SqlSessionFactory**를 생성한다.
* 개발자가 **Application**에 있는 생성, 삭제 등의 메서드를 호출한다. 
*  **SqlSessionFactory**를 **Application** 상에서 호출한다.
* **SqlSessionFactory**가 **SqlSession**컴포넌트를 생성한다.
* **SqlSession**을 개발자가 작성한 코드에 리턴한다.
* 리턴 받아서 **SqlSession**에 있는 메서드를 호출한다.
* **SqlSession**이 개발자가 작성한 SQL문을 호출한다.

### Mybatis-Spring의 주요 컴포넌트

* Mybatis-Spring은 Mybatis와 Spring의 연동을 좀 더 쉽게 할 수 있도록 제공하는 오픈소스이다.
* **SqlSessionFactoryBean**
  * **SqlSessionFactoryBean**이 **SqlSessionFactory**를 생성해준다.
* **SqlSessionTemplate**
  * **SqlSessionFactory**를 기반으로 **SqlSessionTemplate**가 만들어진다.
  * 생성된 **SqlSessionFactor**를 **SqlSessionTemplate**의 생성자에게 전달되도록 설정한다.
  * SqlSession과 같이 SqlSessionTemplate도 **SQL문을 실행**시켜주는 역할을 한다.
  * SqlSession은 thread-safe를 지원하지 않기 때문에 요청이 올 때마다 새로운 객체를 새로 생성해야한다.
  * SqlSessionTemplate은 thread-safe을 지원하기 때문에 멀티스레드 환경에서도 개발자가 편하게 개발할 수 있다.
* 개발자가 해야하는 일
  * **SqlSessionFactoryBean**을 Bean설정파일에 **SpringBean으로 등록**해주어야 한다.
  *  **SqlSessionTemplate**도 **SpringBean으로 등록**해주어야 한다.

* **MyBatis 설정 파일** (sqlMapConfig.xml)
  * VO 객체의 정보를 전달한다.
  * Spring과 같이 사용했을 경우
    *  **XML설정파일에는 VO 객체의 정보만 작성**해주면 된다.
    * 스프링과 연동한 경우에 **DB접속 정보, mapping파일의 위치정보 등과 같은 내용**은 **Spring Bean 설정 파일에 작성**해준다.
* **Mapping 파일**
  * ex) user.xml, student.xml
  * SQL문과 OR Mapping을 설정한다.

* **Spring Bean설정 파일**
  * **SqlSessionFactoryBean을 Bean 등록**할 때 **DataSource 정보**와 **MyBatis Config 파일 정보**, **Mapping 파일 정보**를 함께 설정한다. (위치 정보)
  * SqlSession Template를 Bean으로 등록한다.

### Mapping 파일 (SQL문 포함)

* ```XML
  <select id="selectUserById" parameterType="string" resultType="User"> <!-- MyBatist 설정 파일의 allias -->
  		select * from users where userid=#{value}
  </select>
  <insert id="insertUser" parameterType="User"> 
  		insert into users
  		values(#{userId},#{name},#{gender},#{city} ) <!-- UserVO에 들어 있는 -->
  </insert>
  ```

  

### MyBatis Configuration 파일 (Mybatis 설정파일)

* ```xml
  <configuration>
  	<typeAliases>
          <!-- type에는 해당되는 VO객체 -->
          <!-- type 값을 alias로 짧게 전해줌 -->
  		<typeAlias alias="User" type="myspring.user.vo.UserVO" /> 
  		<typeAlias alias="Person" type="myspring.user.vo.PersonVO" />
  		<typeAlias alias="Student" type="myspring.user.vo.StudentVO" />
  		<typeAlias alias="Dept" type="myspring.user.vo.DeptVO" />
  		<typeAlias alias="Course" type="myspring.user.vo.CourseVO" />
  		<typeAlias alias="CourseStatus" type="myspring.user.vo.CourseStatusVO" />
  	</typeAliases>
  </configuration>
  ```

### Spring Bean 설정 파일 (위치 정보)

* ```xml
  <!-- SqulSessionFactoryBean 클래스를 Bean으로 등록 -->
  	<!-- SqlSessionFactoryBean이  sqlSessionFactory를 생성 -->
  	<bean id ="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
          <!-- 데이터베이스 접속을 위해 컬렉션을 리턴해주는 ref로 dataSource를 받아온다. -->
  		<property name="dataSource" ref="dataSource" />
          <!-- VO객체에 대한 정보를 포함하고 있는 /SqlMapConfig.xml를 설정 -->
  		<property name="configLocation" value="classpath:config/SqlMapConfig.xml" />
          <!-- SQL문을 포함하는 Mapping파일을 설정해준다. -->
  		<property name="mapperLocations" >
  			<list>
                  <!-- 이 위치에 있는 모든 Mapping 파일을 받아온다. -->
  				<value>classpath:config/*Mapper.xml</value>
  				<!-- <value>classpath:config/StudentMapper.xmll</value> -->
  			</list>
  		</property>
  	</bean>
  <!-- SqlSessionTemplate 클래스를 Bean으로 등록 -->
  	<bean id ="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
  		<constructor-arg ref="sqlSessionFactory"/>
  	</bean>
  ```

### Test

```java
//("studentNS.insertStudent",student) = nameSpace + mapping파일의 id 값, student의 정보를 가지고 있는 VO객체에 값을 주고 student는 insertUser의 parameterType에 mapping된다. 
StudentVO student = new StudentVO(1003,"스프링학생",21,"2학년","야간",new DeptVO(10));
int cnt = session.insert("studentNS.insertStudent",student);
System.out.println("등록 건수 : " + cnt);

UserVO user = session.selectOne("userNS.selectUserById", "dooly");
System.out.println(user);
```

### DAO / DTO ( =VO )

* **DAO 정의** (Data Access Object)
  * Database로 data에 접근하기 위한 객체

* **DTO 정의** (Data Transfer Object)
  * 계층간 데이토 교환을 위한 자바 빈즈
  * 값만 들고 있다.