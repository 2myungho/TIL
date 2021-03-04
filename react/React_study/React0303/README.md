#### create-react-app

> webpack, babel, jest, eslint, polyfill, HMR,CSS 후처리 등등
>
> 페이스북에서 관리하는 공식 툴
>
> cra는 서버사이드 렌더링이 필요 없는 프로젝트에 적합하다.
>
> 단점으로는 설정을 변경할 수 없다.



#### SPA

> MPA 처럼 새로운 페이지를 로드하지 않고 **하나의 페이지 안에서 필요한 데이터만 가져오는 형태**
>
> spa가 가능한 조건
>
> * 자바스크립트에서 브라우저로 페이지 전환 요청을 보낼 수 있다.
>   * 단 브라우저는 서버로 요청을 보내지 않아야 한다.
> * 브라우저의 뒤로가기와 같은 사용자의 페이지 전환 요청을 자바스크립트에서 처리할 수 있다.
>   * 이때도 브라우저는 서버로 요청을 보내지 않아야 한다.

#### react router란

> ```
> history : 이 객체를 통해 push, replace를 통해 다른 경로로 이동하거나 앞 뒤 페이지로 전환할 수 있습니다.
> location : 이 객체는 현재 경로에 대한 정보를 지니고 있고 URL 쿼리 (/about?foo=bar 형식)정보도 가지고 있습니다.
> match : 이 객체에는 어떤 라우트에 매칭이 되었는지에 대한 정보가 있고 prams (/about/:name 형식) 정보를 가지고 있습니다.
> ```
>
> 예를들어 /:room 과 같이 :를 사용한 것은 파라미터를 사용하겠다고 선언했다.



#### 스프레드 연산자

> ```javascript
> const arr = [1, 2, 3];
> const arr2 = [...arr];
> const arr2 = [arr[0], arr[1], arr[2]]; //위와 같다
> ```



#### React.Fragment

> return 내부에 태그가 두개 이상일 경우 하나로 감싸주어야 한다.
>
> <React.Fragment> 를 사용하면 실제 돔에는 반영되지 않는  태그를 사용할 수 있다.
>
> ```react
> return (
>     <React.Fragment>
>     	<p>안녕</p>
>         <p>하세요</p>
>     </React.Fragment>
> )
> //축약형으로 <> </> 로 작성 가능하다.
> ```
>
> 



#### Hooks

##### useState

> 상태값 추가
>
> 상태값 변경 함수를 **비동기**로 처리하면서 **배치**(batch)로 처리한다.
>
> * 배치처리란 **일괄 처리**를 말한다.
>
> 상태값을 동기로 처리하면 상태 변경이 호출이 될때마다 화면을 매번 다시 그리기 때문에 성능 이슈가 발생할 수도 있다.
>
> ```react
> //배치처리 하기 때문에 +1씩만 증가한다.
> //로그도 한 번만 출력하게 된다.
> export default function App(){
>     const [count, setCount] = userState(0);
>     function onClick(){
>         setState(count +1);
>         setState(count +1);
>     }
>     console.log('한 번만 출력된다.')
> }
> 
> //2씩 증가한다.
> //배치처리 x
> //함수로 입력하면 처리되기 직전에 상태값을 매개변수로 받기 때문이다.
> export default function App(){
>     const [count, setCount] = userState(0);
>     function onClick(){
>         setState(v => v + 1);
>         setState(v => v + 1);
>     }
>     console.log('두 번만 출력된다.')
> }
> 
> //함수도 배치처리 하는 방법
> //2씩 증가한다.
> import ReactDOM from 'react-dom';
> export default function App(){
>     const [count, setCount] = userState(0);
>     function onClick(){
>         ReactDOM.unstable_batchedUpdates(() => {
>             setState(v => v + 1);
>         	setState(v => v + 1);
>         })
>     }
>     console.log('한 번만 출력된다.')
> }
> ```
>
> 리액트는 상태값 변경을 배치로 처리한다 -> 
>
> * 리액트 내부에서 관리하는 이벤트 처리함수에 대해서만 상태값 변경을 배치로 처리한다.
> * 외부에서 관리되는 이벤트 처리 함수의 경우에는 상태값이 배치로 처리되지 않음.

##### useEffect

> 부수효과 처리 함수 (외부의 상태 변화)
>
> * 부수효과 : 원래의 목적과 다르게 다른 효과 또는 부작용이 나는 상태
> * state의 상태가 변함으로 컴포넌트가 렌더링 되는 것을 처리하는 함수를 말하는 것 같다.
>
> 서버 API 호출, 이벤트 핸들러 등록 등
>
> ```react
> //useEffect는 컴포넌트가 렌더링된 직후에 호출
> //return 문에 입력되는 구문은 컴포넌트가 언마운트 되기 전에 호출
> //두번째 매개변수 [userId]입력 하게되면 userId 변경시에만 useEffect 함수 실행
> //두번째 매개변수에 빈 배열을 입력하면 렌더린된 직후에 useEffect 한 번만 실행 (return문 작성시 언마운트 되기 직전에 한 번 실행)
> //두번째 매개변수에 아무 값도 주지 않으면 state가 변경될때마다 컴포넌트가 렌더링이 되고 useEffect 함수가 계속 실행하게 된다.
> useEffect(() => {
>     getUserApi(userId).then(data => setUser(data));
>     return () => {
>         ...
>     }
> },[userId])
> ```

##### 커스텀 훅

> use로 시작하는 이름을 작성한다.
>
> 사용하고자 하는 컴포넌트 내에서 함수의 형태로 간단하게 사용이 가능하다.
>
> 여러 훅을 조합해 커스텀 훅을 만들 수 있다.
>
> 재사용성이 좋다.
>
> ```react
> //사용하는 컴포넌트
> cosnt user = user(userId);
> 
> //커스텀 훅 useUser
> export default function useUser(userId){
>     ......
> }
> ```
>
> 

**useMemo**

> 부모 컴포넌트가 렌더링 될 때마다 자식 컴포넌트도 렌더링 된다.
>
> 이때 자식의 속성 값이 변경되지 않았을 때 자식 컴포넌트를 렌더링하지 않기 위해서는 useMemo를 사용한다.





#### Lodash 라이브러리 사용

> ```bash
> npm i lodash
> ```
>
> 자바스크립트의 유틸리티 라이브러리로써 array, collection, data, number, object 등이  있으며, 데이터를 쉽게 다룰 수 있게 도와준다
>
> 특히, 자바스크립트에서 배열 안의 객체들의 값을 핸들링할 때 유용하다.