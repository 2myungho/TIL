## Mobx 환경설정

### 1. 리액트 프로젝트 생성

```bash
yarn create react-app mobx-project
```

### 2. Mobx 라이브러리 설치

```bash
yarn add mobx-react mobx
```

### 3. 프로젝트를 eject 시켜서 어노테이션이 적용될 수 있게 한다.

```bash
yarn eject
```

* Are you sure you want to eject? This action is peemanent. Yes  (한 번 eject을 하면 이전 상태로 돌아갈 수 없습니다.)

* This git repository has untracked files or uncommitted changes:  

* 이런 에러가 난다면

* ```bash
  git init
  git add .
  git commit -m "first commit"
  yarn eject
  ```

### 4. package.json에 바벨 플러그인 추가 

```json
 "babel": {
    "presets": [
      "react-app"
    ],
    "plugins": [
      ["@babel/plugin-proposal-decorators", { "legacy": true}],
      ["@babel/plugin-proposal-class-properties", { "loose": true}]
  ]
```

### 5. index.js 수정

```react
import { Provider } from 'mobx-react';
import QuizStore from "./Store/QuizStore";
/*추가*/

ReactDOM.render(
  <Provider QuizStore={QuizStore}>
    <BrowserRouter> /*router라이브러리*/
      <App />
    </BrowserRouter>
  </Provider>,
  document.getElementById('root')
);
```

### 6. container import 설정

```react
import React, { Component } from 'react';
import {inject,observer} from "mobx-react"

@inject("TestStore")
@observer
class MainContainer extends Component {
    render() {
        const books = this.props.TestStore.books;
        console.log(books)
        return (
            <div>
            </div>
        );
    }
}
export default MainContainer;
```

### 7. Store import 설정

```react
import { observable, computed, action } from "mobx";
```