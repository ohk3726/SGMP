package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.Product_Wearing_model;

public class Product_Wearing_db {
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
	public Vector prod_auto_order(Product_Wearing_model PWM){
		try{
			conn = getConn();
			sql = "select distinct t10.prod_id, "
					+"t10.prod_name, "
					+"(t10.prod_cnt_min - t10.prod_cnt + 10) as prod_order, "
					+"to_char(t10.prod_price,'FM999,999,999,999') as prod_price, "
					+"to_char(((t10.prod_cnt_min - t10.prod_cnt + 10) * t10.prod_price),'FM999,999,999,999') as prod_price_sum "
					+"from (select * from product where prod_cnt <= prod_cnt_min and company_id = ?) t10, (select * from product_wearing where to_char(prod_wearing_date,'yyyy-mm-dd') = ? and prod_wearing_condition = '상품준비중') t20 "
					+"where t10.prod_id = t20.prod_id(+) "
					+"and ( "
					+"t20.prod_id is null "
					+"or ((t10.prod_cnt_min - t10.prod_cnt + 10) > t20.prod_wearing_cnt and to_char(t20.prod_wearing_date,'yyyy-mm-dd') = ?) "
					+"or to_char(t20.prod_wearing_date,'yyyy-mm-dd') != ?) "
					+"order by t10.prod_id";
			ps = conn.prepareStatement(sql);
			ps.setString(1, PWM.getProd_wearing_company_id());
			ps.setString(2, PWM.getProd_wearing_date());
			ps.setString(3, PWM.getProd_wearing_date());
			ps.setString(4, PWM.getProd_wearing_date());
			rs = ps.executeQuery();
			
			while(rs.next()){
				String prod_id = rs.getString("prod_id");
				String prod_name = rs.getString("prod_name");
				String prod_order = rs.getString("prod_order");
				String prod_price = rs.getString("prod_price");
				String prod_price_sum = rs.getString("prod_price_sum");
				
				Vector row = new Vector();
				
				row.add(prod_id);
				row.add(prod_name);
				row.add(prod_order);
				row.add(prod_price);
				row.add(prod_price_sum);
				
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	public String prod_auto_order_price(Product_Wearing_model PWM){
		String price = "";
		try{
			conn = getConn();
			sql = "select to_char(sum(price),'FM999,999,999,999') as price "
					+"from (select distinct t10.prod_id, ((t10.prod_cnt_min - t10.prod_cnt + 10) * t10.prod_price) as price "
					+"from (select * from product where prod_cnt <= prod_cnt_min and company_id = ?) t10, (select * from product_wearing where to_char(prod_wearing_date,'yyyy-mm-dd') = ? and prod_wearing_condition='입고예정') t20 "
					+"where t10.prod_id = t20.prod_id(+) "
					+"and ( "
					+"t20.prod_id is null "
					+"or ((t10.prod_cnt_min - t10.prod_cnt + 10) > t20.prod_wearing_cnt and to_char(t20.prod_wearing_date,'yyyy-mm-dd') = ?) "
					+"or to_char(t20.prod_wearing_date,'yyyy-mm-dd') != ?) "
					+"order by t10.prod_id) t30";
			ps = conn.prepareStatement(sql);
			ps.setString(1, PWM.getProd_wearing_company_id());
			ps.setString(2, PWM.getProd_wearing_date());
			ps.setString(3, PWM.getProd_wearing_date());
			ps.setString(4, PWM.getProd_wearing_date());
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
	
	public String order_check(Product_Wearing_model PWM){
		String cnt = "";
		try{
			conn = getConn();
			sql = "select count(*) as cnt "
					+"from (select distinct t10.prod_id, ((t10.prod_cnt_min - t10.prod_cnt + 10) * t10.prod_price) as price "
					+"from (select * from product where prod_cnt <= prod_cnt_min) t10, (select * from product_wearing where to_char(prod_wearing_date,'yyyy-mm-dd') = ?) t20 "
					+"where t10.prod_id = t20.prod_id(+) "
					+"and ( "
					+"t20.prod_id is null "
					+"or ((t10.prod_cnt_min - t10.prod_cnt + 10) > t20.prod_wearing_cnt and to_char(t20.prod_wearing_date,'yyyy-mm-dd') = ?) "
					+"or to_char(t20.prod_wearing_date,'yyyy-mm-dd') != ?) "
					+"order by t10.prod_id) t30";
			ps = conn.prepareStatement(sql);
			ps.setString(1, PWM.getProd_wearing_date());
			ps.setString(2, PWM.getProd_wearing_date());
			ps.setString(3, PWM.getProd_wearing_date());
			rs = ps.executeQuery();
			
			while(rs.next()){
				cnt = rs.getString("cnt");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return cnt;
	}
	public Vector prod_wearing_select(Product_Wearing_model PWM){	
		try{
			conn = getConn();
			sql = "select t10.prod_wearing_id, t10.prod_id, t20.prod_name, t10.prod_wearing_cnt, to_char(t10.prod_wearing_cnt * t20.prod_price,'FM999,999,999,999') as prod_price_sum, t10.prod_wearing_condition "
					+"from product_wearing t10, product t20 "
					+"where t10.prod_id = t20.prod_id "
					+"and t20.company_id = ?"
					+"and to_char(t10.prod_wearing_date,'yyyy-mm-dd') = ? "
					+"order by to_number(t10.prod_wearing_id) desc";
			ps = conn.prepareStatement(sql);
			ps.setString(1, PWM.getProd_wearing_company_id());
			ps.setString(2, PWM.getProd_wearing_date());
			rs = ps.executeQuery();
			
			while(rs.next()){
				String prod_wearing_id = rs.getString("prod_wearing_id");
				String prod_id = rs.getString("prod_id");
				String prod_name = rs.getString("prod_name");
				String prod_wearing_cnt = rs.getString("prod_wearing_cnt");
				String prod_price_sum = rs.getString("prod_price_sum");
				String prod_wearing_condition = rs.getString("prod_wearing_condition");
				
				Vector row = new Vector();
				
				row.add(prod_wearing_id);
				row.add(prod_id);
				row.add(prod_name);
				row.add(prod_wearing_cnt);
				row.add(prod_price_sum);
				row.add(prod_wearing_condition);
				
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	public int prod_wearing_insert(Product_Wearing_model PWM){
		int result = 0;
		result += prod_wearing_update(PWM);
		result += prod_wearing_insert1(PWM);
		return result;
	}
	public int prod_wearing_insert1(Product_Wearing_model PWM){	
		int result = 0;
		try{
			conn = getConn();
			sql = "insert into product_wearing(prod_wearing_id, prod_id,prod_wearing_date, prod_wearing_cnt, prod_wearing_condition,prod_wearing_company_id, prod_wearing_flg, prod_wearing_release) "
					+"select "
					+"(select max(to_number(prod_wearing_id)) from product_wearing)+rownum,"
					+"t10.prod_id, "
					+"sysdate, "
					+"(t10.prod_cnt_min - t10.prod_cnt + 10) as prod_order, "
					+"'상품준비중', "
					+"?,"
					+"'0',"
					+"'ROOT'"
					+"from (select * from product where prod_cnt <= prod_cnt_min and company_id = ?) t10, (select * from product_wearing where to_char(prod_wearing_date,'yyyy-mm-dd') = ?) t20 "
					+"where t10.prod_id = t20.prod_id(+) "
					+"and t20.prod_id is null";
			ps = conn.prepareStatement(sql);
			ps.setString(1, PWM.getProd_wearing_company_id());
			ps.setString(2, PWM.getProd_wearing_company_id());
			ps.setString(3, PWM.getProd_wearing_date());
			result = ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public int product_wearing_result(String prod_wearing_id){	
		int result = 0;
		try{
			conn = getConn();
			sql = "update product_wearing set prod_wearing_condition = '입고완료' where prod_wearing_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, prod_wearing_id);
			result = ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public int prod_wearing_update(Product_Wearing_model PWM){	
		int result = 0;
		try{
			conn = getConn();
			sql = "update product_wearing t90 "
					+"set (t90.prod_id, t90.prod_wearing_cnt) = ( "
					+"select t30.prod_id, t30.prod_order "
					+"from(select t10.prod_id, "
					+"(t10.prod_cnt_min - t10.prod_cnt + 10) as prod_order, t20.prod_wearing_date "
					+"from (select * from product where prod_cnt <= prod_cnt_min) t10, product_wearing t20 "
					+"where t10.prod_id = t20.prod_id(+) "
					+"and (t10.prod_cnt_min - t10.prod_cnt + 10) > t20.prod_wearing_cnt "
					+"and to_char(t20.prod_wearing_date,'yyyy-mm-dd') = ?) t30 "
					+"where t30.prod_id = t90.prod_id "
					+"and t30.prod_wearing_date = t90.prod_wearing_date "
					+") "
					+"where EXISTS ( "
					+"select t30.prod_id, t30.prod_order "
					+"from(select t10.prod_id, "
					+"(t10.prod_cnt_min - t10.prod_cnt + 10) as prod_order, t20.prod_wearing_date "
					+"from (select * from product where prod_cnt <= prod_cnt_min) t10, product_wearing t20 " 
					+"where t10.prod_id = t20.prod_id(+) "
					+"and (t10.prod_cnt_min - t10.prod_cnt + 10) > t20.prod_wearing_cnt "
					+"and to_char(t20.prod_wearing_date,'yyyy-mm-dd') = ?) t30 "
					+"where t30.prod_id = t90.prod_id "
					+"and t30.prod_wearing_date = t90.prod_wearing_date "
					+")";
			ps = conn.prepareStatement(sql);
			ps.setString(1, PWM.getProd_wearing_date());
			ps.setString(2, PWM.getProd_wearing_date());
			ps.executeUpdate();
			result = ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
