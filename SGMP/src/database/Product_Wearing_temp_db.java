package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.Product_Wearing_temp_model;
import model.Shopping_basket_model;

public class Product_Wearing_temp_db {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
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
	public void tempInsert(Product_Wearing_temp_model model){
		try{
			conn = getConn();
			sql = "MERGE INTO product_wearing_temp USING DUAL ON (prod_id = ? and prod_wearing_company_id = ?) "
					+ "when matched then update set prod_wearing_cnt = prod_wearing_cnt + 1 "
					+ "when not matched then insert(prod_id, prod_wearing_cnt, prod_wearing_condition,prod_wearing_company_id) values(?,1,'상품준비중',?)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, model.getProd_id());
			ps.setString(2, model.getProd_wearing_company_id());
			ps.setString(3, model.getProd_id());
			ps.setString(4, model.getProd_wearing_company_id());
			ps.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void PWT_update(Product_Wearing_temp_model model){
		try{
			conn = getConn();
			sql = "update product_wearing_temp set prod_wearing_cnt = ? where prod_id = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, model.getProd_wearing_cnt());
			ps.setString(2, model.getProd_id());
			ps.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Vector PWT_select(Product_Wearing_temp_model model){
		try{
			conn = getConn();
			sql = "select t10.prod_id, t20.prod_name, t10.prod_wearing_cnt, to_char(t20.prod_price,'FM999,999,999,999') as prod_price from product_wearing_temp t10, product t20 where t10.prod_id = t20.prod_id and t10.prod_wearing_company_id = ? and t20.company_id = ? order by prod_id";
			ps = conn.prepareStatement(sql);
			ps.setString(1, model.getProd_wearing_company_id());
			ps.setString(2, model.getProd_wearing_company_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				String prod_id = rs.getString("prod_id");
				String prod_name = rs.getString("prod_name");
				String prod_wearing_cnt = rs.getString("prod_wearing_cnt");
				String prod_price = rs.getString("prod_price");
				
				Vector row = new Vector();
				
				row.add(prod_id);
				row.add(prod_name);
				row.add(prod_wearing_cnt);
				row.add(prod_price);
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	public String PWT_price(Product_Wearing_temp_model model){
		String price = "";
		try{
			conn = getConn();
			sql = "select to_char(sum(t10.prod_wearing_cnt * t20.prod_price),'FM999,999,999,999') as price from product_wearing_temp t10, product t20 where t10.prod_id = t20.prod_id and t10.prod_wearing_company_id = ? and t20.company_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, model.getProd_wearing_company_id());
			ps.setString(2, model.getProd_wearing_company_id());
			rs = ps.executeQuery();
			
			while(rs.next()){
				price = rs.getString("price");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return price;
	}
	public void PWT_delete(String com_code){
		try{
			conn = getConn();
			String sql = "delete from product_wearing_temp where prod_wearing_company_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, com_code);
			int temp = ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void PWT_TO_PRODUCT_INSERT(Product_Wearing_temp_model model,String date, String com_code){
		try{
			conn = getConn();
			String sql = "insert into product_wearing "				
			+"values(?,?,?,'상품준비중',?,(select max(to_number(prod_wearing_id))+1 from product_wearing),'0','ROOT')";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, model.getProd_id());
			ps.setString(2, date);
			ps.setString(3, model.getProd_wearing_cnt());
			ps.setString(4, com_code);
			int commit = ps.executeUpdate();
			
			if(commit != 0){
				PWT_delete(com_code);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
