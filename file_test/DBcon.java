import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
 
public class DBcon {
    String driver = "org.mariadb.jdbc.Driver";
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    
    public DBcon() {
         try {
            Class.forName(driver);
            con = DriverManager.getConnection(
                    "jdbc:mariadb://db.brique.kr:3306/employees",
                    "codingtest",
                    "12brique!@");
            
            if( con != null ) {
                System.out.println("DB 접속 성공");
            }
            
        } catch (ClassNotFoundException e) { 
            System.out.println("드라이버 로드 실패");
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws SQLException{
        DBcon dbcon = new DBcon();
        Statement stmt = dbcon.con.createStatement();
        StringBuilder sb = new StringBuilder();
        //쿼리문
        String sql = sb.append("SELECT e.emp_no, e.first_name, e.last_name, e.gender, e.hire_date, dept.dept_name ,t.title, s.salary AS max_salary \n")
        		.append("FROM employees e \n")
        		.append("INNER JOIN (SELECT c.emp_no, d.dept_name FROM dept_emp c INNER JOIN departments d ON c.dept_no = d.dept_no ORDER BY c.emp_no ASC) dept \n")
        		.append("ON e.emp_no = dept.emp_no \n")
        		.append("INNER JOIN titles t \n")
        		.append("ON dept.emp_no = t.emp_no \n")
        		.append("INNER JOIN (SELECT * FROM (SELECT emp_no, salary, ROW_NUMBER() OVER (PARTITION BY emp_no ORDER BY salary DESC) AS RankNo FROM salaries) rank WHERE RankNo = 1) s  \n")
        		.append("ON t.emp_no = s.emp_no \n")
        		.append("WHERE e.hire_date >= '20000101' \n")
        		.append("ORDER BY e.hire_date ASC")
        		.append(";").toString();
        //쿼리 적용
        ResultSet rs = stmt.executeQuery(sql);
        //메터 정보
        ResultSetMetaData metaInfo = rs.getMetaData();
        
        //컬럼 갯수
        int count = metaInfo.getColumnCount();
		
		//행 갯수 저장
		int row_count = 0;
		while (rs.next()) {
			row_count += 1;
		}
		
		//커서의 위치를 첫번째 전으로 이동
		rs.beforeFirst();
		
        // 컬럼명
        String header[] = new String[count];

     	for (int i = 0; i < count; i++) {
     		header[i] = metaInfo.getColumnName(i + 1);
     		header[count - 1] = "max_salary";
     	}
     	
     	//결과 값을 배열에 할당
     	int p = 0;
     	String[][] contents= new String [row_count][count];
     	
     	while (rs.next()) {
			// 레코드 출력
			for (int i = 0; i < count; i++) {
				contents[p][i] =  rs.getString(i + 1);
			}
			p += 1;
		}
     	rs.close();
		stmt.close();
        
		Dimension dim = new Dimension(800, 500);  //단순 2차원값 입력을 위한 클래스

        JFrame frame = new JFrame("wij complex");
        frame.setLocation(0, 0);				  //출력 위치를 화면 좌상단에 위치
        frame.setPreferredSize(dim);              //위 코드에서 지정한 좌표를 프레임 사이즈로 사용
        frame.setLayout(null);                    //레이아웃을 사용하지 않고 절대좌표계 사용
		
        JTable table = new JTable(contents, header);
        //table.setLocation(0,0);

        JScrollPane jscp1 = new JScrollPane(table); //이런식으로 생성시에 테이블을 넘겨주어야 정상적으로 볼 수 있다.
                                                    //jscp1.add(table); 과 같이 실행하면, 정상적으로 출력되지 않음.
        jscp1.setLocation(0,0);
        jscp1.setSize(700,500);

        frame.add(jscp1);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫기버튼 클릭시 프로그램 종료
    }
}