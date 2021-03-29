## React

> **화면**을 만들기 위한 자바스크립트 **라이브러리**이다.
>
> * 화면을 만들기 위한 기능들을 모아둔 것

#### 장점

> 빠른 업데이트 & 렌더링 속도
>
> 재사용성
>
> 풍부한 생태계를 갖추고 있다.
>
> React Native까지 배우면 모바일 앱도 개발 가능

> **Virtual DOM**
>
> * 웹페이지와 실제 DOM 사이에서 중간 매개체 역할을 한다.
>
> * 데이터가 업데이트되면, 전체 UI를 Virtual DOM에 리렌더링 한다.
>
> * 이전 Virtual DOM에 있던 내용과 수정된 현재의 내용을 비교한다.
>
> * **바뀐 부분만 실제 DOM에 적용**이 된다. (렌더링 된다.)
>
> * **DOM (Document Object Model)  문서 객체 모델** :
>
>   * HTML : 화면에 보이고자 하는 모양과 구조를 문서로 만든 것으로 단순 텍스트로 구성되어 있다. (최초의 설계도)
>   * DOM : HTML 문서의 내용과 구조가 **객체 모델로 변화되어** 다양한 프로그램에서 사용될 수 있다. 
>     * JavaScript에 의해 수정될 수 있는 동적 모델이어야 한다.
>     * 가상 요소를 포함하지 않는다. (ex : after ,before)
>     * 보이지 않는 요소를 포함한다. (ex : display : none)
>
>   

> **Component**
>
> * 재사용 가능한 코드 블록으로 UI 단위를 구성합니다.
>   * 리액트로 작성된 화면은 컴포넌트만으로 구성되어 있습니다.
> * 개발 속도가 빨라짐!
> * `Component`는 `props`를 받아 `Elements`를 출력하는 함수와 같습니다.
>
> 

> **JSX (JavaScript + XML / HTML)**
>
> * 간결한 코드
>
>   * ```react
>     //JSX 사용함
>     <div>Hello, {name}</div>
>     //JSX 사용 안함
>     React.createElement('div', null, `Hello, ${name}`);
>     ```
>
> * 버그를 발견하기 쉬움
>
> * JSX는 객체를 나타낸다.
>
>   * ```react
>     //JSX 사용함
>     const element = (
>     	<h1 className = "greeting">
>         	Hello, world!
>         </h1>
>     )
>     
>     //JSX 사용 안함
>     const element = React.createElement(
>     	'h1',
>         {className : 'greeting'},
>         'Hello, world!'
>     )
>     ```
>
>   * React.createElement()의 결과로 아래와 같은 객체 생성됨
>
>     ```react
>     const element = {
>     	type: 'h1',
>         props: {
>             className: 'greeting',
>             children: 'Hello, world!'
>         }
>     }
>     ```



#### Elements

> React 앱을 구성하는 가장 작은 블록
>
> 우리가 화면에 보는 것
>
> 엘리먼트는 컴포넌트의 구성요소이다.
>
> Element 생성후에는 children이나 attributes를 바꿀 수 없다. (불변성) ??
>
> ```react
> //Root DOM node
> //리액트로 만들어진 모든 앱들은 Root DOM node를 가지게 된다.
> <div id = "root"></div>
> 
> const element = <h1>Hello, world</h1>;
> //Root DOM에다 element 렌더링
> ReactDOM.render(element,
> document.getElementById('root'));
> ```
>
> **Element variable**
>
> ```react
> render(){
>     let button = <div onClick={this.test} />
>     return(
>         {button} //Element variable
>     )
> }
> 
> ```



#### Component

> React를 구성하는 구성요소를 뜻한다.
>
> React의 모든 페이지는 컴포넌트로 구성되어 있다. (컴포넌트 기반)
>
> Component에 Props에 따라서 각기 다른 Elements가 나온다.
>
> Props를 직접 바꿀 수 없고,  같은 Props에 대해서는 항상 같은 결과를 보여줄 것! (pure)
>
> Component에 input으로는 props가, output으로는 Element가 나온다.

![component2](https://user-images.githubusercontent.com/52882578/109093846-63344a80-775c-11eb-93cc-0d9f37592b7d.PNG)

![component3](https://user-images.githubusercontent.com/52882578/109094036-b6a69880-775c-11eb-954c-f0eda9714d78.PNG)

```react
//DOM 태그를 사용한 element
const element = <div />;
//사용자가 정의한 Component를 사용한 element
const element = <Welcom name ="Sara"/>
```



#### Props (Property)

> 컴포넌트 끼리 값을 전달하는 수단이다.
>
> * **Component의 속성**이다.
>
> **변하지 않는 데이터이다** (불변성)
>
> * Read - only 읽을 수만 있고 값을 변경할 수 없다.

#### State

> **React Component의 상태** & 데이터
>
> Component에 대한 **변경 가능한 데이터**
>
> 사용자가 정의한다.
>
> state는 직접 수정하면 안된다.
>
> * 리액트에서 관리한다.
> * setState() 함수를 사용한다.



#### LifeCycle 생명주기

> https://github.com/2myungho/TIL/blob/master/react/LifeCycle.md
>
> Mounting 출생
>
> * 컴포넌트가 생성되었을 때
> * componentDidMount
>
> Updating 인생
>
> * state 가 변경 되었을 때
> * componentDidUpdate
>
> Unmounting 사망
>
> * 컴포넌트가 웹상에서 사라졌을 때
>
> * componentWillUnmount



#### Function Component

> ```react
> function Welcome(props){
> 	return <h1>Hello, {props.name}</h1>
> }
> ```



#### Class Component

> ```react
> class Welcom extends React,Compnent{
> 	render(){
>         return <h1>Hello, {this.props.name}</h1>
>     }
> }
> ```



#### 이벤트 핸들러

> id라는 arument를 deleteRow 함수에 전달
>
> ````react
> //화살표 함수
> <button onClick={(e) => this.deleteRow(id, e)}>Delete Row</button>
> //bind 사용
> <button onClick={this.deleteRow.bind(this, id)}>Delete Row</button>
> ````



#### multiple input

> 동일한 태그가 여러 개일 경우에 useState나 onChange를 여러 개 만드는 것 보다
>
> **각 태그에 name 값을 설정**해주고 이벤트가 발생했을 때 이를 참조하여 상태 값을 관리하는 것이 좋다.
>
> ```react
> import React, { useState } from 'react';
> 
> function InputSample() {
>   //객체 형태로 두 input 태그의 name 속성의 초기값을 설정해준다.
>   const [inputs, setInputs] = useState({
>     name: '',
>     nickname: ''
>   });
> 
>   const { name, nickname } = inputs; // 비구조화 할당을 통해 값 추출
> 
>   const onChange = (e) => {
>     const { value, name } = e.target; // 우선 e.target 에서 name 과 value 를 추출
>     setInputs({
>       ...inputs, // 기존의 input 객체를 복사한 뒤
>       [name]: value // name 키를 가진 값을 value 로 설정
>     });
>   };
> 
>   const onReset = () => {
>     setInputs({
>       name: '',
>       nickname: '',
>     })
>   };
> 
> 
>   return (
>     <div>
>       <input name="name" placeholder="이름" onChange={onChange} value={name} />
>       <input name="nickname" placeholder="닉네임" onChange={onChange} value={nickname}/>
>       <button onClick={onReset}>초기화</button>
>       <div>
>         <b>값: </b>
>         {name} ({nickname})
>       </div>
>     </div>
>   );
> }
> 
> export default InputSample;
> ```



#### Component의 렌더링 막는 법

> **null**을 리턴하면 된다.
>
> ```react
> return null;
> ```



#### React 의 프로덕션 빌드

> ```bash
> npm run build
> 
> npm install -g serve
> serve -s build
> ```





#### 

