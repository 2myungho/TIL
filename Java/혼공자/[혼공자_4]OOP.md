## 객체 지향 프로그래밍

> 현실에서 제품을 만들 때 부품을 먼저 만들고 그 부품을 조립해서 제품을 완성합니다.
>
> 소프트웨어도 마찬가지로 부품에 해당하는 **객체**를 먼저 만들고 그 객체를 하나씩 조립해서 완성된 프로그램을 만드는 기법을 **객체지향 프로그래밍 (OOP)**이라고 합니다.

#### 객체

> 물리적으로 존재하거나 추상적으로 존재하는 것 중에서 자신의 속성을 가지고 있으면서 식별 가능한 것.
>
> 예를들어 , 물리적으로 존재하는 자동차, 자전거, 책, 사람 등과 추상적인 학과, 강의 등이 모두 객체가 될 수 있습니다.
>
> 사람을 예를들어 사람은 **이름, 나이 등의 속성**과 **웃다, 걷다 등의 동작**이 있습니다. **자바는** 이 **속성**과 **동작**을 각각 **필드**와 **메소드**라고 부릅니다.
>
> **객체 모델링 :** 현실 세계의 객체를 **소프트웨어 객체로 설계**하는 것.



#### 클래스

> 클래스는 객체를 생성하기 위한 필드와 메소드가 정의되어 있습니다.
>
> 클래스로부터 만들어진 객체를  **해당 클래스의 인스턴스**라고 합니다.
>
> > ex) 자동차 객체는 자동차 클래스의 인스턴스.
>
> 하나의 클래스로 여러 개의 인스턴스를 만들 수 있는데, 이것은 동일한 설계도 (클래스) 로부터 여러 대의 자동차를 만드는 것과 동일합니다.

#### 객체 생성과 클래스 변수

> ``` java
> 클래스 변수 = new 클래스(); //클래스() -> 생성자
> //new 연산자로 객체를 생성하고 리턴된 객체의 번지를 변수에 저장한다.
> ```
>
> **new**는 클래스로부터 **객체를 생성시키는 연산자**입니다.
>
> **new 뒤에 생성자**가 오는데, **생성자는 클래스() 형태**를 가지고 있습니다.
>
> new 연산자는 **힙 영역**에 **객체를 생성**시킨 후 객체의 **번지를 리턴**



## 클래스의 구성 요소

### 필드

> 필드는 **객체의 데이터가 저장되는 곳**입니다.
>
> Color와 같은 고유데이터, Speed와 같은 상태 데이터, 엔진, 타이어 등과 같은 부품으로 이루어져 있습니다.



### 생성자

> 생성자는 객체 생성 시 **초기화 역할**을 담당합니다.
>
> new 연산자로 클래스로부터 객체를 생성할 때 호출되어 객체의 초기화를 담당합니다.

##### 기본생성자

> **모든 클래스는 생성자가 반드시 존재**하며, 생성자를 하나 이상 가질 수 있습니다.
>
> 클래스 내부에 **생성자 선언을 생략**했더라도 바이트 코드(.class) 파일에는 **기본 생성자를 추가**합니다.
>
> ```java
> public Test(){}
> 
> //그래서 생성자를 따로 선언하지 않아도 생성자를 호출할 수 있습니다.
> Test myTest = new Test();
> ```
>
> 클래스가 public class로 선언되면 기본 생성자도 public이 붙지만 class로만 선언되면 기본 생성자에도 public이 붙지 않습니다.

##### 생성자 선언

> 생성자는 **리턴 타입이 없고** **클래스 이름과 동일**합니다.
>
> 생성자에 **this**를 사용하는 이유는 **필드와 매개변수가 같은 이름을 사용할 수 있게 하기 위함**입니다.
>
> > 필드와 매개변수 이름이 같다면 생성자 내부에서는 매개 변수가 필드 보다 우선순위가 높기 때문에 생성자가 필드에 접근할 수 없습니다. 
>
> ```java
> //생성자 선언
> public class Car{
>     //필드
>     String color;
>     int cc;
>     
> 	//생성자
>     Car(String color, int cc){
>         //this.color는 필드를 나타내고 = color는 매개 변수를 나타냅니다.
>         this.color = color;
>         this.cc = cc;
>     }
> }
> 
> //생성자 호출해서 객체 생성
> public class CaeExample{
>     public static void main(String[] args){
>         Car myCar1 = new Car("검정", 3000);
>         System.out.println("color : " + myCar1.color); //color : 검정
>         System.out.println("cc : " + myCar1.cc); //cc : 3000
>             
>         Car myCar2 = new Car("파랑", 5000);
>         System.out.println("color : " + myCar2.color); //color : 파랑
>         System.out.println("color : " + myCar2.cc); // cc : 5000
>     }
> }
> ```

##### 생성자 오버로딩

> **매개 변수를 달리하는 생성자**를 **여러 개 선언**하는 것을 말합니다.
>
> 위에 코드에서 Car 객체를 생성할 때 외부에서 **제공되는 데이터가 없다면 기본 생성자로 Car 객체를 생성**해야하고,
>
> **color나 cc 중에 하나만 필요**하다고 하면 **매개변수가 하나인 객체를 생성**할 수 있어야 합니다.
>
> ```java
> //생성자 선언
> public class Car{
>     //필드
>     //company 필드와 같이 "현대자동차"로 초기값을 준 경우 Car클래스로 객체를 생성시 모든 객체에 company 필드에는 "현대자동차" 값이 초기값으로 저장되어 있습니다.
>     String company = "현대자동차"
>     String color;
>     int cc;
>     
>     //기본 생성자
>     Car(){}
> 	//생성자2
>     Car(String color){
>         this.color = color;
>     }  
>     //생성자3
>     Car(String color, int cc){
>         this.color = color;
>         this.cc = cc;
>     }
> }
> 
> //생성자 호출해서 객체 생성
> public class CaeExample{
>     public static void main(String[] args){
>         Car myCar1 = new Car();
>         System.out.println("company : " + myCar1.company); //company : 현대자동차
>         
>         Car myCar2 = new Car("검정");
>         System.out.println("company : " + myCar2.company); //company : 현대자동차
>         System.out.println("color : " + myCar2.color); //color : 검정
>             
>         Car myCar3 = new Car("파랑", 5000);
>         System.out.println("company : " + myCar3.company); //company : 현대자동차
>         System.out.println("color : " + myCar3.color); //color : 파랑
>         System.out.println("cc : " + myCar3.cc); // cc : 5000
>     }
> }
> ```



### 메소드

