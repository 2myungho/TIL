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
			Class.forName("oracle.jdbc.OracleDriver"); // ����Ŭ ����̹��� ���ӵ��� �ʱ� ���� ���
			System.out.println("Driver Loading OK!!");
		}catch(ClassNotFoundException ex) {
			//���� �޼����� ���
			System.out.println(ex.getMessage());
			//�޼��� ȣ���ο� �����޽��� ���� ���
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
	
	//user ���
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
			
			//ResultSet�� seclect�� ���� �� executeQuery()�� ��� 
			//select�� ���� �������� �� executeUpdate()���
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
	
	//user ��ü ��ȸ
	public List<UserVO> getUsers(){
		List<UserVO> list = new ArrayList<>();
		String sql = "select * from users order by userid";
		UserVO user = null;
		
		try {
			Connection connection = getConnection();
			//sql�� ������ ���̽��� �������� ���� ��ü
			PreparedStatement stmt = connection.prepareStatement(sql);
		
			//sql�� ���� ����� rs�� ���� , select�� ���� �� ���
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				//Row �����͸� UserVO ��ü�� ���� - ������ �÷��� ���� UserVO ��ü�� ��� ������ ����
				user =  new UserVO(rs.getString("userid"),rs.getString("name"),rs.getString("gender"),rs.getString("city"));
				// �������� UserVO ��ü�� �÷��ǿ� ����
				list.add(user);
			}
			close(stmt,connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//getUserid()�� ������ �ٸ� ���� ������Ʈ
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
