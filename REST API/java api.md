## 랜덤 데이터 java로 받아오기

#### URL 클래스

> 문자열 spec이 지정하는 자원에 대한 **URL 객체 생성**

#### 램덤  api 정보

> https://random-data-api.com/documentation



```java
public class BRIQUE_5 {

	public static void main(String[] args) throws IOException {
		//URL 객체 생성
		URL url = new URL("https://random-data-api.com/api/address/random_address");
		//해당 URL에서 페이지 정보를 가져오기 위해 HttpURLConnection사용
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		
		while((inputLine = bf.readLine()) != null) {
			content.append(inputLine);
		}
		bf.close();
		//HttpURLConnection의 연결을 끊을 때 
		//close는 연결을 종료시킨 후, 다시 연결하려면 openConnection()을 다시 해줘야한다.즉 처음부터 다시 연결한다.
		//disconnect()는 connect()메소드만 호출하면 바로 다시 복구된다.
		con.disconnect();
		System.out.println(content.toString());
	}
    
    //콘솔
    //{"id":5873,"uid":"3379b6bb-1623-4599-a3ff-80575fbb30f2","city":"Nitzscheport" ....출력된다.
```

