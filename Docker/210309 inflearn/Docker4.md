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



### 도커 볼륨을 이용한 소스코드 변경

