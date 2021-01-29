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
                System.out.println("DB ���� ����");
            }
            
        } catch (ClassNotFoundException e) { 
            System.out.println("����̹� �ε� ����");
        } catch (SQLException e) {
            System.out.println("DB ���� ����");
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws SQLException{
        DBcon dbcon = new DBcon();
        Statement stmt = dbcon.con.createStatement();
        StringBuilder sb = new StringBuilder();
        //������
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
        //���� ����
        ResultSet rs = stmt.executeQuery(sql);
        //���� ����
        ResultSetMetaData metaInfo = rs.getMetaData();
        
        //�÷� ����
        int count = metaInfo.getColumnCount();
		
		//�� ���� ����
		int row_count = 0;
		while (rs.next()) {
			row_count += 1;
		}
		
		//Ŀ���� ��ġ�� ù��° ������ �̵�
		rs.beforeFirst();
		
        // �÷���
        String header[] = new String[count];

     	for (int i = 0; i < count; i++) {
     		header[i] = metaInfo.getColumnName(i + 1);
     		header[count - 1] = "max_salary";
     	}
     	
     	//��� ���� �迭�� �Ҵ�
     	int p = 0;
     	String[][] contents= new String [row_count][count];
     	
     	while (rs.next()) {
			// ���ڵ� ���
			for (int i = 0; i < count; i++) {
				contents[p][i] =  rs.getString(i + 1);
			}
			p += 1;
		}
     	rs.close();
		stmt.close();
        
		Dimension dim = new Dimension(800, 500);  //�ܼ� 2������ �Է��� ���� Ŭ����

        JFrame frame = new JFrame("wij complex");
        frame.setLocation(0, 0);				  //��� ��ġ�� ȭ�� �»�ܿ� ��ġ
        frame.setPreferredSize(dim);              //�� �ڵ忡�� ������ ��ǥ�� ������ ������� ���
        frame.setLayout(null);                    //���̾ƿ��� ������� �ʰ� ������ǥ�� ���
		
        JTable table = new JTable(contents, header);
        //table.setLocation(0,0);

        JScrollPane jscp1 = new JScrollPane(table); //�̷������� �����ÿ� ���̺��� �Ѱ��־�� ���������� �� �� �ִ�.
                                                    //jscp1.add(table); �� ���� �����ϸ�, ���������� ��µ��� ����.
        jscp1.setLocation(0,0);
        jscp1.setSize(700,500);

        frame.add(jscp1);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�ݱ��ư Ŭ���� ���α׷� ����
    }
}