Spring MVC



컨트롤러를 만든다.

index.jsp를 만든다

form에서 action을 컨트롤러에 RequestMapping으로 넘겨준다.



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