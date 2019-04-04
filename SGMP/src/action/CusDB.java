package action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CusDB {
	public static final String URL ="jdbc:oracle:thin:@localhost:1521:xe";
	public static final String User = "sgmp";
	public static final String passwd = "sgmp01";
	public ArrayList getCusDB() throws Exception{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql_select = "select * from customer";
		ArrayList list = new ArrayList();
		try{
			conn = DriverManager.getConnection(URL, User,passwd);
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql_select);
			
			while(rs.next()){
				String test1 = rs.getString(1);
				String test2 = rs.getString(2);
				String test3 = rs.getString(3);
				String test4 = rs.getString(4);
				list.add("|");
				list.add(test1);
				list.add(test2);
				list.add(test3);
				list.add(test4);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{
				stmt.close();
				conn.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}
}
