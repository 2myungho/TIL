# Spring MVC

### MVC 패턴의 개념

* Model - View - Controller
* 사용자 인터페이스로부터 비지니스 로직을 분리하기 위함이다.
* 사용자 인터페이스와 비지니스 로직을 서로 영향 없이 쉽게 고칠 수 있는 애플리케이션을 만들 수 있다.
* **Model** 
  * 애플리케이션의 정보
  * 비지니스 로직 및 데이터 처리 담당
  * **DAO 클래스** (data access object : 데이터 접근 객체) , **Service 클래스** (비지니스 로직) 에 해당
* **View**
  * 사용자에게 제공할 화면
  * 모델이 처리한 결과 데이터의 화면 생성 담당
  * **Html과 JSP**를 사용하여 작성할 수 있다.
* **Controller**
  * Model과 View 사이의 상호 작용을 관리
  * 요청처리 및 흐름 제어 담당
  * 모델이 업무수행을 완료하면, 그 결과를 가지고 화면을 생성하도록 뷰에게 전달
  * **Servlet**과 JSP를 사용하여 작성

### MVC 패턴의 개념

1. 클라이언트가 컨트롤러의 요청한다.
2. 컨트롤러는 클라이언트가 보낸 요청을 받아서 모델을 호출한다.
3. 모델이 처리한 결과를 다시 컨트롤러에 돌려준다
4. 컨트롤러는 모델이 준 결과를 받아서 화면 생성 요청을 뷰에 보낸다.
5. 뷰는 화면을 생성하고 결과 화면을 컨트롤러에 다시 돌려준다.
6. 컨트롤러가 브라우저에 응답을 한다.

### 모델2 아키텍쳐 개념

* 모델1 아키텍쳐 : Controller 역할을 JSP가 담당함.
* **모델2 아키텍쳐 :** Controller 역할을 Servlet이 담당함. 주로 사용한다.
  * 1.  클라이언트가 Controller 서블릿에게 요청을 보낸다.
    2. 서블릿은 모델객체를 호출한다. (service, DAO)
    3. 모델객체는 DB와 연동해서 SQL문을 실행한다.
    4. SQL의 결과 값을 다시 모델 객체에 전달해준다.
    5. 모델 객체는 SQL문의 결과 값을 VO객체에 저장해준다.
    6. 값이 저장된 VO객체를 Controller 서블릿에 돌려준다.
    7. Controller 서블릿은 VO객체를 View JSP에 전달해준다.
    8. JSP가 VO객체를 참조하게 된다
    9. 결과 화면을 서블릿에게 전해주고 서블릿이 클라이언트에게 최종적인 응답을 주게 된다.

### Front Controller 패턴 아키텍쳐

* Front Controller는 클라이언트가 보낸 요청을 받아서 공통적인 작업을 먼저 수행한다.

* Front Controller는 적절한 세부 Controller에게 작업을 위임한다.

* Spring MVC도 Front Controller 열할을 하는 **DispatcherServlet**이라는 클래스를 계층의 맨 앞단에 놓고, 서버로 들어오는 모든 요청을 받아서 처리하도록 구성.

* #### DispatcherServlet 클래스

  * 클라이언트의 요청을 받아서 Controller에게 클라이언트의 요청을 전달하고, 리턴한 결과 값을 View에게 전달하여 알맞은 응답을 생성한다.
  * Front Controller 패턴을 적용한다.
  * web.xml에 설정한다.

### Model And View

* Controller가 처리한 **데이터** 및 **화면에 대한 정보**를 보유한 객체이다.



### Spring MVC의 주요 구성 요소의 요청 처리 과정

1. 클라이언트의 요청이 DispatcherServlet에게 전당된다.
2. DispatcherServlet은 HandlerMapping을 사용하여 클라이언트의 요청을 처리할 Controller를 획득한다.
3. DispatcherServlet은 Controller 객체를 이용하여 클라이언트의 요청을 처리한다.
4. Controller는 클라이언트 요청 처리 결과와 View 페이지 정보를 담은 ModelAndView 객체를 반환한다.
5. DispatherServlet은 ViewResolver로 부터 응답 결과를 생성할 View 객체를 구한다.
6. View는 클라이언트에게 전송할 응답을 생성한다.

### Spring MVC 어노테이션

* @Controller
  * Controller 클래스 정의
* @RequestMapping
  * HTTP 요청 URL을 처리할 Controller 메소드 정의
* @RequestParam
  * HTTP 요청에 포함된 파라미터 참조 시 사용
  * 파라미터 값을 넘겨주지 않으면 400에러가 난다. 
* @ ModelAttribute
  * HTTP 요청에 포함된 파라미터를 모델 객체로 바인딩

* @PathVariable
  * 파라미터를 URL 형식으로 받을 수 있도록 해준다.







```
(@RequestParam("userid") String id)
```

modelAndView에는 userDetail, userVO

userDetail.jsp에 userVO넣어줌

dispatcher 은 .do로 끝나는 요청만 받겠다.

@RequestMapping은 요청온 url에 해당하는 컨트롤러를 찾아주는 역할

RequestMapping안에 있는 값은 index.jsp에서 action 값과 같아야 한다.



JSTL

* 자바 소스를 html 태그처럼 쓴다.
* <% %> 



data 이동 순서

userForm.jsp → UserRegController.java → UserRegService.java → UserDaoInterface.java → UserDaoMapper.xml





### data가 깨져서 저장될 경우

web.xml에

```xml
 <!-- UTF-8 관련 파라미터 설정 -->
<filter>
    <filter-name>encodingFilterUTF8</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>encodingFilterUTF8</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```



jsp 파일에

```jsp
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
%>
<% 
   request.setCharacterEncoding("utf-8");
%>
```

Server.xml

```xml
<Connector connectionTimeout="20000" port="8087" protocol="HTTP/1.1" redirectPort="8443"/>

<Connector connectionTimeout="20000" port="8087" protocol="HTTP/1.1" redirectPort="8443" URIEncoding="utf-8"/>
```



여기서 controller의 역할은 웹에서 처리해야할 데이터를 받고, 

이 데이터를 담당할 service를 선택하여 호출합니다.

그리고 처리한 데이터를 다음 페이지에서 볼 수 있게 셋팅하며 이동할 페이지를 리턴합니다.

service는 데이터를 dao를 통해 넘겨주거나 받으면서 비즈니스 로직을 수행하는 역할을 합니다.

dao는 DB를 통해 데이터를 조회하거나 수정 삭제 하는 역할을 합니다.

vo는 DB에 있는 테이블 컬럼 값을 java에서 객체로 다루기 위해 사용합니다.