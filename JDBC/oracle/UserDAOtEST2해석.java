package jdbc.user.test;


import java.util.List;
import java.util.Scanner;

import jdbc.user.dao.UserDAO;
import jdbc.user.dao.UserDAO2;
import jdbc.user.vo.UserVO;

public class UserDAOtEST2 {

	public static void main(String[] args) throws Exception {
		UserDAO2 dao = new UserDAO2();
		
		//등록할 입력 값을 UserVO 객체에 저장
		UserVO insertUser = new UserVO("myungho","이명호1", "남1", "안양1");
		
		int count = dao.updateUser(insertUser);
		System.out.println("갱신된 건 수 "  + count);
		
//		int count = dao.insertUser(insertUser);
//		System.out.println("이미 등록된 건 수 " + count);
		
		count = dao.deleteUser("myungho3");
		System.out.println("삭제 된 건수" + count);
		
		//dao.getUsers()모든 데이터를  list 변수에 저장
		//List<UserVO>는 줄줄이 사탕
		List<UserVO> list = dao.getUsers();
		for(UserVO userVO : list) {
			System.out.println(userVO);
		}
		
		
		
		
		
		
	}

}
