### Plotly.js + react-grid-layout

> `react-grid-layout`  사용시 `plotly.js` 차트 사이즈 맞추기



 #### 사용한 라이브러리

**react-resize-detector**

> `div`에 `ref`를 걸어서 div의 사이즈가 변경될 때를 인식하고 변경된 사이즈를 `width` `height`으로 사용할 수 있게 해주는 라이브러리
>
> ```react
> import { useResizeDetector } from 'react-resize-detector';
> 
> export default function ChartComponentTest1(): JSX.Element {
>   const { width, height, ref } = useResizeDetector({
>     // refreshMode: 'debounce',
>     // refreshRate: 100
>   });
> 	return(
>     	<div ref={ref} style={{height:'100%'}}>
>         	{width}
>             {height}
>         </div>
>     )
> ```



### 히스토그램 사용

```react
import { mockData } from 'data/data';
import React from 'react';
import Plot from 'react-plotly.js';
import { useResizeDetector } from 'react-resize-detector';
import WidgetHeader from '../WidgetHeader/WidgetHeader';
import TableX from './TableX';
import './DataViewerChart.scss';

export default function ChartX1(): JSX.Element {
  const { ref } = useResizeDetector({});

  const x: number[] = [];
  const y: number[] = [];
  for (let i = 0; i < mockData.length; i += 1) {
    x[i] = mockData[i].x1;
  }
  x.sort();

  const min = Number(x[0].toFixed(4));
  const max = Number(x[x.length - 1].toFixed(4));
  return (
    <div ref={ref} className="layoutWrap" data-testid="chartWidget">
      <WidgetHeader name="Bar Chart" />
      <Plot
        data={[
          {
            type: 'histogram',
            x,
            marker: {
              color: 'rgba(255, 100, 102, 0.7)',
              line: {
                color: 'rgba(255, 100, 102, 1)',
                width: 1
              }
            },
            opacity: 0.5,
            xbins: { 
              //히스토그램의 표 설정 (x축 마지막 데이터, 데이터 컨테이너, x축 시작 데이터)
              end: x[x.length - 1],
              size: x[x.length - 1] / 10,
              start: x[0]
            }
          }
        ]}
        layout={{
          showlegend: false, //범례 제거
          yaxis: {
            showticklabels: false, //y축 제거
            showgrid: false, //grid 선 제거
            zeroline: false // 하단 선 제거
          },
          autosize: true,
          margin: {
            l: 50,
            r: 40,
            b: 30,
            t: 20
          }
        }}
        config={{ scrollZoom: true, displayModeBar: false, responsive: true }}
        className="plotWrap"
      />
      <TableX min={min} max={max} />
    </div>
  );
}

```

