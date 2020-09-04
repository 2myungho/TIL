package mymongo.myjava;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class DocumentCRUD {

	public static void main(String[] args) {
		//interface 말고 class import
		MongoClient mongoClient = new MongoClient("localhost",27017);
		System.out.println(mongoClient);
		MongoDatabase database = mongoClient.getDatabase("java_db");
		System.out.println(database);
		
		//createCollection는 컬렉션(테이블)만들어주는 메소드
//		database.createCollection("java_col");
		//getCollection은 컬렉션이 없으면 만들어주고 있으면 가져와 줌, 내용을 추가해주어야 함
		MongoCollection<Document> collection = database.getCollection("java_col");
		System.out.println(collection);
		
		//프라이빗 키인 _id를 자동 해주기 때문에 같은 데이터를 들어가지 않게 설정해주어야 한다.
		Document user1 = new Document("_id",1)
			.append("userid","mongo")
			.append("name","몽고")
			.append("gender","여")
			.append("addr", "서울");
			System.out.println(user1);
		
		Document user2 = new Document("_id",2)
			.append("userid","java")
			.append("name","자바")
			.append("gender","여")
			.append("addr", "경기");
			
		Document user3 = new Document("_id",3)
			.append("userid","javascript")
			.append("name","자바스크립트")
			.append("gender","나")
			.append("addr", "부산");
		
		List<Document> userList = new ArrayList<>();
		userList.add(user2);
		userList.add(user3);
		

		try {
			//insert
			collection.insertOne(user1);
			collection.insertMany(userList);
		}catch(MongoException ex) {
			System.out.println(ex.getCode()+ " " + ex.getMessage());
//			if(ex.getCode() == 11000) {
//				System.out.println("중복된 user 유저입니다!");
//			}
		}
		List<Document> resultList = new ArrayList<>();
		Document query = new Document("userid","java");
		//find()결과를 ArrayList에 저장해주는 경우 
		//into : 결과를 뿌려줌(결과 뿌려줄 리스트를 넣어줌)
		//find에 값을 넣어서 필터링 가능
		collection.find(query).into(resultList); //결과를 가지고 있는 구문
		for (Document document : resultList) {
			System.out.println(document);
			System.out.println(document.getString("userid") + " " + document.getString("name"));
		}
		
		//Update 구문
		//updateOne() 사용
		collection.updateOne(Filters.eq("userid","mongo"),Updates.set("addr", "서울 강동구") );
		
		//deleteOne 사용
		Document query2 = new Document("addr","부산");
		collection.deleteOne(query2);
		
		// find() 결과를 FindIterable에 저장해 주는 경우 Filters.eq("gender","여")
		FindIterable<Document> iterable = collection.find();
		
		
		//FindIterable을 Iterator로 변환
		Iterator<Document> iter = iterable.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
	}

}
