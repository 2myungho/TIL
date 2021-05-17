> 자바스크립트를 지원하는 차트 라이브러리

### plotly.js

* 자바 스크립트 기반의 데이터 시각화 라이브러리
* 인터랙티브 그래프 생성
  * 사용자의 입력을 받을 수 있는 그래프
* 웹 시각화 라이브러리인 **d3.js 를 이용하여 보다 interactive 하게 그래프를 만들어줍니다.**
* [plotly.js](https://plotly.com/javascript/)는  [d3.js](https://d3js.org/) 및 [stack.gl](https://github.com/stackgl) 위에 구축 된 JavaScript 오픈 소스 그래프 라이브러리 입니다.
* d3.js 를 기반으로 만들어져 보다 쉽고 인터렉티브라게 그래프를 만들 수 있다.
* point : 2 개, 데이터 수 : 1000000 , marker: x,  1초 이내



장점

* 차트 화면 스크롤 여부
* 마우스로 영역 표시 가능
* 차트 만들기 코드가 간단함
* 다양한 옵션 기본 제공
* 차트 종류가 다양함

단점

- react 공식문서가 잘 되어 있지 않음
- marker를 사용하면 일정 데이터 이상에서 느려진다. (plotly 뿐만의 단점이 아닌 모든 차트 라이브러리의 단점)
- 다른 차트에 비해 기본 애니매이션 효과가 적용되어 있지 않다.



d3.js (Data-Driven Documents )

* 데이터를 기반으로 문서를 조작하기 위한 자바스크립트의 라이브러리
* HTML, SVG, CSS를 사용하여 사용
* 로우 레벨 작업
* DOM 조작에 대한 데이터 기반 접근 방식을 결합하여 특정 프레임워크에 묶이지 않고도 최식 브라우저의 모든 기능을 제공
  * 데이터를 기반으로 DOM의 엘리먼트들을 조작(생성/수정/삭제)하기 위한 라이브러리
* 
* SVG (Scalable Vector Graphics)를 지원하기 때문에, 확대/축소시에도 화면이 깨지지 않는다.



### Chart.js

point : 2 개, 데이터 수 : 1000000 ,marker: x, 1분 이상

장점 :

* 대중적이여서 정보가 많음
* 국내 개발자들이 한국어 번역을 해둠

단점 : 

* 자잘한 옵션들이 상당히 많았던 느낌이 든다
* 차트 사이즈 변경하기가 번거롭다
  * 자동으로 영역내 전체 사이즈를 defalut로 가지기 때문에 상황에 따라서 좋을 수도 안 좋을 수도 있다
* 데이터 수가 많아지면 다른 차트에 비해 많이 느리다
  * default로 애니메이션 효과가 적용되어 느린 것 같다.
* marker 인식도 굉장히 느렸다.



### Highchart

* 상업적인 용도로 사용하려면 비용 지불



### CanvasJs 

point : 2 개, 데이터 수 : 1000000 ,marker: x, 20초 이내

장점 : 

- 공식 문서에 react 용 예제 코드가 잘 되어 있음
- 공식문서에서 실습 예제가 잘 되어 있음
- default로 클릭 같은 애니매이션이 잘 되어 있는 것 같음
  - 하지만 이런 애니매이션 기능 때문에 많은 데이터를 다룰 땐 성능이 느려질 수도 있다. 
- 50만, 100만의 숫자 데이터를 입력했을 때 chart.js 보다는 빠르다
- marker 인식이 비교한 차트 중에 가장 빠르다 (부드럽게 인식 됨)

단점 : 

* 공식문서에서 파일을 다운 받아 사용해야한다.
  * react에서 npm 설치가 없나보다..
* 상용으로 사용시 유료





# React에서 Plot 시작

#### install

```bash
npm install react-plotly.js plotly.js
npm install --save-dev @types/react-plotly.js
```



#### 기본 bar 차트 만들기

```react
import React from "react";
import Plot from "react-plotly.js";

export default function BasicBar() {
  return (
    <Plot
        data={[
          {
            x: [1, 2, 3],
            y: [2, 6, 3],
            type: 'scatter',
            mode: 'lines+markers',
            marker: {color: 'red'},
          },
          {type: 'bar', x: [1, 2, 3], y: [2, 5, 3]},
        ]}
        layout={ {width: 320, height: 240, title: 'A Fancy Plot'} }
      />
  );
}
```

![react plotly js](https://user-images.githubusercontent.com/52882578/118221463-98794a80-b4b8-11eb-8864-4c865288bf1d.PNG)



#### 차트 이벤트

```react
export default function BasicBar() {
    const onMarkerClick = (e: any) => {
    	console.log(e.points[0].data.marker); //chart에 marker color 배열  
    	console.log(e.points[0]["marker.color"]); //클릭한 marker 색상
    	console.log(e); //이벤트 객체
  	};
    return(...)
}
```

* `e.points` 차트의 포인트마다 정보를 추출할 수 있다. 
  * 예를 들어 useState를 사용해서 클릭한 marker의 color를 바꿀 수 있다.

![react plotly js2](https://user-images.githubusercontent.com/52882578/118222349-6ff25000-b4ba-11eb-8fcc-d1138609cdcf.PNG)

#### `Plot`의 자주 쓰이는 기본 속성

`data`:  

- 차트로 그려질 데이터 배열을 받는다.
- 같은 `x`, `y`, `type`  등여러 차트를 동시에 그릴 수도 있다.
- marker을 사용할 수 있다.

`layout` : 

* 차트의 레이아웃 파라미터들(`width`, `height`. 및 `title`.)을 객체로 받는다.   

* 전체 목록은 [여기에서](https://plotly.com/javascript/reference/) 확인할 수 있습니다 .

* 사용해보니 '%' 는 적용되지 않았다.

* ```react
  layout={{width: 500, height: 500, title: "Bar" }}
  ```

`config` :

* config를 사용하여 차트가 작동하는 방식을 구성할 수 있다.

* 확대 / 축소, 버튼, 모드 바 및 기타 도구와 같은 매개 변수는이 [config를](https://plotly.com/javascript/configuration-options/) 사용하여 수정할 수 있다.

* 예 ) 

  * `scrollZoom` : 마우스 스크롤시 차트 확대 축소,

  * `showLink: true` , `plotlyServerURL : "https://chart-studio.plotly.com"` : 차트 에디터 사이트로 이동 

  * `responsive` : 차트 반응형 기능

  * ```react
    config={{
            scrollZoom: true,
            showLink: true,
            plotlyServerURL: "https://chart-studio.plotly.com",
            responsive: true,
          }}
    ```

