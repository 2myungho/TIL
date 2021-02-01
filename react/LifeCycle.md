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

