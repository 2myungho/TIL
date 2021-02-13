## 상속

> 부모 클래스의 멤버를 자식 클래스에서 물려줄 수 있는 것.

#### 상속의 특징

> 1. 여러 개의 부모 클래스를 상속할 수 없습니다. 그러므로 extends 뒤에는 단 하나의 부모 클래스만 와야 합니다.
>
> 2. **부모 클래스**에서 **private 접근 제한을 갖는 필드와 메서드**는 **상속 대상에서 제외**합니다.

#### 부모 생성자 호출

> main 메서드에서 **자식 객체를 생성하면, 먼저 부모 객체가 생성됩니다.**
>
> 부모 객체를 생성하기 위한 생성자는 **super**을 사용합니다.
>
> super을 부모의 기본 생성자를 호출합니다.
>
> ```java
> //부모 클래스
> public class People{
>     public String name;
>     public String ssn;
>     
>     public People(String name, String ssn){
>         this.name = name;
>         this.ssn = ssn;
>     }
> }
> 
> //자식 클래스
> public class Student extends People{
>     public int StudentNo;
>     
>     public Student(String name, String ssn, int studentNo){
>         super(name, ssn); //부모 생성자 호출
>         this.studentNo = studentNo;
>     }
> }
> 
> //자식 객체 이용
> public class StudentExample{
>     public static void main(String[] args){
>         Student student = new Student("홍길동", "1234561234567", 1);
>         System.out.println("name : " + student.name);
>         System.out.println("ssn : " + student.ssn);
>         System.out.println("studentNo : " + student.studentNo);
>     }
> }
> ```



#### 메소드 재정의 (오버라이딩)

> 자식 클래스에서 부모 클래스의 메소드를 다시 정하는 것.
>
> ##### 규칙
>
> * 부모와 동일한 (**리턴 타입, 메소드 이름, 매개 변수 목록**)을 가져야 합니다.
>
> * 접근 제한을 부모 클래스 보다 더 강하게 재정의할 수 없습니다.
> * 새로운 예외를 throws 할 수 없습니다.

#### 부모 메소드 호출

> 자식 클래스에서 부모 클래스의 메소드를 재정의하게 되면, **부모 클래스의 메소드는 숨겨지고** 재정의된 **자식 클래스만 사용**됩니다.
>
> super로 부모 메소드를 호출하여 같이 사용할 수 있습니다.
>
> ```java
> @Override
> public void fly(){
>     if(flyMode == SUPERSONUC){
>         System.out.println("초음속 비행합니다.");
>     }else{
>         super.fly(); //부모 클래스의 오버라이딩 전의 메소드 사용
>     }
> }
> ```
>
> 

