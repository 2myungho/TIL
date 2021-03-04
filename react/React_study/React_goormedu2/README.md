#### npm 설치된 전역 모듈 위치

> ```bash
> npm root -g
> ```

#### 설치된 전역 모듈 트리

> ```bash
> npm ls -g --depth=0
> ```

####  npm으로 로컬 / 전역 모듈 설치하기

```bash
npm install && npm i
npm install -g && npm i -g
```

#### npm으로 프로젝트 관리하기

> ```bash
> npm init # package.json 생성
> npm install --save && npm i -s # save 명령어를 사용해서 설치된 패키지를 package.json에 기록할 수 있다.
> npm install #package.json 참고하여 node_module 생성
> ```

#### npm으로 스크립트 실행하기

> ```bash
> npm run " "
> ```



#### 제너레이터

> 함수와 비슷한 구문으로 **반복 가능한 객체를 생성**하는 구문
>
> 함수를 즉시 실행하지 않고 next 메서드를 호출하면 실행한다.
>
> * **언제든 호출자에게 제어권을 넘길 수 있다.** (yield)
>
> ```javascript
> //제네레이터 함수
> function * counter(){
>  //시작 부분부터 읽는 녀석
>  yield 1
>  yield 2
>  yield 3
>  return 1
> }
> 
> generatorObject = counter()
> console.log(generatorObject.next()); //{ value: 1, done: false }
> console.log(generatorObject.next()); //{ value: 2, done: false }
> console.log(generatorObject.next()); //{ value: 3, done: false }
> console.log(generatorObject.next()); //{ value: 1, done: true }
> console.log(generatorObject.next()); //{ value: undefined, done: true }
> ```
>
> ##### 반복
>
> ```javascript
> //제네레이터 함수
> function * counter(){
>  //시작 부분부터 읽는 녀석
>  yield 1
>  yield 2
>  yield 3
> }
> generatorObject = counter()
> for(const value of generatorObject){
>  console.log(value) // 1 2 3
> }
> ```
>
> ##### 배열 반대로 출력
>
> ```javascript
> function * reverse(array){
>  for(let i = array.length - 1; i >= 0; i--){
>      yield array[i]
>  }
> }
> a = [1,2,3,4,5]
> for(const value of reverse(a)){
>  console.log(value) // 5 4 3 2 1
> }
> ```
>
> 배열의 내장함수인 reverse와 filter을 사용하면 똑같은 배열을 압축해서 똑같은 배열을 하나 더 만들게 되는데 메모리의 낭비가 발생한다.
>
> 제너레이터를 사용하면 추가 적인 배열을 생성하지 않고 index만 참조하여 사용하기 때문에 메모리에 낭비가 발생하지 않는다.
>
> ```javascript
> function * reverse(array){
>  for(let i = array.length - 1; i >= 0; i--){
>      yield array[i]
>  }
> }
> a = [1,2,3,4,5]
> console.log(reverse(a).next()) //5
> ```
>
> 위와 같이 제너레이터 함수를 한 번 출력하고 필요할 때 다시 출력.



#### 프로미스

> 프로미스는 비동기 처리에 사용되는 객체이다.
>
> **현재 단계가 끝나고 나서 실행**
>
> > **콜백함수는 예약을 걸어두는 것**이다.
>
> ```javascript
> const fs = require("fs")
> 
> console.log("ponit a");
> fs.writeFile("test_2.txt", "Hello javaScript", (error) => {
>  console.log("ponit b");
>  fs.readFile("test_2.txt", (error, data) => {
>      console.log("ponit c");
>      console.log(data.toString())
>  })
>  console.log("ponit d");
> })
> console.log("ponit e");
> 
> /*
> ponit a
> ponit e
> ponit b
> ponit d
> ponit c
> Hello javaScript
> */
> ```
>
> **Promise**
>
> 첫번째 then 파라미터에는 resolve의 값이 들어간다.
>
> 두번째 then 부터는 앞의 then의 return 값이 들어간다.
>
> resolve : 값을 첫번째 then에 전달
>
> reject : 이 promise를 거부한 이유
>
> ```javascript
> const fs = require("fs")
> 
> new Promise((resolve) => {
>  resolve(10)
> }).then((value) => {
>  return 20
> }).then((value) => {
>  return 30
> })
> ```
>
> **async & await**
>
> > 기존의 비동기 처리 방식인 콜백함수 처리 방법과 promise의 단점을 보완하기 위해
>
> ```javascript
> const fs = require("fs")
> 
> async function readAll(){
>  //await : 프로미스의 내용을 곧바로 실행하고 resolve의 값을 리턴
>  const a = await read("test_2.txt")
>  console.log(a);
>  const b = await read("test_2.txt")
>  console.log(b);
>  const c = await read("test_2.txt")
>  console.log(c);
> }
> readAll()
> 
> function read(filename){
>  return new Promise((resolve) => {
>      fs.readFile(filename, (error, data) => {
>          resolve(data.toString())
>      })
>  })
> }
> ```
>
> **간단한 HTTP 통신 예**
>
> then보다도 가독성이 좋음
>
> ```javascript
> //콜백함수 처리
> function logName() {
>   // 아래의 user 변수는 위의 코드와 비교하기 위해 일부러 남겨놓았습니다.
>   var user = fetchUser('domain.com/users/1', function(user) {
>     if (user.id === 1) {
>       console.log(user.name);
>     }
>   });
> }
> 
> // async & await 적용 후
> async function logName() {
>   var user = await fetchUser('domain.com/users/1');
>   if (user.id === 1) {
>     console.log(user.name);
>   }
> }
> ```
>
> 



#### babel 

> 바벨이란 최신 ES6 버전을 구 버전인 ES5로 변환해준다.
> 
> 인터넷 익스플로러 등과 같이 아직 구버전만 지원하는 환경이 있기 때문에 사용해야한다.
> 
> ```bash
> npm install -g babel-cli
> npm install babel-preset-es2015
> 
> babel babel_test.js --presets=es2015 #es6도 수정됨
> babel babel_test.js --presets=es2015 -o babel_test.out.js #babel이 적용된 파일 생성
>babel babel_test.js --presets=es2015 -o babel_test.out.js -w #원본파일 저장시 복제 파일도 자동 업데이트
> ```
>



#### require() 함수와 exports  객체

```javascript
// module.exports 사용 (내보내기)
function add (a,b){
    return a + b
}
function mul (a, b){
    return a * b
}
module.exports = {
    'add': add,
    'mul': mul
}

//require() 함수 사용 (불러내기)
const calculator = require('./calculator_module')

console.log(calculator.add(10, 100)) //110
console.log(calculator.mul(10, 100)) //1000
```

#### import 키워드와 export 키워드

```javascript
//export 사용
export function add (a,b){
    return a + b
}
export function mul (a, b){
    return a * b
}

//import 사용
import { add, mul } from './calculator.js'
console.log(add(10, 100)) // 110
console.log(mul(10, 100)) // 1000
```



#### 화살표 함수의 this

> ```javascript
> const a = function(){alert(this)} //undefined
> const b = () => {alert(this)} // [object object]
> 
> try{
>     a()
> }catch(e){
>     alert("A에서 문제가 발생했습니다.")
>     alert(e)
> }
> try{
>     b()
> }catch(e){
>     alert("B에서 문제가 발생했습니다.")
>     alert(e)
> }
> ```
>
> 우리가 사용하는 최신 버전에서는 일반적으로 **엄격한 모드**가 걸려 있습니다.
>
> 함수 내부에서 this를 사용하게 될 경우 this는 undefined가 되지만 (bind를 사용해야한다.)
>
> 화살표 함수를 사용하게 되면 this가 현재 인스턴스를 가리킨다 (bind를 사용할 필요가 없다)



#### 웹팩

> ```bash
> npm install -g webpack
> 
> webpack <엔트리포인트 파일> <변환 완료된 후의 파일>
> 
> #프로덕션 모드로 변경
> webpack --mode production --env production'
> 
> --watch #옵션을 같이 주게되면 감시모드로 변함 main.js 파일을 저장시 자동 저장
> ```
>
> Webpack = 모듈 번들링
>
> html 파일에 들어가는 자바스크립트 파일들을 하나의 자바스크립트 파일로 만들어주는 방식을 모듈 번들링이라고 한다.
>
> 필요한 다수의 자바스크립트 파일을 하나의 자바스크립트 파일로 만들어 주는 것을 웹팩이라고 한다.

> **webpack :** 웹팩은 파일을 하나로 결합할 때 사용! 모듈 해결...!
>
> **react react-dom :** 리액트!
>
> **babel-loader babel-core :** 바벨을 사용
>
> **babel-preset-env :** es2015, es2016, se2017 문법 사용
>
> **babel--preset-react :** JSX 문법 사용



#### 라이프 사이클

> 어떤 객체가 생성부터 파괴되는 시점까지 자동으로 호출되는 메서드들을 라이프사이클이라고 한다.