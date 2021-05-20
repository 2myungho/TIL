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

