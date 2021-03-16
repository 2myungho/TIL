## 리덕스

컴포넌트 코드로부터 상태 관리 코드를 분리할 수 있다.

데이터를 관리하고 데이터가 변경됐을 때만 컴포넌트가 렌더링 되게 하는 것이 리덕스



액션 -> 미들웨어 -> 리듀서 -> 스토어

> 뷰에서 상태값을 변경하고 싶을 때는 액션을 발생시킨다.
>
> 액션이 발생되면 액션을 미들웨어가 처리한다.
>
> 리듀서에서는 해당 액션에 의해서 상태값이 어떻게 변경되는지.
>
> 리듀서가 새로운 상태 값을 스토어에 저장
>
> 스토어는 데이터를 뷰에게 전달
>
> 뷰는 화면에 출력



스토어

> 서비스의 상태 값들을 관리한다.
>
> 액션처리가 끝났다는 것을 외부에 알릴 수 있다,
>
> 리듀스에 의해서만 state의 값이 변경된다.
>
> ```react
> const reducer = createReducer(INITIAL_STATE, {
>  INCREMENT: state => (state.value += 1),
> });
> 
> //createStore로 스토어를 생성한다.
> //리듀서를 넣어 호출한다.
> const store = createStore(reducer);
> 
> //스토어의 subscribe 메서드를 호출해서 액션 처리가 끝난 것을 외부에 알릴 수 있다.
> store.subscribe(() => {
>  const state = store.getState();
>  if(state === prevState){
>      console.log('상탯값 같음');
>  }else{
>      console.log('상탯값 변경됨')
>  }
>  prevState = state;
> })
> ```
>

subscribe

> 액션처리가 끝났다는 이벤트를 받기 위해서는 store의 subscribe 메서드를 호출해서 함수를 입력하면 된다.
>
> 그 후 액션을 발생시켰을 때 각 액션에 대한 처리가 끝나면 subscribe 내부의 함수가 호출된다.



액션

> type 속성 값을 갖고 있는 객체입니다.
>
> * **상태변화를 일으키기 위한 액션**
> * 액션 객체
>
> 타입 속성 값은 유니크해야합니다.
>
> ```react
> store.dispatch({type: 'todo/ADD', title: '영화 보기', priority: 'high'});
> store.dispatch({type: 'todo/REMOVE', id: 123});
> store.dispatch({type: 'todo/REMOVE_ALL'})
> 
> //보통은 action creator 함수를 만들어서 그것을 호출해서 입력합니다.
> function addTodo({title, priority}){
>     return {type: 'todo/ADD', title, priority};
> }
> function removeTodo({id}){
>     return {type: 'todo/REMOVE', id};
> }
> function removeAllTodo(){
>     return {type: 'todo/REMOVE_ALL'};
> }
> 
> //이렇게 줄일 수 있음
> export function addTodo = ({title, priority}) => ({type: 'todo/ADD', title, priority})
> export function removeTodo = ({id}) => ({type: 'todo/REMOVE', id})
> 
> //하지만 이게 더 가독성이 좋음..
> export const actions = {
>     addTodo: ({title, priority}) => ({type: 'todo/ADD', title, priority})
>     removeTodo: ({id}) => ({type: 'todo/REMOVE', id})
> }
> 
> store.dispatch(addTodo({type: 'todo/ADD', title: '영화 보기', priority: 'high'}));
> store.dispatch(removeTodo({type: 'todo/REMOVE', id: 123}));
> store.dispatch(removeAllTodo({type: 'todo/REMOVE_ALL'}))
> ```

> ```react
> //리듀서에서도 사용할 것이기 때문에 액션 타입을 상수 변수에 저장한다.
> export const ADD = 'todo/ADD';
> export const REMOVE = 'todo/REMOVE';
> export const REMOVE_ALL = 'todo/REMOVE_ALL';
> ```



디스패치

> 액션이 발생했다는 것을 리덕스에게 알려주는 역할
>
> * 액션을 스토어에 전달하는 것을 의미한다.



리듀서

> 상태를 변화시키는 로직이 있는 함수이다.
>
> **액션이 발생했을 때 새로운 상태값을 만든다.**
>
> 리덕스의 상태값을 변경하는 유일한 방법은 액션 객체와 함께 dispatch 메서드를 호출하는 겁니다.
>
> 리듀서는 순수 함수로 작성해야 한다.
>
> * 부수 효과가 없어야 한다.
>   * 외부 상태를 변경하는 것
>   * ex ) 서버 API 호출
>
> ```react
> function reducer(state = INITIAL_STATE, action){
> 	switch (action.type){
>     	//...
>         case REMOVE_ALL:
>             return {
>                 ...state,
>                 todos: [],
>             }
>         case REMOVE:
>             return {
>                 ...state,
>                 todos: state.todos.filter(todo => todo.id !== action.id)
>             };
>         default:
>             return state;
> 	}
> }
> const INITIAL_STATE = {todos: []};
> ```
>
> ##### immer
>
> 객체를 불변 객체로 관리할 수 있음
>
> ```react
> function reducer(state = INITIAL_STATE, action){
>     //draft: 변할 값
>     return produce(state, draft => {
>         switch (action.type){
>             case ADD:
>                 draft.todos.push(action.todo);
>                 break;
>             case REMOVE_ALL;
>                 draft.todos = [];
>                 break;
>             case REMOVE:
>                 draft.todos = draft.todos.filter(todo => todo.id !== action.id);
>                 break;
>             default:
>                 break;
>         }
>     })
> }
> ```

> **createReducer**
>
> 객체를 만들어서 핸들러 함수를 작성
>
> 객체의 키는 액션 타입
>
> ```react
> //위의 리듀서 작성을 가독성 있게
> const reducer = createReducer(INITAL_STATE, {
>     [ADD]: (state,action) => state.todos.push(action.todo),
>     [REMOVE_ALL]: state => (state.todos = []),
>     [REMOVE]: (state, action) =>
>     	(state.todos = state.todos.filter(todo => todo.id !== action.id))
> })
> 
> function createReducer(initalState, handlerMap){
>     return function (state = initialState, action){ //action 내부에 type을 포함한 인자가 포함되어 있다.
>         return produce(state, draft => {
>             const handler = handlerMap[action.type];
>             if(handler){
>                 handler(draft, action);
>             }
>         })
>     }
> }
> ```



미들웨어

> 액션이 디스패치 된 다음, 리듀서에서 해당 액션을 받아와서 업데이트  하기 전에 추가적인 작업을 할 수 있다.
>
> 로컬스토리지에 데이터를 저장하거나 불러오는 간단한 기능을 할 수 있다.
>
> ```react
> //const myMiddleware = store => next => action => next(action);
> 
> import {createStore, applyMiddleware} from 'redux';
> 
> const myMiddleware = store => next => action => {
>  console.log('midlleware start');
>  const result = next(action);
>  console.log('midlleware end');
>  return result;
> }
> const myReducer = (state, action) => {
>  console.log('myReducer');
>  return state;
> }
> 
> const store = createStore(myReducer, applyMidlleware(midlleware));
> ```





구독

> 스토어 값이 필요한 컴포넌트는 스토어를 구독한다.



리덕스에서 비동기 처리하기

> 리덕스는 액션 객체가 생성되면 디스패치가 스토어에 알리고 리듀서가 정해진 로직을 처리하는 등의 동기적인 흐름으로 동작합니다.
>
> 디스패치된 액션이 스토어로 전달되기 전에 미들웨이에서 원하는 작업을 처리한 후 스토어로 액션을 전달한다.
>
> 액션을 스토어에 전달하기 전에 처리하고 싶은 작업을 할 수 있다.
>
> * 시간을 딜레이시켜 동작.

> **redux-thunk**
>
> * 비동기 로직이 간단할 때 사용
> * 가장 간단하게 시작할 수 있다

> **redux-observable**
>
> * 비동기 코드가 많을 때 사용
> * 리액티브 프로그래밍을 공부해야 하므로 진입 장벅이 가장 높다.
>
> **redux-saga**
>
> * 비동기 코드가 많을 때 사용
> * 제너레이터를 적극적으로 활용한다.
> * 테스트 코드 작성이 쉽다.



immer

> ... 스프레드를 계속 사용하기 번거롭다
>
> 불변 객체를 관리한다.



optional chaining

> 



reselect 라이브러리

> 





#### vs 코드가 한번에 많은 파일 변화 감지를 하지 못할 때 에러

> https://studioplug.tistory.com/360 참고



#### 리덕스 컨테이너 구성

> UI 부분은 프레젠테이션 컴포넌트로 구성한다.
>
> 프레젠테이션 컴포넌트들은 리덕스에 직접적으로 접근할 수 없다.
>
> 하나의 기능 내의 컨테이너들을 하나의 컨포넌트에 모아 app.js에서 import한다. (ex) search.js)

> 내 개인적인 생각으로는 searchinput에서 searchInputContainer와 searchInpuView 등으로 나누어 리덕스와 연결된 부분, 화면의 출력될 부분을 나누는 것이 좋다고 생각한다.
>
> 또는 하나의 컨테너에서 비슷하게 관련된 여러개의 프레젠테이션 컴포넌트를 관리할 수도 있다.
>
> * 즉, 프레젠테이션 컴포넌트는 리덕스 스토어에 접근 권한이 없으며, 컨테이너에서 props로만 데이터를 받아오는 방식
> * 컴포넌트의 재사용성률이 증가
> * 하나의 컨테이너에 여러개의 프레젠테이션 컴포넌트를 관리할지, 또는 하나의 프레젠테이션 컴포넌트를 관리할지는 내 마음이다.

> 강의처럼 리덕스 스토어 파일은 만들어야 하나 만들지 않아야하나....
>
> 덕스 모델은 모듈 디렉토리에서 액션, 액션 함수 리듀서, 미들웨어를 모두 하나의 파일에 관리하는 것을 말한다.
>
> * index.js에서는 루트 리듀서와 루트 미들웨어를 만드는 작업을한다.
> * 강의에서는 
>   * 각 기능 디렉토리의 index.js가 액션, 액션 함수, 리듀서를 관리하는 파일
>   * saga.js가 미들웨어 관리하는 파일
>   * common에 있는 store가 루트리듀서와 루트 미들웨어를 만드는 파일



#### 양방향 데이터 흐름

양방향의 MVC 패턴은 어떤 action이 발생하면 데이터의 상태가 변경되고 그에 따라 화면을 변경하는데 상태가 변경되었다는 정보를 View와 Model이 서로 양방향으로 주고 받는 형태이다.

> 사용자가 action을 발생하면 컨트롤러가 view와 model을 업데이트 하고 업데이트 된 정보를 model과 view가 서로 주고 받는다.
>
> 프로젝트가 커지면 modle과 view가 매우 많아지기 때문에 어떤식으로 연결되어 있는지 파악하기 어려워진다.
>
> * state 관리가 어렵다.
> * 부모 컨포넌트가 렌더링 되면서 자식 컨포넌트에 불필요한 렌더링이 된다.

#### 단방향 데이터 흐름

Flux 패턴은 action이 발생하면 dispatch에 의해 store에 변경된 사항이 저장되고 그 저장된 사항에 의해 view가 변경되는 단방향 패턴을 보인다.

> 단방향의 장점은 개발 흐름이 단방향으로 흐르기 때문에 훨씬 파악하기 쉽고 코드의 흐름이 예측 가능하다.
>
> * 오류를 찾기 쉽다
> * 불필요한 렌더링을 할 필요가 없다.

출처 : https://im-developer.tistory.com/158



#### 미들웨어 사가 주요함수

> **all**
>
> * Promise.all()과 비슷하다.
> * all 함수에 배열로 설정된 효과는 **병렬처리**되고,  설정한 이펙트가 **모두 완료될 때까지 기다리도록 지시하는 함수**입니다.
>   * saga가 반환하는 값이자 미들웨어가 실행할 명령을 담고 있는 자바스크립트 객체
>   * take, call, put 등
>
> * ```react
>   //all 사용 x
>   export default function* (){
>       yield takeEvery('search/FETCHAUTOCOMPLETES', fetchAutoComplete),
>       yield takeEvery('search/FETCHAUTOCOMPLETES', fetchAutoComplete),
>   }
>   
>   //all 사용 o
>   export default function* (){
>       yield console.log(all([
>           takeEvery('search/FETCHAUTOCOMPLETES', fetchAutoComplete),
>           takeEvery('search/FETCHAUTOCOMPLETES', fetchAutoComplete),
>       ]))
>   }
>   //payload: Array(2)
>   // 0: {@@redux-saga/IO: true, combinator: false, type: "FORK", payload: {…}},
>   // 1: {@@redux-saga/IO: true, combinator: false, type: "FORK", payload: {…}}
>   ```
>
> **takeEvery**
>
> * 액션을 받을 때마다 태스크가 실행되게 한다.
> * 여러 개의 태스크를 동시에 시작할 수 있다.
> * Task
>   * 하나의 saga가 실행되는 것
>   * 비동기 작업의 단위?
>
> **put**
>
> * Redux store와 통신
> * 미들웨어가 store에 **작업 발송을 예약**하도록 지시하는 효과
> * 다른 태스크가 사가 태스크 대기열에 있거나 진행중이라면 전송이 즉시 수행되지 않는다.
>
> **call**
>
> * 미들웨어가 인자를 사용해서 함수를 호출하도록 지시한다.



#### react-redux 사용이유

**react-redux 없이 구현**

> **액션을 발생**시켰을 때 각 액션에 대한 처리가 끝나면 **subscribe 내부의 함수가 호출**된다.
>
> 아래의 코드는 **subscribe**을 사용하면 **action이 처리될 때**마다 무조건 **컴포넌트를 업데이트** 하게 된다.
>
> 그래서 불필요한 렌더링이 발생하게 된다.
>
> 해결 방법으로는 렌더링 전의 state 값과 렌더링 후의 state 값을 비교해서 같지 않을 때만 렌더링이 발생하도록 한다.
>
> * 데이터가 변경됐을 때만 렌더링이 되도록 한다.
>
> ```react
> const [, forceUpdate] = useReducer(v => v + 1, 0);
> useEffect(() => {
>     let prevFriends = store.getState().friend.friends; //호출 되기전 state값
>     const unsubscribe = store.subscribe(() => {
>         const friends = store.getState().friend.friends; // 호출 된 후의 state값
>         if(prevFriends !== friends){
>             forceUpdate();
>         }
>         prevFriends = friends; //비교후 이전 데이터 갱신
>     })
> })
> ```

**react-redux 사용**

> Provider
>
> * 하위 컴포넌트들이 Provider를 통해 redux store에 접근이 가능해진다.
>
> useSelector
>
> * **useSelector** 훅은 리덕스에서 **액션이 처리**가 되면 반환하는 값의 **이전 값을 기억**했다가 **이전 값이 변경** 되었을 때 해당 컴포넌트를 다시 **렌더링** 해줍니다.
>
> * 여러개의 useSelector 훅을 사용 가능
>
> * ```react
>   //useSelector 미사용 바로 위에 코드
>   //한 줄로 줄어든다
>   
>   //userSeclector 사용
>   const friends = useSelector(state => state.friend.frends);
>   ```





git config --global alias.co checkout

git config --global alias.ci commit

git config --global alias.br branch

git config --global alias.unstage 'reset HEAD --'

git config --global alias.cs "commit -s"



git config --global alias.last 'log -1 HEAD'

git config --global alias.**visual** '!gitk'