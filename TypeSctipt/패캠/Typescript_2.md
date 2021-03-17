#### 리액트 프로젝트 만들기

```bash
npx create-react-app [프로젝트 이름] --typescript
```

#### 기존 프로젝트에 타입스크립트 도입

> ```bash
> npm install --save-dev typescript @babel/preset-typescript ts-loader fork-ts-checker-webpack-plugin tslint tslint-react
> ```
>
> - **typescript**: Typescript(타입스크립트)를 사용합니다.
> - **@babel/preset-typescript**: babel(바벨)에서 Typescript(타입스크립트)를 빌드하기 위한 라이브러리입니다.
> - **ts-loader**: Webpack(웹팩)에서 Typescript(타입스크립트)를 컴파일 하기 위해 필요한 라이브러리입니다.
> - **fork-ts-checker-webpack-plugin**: ts-loader의 성능을 향상시키기 위한 라이브러리입니다.
> - **tslint, tslint-react**: 코딩 컨벤션을 체크하기 위한 라이브러리입니다.
>
> ```bash
> yarn add typescript @types/node @types/react @types/react-dom @types/jest
> ```
>
> * 라이브러리가 충돌나지 않게 잘 보고 설치해야겠다.



#### React.FC

> 최신 버전 CRA 에서는 컴포넌트에 생성성시 붙지 않는다.
>
> ##### 장점
>
> * props에 기본적으로 children이 들어가 있다
> * 컴포넌트 . 을 하면 contextTypes, defaultProps, displayName 등 값들이 자동완성 된다.
>
> ##### 단점
>
> * defaultProps가 제대로 작동하지 않는다는 단점이 있다.
>
> * ```react
>   //React.FC 사용
>   type GreetingsProps = {
>       name: string;
>       mark?: string;
>   };
>   
>   const Greetings: React.FC<GreetingsProps> = ({name, mark = '!'}) => {
>       return (
>           <div>
>               Hello, {name} {mark}
>           </div>
>       )
>   }
>   export default Greetings;
>   
>   type GreetingsProps = {
>       name: string;
>       mark: string;
>   };
>   
>   //미사용
>   function Greetings ({name, mark}): GreetingsProps {
>       return (
>           <div>
>               Hello, {name} {mark}
>           </div>
>       )
>   }
>   Greetings.defaultProps = {
>       mark: '!'
>   }
>   export default Greetings;
>   ```
>
> ##### ?
>
> * ``` react
>   type GreetingsProps = {
>       name: string;
>       mark?: string;
>       onClick: (name: string) => void
>   };
>   위와 같이 ?를 사용하게 되면
>   {mark && <p>{optional}</p>} //과 같은 코드도 작성이 가능하다.
>   ```



#### useState

> ```react
> function Counter() {
> 	const [count, setCount] = useState<number>(0) //제너릭을 사용한다. (생략해도 된다.)
> }
> ```

#### event

> ```react
> const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
> 	const {name, value} = e.target;
> }
> ```

#### useReducer

> ```react
> type Action = 
>     | {type: 'INCREASE'} 
> 	| {type: 'DECREASE'};
> 
> function reducer(state: number, action: Action): number{
> 	switch (action.type){
>     	...
>     }
> }
> ```



#### ReturnType 유틸

> 함수에서 반환하는 **타입**을 가져올 수 있다.
>
> 여기서 타입은 타입스크립트의 타입을 의미합니다
>
> ```react
> const INCREASE = 'counter/INCREASE' as const;
> const DECREASE = 'counter/DECREASE' as const;
> const INCREASE_BY = 'counter/INCREASE_BY' as const;
> 
> export const increase = () => ({type: INCREASE})
> export const decrease = () => ({type: DECREASE})
> export const increaseBy = (diff: number) => ({
>     type: INCREASE_BY,
>     payload: diff
> })
> 
> //리듀서 액션의 대한 타입
> type CounterAction = 
>     | ReturnType<typeof increase>
>     | ReturnType<typeof decrease>
>     | ReturnType<typeof increaseBy>
>     
> //리듀서 액션의 대한 타입 기본형
> type CounterAction = 
>     | {type: 'counter/INCREASE'}
> 	| ...
> ```
>
> 1. 액션에 `as const`가 되어 있어야 한다.
>    - as const가 되어 있지 않으면 `type: string`  이라고 표시된다.
>    - 반대로 as const가 되어 있으면 `type:"counter/INCREASE"`로 잘 표기된다.



#### match 사용하기

> 라우팅을 할 때,  주소 값 뒤에 :id :name 등과 같이 추가적인 정보가 필요한 경우 App.js 파일에서 라우팅 설정을 한다.
>
> ```react
> //App.js
> function App() {
>   return <Route path="/user/:name" component={User} />
> }
> ```
>
> 그리고 User.js에서 match를 사용하면 name 값을 받을 수 있다.
>
> ```react
> export default function User({match}) {
>     console.log(match) // params, isExact, path, url 등등..
>     console.log(match.params.name) // user1, user2 ...등등
> }
> ```
>
> 만약 match를 User.tsx 라는 타입 스크립트 파일에서 사용한다면?
>
> `react-router-dom` 에서 제공하는 `RouteComponentProps`를 사용한다.
>
> ```react
> export default function User({match}: RouteComponentProps) {
>     const name = match.params.name;
> }
> ```
>
> 하지만 위와 같이 사용할 경우 `parans.name`부분에서 {}형식에 name이 없다는 에러가 발생한다.
>
> 우리가 받을 params 타입을 만들어서 제네릭에 주입해줘야 사용할 수 있다.
>
> ```react
> type MatchParam = {
>     name: string
> }
> export default function User({match}: RouteComponentProps<MatchParam>) {
>     const name = match.params.name;
> }
> ```