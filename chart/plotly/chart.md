> 자바스크립트를 지원하는 차트 라이브러리



d3.js (Data-Driven Documents )

* 데이터를 기반으로 문서를 조작하기 위한 자바스크립트의 라이브러리
* HTML, SVG, CSS를 사용하여 사용
* 로우 레벨 작업
* DOM 조작에 대한 데이터 기반 접근 방식을 결합하여 특정 프레임워크에 묶이지 않고도 최식 브라우저의 모든 기능을 제공
  * 데이터를 기반으로 DOM의 엘리먼트들을 조작(생성/수정/삭제)하기 위한 라이브러리
* 
* SVG (Scalable Vector Graphics)를 지원하기 때문에, 확대/축소시에도 화면이 깨지지 않는다.



### **Plotly.js** 

* 자바 스크립트 기반의 데이터 시각화 라이브러리
* 인터랙티브 그래프 생성
  * 사용자의 입력을 받을 수 있는 그래프
* 웹 시각화 라이브러리인 **d3.js 를 이용하여 보다 interactive 하게 그래프를 만들어줍니다.**
* [plotly.js](https://plotly.com/javascript/)는  [d3.js](https://d3js.org/) 및 [stack.gl](https://github.com/stackgl) 위에 구축 된 JavaScript 오픈 소스 그래프 라이브러리 입니다.
* d3.js 를 기반으로 만들어져 보다 쉽고 인터렉티브라게 그래프를 만들 수 있다.



장점 : 

1. 차트 화면 스크롤, 마우스 영역 지정 등 다양한 기능이 기본적으로 제공.
   1. 그 외에 chart edit 페이지 연결 등 다양한 기능 사용 가능
2. 그래픽적인 디자인이 많이 들어가지 않아 다른 차트에 비해 속도 성능이 뛰어남.
3. 차트의 종류가 다양함.

단점 : 

1. 다른 차트에 비해 디자인적인 요소가 부족함.
2. react 공식문서가 친절하지 않음

### Chart.js

장점 : 

1. javascript chart 중 가장 정보가 많음.
2. 국내 개발자들이 공식문서를 한국어 번역해두었음. 
3. 디자인적인 요소가 훌륭함.

단점 : 

1. 데이터 수가 많아지면 다른 차트에 비해 상당히 느려짐.
2. 데이터 수가 많아지면 marker의 인식 속도도 느려짐
   1. 디자인 적인 요소가 많이 들어가서 다른 차트에 비해 많이 느림.

### CanvasJs

장점 : 

1. 공식 문서에 React용 예제 코드가 가장 잘 되어 있음.
2. 공식 문서에서 예제 실습이 간단함
3. 기본적인 디자인 애니메이션이 잘 되어 있음
4. 디자인적인 요소가 많은 것에 비해 속도가 Chart.js 보다 빠름
   1.  데이터의 수가 많아져도 marker의 인식 속도가 빠름

단점 : 

1. 공식문서에서 react용 canvasjs.react,js 파일을 다운 받아 사용해야함.
   1. react용 npm이 없는 것 같다.
2. 상용으로 사용시 유료
   1. HighChart 처럼 조건부 무료 차트는 예제를 만들어보지 않으려고 했는데 문서 정리가 끝나고 뒤늦게 알아차렸다..

### Highchart

* 상업적인 용도로 사용하려면 비용 지불



### 속도 비교

1. data 종류 : 2개
2. data 수 : 100만개
3. marker : x
4. 속도(화면 출력) :

| **Plotly.js** | **Chart.js** | **Canvas.js** |
| :------------ | :----------- | :------------ |
| 2초 이내      | 60초 이상    | 20초 이내     |





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

