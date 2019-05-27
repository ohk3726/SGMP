package action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import view.Product;

public class Product_db {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String user = "sgmp";
	private static final String pass = "sgmp01";
	Product mList;
	
	public Product_db(){
		
	}
	
	public Product_db(Product mList){
		this.mList = mList;
	}
	
	public Connection getConn(){
		Connection conn = null;
		
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(URL,user,pass);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public Vector getMemberList(){
		Vector data = new Vector();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = getConn();
			String sql = "select * from product";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				String prod_id = rs.getString("prod_id");
				String prod_name = rs.getString("prod_name");
				String prod_cnt = rs.getString("prod_cnt");
				String prod_price = rs.getString("prod_price");
				String prod_pic = rs.getString("prod_pic");
				String prod_main_category = rs.getString("prod_main_category");
				String prod_sub_category = rs.getString("prod_sub_category");
				String prod_Ssub_category = rs.getString("prod_Ssub_category");
				String prod_ex = rs.getString("prod_ex");
				String prod_date = rs.getString("prod_date");
				
				Vector row = new Vector();
				row.add(prod_id);
				row.add(prod_name);
				row.add(prod_cnt);
				row.add(prod_price);
				row.add(prod_main_category);
				row.add(prod_sub_category);
				row.add(prod_Ssub_category);
				row.add(prod_ex);
				row.add(prod_date);
				row.add(prod_pic);
				
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	public Vector getMemberList(String prodid){
		Vector data = new Vector();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = getConn();
			String sql = "select * from product_wearing where prod_id = '"+prodid+"'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				String prod_wearing_date = rs.getString("prod_wearing_date");
				String prod_wearing_cnt = rs.getString("prod_wearing_cnt");
				String prod_wearing_condition = rs.getString("prod_wearing_condition");
				
				Vector row = new Vector();
				row.add(prod_wearing_date);
				row.add(prod_wearing_cnt);
				row.add(prod_wearing_condition);
				
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
}
