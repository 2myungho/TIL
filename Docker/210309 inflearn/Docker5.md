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