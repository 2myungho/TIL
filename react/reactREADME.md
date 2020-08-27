## 08/25 리덕스 사용해보기
> class형 컴포넌트와 mobx를 사용해 보았으니 함수형 컴포넌트와 리덕스를 사용해서 간단한 게시판을 구현하려고 한다.
>
> 1. 액션 타입 정의하기
>
>    ```react
>    const INCREASE = 'counter/INCREASE';
>    const DECREASE = 'counter/DECREASE'
>    ```
>
>    * 액션타입은 대문자로 정의
>    * 문자열 내용은 '모듈 이름/액션이름' 현태로 저장
>
> 2. 액션 생성 함수 만들기
>
>    ```react
>    export const increase = () => ({type:INCREASE});
>    export const decrease = () => ({type: DECRASE});
>    ```
>
>    * 앞부분에 export 라는 키워드가 들어간다.

