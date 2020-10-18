## Spring Security

> 인증(Authentication)과 인가(Authorization)를 담당하는 스프링 프레임워크이다.
>
> 인증 : 사용자의 아이디와 비밀번호가 올바른지 확인하는 일련의 작업과정
>
> 인가 : 사용자가 서비스를 사용할 수 있는 권한
>
> 즉 사용자의 인증이 올바르면 서버에서 제공하는 서비스를 이용할 수 있는 권한을 관리한다.



* JwtAuthenticationEntryPoint.java
  * AuthenticationEntryPoint 인터페이스를 사용함으로서, 인증이 필요한 resource에 엑세스 하려고 시도 할 때에 호출하게 된다. 그 중에서 예외가 발생할 때마가 이 메소드가 호출하게 된다.
  * 이 클래스틑 인증절차 없이 자원에 엑세스 하려고 시도하는 클라이언트에게 401 오류를 반환하는데 사용됩니다.

* UserPrincipal.java
  * UserDetails를 구현할 UserPrincipal클래스를 정의
  * spring security는 지금 작성하는 UserPrincipal 객체에 저장 된 정보를 사용하여 인증 및 권한부여를 수행하게 됩니다.

* JwtTokenProvider.java (JWT 발급 클래스)
  * 사용자가 성공적으로 로그인 한 후 JWT를 생성하고, JWT의 유효성을 검사하는데 사용됩니다.
  * 

* JwtAuthenticationFilter  (시큐리티 필터, 보안 인증 필터)

  * filter의 Authoriztion 헤더에서 가져온 JWT를 파싱하고, 사용자의 ID를 읽습니다.

    그 후 데이터베이스에서 사용자의 세부 정보를 가져오고 스프링 시큐리티(Spring Security) 내에서 인증을 설정 해 줍니다.

  * JWT인증 토큰을 읽고, 유효성을 검사하고, 토큰과 관련된 세부사항을 로드합니다.

* ccurrentUser