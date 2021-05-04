#### 패키지

> ```bash
> yarn init -y # package.json 생성
> yarn add typescript ts-node #typescript를 사용하기 위한 모듈, ts-node는 콘솔에서 바로 실행시켜주는 모듈
> yarn run tsc --init #타입스크립트 설정이 만들어짐 tsconfig.json
> yarn run tsc # js 파일이 만들어진다 
> ```



#### tsconfig

```json
"target": "es2015" //es5로 설정되어 있으면 tsc할 때 var로 변경된다.

"outDir": "./dist", //컴파일된 코드를 어디에 저장될지 설정 (dist라는 파일에 만들어짐)
```

```bash
node ./dist/practice.js #컴파일된 파일 실행
yarn run ts-node ./src/practice.ts #컴파일 하지 않고 실행
```



#### 기본타입

````typescript
let count = 0;
count += 1;

const message: string = 'hello world';
const done: boolean = false;

const number: number[] = [1,2,3];
const messages: string[] = ['hello', 'world'];


let mightBeUndefined: string | undefined = 'undefined' //문자일 수도 있고 undefined일 수도 있다.
let nullableNumber: number | null = null; //숫자일 수도 있고 null일 수도 있다.

let color: 'red' | 'orange' | 'yellow' = 'red'; //'red', 'orange', 'yellow' 값으로 밖에 변경이 불가능하다.
````

#### 함수

```typescript
//함수의 파라미터에 타입을 정해주지 않으면 any 속성이 된다.
//any 속성은 어떠한 타입도 될 수 있다.
function sum(x:number, y:number): number {
    return x + y;
}

const result = sum(1, 2);

//함수에 인자를 배열로
function sumArray(numbers: number[]): number {
    return numbers.reduce((acc, current) => acc + current, 0);
}
const total = sumArray([1,2,3,4,5]);
console.log(total)

//두가지 이상 타입
function returnStringOrNull(): string | number(){
    return 4; //return "4" 둘다 가능
}
```

> 함수에 타입을 정해주지 않으면 자동으로 void가 된다.
>
> void가 되면 리턴값을 사용할 수 없다.



#### 인터페이스

> class 또는 객체를 지정할 때 사용
>
> ```typescript
> interface Shape {
>     getArea(): number;
> }
> 
> class Circle implements Shape {
>     radius: number;
> 	
>     //자바의 생성자 역할
>     constructor(radius: number){
>         this.radius = radius;
>     }
> 
>     getArea(){
>         return this.radius * this.radius * Math.PI;
>     }
> }
> 
> class Rectangle implements Shape{
>     width: number;
>     height: number;
> 
>     constructor(width: number, height: number){
>         this.width = width;
>         this.height = height;
>     }
> 
>     getArea(){
>         return this.width * this.height
>     }
> }
> 
> const circle = new Circle(5); //78.53981633974483
> const rectangle = new Rectangle(2, 5) //10
> 
> //Shape 인터페이스로 이루어진 배열
> const shapes: Shape[] = [circle, rectangle];
> 
> //배열을 각각 실행
> shapes.forEach(shape => {
>     console.log(shape.getArea());
> })
> ```



#### 객체

>```typescript
>interface Person {
>    name: string;
>    age?: number //Person 인터페이스에는 age 값이 있을 수도 없을 수도 있다.
>}
>
>interface Developer extends Person {
>    //Developer는 Person 인터페이스를 상속받는다.
>    skills: string[];
>}
>
>const person: Person = {
>    name: '김사람',
>    age: 20,
>}
>
>const exoert: Developer = {
>    name: '김개발',
>    skills: ['javascript', 'react', 'typescript']
>}
>```



#### Type Alias

> ```typescript
> Type Person {
>     name: string;
>     age?: number //Person 인터페이스에는 age 값이 있을 수도 없을 수도 있다.
> }
> 
> Type Developer = Person & {
>     //Developer는 Person 인터페이스를 상속받는다.
>     skills: string[];
> }
> 
> const person: Person = {
>     name: '김사람',
>     age: 20,
> }
> 
> const exoert: Developer = {
>     name: '김개발',
>     skills: ['javascript', 'react', 'typescript']
> }
> 
> //Type Alias 의 기능
> type People = Person[];
> const people: People = [person, export];
>     
> type Color = 'red' | 'orange' | 'yellow';
> const color: Color = 'red'
> ```
>
> type Alias를 쓸지 인터페이스를 쓸지 프로젝트에서 일관성 있게 하자.



#### Generics

> 타입스크립트에서 함수 클래스 인터페이스 Type Alias를 사용할 때 여러 종류의 타입에 대하여 호환을 맞추어야 할 때 사용
>
> `<T>`
>
> ```typescript
> function merge<T1, T2>(a: T1, b: T2){
>  return {
>      ...a,
>      ...b
>  }
> }
> 
> const merged = merge({foo: 1}, {bar: 2});
> //에디터에서 merged. 을 입력하면 사용할 수 있는 객체가 나타난다.
> ```
>
> any와 같이 모든 값을 넣을 수 있지만 any를 사용하면 에디터에서 `(property)param: any`로 표시되지만
>
> Generics을 사용하면 `(property) param: String` 등으로 표시된다.
>
> ##### 인터페이스에서 사용
>
> ```typescript
> type Item<T, V> = {
> 	list: T[];
> 	value: V
> }
> const items: Items<number, string> = {
> 	list: [1,2,3],
>  value: 'aaaa',
> }
> ```
>
> #### 리액트에서 사용할 때
>
> ```react
> //type
> type SearchState<T> = {
>     keyword: T;
>     autoCompletes: AutoComplete[]
> }
> 
> //state
> const INITIAL_STATE: SearchState<string> = {
>     keyword: '',
>     autoCompletes: [],
> };
> 
> //reducer
> function searchReducer(state: SearchState<string> = INITIAL_STATE, action: SearchAction): SearchState<string> {
>     
> }
> ```
>
> 제네릭을 사용하면 위와 같이 인터페이스를 사용할 때 타입을 따로 지정해 줄 수 있다.
>
> searchState를 사용할 때 제네릭 부분에 원하는 타입을 넣어서 객체마다 다른 타입으로 사용할 수 있다. 







자바스크립트에 사용할 때 참고

https://basarat.gitbook.io/typescript/intro-1/jest



타입스크립트를 사용하면 에디터에서 변수의 타입을 알려주는 등의 지원이 좋았다.