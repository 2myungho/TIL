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

```react
import React, { useEffect } from 'react';
import { Col, Row } from 'antd';
import { Data } from 'plotly.js';
import Plot from 'react-plotly.js';
import ImpTableSample from './ImpTableSample';
import style from './impSample.module.css';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from 'common/store';
import { getImportant } from 'common/dataSlice';

export default function ImpViewSample(): JSX.Element {
  const impData = useSelector((state: RootState) => state.data.impData);
  const dispatch = useDispatch();

  const impArr: { key: string; value: number }[] = [];

  for (let i = 0; i < Object.keys(impData).length; i += 1) {
    impArr[i] = {
      key: Object.keys(impData)[i],
      value: Object.values(impData)[i]
    };
  }

  impArr.sort(function (a, b) {
    return b.value - a.value;
  });

  const labels = impArr.map(data => data.key);
  const values = impArr.map(data => data.value);

  const data = {
    type: 'pie',
    values: values.slice(0, 5),
    labels: labels.slice(0, 5)
  };

  const layout = {
    legend: {
      orientation: 'h'
    },
    margin: {
      l: 30,
      r: 30,
      b: 30,
      t: 30
    }
  } as Partial<Plotly.Layout>;

  useEffect(() => {
    dispatch(getImportant(waferId));
  }, [dispatch, waferId]);
  
  return (
    <Row className={style.impRow}>
      <Col span={8} className={`${style.colWap} ${style.colChart}`}>
        <div className={style.impHeader}>Wafer_id</div>
        <Plot
          data={[data] as Data[]}
          layout={layout}
          config={{ displayModeBar: false, responsive: false }}
          className={style.impChart}
        />
      </Col>
      <Col span={16} className={style.colWap}>
        <ImpTableSample impArr={impArr} />
      </Col>
    </Row>
  );
}















import { createAsyncThunk, createSlice, PayloadAction } from '@reduxjs/toolkit';
import axios from 'axios';

export interface Data {
  wafer_id: string;
  output_dtts: number;
  product_id: string;
  output_name: string;
  y: number;
  yhat: number;
  important_features: { [key: string]: number };
}
export interface DataState {
  data: Data[];
  impData: { [key: string]: number };
}

const initialState: DataState = {
  data: [
    {
      wafer_id: '0',
      output_dtts: 1,
      product_id: 'H',
      output_name: 'T',
      y: 2,
      yhat: 0,
      important_features: {}
    }
  ],
  impData: {}
};

export const getAllData = createAsyncThunk('data/getAllData', async () => {
  const response = await axios.get('http://localhost:3010/qps/v1/data');
  return response.data;
});

export const getImportant = createAsyncThunk(
  'data/getImportant',
  async (wafer_id: any) => {
    const response = await axios.get(
      `http://localhost:3010/qps/v1/data/imp/?id=${wafer_id}`
    );
    return response.data;
  }
);

const data = createSlice({
  name: 'data',
  initialState,
  reducers: {},
  extraReducers: builder => {
    builder.addCase(getAllData.fulfilled, (state, { payload }) => {
      state.data = payload;
    });
    builder.addCase(getImportant.fulfilled, (state, { payload }) => {
      state.impData = payload;
    });
  }
});
export default data.reducer;

```

