

## 9/7

### 프레임워크와 라이브러리

* 프라임워크는 어떤 물건을 만드는 데 필요한 틀이며, 공통적으로 많이 사용되는 기능을 편리하게 제공해줌. 개발자가 개발에만 집중할 수 있도록 한다.

* 라이브러리는 스패너 망치 등이 들어 있는 공구박스라 할 수 있으며, 특정 기능에 대한 도구나 함수를 모아둔 집합

* 라이브러리는 프레임워크와 반대로 개발자가 흐름을 제어하며, 필요한 상황에 가져다가 쓰는 것.

* 어플리케이션을 만들 때의 주도권을 프레임워크는 프레임워크가 가지고 있고, 라이브러리는 개발자가 가지고 있다.

* 둘의 차이는 흐름에 대한 제어 권한이 어디에 있느냐의 차이이다.

### Spring Framework

* Java 엔터프라이즈 개발을 편하게 해주는 오픈소스 경량급 애플리케이션 프레임워크이다.



## 9/6

### IoC (Inversion of Control)

* 제어권의 역전이라고 하며 객체의 생성, 생명주기의 관리까지 모든 객체의 제어권이 바뀌었다는 것을 의미한다.
* 의존관계를 결정, 설정, 라이프사이클을 해결하기 위한 디자인 패턴

###  Ioc 컨테이너

* 스프링 프레임워크도 Ioc 컨테이너 기능을 제공한다.
* 객체를 생성하고, 의존성을 관리한다.
* POJO의 생성(특정 플랫폼에 종속하지 않은 것 일반적인 자바 객체) ,초기화, 서비스에 대한 권한을 가진다.
*  라이프사이클 관리를 해준다.

  

### DI (Dependency Injection)

* 의존성 주입
* 컨테이너가 각 클래스간의 의존관계를 빈 설정 정보를 바탕으로 자동으로 연결해주는 것을 말한다.
* 개발자는 빈 설정에서 의존관계가 필요하다는 정보를 추가해주면 된다.  (xml, 어노테이션 등 사용)
* 각 클래스간의 의존관계를 빈(Bean)설정 정보를 바탕으로 컨테이너가 자동으로 연결해준다
* DI의 종류
  * Setter Injection
    * Setter 메서드를 만들고 이를 통해 의존성을 주입한다.
  * Constructor Injection
    * 클래스의 생성자를 만들고 이를 통해 의존성을 주입한다.
  * Method Injection
    * 일반적인 자바 메서드를 만들고 이를 통해 의존성을 주입한다.

### Spring DI 컨테이너

* #### bean : Spring DI 컨테이너가 관리하는 객체를 빈이라고 한다.

*  컨테이너 역할을 담당하는 객체 종류

* BeanFactory

  * 빈을 관리한다는 의미로 빈 팩토리라고도 한다.
  * Bean을 등록 생성, 조회, 반환 관리한다.
  * 보통 어플리케이션 컨텍스를 주로 사용한다.
  * Bean을 조회할 수 있는 **getBean(name)** 메서드가 있다.  (name = 빈의 id)

* ApplicationContext

  * 빈 팩토리에 여러 기능을 추가하였다.

  * Bean을 등록 ,생성, 조회,반환 관리하는 기능은 빈팩토리와 같다.

  * Spring이 제공하는 ApplicationContext 구현 클래스가 여러 종류가 있다.

### Configuration MetaData

* config/spring_beans.xml

* ```java
  <!-- StringPrinter 클래스를 Bean으로 등록 -->
  	<bean id="strPrinter" class="myspring.di.xml.StringPrinter" />
  	<!-- ConsolePrinter 클레스를 Bean으로 등록 -->
  	<bean id="conPrinter" class="myspring.di.xml.ConsolePrinter"/>
  	
  	<!-- Hello 클래스를 Bean으로 등록 -->
  	<!--  scope : singlton, prototype, request, session -->
  	<bean id="hello" class="myspring.di.xml.Hello" scope="singleton">
  		<!-- setter injection -->
  		<!-- setName("스프링") -->
  		<property name="name" value="스프링" />
  		<!-- setPrinter(new StringPrinter()) -->
  		<property name="printer" ref="strPrinter"/> <!-- 어떤 빈의 id를 참조할지 -->
  	</bean>
  </beans>
  
  <!-- 
  	각 bean에 해당하는 생성자에 출력문을 넣으면 xml에 bean 작성 순서대로 출력 된다. 
  	클래스를 bean으로 등록하면 생성되는 객체는 싱글톤 객체로 생성 된다.
  	property로 set메서드에 파라미터와 값을 전달해줄 수 있다.
  -->
  ```

* 

### DI의 장점

* 코드가 단순해진다.
* 컴포넌트 간의 결합도가 제거된다.

### DL (Dependency Lookup)

* 의존성 검색
* 컨테이너의 저장 되어 있는 Bean에 접근하기 위해 컨테이너가 제공하는 API를 이용하여 Bean을 Lookup을 한다.
* DL을 사용하면 컨테이너 종속성이 증가하여 주로 DI를 사용한다.

 

### jUnit

* Java에서 독립된 단위테스트(Unit Test)를 지원해주는 프레임워크이다
* buld path -> class path 선택 -> Add library -> junit 선택 -> 버전 선택 -> 메이븐 업데이트

* 이클립스에 내장되어 있다
* @Test 어노테이션 선언, public void로 선언
* Assert.assertSame() - 주소비교
* Assert.assertEquals() - 값 비교

### Spring-Test에서 지원하는 어노테이션

* @RunWith(SpringJUnit4ClassRunner.class)
  
  * SpringJUnit4ClassRunner라는 클래스를 지정해주면 jUnit이 테스트를 진행하는 중에 ApplicationContext를 만들고 관리하는 작업을 진행해 준다.
* @ContextConfiguration(locations = " ")
  
* 스프링 빈 설정파일의 위치를 지정할 때 사용되는 어노테이션이다.
  
* ```java
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration(locations = "classpath:config/spring_beans.xml")
  ```

* @Autowired

  * 해당 변수에 **자동으로 빈(Bean)을 매핑** 해준다.
  * 스프링 빈(Bean) 설정 파일을 읽기 위해 굳이 GenericXmlApplicationContext를 사용할 필요가 없다.
  * getBean()을 대체하는 어노테이션

  ```java
  //getBean을 이용한 방법
  Hello hello2 = factory.getBean("hello", Hello.class);
  //@Autowired을 이용한 방법
  @Autowired
  //@Qualifier는 @Autowired와 같이 사용하며 똑같은 타입의 빈이 있을 때 원하는 빈을 호출하고 싶을 때 사용 
  @Qualifier("helloC")
  Hello hello;
  ```

  

## 9/8

### Bean 등록 Annotation

* @Component 
  : 컴포넌트를 나타내는 일반적인 스테레오 타입으로 
  <bean> 태그와 동일한 역할을 한다. 

  * Component( ) 공백으로 두면 자동으로 클래스의 소문자로 id가 자동으로 들어간다.
    
  * ```html
    <!-- 어노테이션이 선언된 클래스들을 스캔하기 위한 설정 -->
    <!-- xml에서 설정 -->
    <context:component-scan base-package = "myspring.di.annot" />
    ```

* Repository
  * DAO
* Service
* Controller



* @Resource
  : 어플리케이션에서 필요로 하는 자원을 자동 연결할 때 사용된다.
  @Resource는 변수, setter 메서드에 적용 가능하다. 
  의존하는 객체를 주입할 때 주로 Name을 이용하게 된다. 
* @Bean
  : 새로운 빈 객체를 제공할 때 사용되며, 이것이 적용된 메서드의 이름을 Bean의 식별값으로 사용한다.
* @Config
  : 클래스에 이것을 선언하는 것은 스프링 IoC 컨테이너가 해당 클래스를 Bean 정의의 설정으로 사용한다는 것을 나타낸다.

### 전략 1.  XML에 설정하는 방식

### 전략 2. Annotation + xml 설정을 사용해서 설정하는 방식



### 전략3. 어노테이션 설정 단독 사용 (ver3.0)

* XML을 전혀 사용하지 않는다.
* @Configuration과 @Bean 어노테이션을 이용해서 스프링 컨테이너에 새로운 빈 객체를 제공할 수 있다.



### Spring JDBC

### Apache Commons DBCP