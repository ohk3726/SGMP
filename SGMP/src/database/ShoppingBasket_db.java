package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.Product_model;
import model.Shopping_basket_model;

public class ShoppingBasket_db {
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
	public void basketInsert(Shopping_basket_model model){
		try{
			conn = getConn();
			sql = "MERGE INTO SHOPPING_BASKET USING DUAL ON (prod_id = ?) "
					+ "when matched then update set sb_cnt = sb_cnt+1 "
					+ "when not matched then insert(prod_id, prod_name, prod_price, sb_cnt) values(?,?,?,1)";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, model.getProd_id());
			ps.setString(2, model.getProd_id());
			ps.setString(3, model.getProd_name());
			ps.setInt(4, Integer.parseInt(model.getProd_Price()));
			ps.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void basketupdate(Shopping_basket_model model){
		try{
			conn = getConn();
			sql = "update shopping_basket set sb_cnt = ? where prod_id = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(model.getSb_cnt()));
			ps.setString(2, model.getProd_id());
			ps.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Vector SB_select(){
		try{
			conn = getConn();
			sql = "select prod_id, prod_name, to_char(prod_price,'FM999,999,999,999') as prod_price, sb_cnt from shopping_basket order by prod_id";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				String prod_id = rs.getString("prod_id");
				String prod_name = rs.getString("prod_name");
				String prod_price = rs.getString("prod_price");
				String sb_cnt = rs.getString("sb_cnt");
				
				Vector row = new Vector();
				
				row.add(prod_id);
				row.add(prod_name);
				row.add(prod_price);
				row.add(sb_cnt);
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	public String SB_select(String prodid){
		String data = "";
		try{
			conn = getConn();
			sql = "select sb_cnt as cnt from shopping_basket where prod_id = '"+prodid+"'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				data = rs.getString("cnt");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	public void SB_delete(){
		try{
			conn = getConn();
			sql = "delete from shopping_basket";
			ps = conn.prepareStatement(sql);
			ps.executeQuery();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String sb_Allprice(){
		String allprice = "";
		try{
			conn = getConn();
			String sql = "select to_char(sum(prod_price * sb_cnt),'FM999,999,999,999') as allprice from shopping_basket";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				allprice = rs.getString("allprice");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return allprice;
	}
	
	public void SB_TO_SALE_INSERT(String cus){
		String MSI = String.valueOf(Integer.parseInt(MAX_SALE_ID())+1);
		try{
			conn = getConn();
			String sql = "insert into sale "
					+ "select ?, prod_id, ?, sb_cnt, sysdate "
					+ "from shopping_basket";
			ps = conn.prepareStatement(sql);
			ps.setString(1, MSI);
			ps.setString(2, cus);
			
			int commit = ps.executeUpdate();
			
			if(commit != 0){
				SB_PROD_UPDATE();
				SB_DELETE();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public String MAX_SALE_ID(){
		String MSI="";
		try{
			conn = getConn();
			String sql = "select max(to_number(sale_id)) as MSI from sale";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				MSI = rs.getString("MSI");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return MSI;
	}
	public void SB_DELETE(){
		try{
			conn = getConn();
			String sql = "delete from shopping_basket";
			ps = conn.prepareStatement(sql);
			int temp = ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void SB_PROD_UPDATE(){
		try{
			conn = getConn();
			String sql = "update product t10 "
			+"set prod_cnt = prod_cnt - (select sb_cnt from shopping_basket t20 where t10.prod_id = t20.prod_id(+)) "
			+"where t10.prod_id in (select prod_id from shopping_basket)";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
