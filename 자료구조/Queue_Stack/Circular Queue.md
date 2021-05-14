### 원형 큐 (Circular Queue)

#### 배열로 구현한 선형큐의 단점

![Circular Queue](https://user-images.githubusercontent.com/52882578/116970158-6f0b3280-acf2-11eb-8b50-b1448c6983e3.PNG)

> 위에 그림을 보면
> 큐에서 삽입과 꺼내기를 반복하다보면 **rear**가 배열의 맨 마지막 인덱스를 가리키게 되고, 앞에는 데이터가 비어 있는 위와 같은 모양이 됩니다. 
>
> 저장소가 아직 비어 있지만 삽입을 해도 이 저장소는 가득 찼다고 인지하게 됩니다.
>
> Dequeue를 실행할 때마다 데이터를 한 칸 앞으로 이동시키지 않고, 인덱스로 큐의 연산을 진행했기 때문입니다.
>
> 위와 같은 문제를 해결하기 위해 나타난 방법이 **원형 큐** 입니다.



### 원형 큐

순환 대기열을 사용합니다.
순환 대기열을 사용한다는 것은 rear가 저장소 끝에 도달하면 다시 대기열의 시작으로 돌아오는 것을 의미합니다.



https://levelup.gitconnected.com/visualize-design-and-analyse-the-circular-queue-data-structure-6a4ff6d4359c