### Linux node version 업데이트 하기

> 현재 node 버전 확인
>
> ```bash
> node -v
> ```
>
> 강제로 캐시 삭제
>
> ```bash
> sudo npm cache clean -f
> ```
>
> n 모듈 설치
>
> ```bash
> sudo npm install -g n
> ```
>
> n 모듈을 사용하여 Nodejs 설치 (다른 버전의 Node.js 를 설치하려면 sudo n 5.11.0 이런식으로 명령어를 입력하면 됩니다)
>
> ```bash
> sudo n 14.16.0
> ```
>
> 새로운 node 버전 확인 (바뀌지 않았을 경우 터미널 재시작)



### Dockerfile.dev 빌드! unable to evaluate symlink .. 에러

> 원래 이미지를 빌드할 때 해달 디렉토리만 정해주면 Dockerfile을 자동으로 찾아서 빌드하는데 Dockerfile을 .dev와 .prod로 나누면 자동으로 올바른 도커 파일을 찾지 못하여 이런 에러가 발생한다.
>
> 해결책은 임의로 build할 때 어떠한 파일을 참조할지 알려준다.
>
> ```bash
> docker build -f Dockerfile.dev .
> #-f 옵션은 이미지를 빌드할 때 쓰일 도커 파일을 임의로 지정해준다.
> ```



### node_modules

> Dockerfile에서 COPY 할 때 모든 파일을 COPY하는데  node_modules 까지 복사가 되어 시간이 너무 오래 걸린다. node_modules는 RUN npm install 단계에서 이미 만들어지기 때문에 빌드 전에 node_modules 폴더를 지우고 빌드하자



#### 도커 이미지로 리액트 앱 실행

> ```bash
> docker run -it -p 3000:3000 <이미지 이름>
> # 리액트쪽에서 업그레이드로 인해 -it 붙여야만 실행 가능
> #포트 맵핑을 해주지 않으면 컨테이너 안에서 실행되고 있는 리액트 앱에 도달하지 못한다.
> ```



### 도커 컴포즈로 리액트 앱 실행해보기

#### docker-compose.yml

```yaml
version: "3"
services:
  react:
    build:
      context: .
      dockerfile: Dockerfile.dev
    ports:
      - "3000:3000"
    volumes:
      - ./:/usr/src/app #현재 디렉터리에 있는 모든 파일
    stdin_open: true
```

![docker-compose2](https://user-images.githubusercontent.com/52882578/113225316-4e515680-92c8-11eb-8b5c-7b08d29a51c9.PNG)

> ```bash
> docker-compose --build
> ```



### 도커로 리액트 앱 테스트 하기

#### 이미지 생성

```bash
docker build -f dockerfile.dev .
```

#### 앱 실행

```bash
docker run -it 이미지 이름 npm run start
```

> 도커 컨테이너 안에는 node-modules가 있기 때문에 테스트할 수 있는 모듈이 포함되어 있다.

> 테스트 파일이 변경될 때 바로 반영이 될 수 있도록 해본다.
>
> ```yaml
> tests:
>     build:
>       context: .
>       dockerfile: Dockerfile.dev
>     volumes:
>       - ./:/usr/src/app #현재 디렉터리에 있는 모든 파일
>     command: ["npm", "run", "test"]
> ```



### Nginx

> 운영 환경 (배포 후)에 Nginx 사용
>
> ![Nginx](https://user-images.githubusercontent.com/52882578/113226176-78a41380-92ca-11eb-968a-8a6141e1eadd.PNG)
>
> **운영 환경에 가면 개발 서버가 없어진다.**
>
> 개발 서버 대신 **Nginx** 웹 서버가 정적 파일을 제공해주게 된다.
>
> ![Nginx2](https://user-images.githubusercontent.com/52882578/113226463-3af3ba80-92cb-11eb-9964-b9126280479f.PNG)
>
> #### 왜 개발환경 서버와 운영환경 서버가 다른가?
>
> 개발에서 사용하는 서버는 소스를 변경하면 자동으로 전체 앱을 다시 빌드해서 변경 소스를 반영해주는 것 같이 개발 환경에 특화 된 기능들이 있기 때문에 그러한 기능이 없는 Nginx 서버보다 더욱 적합하다.
>
> 그리고 우녕ㅇ황경에서는 소스를 변경할 때 다시 반영해줄 필요가 없으며 개발에 필욯한 기능들이 필요하지 않기에 더 깔끔하고 빠른 Nginx를 웹서버로 사용한다.



### 운영환경 도커 이미지를 위한 Dockerfile 작성하기

![Nginx3](https://user-images.githubusercontent.com/52882578/113227722-514f4580-92ce-11eb-8dd2-2616a73397cd.PNG)

> CMD  : 컨테이너가 시작할 때 실행하는 명령어
>
> 개발환경에서는 package.json과 각종 파일을 받은 후 컨테이너가 시작하면 npm run start로 리액트를 실행한다.
>
> 운영환경에서는 각종 파일을 받은 후 컨테이너가 시작하면 npm run build 명령어를 시작해서 build 파일을 만든다. 그 만들어진 build 파일들 Nginx 서버가 웹브라우저에 보일 수 있게 해준다.

#### 운영 환경의 Dockerfile 2단계

> 1. 빌드 파일을 생성한다.
> 2. Nginx를 가동하고 생성된 빌드 폴더의 파일들을 웹브라우저의 요청에 따라 제공하여 준다.
>
> ```bash
> docker build .
> 
> #nginx는 기본 포트가 80이다.
> docker run -p 8080:80 <이미지 이름>
> ```
>
> 



* 로컬에서는 개발서버가 존재하지만 배포 후에는 왜 개발 서버가 사라지는 가에 대한 질문
  * 개발 서버는 리액트에서 지원해주는 것인가?
  * 내가 찾은 답:
    * 리액트를 실행하면 웹팩에서 제공하는 `webpack-dev-server` 개발 서버를 지원한다. 
    * webpack-dev-server : 빠른 실시간 리로드 기능을 갖춘 개발 서버