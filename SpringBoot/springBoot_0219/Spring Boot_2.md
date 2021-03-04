##### @RequestParam

> ```java
> RequestParam("가져올 데이터의 이름")[데이터타입][가져온 데이터를 담을 변수]
> ```
>
> ```java
> @GetMapping("hello-mvc")
> public String hellMvc(@RequestParam("name") String name, Model model){
>     //ex) 가져올 데이터의 이름은 주소창의 name = 1 일 때 name 변수에 1이 저장된다.
>     model.addAttribute("name", name);
>     return "hello-template";
> }
> ```



##### @ResponseBody

>Http body부에 직접 return 데이터를 넣어주겠다는 의미
>
>templates를 만든 api는 ResponseBody를 사용하지 않고 html 방식으로 작성된다. (ResponseBody 시용시 return된 값만 화면에 출력)
>
>ResponseBody를 사용하는 api는 **JSON 형태**로 http body부에  문자를 직접 반환 **(View 없이)**



**@RequiredArgsConstructor**

> 초기화 되지 않은 **final 필드에 대해 생성자를 생성**해준다.
>
> **의존성 주입(DI)의 편의성**을 위해서 사용된다.
>
> Lombok 어노테이션이다.
>
> ```java
> //@RequiredArgsConstructor 사용
> @Controller
> @RequiredArgsConstructor
> public class MemberController{
>     private final MemberService memberService;
> }
> 
> //미사용
> @Controller
> public class MemberController{
>     private final MemberService memberService;
>     
>     @Autowired
>     public MemberController(MemberService memberService){
>         this.memberService = memberService;
>     }
> }
> @Service
> public class MemberService{
>     private final MemberRepository memberRepository;
>     
>     public MemberService(MemberRepository memberRepository){
>         this.memberRepository = memberRepository;
>     }
> }
> ```
>
> 



#### 일반적인 웹 애플리케이션 계층 구조

> **컨트롤러**
>
> * 웹 MVC의 컨트롤러 역할
>
> **서비스**
>
> * 핵심 비즈니스 로직 구현
> * ex) 회원은 중복 가입이 불가능하다.
>
> **도메인**
>
> * 비즈니스 도메인 객체
> * ex) 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리됨
>
> ##### 리포지토리
>
> * 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리



#### 컴포넌트 스캔과 자동 의존관계 설정

> **@Component**
>
> * @Component 어노테이션이 있으면 스프링 빈으로 자동 등록된다.
> * 내부에 @ComponentScan 가 있는데 이 어노테이션은 어디서부터 컴포넌트를 찾아볼 것인지 알려주는 역할을 한다.
> * @Controller, @Repository, @Service 등도 스프링 빈으로 자동 등록된다.
> * 예제 기준으로 main 메소드가 있는 hello.hellospring 패키지 하위에서만 @Component가 작동된다.

> 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 **싱글톤**으로 등록한다. (유일하게 하나만 등록해서 공유한다.)
>
> 같은 스프링 빈이면 모두 같은 인스턴스다.
>
> **Spring IoC 컨테이너**가 관리하는 **자바 객체**를 **빈(bean)**이라는 용어로 부른다.



#### 자바 코드로 직접 스프링 빈 등록하기

> @Service와 @Repository 어노테이션을 사용하지 않을 때 사용한다.
>
> 컴포넌트 스캔으로 빈을 등록하면 구현 클래스를 변경할 시 여러 코드를 변경하게 된다.
>
> 자바 코드로 직접 스프링 빈을 구현하면 구현 클래스 변경시 이 부분의 스프링 빈만 변경해주면 된다.
>
> ```java
> @Configuration
> public class SpringConfig{
>     @Bean
>     public MemberService memberService(){
>         return new MemberService(memberRepository());
>     }
>     
>     @Bean
>     public MemberRepository memberRepository(){
>         return new MemoryMemberRepository;
>     }
> }
> ```



#### @Autowired 

>  Autowired를 통한 DI는 Controller, Service 등과 같이 스프링이 관리하는 객체에서만 동작한다.
>
> @Component를 선언하거나 Config에 빈 설정을 해야만 동작한다.
>
> 내가 직접 생성한 객체에서는 동작하지 않는다.



#### 의존성 주입 3가지

> **생성자 주입 (권장)(의존 관계가 실행중에 동적으로 변할 일이 거의 없기 때문에 생성자 주입을 권장)**
>
> * ```java
>   private final MemberService memberService;
>   @Autowired
>   public MemberController(MemberService memberService){
>       this.memberService = memberService;
>   }
>   ```
>
> **필드 주입 (좋지 않음)**
>
> * ```java
>   @Autowired private MemberService memberservice;
>   ```
>
> **setter 주입 (누군가가 호출할 때 public으로 열려 있다.)**
>
> * ```java
>   private final MemberService memberService;
>   @Autowired
>   public setMemberController(MemberService memberService){
>       this.memberService = memberService;
>   }
>   ```





