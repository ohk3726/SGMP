package action;

import java.awt.List;
import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import oracle.jdbc.driver.OracleDriver;

public class Dbtest {
	public static final String URL ="jdbc:oracle:thin:@localhost:1521:xe";
	public static final String User = "sgmp";
	public static final String passwd = "sgmp01";
	public ArrayList getDbtest() throws Exception{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql_select = "select t10.sale_id, t20.prod_name, t10.sale_cnt, t20.prod_price, t30.cust_name, t30.cust_sale from sale t10, product t20, customer t30 where t10.prod_id = t20.prod_id and t10.cust_id = t30.cust_id";
		ArrayList list = new ArrayList();
		try{
			conn = DriverManager.getConnection(URL, User,passwd);
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql_select);
			
			while(rs.next()){
				String sale_id = rs.getString(1);
				String prod_name = rs.getString(2);
				String sale_cnt = rs.getString(3);
				String prod_price = rs.getString(4);
				String cust_name = rs.getString(5);
				String cust_sale = rs.getString(6);
				list.add(sale_id);
				list.add(prod_name);
				list.add(sale_cnt);
				list.add(prod_price);
				list.add(cust_name);
				list.add(cust_sale);
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
	public ArrayList getDbtest(String temp) throws Exception{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql_select = "select t10.sale_id, t20.prod_name, t10.sale_cnt, t20.prod_price, t30.cust_name, t30.cust_sale from sale t10, product t20, customer t30 where t10.prod_id = t20.prod_id and t10.cust_id = t30.cust_id and t30.cust_id = '"+temp+"'";
		ArrayList list = new ArrayList();
		try{
			conn = DriverManager.getConnection(URL, User,passwd);
			System.out.println("DB연결 성공");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql_select);
			
			while(rs.next()){
				String sale_id = rs.getString(1);
				String prod_name = rs.getString(2);
				String sale_cnt = rs.getString(3);
				String prod_price = rs.getString(4);
				String cust_name = rs.getString(5);
				String cust_sale = rs.getString(6);
				list.add(sale_id);
				list.add(prod_name);
				list.add(sale_cnt);
				list.add(prod_price);
				list.add(cust_name);
				list.add(cust_sale);
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
	public ArrayList getDbtest2(String temp) throws Exception{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql_select = "select * from test where test1 = "+temp;
		ArrayList list = new ArrayList();
		try{
			conn = DriverManager.getConnection(URL, User,passwd);
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql_select);
			
			while(rs.next()){
				String test2 = rs.getString(2);
				String test3 = rs.getString(3);
				String test4 = rs.getString(4);
				String test6 = rs.getString(6);
				list.add("|");
				list.add(test2);
				list.add(test3);
				list.add(test4);
				list.add(test6);
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
