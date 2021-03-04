#### 보일러 플레이트 코드

> 재사용 가능한 프로그램
>
> 보일러 플레이트 : 변경 없이 계속하여 재사용할 수 있는 저작품을 말한다.



#### Node js 시작

**npm 패키지 만들기**

> ```bash
> npm init
> ```
>
> * package.json

**Express 설치**

> ```bash
> npm install express --save
> ```

**Node Start**

> ```json
> "scripts": {
>     "start": "node index.js",
>     "test": "echo \"Error: no test specified\" && exit 1"
> },
> ```
>
> start 작성후
>
> ```bash
> npm run start
> ```



**Mongodb 유저 & 클러스터 생성**

>

**Mongoose 알아보기**

> ```bash
> npm install mongoose --save
> ```

**Mongoose 연결**

> ```javascript
> const mongoose = require('mongoose')
> mongoose.connect('mongodb+srv://[유저아이디]:[비밀번호]@cluster0.zgz7q.mongodb.net/myFirstDatabase?retryWrites=true&w=majority',{
>   useNewUrlParser : true, useUnifiedTopology : true, useCreateIndex : true, useFindAndModify: false
> }).then(() => console.log("MongoDB Connected...!!!!!!!!!!!!!!!")) //연결 성공
>   .catch(err => console.log(err + '!!!!!!!!!')) //연결 실패
> ```



**스키마 생성**

> 데이터베이스의 구조와 제약조건에 관한 전반적인 명세를 기술한 것
>
> * 개체의 특성을 나타낸 **속성 (Attribute)**
> * 속성들의 집합으로 이루어진 **개체 (Entity)** 
> * 개체 사이에 존재하는 **관계 (Relation)**
> * 이들이 유지해야할 제약조건들을 기술한 것
>
> **Model** : 스키마를 감싸주는 역할





#### GIT VS GIT HUB

> GIT은 소스코드를 관리할 수 있는 툴이다.
>
> GIT HUB는 GIT을 사용하는 서비스이다. 



#### body-parser

> parsing 
>
> * 가지고 있는 데이터를 내가 원하는 형태의 데이터로 '가공'하는 과정
> * 그 과정을 수행하는 모듈 혹은 메소드를 **parser**이라고 한다.
>
> 예를들어 회원 가입할 때  client에서 오는 정보를 서버에서 분석해서 가져올 수 있게 해주는 것이다.
>
> 스프링에서 사용하는 @Requestbody를 생각해보자. 
>
> Node js에서는 데이터를 키 : 값으로 받기 위해 body-parser 라이브러리를 사용해야하는구나 싶다.
>
> ```javascript
> //application/x-www-form-urlencoded 와 같은 데이터를 분석해서 가지고 올 수 해줌
> app.use(bodyParser.urlencoded({extended : true}));
> //application/json 과 같은 데이터를 분석해서 가지고 올 수 있음
> app.use(bodyParser.json());
> ```
>
> ```javascript
> app.post('/register', (req,res) => {
>   const user = new User(req.body)
> 
>   user.save((err, userInfo) => {
>     if(err) return res.json({success : false, err})
>       return res.status(200).json({
>         //json 파일로 성공적으로 회원이 생성됐다면 success : true 반환
>         success : true
>       })
>   })
> })
> ```
>
> 



#### NODE MON

> 소스를 변경할 때 그걸 감지해서 자동으로 서버를 재시작해주는 toll
>
> 서버를 재시작할 필요가 없음
>
> ```bash
> npm install nodemon --save --dev
> #--dev : 로컬에서만 사용하겠다,
> ```
>
> ```json
> //start시 nodemon 사용해서 시작하겠다 키 부분은 편한데로 작성한다.
> "backend": "nodemon index.js",
> ```



#### 환경 변수 process.env.NODE_ENV

> Local 환경
>
> * development
>
> Deploy (배포) 한 후
>
> * production



#### 비밀 정보를 보호하기

````javascript
const mongoose = require('mongoose')
mongoose.connect('mongodb+srv://myungho:mongodb123@cluster0.zgz7q.mongodb.net/myFirstDatabase?retryWrites=true&w=majority'...
````

> 위 부분에  MongoDB ID와 PW가 노출되어 있습니다.
>
> Local환경에서는 dev 파일에서 관리하고
>
> Deploy 환경에서는 HEROKU의 관리를 받도록 한다.
>
> * https://www.heroku.com/
> * 헤로쿠의 사용법은 나중으로
>
> key.js 파일
>
> ```javascript
> if(process.env.NODE_ENV === 'production'){
>     //배포 환경이면 prod 파일 사용
>     module.exports = require('./prod');
> }else{
>     //로컬 환경이면 dev 파일 사용
>     module.exports = require('./dev');
> }
> ```

> 저 부분을 따로 gitignore에 작성한다.



#### Bcrypt로 비밀번호를 암호화 하기

> ```bash
> npm install bcrypt --save
> ```
>
> save 하기 전에 암호화를 해야하며, mongoose의 기능을 이용한다.

##### 회원 가입

![bcrypt_0](https://user-images.githubusercontent.com/52882578/108813118-bb037200-75f3-11eb-8c03-a0b894f5a1b2.PNG)

##### 비밀번호 암호화 후

![bcrypt_1](https://user-images.githubusercontent.com/52882578/108813123-bc349f00-75f3-11eb-840d-31f26ca2e57e.PNG)

#### 토큰 생성

> JWT (Json Web Token)토큰 라이브러리
>
> ```bash
> npm install jsonwebtoken --save
> ```



#### Auth 기능 만들기

> 사용자의 로그인 유무 확인
>
> 관리자 유저인지 등을 체크
>
> 글을 쓰거나 지울 때 권한 확인



#### 미들웨어

> 콜백 funciton하기 전에 중간에서 해주는 작업