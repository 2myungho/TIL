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
		//interface ���� class import
		MongoClient mongoClient = new MongoClient("localhost",27017);
		System.out.println(mongoClient);
		MongoDatabase database = mongoClient.getDatabase("java_db");
		System.out.println(database);
		
		//createCollection�� �÷���(���̺�)������ִ� �޼ҵ�
//		database.createCollection("java_col");
		//getCollection�� �÷����� ������ ������ְ� ������ ������ ��, ������ �߰����־�� ��
		MongoCollection<Document> collection = database.getCollection("java_col");
		System.out.println(collection);
		
		//�����̺� Ű�� _id�� �ڵ� ���ֱ� ������ ���� �����͸� ���� �ʰ� �������־�� �Ѵ�.
		Document user1 = new Document("_id",1)
			.append("userid","mongo")
			.append("name","����")
			.append("gender","��")
			.append("addr", "����");
			System.out.println(user1);
		
		Document user2 = new Document("_id",2)
			.append("userid","java")
			.append("name","�ڹ�")
			.append("gender","��")
			.append("addr", "���");
			
		Document user3 = new Document("_id",3)
			.append("userid","javascript")
			.append("name","�ڹٽ�ũ��Ʈ")
			.append("gender","��")
			.append("addr", "�λ�");
		
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
//				System.out.println("�ߺ��� user �����Դϴ�!");
//			}
		}
		List<Document> resultList = new ArrayList<>();
		Document query = new Document("userid","java");
		//find()����� ArrayList�� �������ִ� ��� 
		//into : ����� �ѷ���(��� �ѷ��� ����Ʈ�� �־���)
		//find�� ���� �־ ���͸� ����
		collection.find(query).into(resultList); //����� ������ �ִ� ����
		for (Document document : resultList) {
			System.out.println(document);
			System.out.println(document.getString("userid") + " " + document.getString("name"));
		}
		
		//Update ����
		//updateOne() ���
		collection.updateOne(Filters.eq("userid","mongo"),Updates.set("addr", "���� ������") );
		
		//deleteOne ���
		Document query2 = new Document("addr","�λ�");
		collection.deleteOne(query2);
		
		// find() ����� FindIterable�� ������ �ִ� ��� Filters.eq("gender","��")
		FindIterable<Document> iterable = collection.find();
		
		
		//FindIterable�� Iterator�� ��ȯ
		Iterator<Document> iter = iterable.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
	}

}
