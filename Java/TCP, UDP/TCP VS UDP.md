## TCP VS UDP

#### TCP (Transmission Control Protocol)

> 서버와 클라이언트 간에 데이터를 **신뢰성** 있게 전달하기 위해 만들어진 **프로토콜**입니다.
>
> OSI 7계층중 4계층 **전송계층**
>
> * 전송계층은 ip에 의해 전달되는 **패킷의 오류를 검사**하고 **재전송 요구 등의 제어**를 담당하는 계층입니다.
>* **세그먼트 (Segment)** : 데이터를 전송하기 위하여 **적절한 크기로 분할한 조각**
> * **패킷 (Packet)** : 3계층의 네트워크 계층에서 사용됩니다.
>  * 전송을 위한 분할된 데이터 조각 (세그먼트)에 **목적지까지의 전달을 위하여** Source IP와 Destination IP가 포함된 **IP Header**가 붙은 형태의 메세지입니다.
> 
>##### 연결형 서비스
> 
>* TCP 3 - way handshaking  과정을 통해 **연결을 설정**합니다. **(연결 지향형)**
> * TCP 4 - way handshaking 과정을 통해 **연결을 해제**합니다.
> 
>##### 흐름 제어
> 
>- **데이터 처리 속도를 조절**하여 수신자의 버퍼 오버플로우를 방지합니다.
> 
>##### 혼잡 제어
> 
>* 네트워크 내의 **페킷 수가 넘치게 증가하지 않도록 방지**합니다.
> 
>##### 신뢰성이 높은 전송

#### IP

> IP는 컴퓨터들의 네트워크 상에서 구분되기 위한 이름
>
> 예를들면 실생활의 우편주소.

> 쉽게 말해   **ip**는 **데이터가 찾아갈 주소**
>
> **TCP**는 찾아온 데이터가 형식에 맞게 왔는지 확인하는 **필터** 역할



##### UDP (User Datagram Protocol)

> **비연결형 프로토콜**입니다.
>
> 혼잡제어를 하지 않기 때문에 **TCP 보다 빠릅니다.**
>
> * 대신 패킷 손실이 발생할 수 있습니다.
> * 예를들어 1000개의 패킷을 전송할 경우 10개의 패킷 손상이 되거나 분실되었을 때,
> * **TCP**는 이 10개의 **패킷을 재전송**하기 위해 시간이 더 걸릴 것입니다.
> * **UDP**는 이 10개의 **패킷을 무시**하고 전송하기 때문에 TCP보다 전송 속도가 더 빠릅니다.
>
> DNS, Broadcasting에서 사용
>
> (도메인, 실시간 동영상 서비스에서 사용)



#### TCP VS UDP

##### TCP의 데이터 송신 과정

> 송신자 : 지금 시간 괜찮으세요?
>
> 수신자 : 네. 지금 시간 괜찮아요.
>
> 송신자 : 지금 거기로 갈게요.
>
> 수신자 : 네. 나와 있을게요.

##### UDP의 데이터 송신 과정

> **일발적인 송신**이 가능합니다.
>
> 송신자 : 안녕하세요?
>
> 송신자 : 왜 말이 없으세요?
>
> 송신자 : 말 좀 해주세요!



![TCP vs UDP](C:\Users\82108\LMH\TIL\TIL\Java\TCP, UDP\image\TCP vs UDP.png)