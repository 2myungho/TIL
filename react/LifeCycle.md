## Class형 컴포넌트의 수명주기 (Life cycle)

##### will

> 어떤 작업을 작동하기 전

##### did

> 어떤 작업을 작동한 후에 실행



#### render 관련

* Component**WillMount**

  > 렌더링 되기 전에 실행

* Component**didMount**

  > 렌더링 된 직후에 실행

* Component**WillUnmount**

  > 컴포넌트가 웹 브라우저상에서 사라지기 전에 실행



#### Props 관련

* **shouldComponentUpdate**

  > props 또는 state가 변경되었을 때 실행

* Component**WillUpdate**

  > shouldComponentUpdate 이후, 컴포넌트 업데이트 직전에 싱행

* Component**DidUpdate**

  > 컴포넌트 업데이트 직후에 실행



## 함수형 컴포넌트 수명주기







## 함수형 컴포넌트와 클래스형 컴포넌트의 차이

> 함수형 컴포넌트는 클래스형 컴포넌트보다 **선언하기가 좀 더 편하다.**
>
> 메모리 자원을 덜 사용한다는 장점이 있다.
>
> state와 라이프사이클을 사용할 수 없었던 단점이 있었는데 Hooks이 도입되면서 해결되었다.

> **state 사용 차이**
>
> > state : 컴포넌트 내부에서 바뀔 수 있는 값
> >
> > 클래스형은 state와 라이프사이클 기능 사용이 가능하다.
> >
> > 함수형을 state와 라이프사이클 기능을 사용할 수 없고 Hooks를 통해 해결 가능하다.
>
> 