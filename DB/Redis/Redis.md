레디스란

> Redis(Remote Dictionary Server)
>
> **메모리 키-값 구조** 데이터 관리 시스템이며, **모든 데이터를 메모리에 저장**하고 **빠르게 조회**할 수 있는 비관계형 데이터베이스이다. (NoSql)



레디스를 쓰는 이유

> 메모리에 저장을 하기 때문에 Mysql 같은 데이터베이스에 데이터를 저장하는 것과 데이터를 불러올 때 훨씬 빠르게 처리할 수가 있으며,
>
> 비록 메모리에 저장하지만 영속적으로도 보관이 가능하다.
>
> 그래서 서버를 재부팅 해도 데이터를 유지할 수 있는 장점이 있다.



레디스 클라이언트 생성

> 레디스에서 제공하는 createClient() 함수를 이용해서 redis.createClient로 레디스 클라이언트를 생성해준다.
>
> 레디스 서버와 node.js 앱이 작동하는 곳이 다른 곳이라면 host인자와 port트 인자를 주어야한다.
>
> ```javascript
> const client = redis.createClient({
> 	host: "https://redis-server.com",
> 	hoet: 6379
> })
> ```
>
> 하지만!
>
> Docker-Compose를 사용할 때는 host 옵션은 docker-compose.yml 파일에 명시한 컨테이너 이름으로 주면된다.
>
> ```javascript
> const client = redis.createClient({
> 	host: "redis-server",
> 	hoet: 6379
> })
> ```
>
> 