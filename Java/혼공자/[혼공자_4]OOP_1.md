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

> 객체의 동작에 해당하는 중괄호 {} 블럭

##### 매개 변수가 있는 메소드 호출

> ```java
> //메소드 선언
> public class Calculator{
>     //메소드
>     void powerOn(){
>         System.out.println("전원을 켭니다")
>     }
>     
>     int plus(int x, int y){
>         int result = x + y;
>         return result;
>     }
> }
> //메소드 호출
> public class CalculatorExample{
>     public static void main(String[] args){
>     	Calculator myCalc = new Calculator();
>         myCalc.powerOn(); 
>         
>         int result  = myCalc.plus(5, 6);
>         System.out.println("result : " + result);
>     }
> }
> //전원을 켭니다.
> //11
> ```

##### 매개 변수의 개수를 모를 경우

> 예를들어 여러 개의 수를 모두 합산하는 메소드를 선언해야할 때,
>
> 해결책을 다음과 같이 매개  변수를 **배열 타입으로 선언**합니다. `int sum1(int[] values){}`
>
> ```java
> //메소드 선언
> public class Computer{
>     int sum1(int[] values){
>         int sum = 0;
>         for(int i = 0; i < values.length; i++){
>             sum += values[i];
>         }
>         return sum;
>     }
> }
> //메소드 호출
> public class ComputerExample{
>     public static void main(String[] args){
>         Computer myCom = new Computer();
>         
>         int[] values = {1,2,3};
>         int result = myCom.sum1(values1);
>         System.out.println("result : " + result); //6
>     }
> }
> ```

##### 리턴값이 없는 메소드 : void

> ```java
> //메소드 선언
> public class Car{
>     //필드
>     int gas;
>     //메소드
>     void setGas(int gas){
>         this.gas = gas // 리턴값이 없는 메소드로 매개값을 받아서 gas 필드값을 변경
>     }
>     boolean isLeftGas(){
>         if(gas == 0){
>             System.out.println("가스가 없습니다.")
>             return false; //false를 리턴
>         }else{
>             System.out.println("가스가 있습니다.")
>             return true; //true를 리턴
>         }
>     }
> }
> 
> //메소드 호출
> public class CarExample{
>     public static void main(String[] args){
>         Car myCar = new Car();
>         
>         myCar.setGas(5); //Car클래스의 setGas() 메소드 호출
>         
>         if(myCar.isLeftGas()){ //Car클래스의 isLeftGas()메소드 호출
>             System.out.println("가스를 주입할 필요가 없습니다.")
>         }else{
>             System.out.println("가스를 주입하세요.")
>         }
>     }
> }
> //가스가 있습니다.
> //가스를 주입할 필요가 없습니다.
> ```

##### 클래스 내부/외부에서 메소드 호출 차이

> 클래스 내부에서 메소드를 호출할 때는 new 연산자로 객체를 생성할 필요가 없습니다.
>
> ```java
> public class ClassName{
>     int method1(int x, int y){
>         int result = x + y;
>         return result;
>     }
>     void method2(){
>         int result1 = method1(10,20) //result1에 30 저장
>         int result2 = method1(10,20) //result2에 30.0 저장
>     }
> }
> ```
>
> 클래스 외부에서 메소드를 호출할 때는  new 연산자로 객체를 생성해야합니다.
>
> ```java
> //ex)
> Car myCar = new Car();
> myCar.setGas(5); //Car클래스의 setGas() 메소드 호출
> ```

##### 메소드 오버로딩

> 생성자 오버로딩과 같이 매개 값을 다양하게 받아 처리할 수 있도록 하기 위함입니다.



#### 정적 멤버와 static

> **정적 멤버**는 **클래스에 고정된 멤버**로서 **객체를 생성하지 않고 사용할 수 있는 필드와 메소드**를 말합니다.
>
> 이를 정적 필드, 정적 메소드라고 합니다.
>
> 정적 맴버를 선언하려면 필드와 메소드 선언 시 **static 키워드**를 추가적으로 붙이면 됩니다.
>
> 클래스에 고정된 멤버이므로 **메소드 메모리 영역에** 적재할 때 **클래스별로 관리**됩니다.
>
> ```java
> //메소드 선언
> public class Calculator{
>     int test;
>     //정적 필드
>     static double pi = 3.14159;
>     
>     //정적 메소드
>     static int plus(int x, int y){
>         //정적 메소드 내부에서는 인스턴스 필드나 인스턴스 메소드를 사용할 수 없습니다.
>         //this.test (x)
>         //this.pi (o)
>         //인스턴스 멤버를 사용하고 싶다면 객체를 먼저 생성하고 참조 변수로 접근해야합니다.
>         //Calculator myCal = new Calculator();
>         //myCal.test (o)
>         return x + y;
>     }
> }
> //메소드 호출
> public class CalculatorExample{
>  public static void main(String[] args){
>      double result1 = 10 * 10 * Calculator.pi //객체 생성 없이 Calculator 클래스의 pi 필드를 사용
>      int result2 = Calculator.plus(10,5) //객체 생성 없이 Calculator 클래스의 plus 메서드를 사용
>  }
> }
> ```

> main() 메소드도 정적 메소드이기 때문에 인스턴스 멤버를 사용하기 위해서는 위와 같이 main 메소드 내부에 객체를 생성해서 사용해야 합니다.

#### 싱글톤

> 전체의 프로그램에서 **단 하나의 객체**만 만들도록 보장해야 하는 경우가 있습니다.
>
> 단 하나만 생성된다고 해서 이 객체를 **싱글톤**이라고 합니다.
>
> 싱글톤을 만드려면 외부에서 new 연산자로 생성자를 호출할 수 없게 막아야 합니다.
>
> > 외부에서 new 연산자를 사용하면 여러개의 객체를 생성할 수 있기 때문입니다.
>
>  그렇기 때문에 **생성자 앞**에 **private 접근 제한자**를 붙여주면 됩니다.
>
> ```java
> public class Singleton{
>     private static Singleton singleton = new Singleton(); //생성자 앞에 private를 붙임
>     private Singleton(){}
>     //new 연산자가 아닌 getInstance()를 통해서만 싱글톤 객체를 얻을 수 있습니다.
>     static Singleton getInstance(){
>         return singleton;
>     }
> }
> 
> public class SingletonExample{
>     public static void main(String[] args){
>         //Singleton obj1 = new Singleton(); 컴파일 에러가 난다. new 연산자로 생성할 수 없음
>         
>         //obj1과 obj2는 같은 객체를 참조합니다.
>         Singleton obj1 = Singleton.getInstance();
>         Singleton obj2 = Singleton.getInstance();
>         
>         if(obj1 == obj2){
>             System.out.println("같은 싱글톤 객체입니다.")
>         }
>         
>     }
> }
> ```

#### final 필드

> final 필드는 **초기값이 저장되면** 이것이 **최종적인 값**이 되어서 프로그램 실행 도중에 **수정할 수 없다는 뜻**입니다.
>
> ##### final 필드에 초기값 주는 방법
>
> 1. 필드 선언 시에 주는 방법
>
> 2. **생성자에서 주는 방법**
>
>    * ```java
>      public class Person{
>          final String nation = "Korea"; //필드 선언시 초기값
>          final String ssn; //생성자에서 주는 방법
>          
>          public Person(String ssn){
>              this.ssn = ssn;
>          }
>      }
>      
>      public class PersonExample{
>          public static void main(String[] args){
>              Person p1 = Person("123456-1234567");
>              System.out.println(p1.nation) //Korea
>              System.out.println(p1.ssn) //123456-1234567 final 필드 초기 값
>          }
>      }
>      ```

#### 상수

> 일반적으로 불변의 값을 **상수(static final)**라고 합니다. 
>
> 예를들어 원주율 파이, 지구의 반지름 같이 불변의 값에 사용합니다.
>
> **상수(static final)와 final 필드의 차이점**
>
> > **상수의 값**은 **객체마다 저장할 필요가 없는 공용성**을 띄고 있으며, **여러 가지 값으로 초기화될 수 없기 때문**입니다.
> >
> > 객체마다 저장할 필요가 없는 공동성을 띈다 = 상수는 static이면서 final 이어야 한다.
>
> 상수의 **이름은 대문자**로 작성하는 것이 관례입니다.
>
> ```java
> //상수 선언
> public class Earth{
>     static final double EARTH_RADIUS = 6400;
>     static final double EARTH_AREA = 4 * Math.PI * EARTH_RADIUS * EARTH_RADIUS;
> }
> //상수 사용
> public class EarthExample{
>     public static void main(String[] args){
>     	System.out.println("지구의 반지름 : " + Earth.EARTH_RADIUS + "km");
>         System.out.println("지구의 표면적 : " + Earth.EARTH_AREA + "km^2");
>     }
> }
> ```



#### 생성자의 접근 제한자

> **public**
>
> - **모든 패키지에서** 아무런 제한 없이 생성자를 호출할 수 있도록 합니다.
>
> **protected**
>
> * **같은 패키지에 속하는 클래스**에서 생성자를 호출할 수 있도록 합니다.
> * deault 패키지와는 다르게 **다른 패키지에 속한 클래스가** **해당 클래스의 자식 클래스**라면 생성자를 호출할 수 있습니다.
>
> ##### default
>
> * **같은 패키지에 속하는 클래스**에서 생성자를 호출할 수 있도록 합니다.
>
> ##### private (싱글톤 객체)
>
> * 동일한 패키지이건 다른 패키지이건 상관 없이 생성자를 호출하지 못하도록 제한합니다.
> * 오로지  클래스 내부에서만 생성자를 생성할 수 있고 객체를 만들 수 있습니다. 

#### 필드와 메소드의 접근 제한

> **public**
>
> - **모든 패키지에서** 아무런 제한 없이 필드와 메소드를 호출할 수 있도록 합니다.
>
> **protected**
>
> * **같은 패키지에 속하는 클래스**에서필드와 메소드를 호출할 수 있도록 합니다.
> * deault 패키지와는 다르게 **다른 패키지에 속한 클래스가** **해당 클래스의 자식 클래스**라면 필드와 메소드를 호출할 수 있습니다.
>
> ##### default
>
> * 필드와 메소드를 선언할 때 접근 제한자를 생략하면 default 접근제한자를 가집니다.
> * **같은 패키지에 속하는 클래스**에서 필드와 메소드를 호출할 수 있도록 합니다.
>
> ##### private
>
> * 동일한 패키지이건 다른 패키지이건 상관 없이 필드와 메소드를 호출하지 못하도록 제한합니다.
> * 오로지  클래스 내부에서만 필드와 메소드를 생성할 수 있고 객체를 만들 수 있습니다. 

