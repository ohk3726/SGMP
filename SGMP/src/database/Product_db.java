package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JComboBox;

import model.Product_model;

public class Product_db {
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
	
	public Vector prod_select(int a, String com_code){	
		try{
			conn = getConn();
			sql = "select prod_id, prod_name, prod_cnt,prod_cnt_min, to_char(prod_price,'FM999,999,999,999') as prod_price, "
					+ "to_char(prod_wearing_price,'FM999,999,999,999') as prod_wearing_price,prod_pic, prod_main_category, prod_sub_category, prod_Ssub_category, "
					+ "to_char(prod_mod_date,'yyyy-mm-dd') as prod_mod_date, prod_flag from product where company_id = '"+com_code+"'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				String prod_id = rs.getString("prod_id");
				String prod_name = rs.getString("prod_name");
				String prod_cnt = rs.getString("prod_cnt");
				String prod_min_cnt = rs.getString("prod_cnt_min");
				String prod_price = rs.getString("prod_price");
				String prod_wearing_price = rs.getString("prod_wearing_price");
				String prod_pic = rs.getString("prod_pic");
				String prod_main_category = rs.getString("prod_main_category");
				String prod_sub_category = rs.getString("prod_sub_category");
				String prod_Ssub_category = rs.getString("prod_Ssub_category");
				String prod_mod_date = rs.getString("prod_mod_date");
				String prod_flag = rs.getString("prod_flag");
				
				Vector row = new Vector();
				
				row.add(prod_id);
				row.add(prod_name);
				row.add(prod_cnt);
				row.add(prod_min_cnt);
				row.add(prod_price);
				row.add(prod_wearing_price);
				row.add(prod_pic);
				row.add(prod_main_category);
				row.add(prod_sub_category);
				row.add(prod_Ssub_category);
				row.add(prod_mod_date);
				row.add(prod_flag);
				
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	public Vector prod_select(String prodid){		
		try{
			conn = getConn();
			sql = "select * from product_wearing where prod_id = '"+prodid+"'";
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
	public String prod_select_1(String prodid){		
		String prod_cnt="";
		try{
			conn = getConn();
			sql = "select * from product where prod_id = '"+prodid+"'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				prod_cnt = rs.getString("prod_cnt");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return prod_cnt;
	}
	public Vector prod_select_name(String prodname){		
		try{
			conn = getConn();
			String sql = "select prod_id, prod_name, prod_cnt, to_char(prod_price,'FM999,999,999,999') as prod_price, "
					+ "prod_pic, prod_main_category, prod_sub_category, prod_Ssub_category, to_char(prod_wearing_price,'FM999,999,999,999') as prod_wearing_price, prod_cnt_min, "
					+ "to_char(prod_mod_date,'yyyy-mm-dd') as prod_mod_date, prod_flag from product where prod_name LIKE '%"+prodname+"%' and prod_flag = '판매중'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				String prod_id = rs.getString("prod_id");
				String prod_name = rs.getString("prod_name");
				String prod_cnt = rs.getString("prod_cnt");
				String prod_price = rs.getString("prod_price");
				String prod_pic = rs.getString("prod_pic");
				String prod_wearing_price = rs.getString("prod_wearing_price");
				String prod_main_category = rs.getString("prod_main_category");
				String prod_sub_category = rs.getString("prod_sub_category");
				String prod_Ssub_category = rs.getString("prod_Ssub_category");
				String prod_mod_date = rs.getString("prod_mod_date");
				String prod_flag = rs.getString("prod_flag");
				String prod_cnt_min = rs.getString("prod_cnt_min");
				
				Vector row = new Vector();
				
				row.add(prod_id);
				row.add(prod_name);
				row.add(prod_cnt);
				row.add(prod_cnt_min);
				row.add(prod_price);
				row.add(prod_wearing_price);
				row.add(prod_pic);
				row.add(prod_main_category);
				row.add(prod_sub_category);
				row.add(prod_Ssub_category);
				row.add(prod_mod_date);
				row.add(prod_flag);
				
				
				
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	public Vector prod_select_name_1(String prodname){
		try{
			conn = getConn();
			sql = "select prod_id, prod_name, to_char(prod_price,'FM999,999,999,999') as prod_price, prod_cnt, prod_pic, prod_flag from product where prod_name LIKE '%"+prodname+"%' and prod_flag = '판매중'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				String prod_id = rs.getString("prod_id");
				String prod_name = rs.getString("prod_name");
				String prod_price = rs.getString("prod_price");
				String prod_cnt = rs.getString("prod_cnt");
				String prod_pic = rs.getString("prod_pic");
				String prod_flag = rs.getString("prod_flag");
				
				Vector row = new Vector();
				
				row.add(prod_id);
				row.add(prod_name);
				row.add(prod_price);
				row.add(prod_cnt);
				row.add(prod_pic);
				row.add(prod_flag);
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	public Vector prod_select_name_2(String prodname,String com_code){
		try{
			conn = getConn();
			sql = "select prod_id, prod_name, to_char(prod_price,'FM999,999,999,999') as prod_price, prod_cnt, prod_pic, prod_flag from product where company_id = ? and prod_name LIKE '%"+prodname+"%'";
			ps = conn.prepareStatement(sql);
			ps.setString(1, com_code);
			rs = ps.executeQuery();
			
			while(rs.next()){
				String prod_id = rs.getString("prod_id");
				String prod_name = rs.getString("prod_name");
				String prod_price = rs.getString("prod_price");
				String prod_cnt = rs.getString("prod_cnt");
				String prod_pic = rs.getString("prod_pic");
				String prod_flag = rs.getString("prod_flag");
				
				Vector row = new Vector();
				
				row.add(prod_id);
				row.add(prod_name);
				row.add(prod_price);
				row.add(prod_cnt);
				row.add(prod_pic);
				row.add(prod_flag);
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	public Vector prod_select_id(String prodid){
		try{
			conn = getConn();
			sql = "select prod_id, prod_name, prod_price, prod_cnt, prod_pic, prod_flag from product where prod_id ='"+prodid+"'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				String prod_id = rs.getString("prod_id");
				String prod_name = rs.getString("prod_name");
				String prod_price = rs.getString("prod_price");
				String prod_cnt = rs.getString("prod_cnt");
				String prod_pic = rs.getString("prod_pic");
				String prod_flag = rs.getString("prod_flag");
				
				Vector row = new Vector();
				
				row.add(prod_id);
				row.add(prod_name);
				row.add(prod_price);
				row.add(prod_cnt);
				row.add(prod_pic);
				
				if(prod_flag.equals("2")){
					row.add("재고부족");
				}
				else if(prod_flag.equals("1")){
					row.add("판매중");
				}
				else if(prod_flag.equals("0")){
					row.add("판매종료");
				}
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	public Vector prod_select_category(String category){
		try{
			conn = getConn();
			sql = "select prod_id, prod_name, to_char(prod_price,'FM999,999,999') as prod_price, prod_cnt, prod_pic, prod_flag from product where prod_main_category ='"+category+"'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				String prod_id = rs.getString("prod_id");
				String prod_name = rs.getString("prod_name");
				String prod_price = rs.getString("prod_price");
				String prod_cnt = rs.getString("prod_cnt");
				String prod_pic = rs.getString("prod_pic");
				String prod_flag = rs.getString("prod_flag");
				
				Vector row = new Vector();
				
				row.add(prod_id);
				row.add(prod_name);
				row.add(prod_price);
				row.add(prod_cnt);
				row.add(prod_pic);
				row.add(prod_flag);
				
				if(prod_flag.equals("2")){
					row.add("재고부족");
				}
				else if(prod_flag.equals("1")){
					row.add("판매중");
				}
				else if(prod_flag.equals("0")){
					row.add("판매종료");
				}
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	public int prod_update(Product_model model){
		int result = 0;
		try{
			conn = getConn();
			sql = "update product set prod_name = '"+model.getProd_name()+
					"' , prod_cnt = "+model.getProd_cnt()+
					" , prod_price = "+model.getProd_price()+
					" , prod_cnt_min = "+model.getProd_cnt_min()+
					" , prod_wearing_price = "+model.getProd_wearing_price()+
					" , prod_main_category = '"+model.getProd_main_category()+
					"' , prod_sub_category = '"+model.getProd_sub_category()+
					"' , prod_Ssub_category = '"+model.getProd_Ssub_category()+
					"' , prod_mod_date = sysdate"+
					" , prod_flag = '"+model.getProd_flag()+"'"+
					" , prod_pic = '"+model.getProd_pic()+"'"+
					" where prod_id = '"+model.getProd_id()+"'";
			ps = conn.prepareStatement(sql);
			result=ps.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public void prod_delete(Product_model model){
		try{
			conn = getConn();
			String sql = "delete from product where prod_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, model.getProd_id());
			ps.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public int prod_insert(Product_model model){
		String MSI = String.valueOf(Integer.parseInt(MAX_PRODUCT_ID())+1);
		int result=0;
		try{
			conn = getConn();
			String sql = "insert into product values(?,?,0,?,?,?,?,?,sysdate,?,?,'판매중지',sysdate)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, MSI);
			ps.setString(2, model.getProd_name());
			ps.setString(3, model.getProd_price());
			ps.setString(4, model.getProd_pic());
			ps.setString(5, model.getProd_main_category());
			ps.setString(6, model.getProd_sub_category());
			ps.setString(7, model.getProd_Ssub_category());
			ps.setString(8, model.getProd_wearing_price());
			ps.setString(9, model.getProd_cnt_min());
			result=ps.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public int product_update(String com_code, String prod_id, String prod_cnt){
		int result=0;
		try{
			conn = getConn();
			String sql = "update product set prod_cnt = prod_cnt + ? where prod_id = ? and company_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, prod_cnt);
			ps.setString(2, prod_id);
			ps.setString(3, com_code);
			result=ps.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public String MAX_PRODUCT_ID(){
		String MPI="";
		try{
			conn = getConn();
			String sql = "select max(to_number(prod_id)) as MPI from product";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				MPI = rs.getString("MPI");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return MPI;
	}
	public void prod_flag(JComboBox combo){
		try{
			conn = getConn();
			String sql = "select distinct prod_flag from product";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				combo.addItem(rs.getString("prod_flag"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void prod_main_category(JComboBox combo){
		try{
			conn = getConn();
			String sql = "select distinct prod_main_category from product";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				combo.addItem(rs.getString("prod_main_category"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void prod_sub_category(JComboBox combo, String category){
		try{
			conn = getConn();
			String sql = "select distinct prod_sub_category from product where prod_main_category = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, category);
			rs = ps.executeQuery();
			
			while(rs.next()){
				combo.addItem(rs.getString("prod_sub_category"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}	
}
