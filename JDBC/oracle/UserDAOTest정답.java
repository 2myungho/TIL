package jdbc.user.test;

import java.util.List;

import jdbc.user.dao.UserDAO;
import jdbc.user.vo.UserVO;

public class UserDAOTest {
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		
		UserVO user = dao.getUser("dooly");
		System.out.println(user);

		//등록할 입력 값을 UserVO 객체에 저장
		UserVO insUser = new UserVO("spring", "스프링부트", "남", "경기");
		
//		int cnt = dao.insertUser(insUser);
//		System.out.println("등록된 건수 " + cnt);

		int cnt = dao.updateUser(insUser);
		System.out.println("갱신된 건수 " + cnt);
		
		cnt = dao.deleteUser("spring");
		System.out.println("삭제된 건수 " + cnt);
		
		List<UserVO> list = dao.getUsers();
		for (UserVO userVO : list) {
			System.out.println(userVO);
		}
		
	}
}
