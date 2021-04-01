### Dockerfile 작성

```dockerfile
FROM node:10

RUN npm install

CMD ["node", "server.js"]
```

> FROM에 node를 입력한 이유
>
> * RUN에서 npm을 사용하려면 npm이 들어있는 베이스 이미지를 찾아야 하는데 그것들 중 하나가 node 이미지입니다.
>
> RUN에서 npm install 하는 이유
>
> * package.json에 적혀 있는 종속성들을 웹에서 자동으로 다운 받아서 설치해주기 위해



### 빌드시 Package.json 파일이 없다고 나오는 이유

```dockerfile
FROM node:10

RUN npm install

CMD ["node", "server.js"]
```

> 위의 RUN 단계에서 npm install을 하기 위해서는 pacage.json 파일이 필요하다.
>
> node 베이스 이미지의 파일 스냅샷이 임시 컨테이너의 하드 디스크로 들어오는데 이 파일 스냅샷 안에는 package.json, 코드 파일 등이 임시 컨테이너 밖에 존재한다. 즉 package.json이 컨테이너 안에 없기 때문에 npm install이 에러가 발생한다.
>
> package.json 및 파일을 컨테이너 안으로 넣어주려면 COPY 명령어를 사용한다.
>
> ```dockerfile
> COPY ./ ./
> #처음 ./는 로컬에 이 경로에 있는 파일을 가르킨다
> #두번째 ./는 도커 컨테이너 내에서 파일이 복사될 경로이다.
> 
> ```



### 어플리케이션 실행시 접근이 안되는 이유 (포트맵핑)

> 현재까지 컨테이너를 실행하기 위해 사용한 명령어
>
> ```
> docker run <이미지 이름>
> ```
>
> 앞으로 컨테이너를 실행하기 위해 사용할 명령어
>
> ```dockerfile
>  docker run -d -p 5000 : 8080 <이미지 이름>
>  #브라우저 : localhost:5000
>  #컨테이너 : 컨테이너 네트워크 8080
>  
>  #5000 포트로 접속하면 컨테이너 8080포트와 맵핑 되어 있어서 컨테이너 8080포트로 들어가게 된다.
> ```



### Working Directory 명시하기

> 이미지 안에서 어플리케이션 소스 코드를 갖고 있을 디렉토리를 생성하는 것이다.
>
> 그리고 이 디렉토리가 어플리케이션에 working directory가 된다.

> 그런데 왜 따로 working 디렉토리가 있어야 하나?
>
> COPY한 파일들이 이미지의 루트 디렉터리에 복사 되었다.
>
> ![working](https://user-images.githubusercontent.com/52882578/113081163-2227ce00-9213-11eb-88e6-d813cd129117.PNG)
>
> 1. 이 중에서 원래 이미지에 있던 파일과 이름이 같다면 ?
>    * 원래 있던 파일이 COPY된 파일로 덮어 씌어진다.
> 2. 모든 파일이 한 디렉터리에 들어가 버리니 너무 정돈이 안 되어 있다.
> 3. 그래서 모든 **어플리케이션을 위한 소스**들을 **WORK 디렉토리**를 따로 만들어서 **보관**한다.

> ```dockerfile
> FROM node:10
> 
> WORKDIR /usr/src/app
> ```
>
> 새로 빌드를 해주고 확인하기 위해
>
> ```bash
> docker run -it <이미지 id> sh
> ls
> ```
>
> ![working2](https://user-images.githubusercontent.com/52882578/113081615-f6f1ae80-9213-11eb-81f6-fb989678f3c3.PNG)
>
> working 디렉터리에 들어온 파일이 보인다
>
> `WORKDIR /usr/src/app` 이렇게 설정해 주었기 때문에 root 디렉터리부터 보이지 않는다.



### 어플리케이션 소스 변경으로 다시 빌드하는 것에 대한 문제점

> COPY 부분으로 인해서 소스를 변화시킨 부분은 server.ts 하나 뿐인데 모든 node _module에 있는 종속성들까지 다시 다운받아야 한다.
>
> 그리고 소스 하나 변경 시켰을 뿐인데 이미지를 다시 생성하고 다시 컨테이너를 실행시켜주어야 한다.
>
> #### 어플리케이션 소스 변경으로 재빌드시 효율적으로 하는 법
>
> ```dockerfile
> #원래
> FROM node:8
> WORKDIR /usr/src/app
> COPY ./ ./
> RUN npm install
> CMD ["node", "server.ts"]
> 
> #변경
> FROM node:8
> WORKDIR /usr/src/app
> COPY package.json ./
> RUN npm install
> COPY ./ ./
> CMD ["node", "server.ts"]
> ```
>
> **변경한 이유**
>
> * 소스 코드만 변경했을 때 종속성은 casing 이 된다. 
>
> ![casing](https://user-images.githubusercontent.com/52882578/113087735-396cb880-921f-11eb-95d0-153220fa58dc.PNG)
>
> * 모듈은 모듈에 변화가 생길때만 다시 다운 받아주며, 소스 코드에 변화가 생길 때 모듈을 다시 받는 현상을 없앴다.



### VOLUME

> #### 볼륨
>
> 데이터를 컨테이너가 아닌 호스트에 저장하는 방식이다. 따라서 데이터 볼륨은 컨테이너끼리 데이터를 공유할 때 활용할 수 있다.
>
> 
>
> #### 바인드 마운트
>
> 바인드 마운트를 사용하면 **호스트 파일 시스템의 특정 경로**를 **컨테이너로 바로 마운트**할 수 있다.
>
> volume을 이용해서 컨테이너를 실행하면 이미지를 새로 빌드하지 않아도 바꾼 소스코드가 적용이 된다.
>
> ```bash
> docker run -d -p 5000:8080 -v /usr/src/app/node_modules -v $(pwd):/usr/src/app dlaudghtn/nodejs
> 
> #-v /usr/src/app/node_modules 이 부분은 로컬에 node_modules이 없기 때문에 참조하지 않는다는 부분이다.
> ```
>
> VOLLUME의 작동원리는 도커 컨테이너가 로컬의 파일 or 디렉토리 (package.json, server.js 등) 을 참조해서 (Mapping) 도커 컨테이너에 반영한다.
>
> COPY
>
> * 로컬  → 파일 복사 →  도커 컨테이너 : 로컬에서 파일을 복사해서 이미지에 넣어준다
> * 로컬 ← 파일 참조 ← 도커 컨테이너 : 도커 컨테이너가 로컬 파일을 참조해서 반영한다.



### Docker Compose란 

> Docker Compose는 **다중 컨테이너 도커 어플리케이션**을 정의하고 실행하기 위한 도구입니다.
>
> 내가 예전에 했던 프로그램에서 account서비스와 accountDB를 각각의 컨테이너로 한 번에 실행한 것을 생각해보자
>
> * 다중 컨테이너를 실행시키는?

### docker-compose.yml

![docker-compose](https://user-images.githubusercontent.com/52882578/113095461-6e343c00-922e-11eb-87a4-45e4613c12cf.PNG)

> #### docker-compose.yml 설정
>
> ```yaml
> version: "3"
> services: 
>   redis-server:
>     image: "redis"
>   node-app:
>     build: .
>     ports:
>       - "5000:8080"
> ```
>
> 파일 설정후
>
> ```bash
> docker-compose up # 이미지가 없을 때 이미지를 빌드하고 컨테이너 시작
> 
> docker-compose up --build # 이미지가 있든 없든 이미지를 빌드하고 컨테이너 시작
> 
> docker-compose up -d --build # 컨테이너 백그라운드에서 실행
> ```

#### docker compose 컨테이너 멈추기

```bash
docker-compose down 
```

