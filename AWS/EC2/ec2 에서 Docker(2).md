## Docker hub에  spring boot image 업로드

**Java 빌드 과정**

* windows의 패키지 관리자  **choco** 설치

* Gradle 설치
  * ```
    brew install gradle
    ```

* Jar 파일 실행

  * ```bash
    gradle bootJar #build/libs/ .jar 파일을 생성해줌
    java -jar build/libs/{jar 파일 이름}.jar
    ```

**Docker 이미지 로컬에서 업로드**

* Dockerfile 생성

  * ```dockerfile
    FROM adoptopenjdk/openjdk11:alpine-jre
    ARG JAR_FILE=target/*.jar
    COPY ${JAR_FILE} app.jar
    
    EXPOSE 9002
    ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"]
    ```

* Docker Image 생성

  * ```bash
    # Back-End
    # Account-service
    docker build --build-arg JAR_FILE=build/libs/*.jar -t {DockerHub ID}/account-service:v1 .
    # Group-service
    docker build --build-arg JAR_FILE=build/libs/*.jar -t {DockerHub ID}/group-service:v1 .
    # Todo-service
    docker build --build-arg JAR_FILE=build/libs/*.jar -t {DockerHub ID}/todo-service:v1 .
    # Auth-service
    docker build --build-arg JAR_FILE=build/libs/*.jar -t {DockerHub ID}/auth-service:v1 .
    # Point-service
    docker build --build-arg JAR_FILE=build/libs/*.jar -t {DockerHub ID}/point-service:v1 .
    ```

* Docker Push

  * ```
    {DockerHub ID}#docker hub 로그인 확인
    Docker login
    
    docker push {DockerHub ID}/planit-front:v1
    docker push {DockerHub ID}/account-service:v1
    docker push {DockerHub ID}/group-service:v1
    docker push {DockerHub ID}/todo-service:v1
    docker push {DockerHub ID}/auth-service:v1
    docker push {DockerHub ID}/point-service:v1
    ```

**Docker 이미지 EC2에서 다운로드**

* ```
  docker pull {DockerHub ID}/planit-front:v1
  docker pull {DockerHub ID}/account-service:v1
  docker pull {DockerHub ID}/group-service:v1
  docker pull {DockerHub ID}/todo-service:v1
  docker pull {DockerHub ID}/auth-service:v1
  docker pull {DockerHub ID}/point-service:v1
  ```



## Docker compose 사용

Docker compose는 여러 개의 컨테이어로 구성된 애플리케이션을 관리하기 위한 간단한 오케스트레이션 도구입니다.

Docker compose를 사용하면 컨테이너 실행에 필요한 옵션을 docker-compose.yml 파일에 적어둘 수 있습니다.

Docker compose 파일은 여러개의 도커 이미지를 이용해 여러 개의 컨테이너를 만드는 파일이다.



깃 설치

* ```
  sudo apt install git
  git --version
  
  git config --global user.name "이름"
  
  git config --global user.mail "메일"
  
  git config --global color.ui "auto"
  ```



docker compose 설치

* ```
  sudo curl -L "https://github.com/docker/compose/releases/download/1.24.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
  ```

권한 설정

* ```
  sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
  ```

심볼릭 링크 설정

* ```
  sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compos
  ```

버전 확인

* ```
  docker-compose -version 
  ```


Docker compose  실행

* ```
  docker-compose down --volume
  docker-compose up -d
  ```

Container와 image 생성 됨, compose 파일에 DB설정을 해두면 DB도 생성 됨





보안그룹, react 프록시 수정



```react
import React, { useEffect, useState } from 'react';
import Plot from 'react-plotly.js';
import { Data, PlotDatum, PlotMouseEvent } from 'plotly.js';
import { useDispatch, useSelector } from 'react-redux';
import { setImpLabel, setValuesIndex } from 'common/dataSlice';
import { RootState } from 'common/store';
import style from './ImpView.module.css';

interface ImportantData {
  impArr: { key: string; value: number }[];
  allDataCh: boolean;
}

export default function ImpChart({
  impArr,
  allDataCh
}: ImportantData): JSX.Element {
  const dispatch = useDispatch();
  const valuesIndex = useSelector((state: RootState) => state.data.valuesIndex);
  const [lackValue, setLackValue] = useState(0);
  const [remainingValues, setRemainingValues] = useState(0);

  const labels = impArr.map(data => data.key);
  const values = impArr.map(data => data.value);

  useEffect(() => {
    setLackValue(
      1 -
        values.reduce(function add(sum, currValue) {
          return sum + currValue;
        }, 0)
    );

    let viewValues = 0;
    for (let i = 0; i < values.length; i += 1) {
      if (viewValues > 70) {
        dispatch(setValuesIndex(i));
        break;
      }
      viewValues += values[i] * 100;
    }

    let remainingSum = 0;
    for (let i = valuesIndex; i < values.length; i += 1) {
      remainingSum += values[i] * 100;
    }
    setRemainingValues(remainingSum);
  }, [dispatch, values, valuesIndex]);

  const data = {
    type: 'pie',
    values: values
      .slice(0, valuesIndex)
      .concat(remainingValues / 100 + lackValue),
    labels: labels.slice(0, valuesIndex).concat('etc'),
    showlegend: false
  };
  const allData = {
    type: 'pie',
    values: values.concat(lackValue),
    labels: labels.concat('lack'),
    showlegend: false
  };

  const layout = {
    legend: {
      orientation: 'h'
    },
    margin: {
      l: 20,
      r: 20,
      b: 20,
      t: 20
    }
  } as Partial<Plotly.Layout>;

  interface PieDatum extends PlotDatum {
    label: string;
  }
  interface PieEvent extends PlotMouseEvent {
    points: PieDatum[];
  }
  const onGetImpLabel = (e: PieEvent) => {
    dispatch(setImpLabel(e.points[0].label));
  };

  return (
    <>
      {!allDataCh && (
        <Plot
          data={[data] as Data[]}
          layout={layout}
          config={{ displayModeBar: false, responsive: false }}
          className={style.impChart}
          onClick={onGetImpLabel as (event: Readonly<PlotMouseEvent>) => void}
        />
      )}
      {allDataCh && (
        <Plot
          data={[allData] as Data[]}
          layout={layout}
          config={{ displayModeBar: false, responsive: false }}
          className={style.impChart}
          onClick={onGetImpLabel as (event: Readonly<PlotMouseEvent>) => void}
        />
      )}
    </>
  );
}

```



```react
import React, { useState } from 'react';
import { Button, Col, Row } from 'antd';
import { useSelector } from 'react-redux';
import { RootState } from 'common/store';
import ImpTable from './ImpTable';
import ImpChart from './ImpChart';
import style from './ImpView.module.css';

export default function ImpView(): JSX.Element {
  const data = useSelector((state: RootState) => state.data.data);
  const selectedWaperIds = useSelector(
    (state: RootState) => state.qualityPredict.selectedWaperIds
  );
  const impData = data.find(d => d.wafer_id === '12J9183.01')
    ?.important_features as { [key: string]: number };

  const impArr: { key: string; value: number }[] = [];

  for (let i = 0; i < Object.keys(impData).length; i += 1) {
    impArr[i] = {
      key: Object.keys(impData)[i],
      value: Object.values(impData)[i]
    };
  }

  impArr.sort((a, b) => {
    return b.value - a.value;
  });

  const [allDataCh, setAllDataCh] = useState(false);
  const onAllData = () => {
    setAllDataCh(!allDataCh);
  };

  return (
    <Row className={style.impRow}>
      <Col span={8} className={`${style.colWap} ${style.colChart}`}>
        <div className={style.impHeader}>
          <p>{selectedWaperIds[1]}</p>
          <Button className={style.headerText} onClick={onAllData}>
            All Data
          </Button>
        </div>
        <ImpChart impArr={impArr} allDataCh={allDataCh} />
      </Col>
      <Col span={16} className={style.colWap}>
        <ImpTable impArr={impArr} />
      </Col>
    </Row>
  );
}

```

