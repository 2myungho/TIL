### Travis CI

> Travix CI는 Github에서 진행되는 오픈소스 프로젝트를 위한 **지속적인 통합 **(Continuous Integeration) 서비스이다.

#### 흐름

로컬 Git -> Github -> Travis CI -> AWS

> **.travis.yml 파일 설정**
>
> ```yaml
> sudo: required
> 
> lanuage: generic
> 
> services:
>   - docker
> 
> berfore_install:
>   - echo "start createing an image with dockerfile"
>   - docker build -t dlaudghtn/docker-react-app -f Dockerfile.dev .
> 
> script: 
>   - docker run -e CI=true dlaudghtn/docker-react-app npm run test -- --coverage
> 
> after_success:
>   - echo "Test success"
> ```
>
> 1. 로컬 Git에 있는 소스를 Github 저장소에 Push 한다.
> 2. Github master 저장소에 소스가 Push가 되면 Travis CI에게 소스가 Push 되었다고 애기를 해준다.
> 3. Travis CI는 업데이트 된 소스를 Github에서 가지고 온다.
> 4. 깃헙에서 가져온 소스의 테스트 코드를 실행해 본다.
> 5. 테스트 코드 실행 후 테스트가 성공하면 AWS 같은 호스팅 사이트로 보내서 배포를 한다.



### Nginx의 설계 방법

Nginx의 기능

> * 정적 파일을 제공함
> * 라우팅 기능 (프록시 기능)

#### Nginx의 Proxy를 이용한 설계

> api 경로를 통해 제공해줌

<img src="https://user-images.githubusercontent.com/52882578/113369104-6e057f00-939b-11eb-806b-4e23cd85ef12.PNG" alt="Nginx4" style="zoom:80%;" />

> ```javascript
> axios.get('/api/values')
> ```



#### Nginx는 정적파일을 제공만 해주는 설계

> port를 이용

<img src="https://user-images.githubusercontent.com/52882578/113369383-321ee980-939c-11eb-8f29-6e8cdefe283c.PNG" alt="Nginx5" style="zoom:80%;" />

> ```javascript
> axios.get('http://localhost:5000/api/values')
> 
> axios.get('http://leemh.dst.lo:5000/api/values')
> ```



### Nginx.conf 파일

> ```javascript
> server{
> 	listen 3000;
>     
>     location / { 
>         //location/로 갈 때 무슨 일을 할지
>         root /usr/share/nginx/html;
>         index index.html index.htm;
>         try_files $uri $uri/ /index.html;
>     }
> }
> ```



### Docker Voume 을 이용한 데이터 베이스 데이터 유지하기

> 원래 컨테이너를 지우면 컨테이너에 저장된 데이터가 지워지게 된다.
>
> Docker Voume을 사용해서 데이터 베이스의 저장된 자료를 컨테이너를 지우더라도 자료가 지워지지 않을 수 있게 할 수 있다.
>
> **볼륨 사용 X**
>
> 1. 이미지로 컨테이너 생성
> 2. 컨테이너 생성 후 읽기 전용
> 3. 컨테이너 안에서의 변화
> 4. 변화된 데이터를 컨테이너 안에 저장
> 5. 컨테이너 삭제시 컨테이너 안에 저장된 데이터도 함께 삭제
>
> **Volume 이란?**
>
> * 볼륨은 도커 컨테이너에 의해 생성되고 사용되는 지속적인 데티어를 위한 선호 메커니즘이다.
>
> ![volume](https://user-images.githubusercontent.com/52882578/113390787-5e9f2980-93cd-11eb-9355-42b5125b6d11.PNG)
>
> 이렇게 컨테이너에 뱐화가 일어난 데이터가 컨테이너 안에 저장되는 것이 아닌 호스트 파일 시스템에 저장되고 그 중에서도 도커에 의해서만 통제가 되는 도커 Area에 저장이 되므로 컨테이너를 삭제해도 변화된 데이터는 사라지지 않는다.





Travis CI에서 빌드 후 테스트를 진행하는데 빌드 된 이미지는 Dockerhub에 push 된다.

