## React.js

#### React 작동 원리

> React는 실제로 DOM을 제어하는 방식이 아니라 중간에 **가상의 DOM**인 **Virtual DOM**을 두어 개발의 편의성과 성능을 개선했다.
>
> * 편의성 : DOM을 직접 제어하지 않음
>
> * 성능 : 배치 처리로 DOM 변경
>
> * **DOM (Document Object Model)  문서 객체 모델** : 
>* 내가 작성한 HTML 코드가 DOM (X)
>   * **DevTools (개발자 도구)** 에서 보이는 코드가 DOM (O)
> 
>
>   
>**virtual DOM**
> 
>* 실제 DOM의 구조와 비슷한, React 객체의 트리이다.
> * 개발자는 직접 DOM을 제어하지 않고 Virtual DOM을 제어하고, 
> * React에서 적절하게 Virtual DOM을 DOM에 반영하는 작업을 한다.
> * 스크린에 그려지는 것이 없기 때문에 Real DOM만 사용한 것보다 더 빠르다.



#### Real DOM VS Virtual DOM

> **Real DOM**
>
> * 만약 10개의 리스트가 있다.
> * 10개의 리스트 중 1번 리스트를 변경했을 때 **전체 리스트가 Reload** 된다.
>
> **Virtual DOM**
>
> * 만약 10개의 리스트가 있다.
> * Virtual DOM이 업데이트 되기 직전의 Virtual DOM 스냅샷과 비교하고 어떤 리스트들이 변했는지 파악한다.
> * 변화가 있는 리스트들만 실제 DOM에서 업데이트 된다.



#### Babel

> 최신 자바스크립트 문법을 지원하지 않는 브라우저들을 위해서 사용
>
> 최신 자바스크립트 문법을 구형 브라우저에서도 돌 수 있게 변환 시켜줌.



#### Webpack

> Javascript Application의 Static Module Bundler
>
> 웹팩은 프로젝트 구조를 분석하고 자바스크립트 모듈을 비롯한 관련 리소스들을 찾은 다음 이를 브라우저에 이용할 수 있는 **번들로 묶고** 패킹하는 **모듈 번들러이다.**



#### Props

> properties의 줄임말
>
> Props는 **부모 컴포넌트에서 자식 컴포넌트로**만 이동 가능하다.
>
> * ```react
>   <ChatMessages>
>   	messages = {messages}
>       currentMember={member}
>   </ChatMessages>
>   ```
>

#### State

> 부모 자식 사이에서 데이터를 전달하는 것이 아닌 **컴포넌트 안에서** 데이터를 교환 및 전달할 때 State를 사용한다.
>
> * ```react
>   state = {
>   	message : '',
>       attachFile : undefined,
>       openMenu : false
>   }
>   ```



#### React 생성

```bash
npx create-react-app .
```

#### React 실행

```bash
npm run start
```





#### npm npx

> **npm (Node Packaged Manager)** (관리)
>
> * node.js를 설치하면 내장되어 있다.
>
> **npx** (실행)
>
> * npm을 좀 더 편하게 사용하기 위해서 npm에서 제공해주는 하나의 도구



#### React Router Dom

> ```bash
> #설치
> npm install react-router-dom --save
> ```
>
> 문법
>
> ```javascript
> <Switch>
>   <Route exact path="/" component = {LandingPage} />
>   <Route exact path="/login" component = {LoginPage} />
>   <Route exact path="/register" component = {RegisterPage}/>
> </Switch>
> ```



#### AXIOS

> * Node.js를 위한 **Promise API**를 활용하는 HTTP **비동기 통신** 라이브러리이다.
>
> * 요청과 응답 데이터의 변형
>
> * HTTP 요청 취소 및 요청과 응답을 JSON 형태로 자동 변경
>
> ```bash
> npm install axios --save
> ```

> ##### 동기식 처리
>
> * 요청과 결과가 동시에 일어난다는 약속
> * 직렬적으로 태스크(task)를 수행한다.
> * 예를들어 서버에서 데이터를 가져와 화면에 표시하는 작업을 수행할 때, 
> * 서버에 데이터를 요청하고 데이터가 응답될 때까지 이후 태스크들을 블로킹(작업 중단)된다.
>   * 블로킹 방식으로는 A가 B에게 메시지를 보내고 B가 답변을 보낼 때까지 A는 메세지를 보낼 수 없다.
>
> ##### 비동기식 처리
>
> * 웹페이지를 리로드하지 않고 데이터를 불러오는 방식.
>   * Axios를 통해 서버에 요청을 한 후 멈추어 있는 것이 아니라 그 프로그램을 계속 돌아간다는 의미 내포
> * 병렬적으로 태스크를 수행한다.
> * 예를들어 서버에서 데이터를 가져와 화면에 표시하는 작업을 수행할 때,
> * 서버에 데이터를 요청한 이후 서버로부터 데이터가 응답될 때까지 **대기하지 않고 (Non-Blocking)** 즉시 다음 태스크를 수행한다.
>   * 대중에 일반적인 채팅 프로그램, A가 B에게 메세지를 보내고 B가 A에게 메세지를 보내지 않아도 A는 계속해서 메세지를 보낼 수 있다.



#### CORS (Cross-Origin Resource Sharing)

> 예를들어 
>
> server origin = localhost:4000
>
> client origin = localhost:3000
>
> 이렇게 두개의 다른 포트를 가지고 있는 서버는 아무 설정 없이 Request를 보낼 수 없다.

> **해결방법**
>
> **proxy 설정**으로 해결할 수 있다!
>
> ```bash
> npm install http-proxy-middleware --save
> ```
>
> ##### setupProxy.js
>
> ```javascript
> const { createProxyMiddleware } = require('http-proxy-middleware');
> module.exports = function (app) {
>     app.use(
>         '/api',
>         createProxyMiddleware({
>             target: 'http://localhost:5000',
>             changeOrigin: true,
>         })
>     );
> };
> ```



#### Proxy Server

> **유저 → 프록시 서버 → 인터넷**
>
> * 아이피를 Proxy Server에서 임의로 바꿔 버릴 수 있다.
> * 그래서 인터넷에서는 접근하는 사람의 IP를 모르게 된다.
>
> **유저 ← 프록시 서버 ← 인터넷**
>
> * 방화벽 기능
> * 웹 필터 기능
> * 캐쉬, 데이터, 공유 데이터 제공 기능

> **사용이유**
>
> * 회사에서 직원들의 인터넷 사용 제어
> * 캐쉬를 이용해 더 빠른 인터넷 이용 제공
> * 더 나은 보안 제공
> * 이용 제한된 사이트 접근 가능



#### Concurrently 

> 프론트엔드와 백엔드 서버 한 번에 켜기
>
> ```bash
> npm install concurrently --save
> ```
>
> **pacage.json 설정**
>
> ```json
> // "start" : "concurrenly \ command1 arg\" \"command2 arg\""
> 
> "dev" : "concurrently \"npm run backend\" \"npm run start --prefix client\""
> ```



#### CSS FRAMWORK

> * Material UI
> * React Bootstrap
> * Semantic UI
> * Ant Design
> * Materialize

> 이 강의에서는 **Ant Design**을 사용하기로 함
>
> ```bash
> npm install antd --save
> ```
>
> **index.js 에 import 추가**
>
> ```javascript
> import 'antd/dist/antd.css';
> ```



#### Redux

> React의 상태관리 라이브러리
>
> **Redux 데이터 Flow** (한방향)

> **React Component** → (Dispatch) →  **Action**  → (Middleware) →**REDUCER** → **STORE** → Subscibe → **React Component**

> **Action**
>
> > 무엇이 일어나는지 설명하는 객체
>
> ```react
> {type : 'LIKE_ARTICLE', articleId : 42} //id 42번에 좋아요
> {type : 'FETCH_USER_SUCCESS', response : {id : 3, name : 'Mary'}} // id가 3이고 이름이 mary인 user 가져오는 것을 성공했다.
> {type : 'ADD_TODO', text : 'Read the Redux docs.'} // 이 텍스트를 todo리스트에 add했다.
> ```
>
> ##### Reduser
>
> > 원래의 state가 action을 통해 변한 것을 설명해주는 것
>
> ``` react
> (previousState, action) => nextState
> //이전 State와 action을 받은 후에 next state를 return 한다.
> ```

> 1. redux
> 2. react-redux
> 3. redux-promise
>    * 디스패치한테 promise가 왔을 때 어떻게 해야하는지 알려주는 역할
> 4. redux-thunk
>    * 액션 객체가 아닌 함수를 디스패치할 수 있다.
>
> ````bash
> npm install redux react-redux redux-promise redux-thunk --save
> ````
>
> 



