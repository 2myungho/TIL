package jdbc.user.test;

import java.util.List;

import jdbc.user.dao.UserDAO;
import jdbc.user.vo.UserVO;

public class UserDAOTest {
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		
		UserVO user = dao.getUser("dooly");
		System.out.println(user);

		//����� �Է� ���� UserVO ��ü�� ����
		UserVO insUser = new UserVO("spring", "��������Ʈ", "��", "���");
		
//		int cnt = dao.insertUser(insUser);
//		System.out.println("��ϵ� �Ǽ� " + cnt);

		int cnt = dao.updateUser(insUser);
		System.out.println("���ŵ� �Ǽ� " + cnt);
		
		cnt = dao.deleteUser("spring");
		System.out.println("������ �Ǽ� " + cnt);
		
		List<UserVO> list = dao.getUsers();
		for (UserVO userVO : list) {
			System.out.println(userVO);
		}
		
	}
}
