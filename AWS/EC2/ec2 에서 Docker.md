## ec2 ubuntu 서버에서 docker 설치하기

* ```bash
  //업그레이드가 필요한 패키지를 최신 버전의 패키지로 업데이트
  sudo apt update
  
  //도커를 위해 필요한 패키지 설치
  
  //패키지 관리자가 https를 통해 데이터 및 패키지에 접근할 수 있도록 한다.
  sudo apt install apt-transport-https 
  
  //certificate authority에서 발행되는 디지털 서명. SSL 인증서의 PEM 파일이 포함되어 있어 SSL 기반 앱이 SSL 연결이 되어있는지 확인할 수 있다.
  sudo apt install ca-certificates
  
  //특정 웹사이트에서 데이터를 다운로드 받을 때 사용
  sudo apt install curl
  
  //*PPA를 추가하거나 제거할 때 사용한다.
  //PPA (Personal Package Archive) : 개인 패키지 저장소
  sudo apt install software-properties-common
  
  //Docker 설치
  curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
  sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"
  sudo apt update
  apt-cache policy docker-ce
  sudo apt install docker-ce
  ```

* 

