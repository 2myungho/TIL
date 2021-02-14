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



#### final 클래스와 final 메소드

> final 키워드는 클래스, 필드, 메소드를 선언할 때 사용할 수 있는데, 해당 선언이 **최종 상태**이고 **결코 수정될 수 없음**을 뜻합니다.
>
> final 클래스는 부모 클래스가 될 수 없어 자식 클래스는 만들 수 없습니다.
>
> final 메소드는 재정의(오버라이딩)할 수 없습니다



#### 다형성

> 사용 방법은 동일하지만 다양한 객체를 이용해서 다양한 실험 결과가 나오게 하는 성질입니다.
>
> 예를들어 자동차 타이어를 사용하는 방법을 동일하지만 어떤 타이어를 사용하느냐에 따라 주행 성능이 달라질 수 있습니다.



#### 자동 타입 변환

> 클래스의 변환은 상속 관계에 있는 클래스 사이에서 발생합니다.
>
> **자식은 부모 타입으로 자동 타입 변환이 가능합니다.**
>
> Animal과 Cat 클래스가 다음과 같이 상속 관계에 있다고 가정합니다.
>
> ```java
> class Animal{}
> 
> class Cat extends Animal{}
> //Cat클래스로부터 Cat 객체를 생성하고 이것을 Animal 변수에 대입하면 자동 타입 변환이 일어납니다.
> Cat cat = new Cat();
> Animal animal = cat;
> 
> Animal animal = new Cat(); //도 가능하다
> ```
>
> 바로 위의 부모가 아니더라도 상속 계층에서 상위 타입이라면 자동 타입 변환이 일어날 수 있습니다.

> **부모타입으로 자동 타입 변환된 이후**에는 **부모 클래스에 선언된 필드와 메소드만 접근이 가능**합니다.
>
> 변수는 자식 객체를 참조하지만 변수로 접근 가능한 멤버는 부모 클래스 멤버로만 한정됩니다.
>
> **예외**
>
> > 메소드가 자식 클래스에서 재정의되었다면 자식 클래스의 메소드가 대신 호출됩니다.
>
> ```java
> //부모 클래스
> class Parent{
>     void method1(){...}
>     void method2(){...}
> }
> //자식 클래스
> class Child extends Parent{
>     @Override
>     void method2(){...}
>    	void method3(){...}
> }
> //호출
> class ChildExample{
>     public static void main(String[] args){
>     	Child child = new Child(); //Child 객체 생성
>         Parent parent = child; //자동 타입 변환
>         
>         parent.method1(); //부모 클래스의 메소드를 호출
>         parent.method2(); //재정의된 자식 클래스의 메소드를 호출
>         parent.method3() // 호출 불가능 (부모 클래스에 선언된 필드와 메소드만 접근이 가능)  
>     }
> }
> ```



#### 필드의 다형성

> 자동 타입 변환이 필요한 이유가 무엇일까?
>
> 그냥 자식 타입으로 사용하면 될 것을 부모 타입으로 변환해서 사용하는 이유가 무엇일까?
>
> **다형성을 위해서**입니다.
>
> 예를들어 타이어와 같은 부품을 고장이나기도 하고 더 성능이 좋은 부품으로 교체를 하기도 합니다. 프로그래밍에서도 마찬가지로 이 객체들이 다른 객체로 교체될 수 있어야 합니다.
>
> ```java
> class Car{
>     //필드
>     Tire frontLeftTire = new Tire();
>     Tire frontRightTire = new Tire();
>     Tire backLeftTire = new Tire();
>     Tire backRightTire = new Tire();
>     //메소드
>     void run(){...}
> }
> //Tire를 한국 타이어로 교체하고 싶다면 Tire 클래스의 자식 클래스가 타입이 되어도 괜찮다.
> //자식 타입은 부모 타입으로 자동 타입 변환이 되기 때문이다.
> Car myCar = new Car();
> myCar.frontRightTire = new HankookTire();
> 
> //frontRightTire에 Tire의 자식 객체가 저장되어도 Car 객체는 Tire 클래스에 선언된 필드와 메소드만 사용하므로 문제되지 않습니다.
> ```



#### 강제 타입 변환

> 부모 타입을 자식 타입으로 변환하는 것을 말합니다.



#### 추상 클래스

> 클래스들의 공통적인 특성을 추출해서 선언한 클래스를 추상 클래스라고 합니다.
>
> 추상 클래스가 부모, 실체 클래스가 자식으로 구현되어 있습니다.
>
> 예를들어 추상 클래스는 동물, 실체 클래스는 개, 고양이, 물고기 등이 됩니다.