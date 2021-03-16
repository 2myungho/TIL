#### 리액트 프로젝트 만들기

```bash
npx create-react-app [프로젝트 이름] --typescript
```



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