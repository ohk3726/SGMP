package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import model.Customer_model;

public class Customer_db {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String URL ="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String User = "sgmp";
	private static final String passwd = "sgmp01";
	private Vector data = new Vector();
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql = "";
	public Connection getConn(){
		Connection conn = null;
		
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(URL,User,passwd);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public String login(String user_id, String user_pw){
		String login="";
		try{
			conn = getConn();
			sql = "select count(*) as login from user_table where user_id = ? and user_pw = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.setString(2, user_pw);
			rs = ps.executeQuery();
			while(rs.next()){				
				login = rs.getString("login");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return login;
	}
	
	public Vector cust_select(){
		try{
			conn = getConn();
			sql = "select cust_id, cust_name, cust_address from customer";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				String cust_id = rs.getString("cust_id");
				String cust_name = rs.getString("cust_name");
				String cust_address = rs.getString("cust_address");
				Vector row = new Vector();
				
				row.add(cust_id);
				row.add(cust_name);
				row.add(cust_address);
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	public String cust_id(String cus){
		String cust_id = "";
		try{
			conn = getConn();
			sql = "select cust_id from customer where cust_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, cus);
			rs = ps.executeQuery();
			
			while(rs.next()){
				cust_id = rs.getString("cust_id");
				Vector row = new Vector();
				
				row.add(cust_id);
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return cust_id;
	}
	
	public Vector cust_select(String data1, String group){
		try{
			conn = getConn();
			if(group.equals("전체")){
				sql = "select cust_id, cust_name, cust_address from customer";
			}
			else if(group.equals("이름")){
				sql = "select cust_id, cust_name, cust_address from customer where cust_name = ?";
			}
			else{
				sql = "select cust_id, cust_name, cust_address from customer where cust_id = ?";
			}
			ps = conn.prepareStatement(sql);
			if(!group.equals("전체")){
				ps.setString(1, data1);
			}
			rs = ps.executeQuery();
			
			while(rs.next()){
				String cust_id = rs.getString("cust_id");
				String cust_name = rs.getString("cust_name");
				String cust_address = rs.getString("cust_address");
				
				
				Vector row = new Vector();
				
				row.add(cust_id);
				row.add(cust_name);
				row.add(cust_address);
				
				
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	public void customer_insert_or_update(Customer_model cust_model){
		try{
			conn = getConn();
			sql = "MERGE INTO CUSTOMER USING DUAL ON (cust_id = ?) "
					+ "when matched then update set cust_name = ?, cust_address = ? "
					+ "when not matched then insert(cust_id, cust_name, cust_address,cust_date) values(?,?,?,sysdate)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, cust_model.getCust_id());
			ps.setString(2, cust_model.getCust_name());
			ps.setString(3, cust_model.getCust_address());
			ps.setString(4, cust_model.getCust_id());
			ps.setString(5, cust_model.getCust_name());
			ps.setString(6, cust_model.getCust_address());
			ps.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
