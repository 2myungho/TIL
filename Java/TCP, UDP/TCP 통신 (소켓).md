## TCP 통신 (소켓)

##### Stream

> 바이트 단위의 입출력

##### Reader / Write

> 캐릭터 단위의 입출력
>
> 캐릭터는 바이트 2개가 모여서 구성한 데이터

##### InputStreamReader(InputStream in)

> 주어진 입력 바이트 스트림 in에 대해 기본 인코딩을 사용하는 객체를 생성한다.
>
> 바이트 스트림 -> 문자 스트림

#####  OutputStreamWriter(OutputStream out)

> 주어진 출력 바이트 스트림 out에 대해 기본 인코딩을 사용하는 객체를 생성한다. 
>
> 문자 스트림 -> 바이트 스트림

##### 바이트 스트림

> 한번에 한 바이트씩 연속적으로 전송되는 데이터의 흐름과 같이 끊임 없이 연속되는 바이트 열



```java
//클라이언트의 연결 요청을 기다리면서 연결 수락을 담당
//클라이언트가 연결 요청을 해오면 ServerSocke은 연결을 수락하고 통신용 Socket을 만든다.
ServerSocket server = null;
//연결된 클라이언트와 통신을 담당
Socket socket = null;
```



출처 :

>  https://m.blog.naver.com/PostView.nhn?blogId=pisibook&logNo=221582271090&proxyReferer=https:%2F%2Fwww.google.com%2F

