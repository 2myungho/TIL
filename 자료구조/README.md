![stack_heap](https://user-images.githubusercontent.com/52882578/109632519-7430f180-7b8a-11eb-8979-e073a94b6e4c.PNG)



#### 스택

![stack_1](https://user-images.githubusercontent.com/52882578/109632358-4b106100-7b8a-11eb-946e-dab9a6463d75.PNG)

**10이라는 데이터**를 넣고 **이름을 a**라고 정해준다.

![stack_2](https://user-images.githubusercontent.com/52882578/109633527-87908c80-7b8b-11eb-9709-b767df3dfe83.PNG)

만약 스택에 데이터가 아닌 **위치 주소(사진에서는 #1)**가 저장되어 있다면 **힙에 위치를 찾아서** 출력합니다.

스택에 있는 주소가 힙에 있는 데이터를 가리키는 과정을 **레퍼런스**한다고 한다.

C는 레퍼런스가 들어 있는 변수이기 때문에 **C는 레퍼런스 변수**라고 한다.

![heap](https://user-images.githubusercontent.com/52882578/109633733-c9213780-7b8b-11eb-8431-11682dd34138.PNG)



#### 비파괴적 처리

> 어떠한 처리 후 원본이 변경되지 않는다
>
> +, - 연산자



#### 파괴적 처리

> 어떠한 처리 후 원본이 변경된다.
>
> ex) push 메소드



#### const 상수

> const는 스택에 있는 값을 변경하지 못하게 만듦
>
> 그렇기 때문에 데이터가 힙에 저장되는 배열의 값을 변경이 가능하다.