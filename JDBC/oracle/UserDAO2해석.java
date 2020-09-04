package jdbc.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jdbc.user.vo.UserVO;

public class UserDAO2 {
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String user = "scott";
	String password = "tiger";
	
	public UserDAO2() {
		try {
			Class.forName("oracle.jdbc.OracleDriver"); // 오라클 드라이버에 종속되지 않기 위해 사용
			System.out.println("Driver Loading OK!!");
		}catch(ClassNotFoundException ex) {
			//오류 메세지를 출력
			System.out.println(ex.getMessage());
			//메서드 호출경로와 오류메시지 같이 출력
			ex.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	public void close(Statement stmt,Connection con)throws SQLException{
		if(stmt != null) stmt.close();
		if(con != null) con.close();
	}
	
	//user 등록
	public int insertUser(UserVO user) throws SQLException {
		String sql = "insert into users values(?,?,?,?) ";
		Connection connection = null;
		int count = 0;
		try {
			connection = getConnection();
			//AutoCommit Mode off
			connection.setAutoCommit(false);
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1,user.getUserid());
			stmt.setString(2,user.getName());
			stmt.setString(3,user.getGender());
			stmt.setString(4,user.getCity());
			
			//ResultSet은 seclect가 있을 때 executeQuery()와 사용 
			//select가 없는 쿼리문일 때 executeUpdate()사용
			count = stmt.executeUpdate();
			
			if(count == 1) {
				connection.commit();
			}else {
				connection.rollback();
			}
			close(stmt,connection);
		} catch (SQLException e) {
			try {
				connection.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return count ;
	}
	
	//user 전체 조회
	public List<UserVO> getUsers(){
		List<UserVO> list = new ArrayList<>();
		String sql = "select * from users order by userid";
		UserVO user = null;
		
		try {
			Connection connection = getConnection();
			//sql을 데이터 베이스로 내보내기 위한 객체
			PreparedStatement stmt = connection.prepareStatement(sql);
		
			//sql의 질의 결과를 rs에 저장 , select가 있을 때 사용
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				//Row 데이터를 UserVO 객체에 저장 - 각각의 컬럼의 값이 UserVO 객체의 멤버 변수에 저장
				user =  new UserVO(rs.getString("userid"),rs.getString("name"),rs.getString("gender"),rs.getString("city"));
				// 여러개의 UserVO 객체를 컬렉션에 저장
				list.add(user);
			}
			close(stmt,connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//getUserid()가 맞으면 다른 내용 업데이트
	public int updateUser(UserVO user) {
		String sql = "update users set name = ?, gender = ?, city = ? where userid = ?";
		Connection connection = null;
		int updCnt = 0;
		
		try {
			connection = getConnection();
			//AutoCommit Mode off
			connection.setAutoCommit(false);
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getGender());
			stmt.setString(3, user.getCity());
			stmt.setString(4, user.getUserid());
			
			updCnt = stmt.executeUpdate();
			
			if(updCnt == 1) {
				connection.commit();
			}else {
				connection.rollback();
			}
			
			close(stmt,connection);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return updCnt;
	}
	
	public int deleteUser(String userid) {
		String sql = "DELETE FROM users WHERE userid = ? ";
		
		int delCnt = 0;
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1,userid);
		
			delCnt = stmt.executeUpdate();
			
			close(stmt,connection);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return delCnt;
	}
	

}
