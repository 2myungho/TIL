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



#### Hooks

> 함수형 컴포넌트에서도 state 관리와 라이프사이클 관리를 할 수 있게 도와주는 함수이다.

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
>
> 부수효과 함수의 반환값은 항상 함수타입이어야 한다.
>
> * async await은 프로미스 객체를 반환하기 때문에 사용할 수 없다
> * 만약 사용하고 싶다면 useEffect 외부에 만들어서 사용하는 방법을 사용한다.
> * 하지만 컴포넌트가 렌더링 될 때마다 객체가 계속 생성된다. 그러지 않기 위해서 useCallback을 사용한다.
>
> **의존성 배열 사용에 대해**
>
> * 영상에서는 의존성 배열을 웬만하면 피하라고 말해주고 있다
>
> * 의존성 배열을 관리하는데 많은 시간과 노력이 필요하게 될 것이라고 한다.
>
> * 의존성 배열을 사용하지 않고 사용하는 방법으로는 **상태값 변경 함수에 함수를 입력하면된다.**
>
>   * 상태값 변경 함수에 함수를 입력할 때는 이 함수의 **매개 변수로 이전 상태값이 들어온다.**
>
>   * ```react
>     useEffect(() => {
>     	function onClick(){
>             setCount(prev => prev + 1);
>         }
>     })
>     ```



##### 커스텀 훅

> use로 시작하는 이름을 작성한다.
>
> 사용하고자 하는 컴포넌트 내에서 함수의 형태로 간단하게 사용이 가능하다.
>
> 여러 훅을 조합해 커스텀 훅을 만들 수 있다.
>
> 재사용성이 좋다.
>
> * input을 관리하는 코드 등 비슷한 코드가 반복된다.
> * 중복된 코드가 줄어서 가독성이 좋아진다.
> * 재사용이 가능한 함수.
> * 한 기능의 로직을 분리하는 경우?
>
> ```react
> //사용하는 컴포넌트
> const user = user(userId);
> 
> //커스텀 훅 useUser
> export default function useUser(userId){
>  ......
> }
> ```
>
> 

**useMemo**

> 부모 컴포넌트가 렌더링 될 때마다 자식 컴포넌트도 렌더링 된다.
>
> 이때 자식의 속성 값이 변경되지 않았을 때 자식 컴포넌트를 렌더링하지 않기 위해서는 useMemo를 사용한다.

##### useRef

> current가 실제 돔 요소를 가리키게 된다.

##### useCallback

> 한 번 생성된 함수를 계속해서 재사용하게 된다.
>
> ```react
> const onSave = useCallback(() => saveToServer(name, age), [name, age]);
> ```
>
> 위의 코드의 의존성 배열에 name과 age 가 바뀔 때만 새로운 함수를 생성한다는 뜻
>
> * name과 age가 변경되지 않으면 렌더링하지 않음



#### Lodash 라이브러리 사용

> ```bash
> npm i lodash
> ```
>
> 자바스크립트의 유틸리티 라이브러리로써 array, collection, data, number, object 등이  있으며, 데이터를 쉽게 다룰 수 있게 도와준다
>
> 특히, 자바스크립트에서 배열 안의 객체들의 값을 핸들링할 때 유용하다.

#### hotkeys 라이브러리 사용

> ```bash
> npm i hotkeys-js
> ```



#### Context API

> 애플리케이션으로 **전역적으로 데이터가 사용되야 할 때** 사용됩니다.
>
> 하위 컴포넌트와 상위 컴포넌트가 상당히 멀리 떨어져 있다면 중간에 있는 컴포넌트가 자신은 사용하지 않는 함수를 전달해 주어야 한다.
>
> ```react
> const UserContext = createContext('unkown'); //초기값 설정
> 
> export default function App(){
>     return(
> 		<UserContext.Provider value="mike">
>     		<Profile />
>     	</UserContext.Provider>
>     )
> }
> 
> function Profile(){
>     return(
>     	<Greeting />
>     )
> }
> 
> function Greeting(){
>     return(
>         <UserContext.Consumer>
>             {username => username}
>         </UserContext.Consumer>
>     )
> }
> 
> // 화면에 mike 출력
> 
> //Consumer 더 간단하게 사용하기
> function Greeting(){
>     const username = useContext(UserContext);
>     return {username}
> }
> ```
>
> 제일 하위에 있는 Greeting 컴포넌트가 가장 가까운 Provider의 값을 찾아간다.
>
> 만약 경로에 Provider의 값이 없다면 처음 정해준 초기값을 가져온다.
>
> 위의 코드를 보면 App과 Greeting 사이의 Profile 컴포넌트는 함수를 전달해 주지 않았다.
>
> * Consumer의 값이 변경되어도 중간에 있는 Profile 컴포넌트는 렌더링이 되지 않는다.



#### 컴포넌트 타입 작성법

> ````react
> //props의 타입 지정
> MyComponent.propTypes = {
>     //...
> }
> 
> export default function MyComponent({props1, props2}){
>     console.log(COLUMNES);
> }
> 
> //타입 지정과 컴포넌트 보다는 중요도가 밀리기 때문에 아래에 작성한다.
> //외부 변수는 대문자로 입력한다. (한눈에 알아볼 수 있게)
> //아래와 같이 큰 객체는 외부에서 생성하는 것이  좋다. 컴포넌트 내부에 생성하면 렌더링 될 때마다 객체를 매번 생성해야한다.
> const COLUMNES = [
>     {id: 1, name: 'phoneNumber', width: 200, color: 'white'}
> ]
> ````



#### 속성값 타입 정의하기

> ```react
> User.propTypes = {
> 	male: PropTypes.boll.isRequired, //isRequired가 붙으면 필수값을 의미
>     age: PropTypes.number,
>     //oneOf는 배열에 포함된 값만 사용할 수 있다.
>     type: PropTypes.oneOf(['gold', 'silver', 'bronze']),
>     //함수에 사용될 매개변수 타입과 반환값을 주석으로 표시해준다.
>     onChangeName: PropTypes.func, //(name: string) => void
>     onChangeTitle: PropTypes.func.isRequired,
> }
> ```



#### 가독성 높이는 조건부 렌더링

> ````react
> //중첩 삼항연산자
> <div>
> 	{isEvent ? (
>     	<div>
>         	<p>오늘의 이벤트</p>
>         </div>
>     ) : isLogin ? (
>     	cash <= 10000 ? (
>         	<p>안녕하세요</p>
>         ) : null
>     ) : null}
> </div>
> 
> //&& 연산자 사용
> <div>
> 	{isEvent ? (
>     	<div>
>         	<p>오늘의 이벤트</p>
>         </div>
>     ) : isLogin && (
>     	cash <= 10000 && (
>         	<p>안녕하세요</p>
>         )
>     )}
> </div>
> ````



#### 재사용성을 고려한 컴포넌트 구분법

> 재사용성이 좋은 컴포넌트와 그렇지 않은 컴포넌트로 구분
>
> * 비즈니스 로직과 상태값의 유무로 컴포넌트를 구분한다.

> **비즈니스 로직**
>
> * 데이터 처리를 수행하기 위해 데이터를 활용하여 계산, 판단, 가공 등을 하는 '로직'을 의미한다.
> * ex ) 아이디 중복 찾기 등
>   * 회원이 작성한 아이디 값 저장하기
>   * 회원정보가 있는 데이터베이스 연결
>   * 데이터베이스에 회원이 작성한 아이디 값이 있는지 select
>   * 회원의 아이디가 이미 있는지 없는지 여부를 데이터화 하여 저장 등
>
> **재사용성이 좋은 컴포넌트**
>
> * 비즈니스 로직이 없다.
> * 상탯값이 없다
> * 단, UI 효과를 위한 상탯값은 제외한다.
>
> **재사용성이 좋지 않은 컴포넌트**



#### 렌더링 속도를 올리기 위한 성능 최적화 방법

> 일단 코딩할 때는 성능 최적화 방법을 신경쓰지 않고 코딩을 시작한다.
>
> * 대부분의 웹페이지는 성능 최적화를 하지 않아도 문제 없이 잘 돌아간다.
> * 성능관련 훅과 함수를 미리 고민해서 사용하지 않고 성능 이슈가 발생한 부분의 코드만 최적화 하는 것을 추천한다.
>   * 성능을 최적화하는 코드는 가독성도 좋지 않고 유지보수 비용도 증가한다.

> Memo함수에 속성값 비교 함수 사용
>
> * 만약 속성값 비교 함수를 사용하지 않으면 **얕은 비교**를 하게 된다.
> * **메모이제이션**
>   * 함수의 결과를 저장하는 장소(캐쉬)를 마련해 두고, 한 번 계산한 값을 저장해 뒀다가 재활용하는 최적화 기법을 말한다.
>
> ```react
> function isEqual(prevProps, nextProps){
>     return false
> }
> export default React.memo(MyComponent, isEqual);
> ```
>
> isEqual이 false를 반환하면 컴포넌트를 렌터링 한다. 반대로 true를 반환하면 렌더링하지 않는다.
>
> * 예를들어 버튼1 버튼2 버튼을 누를 경우 숫자가 올라갈 때
>
> * ```react
>   function isEqual(prevProps, nextProps){
>       return prevProps.value1 === nextProps.value1;
>   }
>   ```
>
> * 위와 같이 작성된다면 버튼 2를 누를 땐 데이터 값을 올라가지만 렌더링이 되지 않아 화면에 출력되지 않는다.
>
> * 반대로 버튼1을 누르게 되면 버튼1의 숫자는 하나씩 올라면서 렌더링 되며 버튼2의 값도 눌러둔 만큼 화면에 반영된다.
>
> (React.memo 함수를 e.target.value 렌더링에 한 번 사용해보자!)

> useMemo 훅을 사용
>
> 필요할 때만 값을 변경하게 할 수 있다.

> key 값을 이용한다.
>
> 가상돔과 실제돔 사이에서 성능최적화 하는 방법

