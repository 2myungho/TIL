package jdbc.user.test;


import java.util.List;
import java.util.Scanner;

import jdbc.user.dao.UserDAO;
import jdbc.user.dao.UserDAO2;
import jdbc.user.vo.UserVO;

public class UserDAOtEST2 {

	public static void main(String[] args) throws Exception {
		UserDAO2 dao = new UserDAO2();
		
		//����� �Է� ���� UserVO ��ü�� ����
		UserVO insertUser = new UserVO("myungho","�̸�ȣ1", "��1", "�Ⱦ�1");
		
		int count = dao.updateUser(insertUser);
		System.out.println("���ŵ� �� �� "  + count);
		
//		int count = dao.insertUser(insertUser);
//		System.out.println("�̹� ��ϵ� �� �� " + count);
		
		count = dao.deleteUser("myungho3");
		System.out.println("���� �� �Ǽ�" + count);
		
		//dao.getUsers()��� �����͸�  list ������ ����
		//List<UserVO>�� ������ ����
		List<UserVO> list = dao.getUsers();
		for(UserVO userVO : list) {
			System.out.println(userVO);
		}
		
		
		
		
		
		
	}

}
