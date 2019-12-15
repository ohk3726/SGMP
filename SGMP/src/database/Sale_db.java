package database;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.data.jdbc.JDBCPieDataset;

import model.Product_model;
import model.Sale_model;
import model.Shopping_basket_model;

public class Sale_db {
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
	public Vector sale_select(){	
		try{
			conn = getConn();
			
			sql = "select t10.sale_id, t20.prod_id, t20.prod_name, to_char(t20.prod_price,'FM999,999,999,999') as prod_price, t10.sale_cnt, to_char((t20.prod_price * t10.sale_cnt),'FM999,999,999,999') as cnt_price "+
					"from sale t10, product t20 "+
					"where t10.prod_id = t20.prod_id(+) "+
					"order by to_number(t10.sale_id)";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String sale_id = rs.getString("sale_id");
				String prod_id = rs.getString("prod_id");
				String prod_name = rs.getString("prod_name");
				String prod_price = rs.getString("prod_price");
				String sale_cnt = rs.getString("sale_cnt");
				String cnt_price = rs.getString("cnt_price");
				
				Vector row = new Vector();
				row.add(sale_id);
				row.add(prod_id);
				row.add(prod_name);
				row.add(prod_price);
				row.add(sale_cnt);
				row.add(cnt_price);
				
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	public Vector sale_select(String sale_id){	
		try{
			conn = getConn();
			
			sql = "select t10.sale_id,t20.prod_id, t20.prod_name, to_char(t20.prod_price,'FM999,999,999,999') as prod_price, t10.sale_cnt, to_char((t20.prod_price * t10.sale_cnt),'FM999,999,999,999') as cnt_price "+
					"from sale t10, product t20 "+
					"where t10.prod_id = t20.prod_id(+) "+
					"and t10.sale_id = '"+sale_id+"' "+
					"order by t10.sale_id";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String sale_id1 = rs.getString("sale_id");
				String prod_id = rs.getString("prod_id");
				String prod_name = rs.getString("prod_name");
				String prod_price = rs.getString("prod_price");
				String sale_cnt = rs.getString("sale_cnt");
				String cnt_price = rs.getString("cnt_price");
				
				Vector row = new Vector();
				row.add(sale_id1);
				row.add(prod_id);
				row.add(prod_name);
				row.add(prod_price);
				row.add(sale_cnt);
				row.add(cnt_price);
				
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	
	public Vector sale_select1(String sale_id1){	
		try{
			conn = getConn();
			
			sql = "select distinct t10.sale_id, to_char(t10.sale_date,'yyyy-mm-dd') as sale_date "+
					"from sale t10, product t20 "+
					"where t10.prod_id = t20.prod_id(+) "+
					"and t10.sale_id = '"+sale_id1+"' "+
					"order by to_char(t10.sale_date,'yyyy-mm-dd')";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String sale_id = rs.getString("sale_id");
				String sale_date = rs.getString("sale_date");
				
				Vector row = new Vector();
				
				row.add(sale_id);
				row.add(sale_date);
				
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	public String sale_select2(String sale_id1){
		String cnt = "";
		try{
			conn = getConn();
			
			sql = "select count(*) as cnt "+
					"from sale "+
					"where sale_id = '"+sale_id1+"' ";
			ps = conn.prepareStatement(sql);
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
	public Vector custid_select(String cust_id){		
		try{
			conn = getConn();
			
			sql = "select distinct sale_id, to_char(sale_date,'yyyy-mm-dd') as sale_date from sale where cust_id = "+cust_id+" order by sale_date";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				String sale_id = rs.getString("sale_id");
				String sale_date = rs.getString("sale_date");
				
				Vector row = new Vector();
				
				row.add(sale_id);
				row.add(sale_date);
				
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	public String custid_select_1(String cust_id){		
		String cnt = "";
		try{
			conn = getConn();
			
			sql = "select count(*) as cnt from customer where cust_id = '"+cust_id+"'";
			ps = conn.prepareStatement(sql);
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
	public void sale_delete(Sale_model sale, int select){
		try{		
			conn = getConn();
			System.out.println(select);
			if(select == 0){
				sql = "delete from sale where sale_id = ?";
				
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, sale.getSale_id());
			}
			else if(select == -1){
				sql = "delete from sale where sale_id = ? and prod_id = ?";
				
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, sale.getSale_id());
				ps.setString(2, sale.getProd_id());
			}
			else{
				sql = "update sale set sale_cnt = ? where sale_id = ? and prod_id = ?";
				
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, String.valueOf(select));
				ps.setString(2, sale.getSale_id());
				ps.setString(3, sale.getProd_id());
			}
			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void prod_update(Sale_model sale, int select){
		try{		
			conn = getConn();
			if(select == 0){
				sql = "update product t90 "+
						"set (t90.prod_id, t90.prod_cnt) = (  "+
								"select t30.prod_id, t30.prod_cnt "+
								"from  "+
								"(select t10.prod_id, (t20.prod_cnt + t10.sale_cnt) as prod_cnt "+
								"from (select * from sale where sale_id = ? ) t10, "+
								"product t20 "+
								"where t10.prod_id = t20.prod_id) t30 "+
								"where t30.prod_id = t90.prod_id "+
								") "+
								"where EXISTS ( "+
								"select t30.prod_id, t30.prod_cnt "+
								"from "+
								"(select t10.prod_id, (t20.prod_cnt + t10.sale_cnt) as prod_cnt "+
								"from (select * from sale where sale_id = ?) t10, "+
								"product t20 "+
								"where t10.prod_id = t20.prod_id) t30 "+
								"where t30.prod_id = t90.prod_id "+
								")";
				
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, sale.getSale_id());
				ps.setString(2, sale.getSale_id());
			}
			else if(select == -1){
				sql = "update product t90 "+
						"set (t90.prod_id, t90.prod_cnt) = (  "+
						"select t30.prod_id, t30.prod_cnt "+
						"from  "+
						"(select t10.prod_id, (t20.prod_cnt + t10.sale_cnt) as prod_cnt "+
						"from (select * from sale where sale_id = ?) t10, "+
						"product t20 "+
						"where t10.prod_id = t20.prod_id "+
						"and t10.prod_id = ?) t30 "+
						"where t30.prod_id = t90.prod_id "+
						") "+
						"where EXISTS ( "+
						"select t30.prod_id, t30.prod_cnt "+
						"from "+
						"(select t10.prod_id, (t20.prod_cnt + t10.sale_cnt) as prod_cnt "+
						"from (select * from sale where sale_id = ?) t10, "+
						"product t20 "+
						"where t10.prod_id = t20.prod_id "+
						"and t10.prod_id = ?) t30 "+
						"where t30.prod_id = t90.prod_id "+
						")";
				
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, sale.getSale_id());
				ps.setString(2, sale.getProd_id());
				ps.setString(3, sale.getSale_id());
				ps.setString(4, sale.getProd_id());
			}
			else{
				sql = "update product t90 "+
						"set (t90.prod_id, t90.prod_cnt) = (  "+
						"select t30.prod_id, t30.prod_cnt "+
						"from  "+
						"(select t10.prod_id, (t20.prod_cnt + t10.sale_cnt - ?) as prod_cnt "+
						"from (select * from sale where sale_id = ?) t10, "+
						"product t20 "+
						"where t10.prod_id = t20.prod_id "+
						"and t10.prod_id = ?) t30 "+
						"where t30.prod_id = t90.prod_id "+
						") "+
						"where EXISTS ( "+
						"select t30.prod_id, t30.prod_cnt "+
						"from "+
						"(select t10.prod_id, (t20.prod_cnt + t10.sale_cnt - ?) as prod_cnt "+
						"from (select * from sale where sale_id = ?) t10, "+
						"product t20 "+
						"where t10.prod_id = t20.prod_id "+
						"and t10.prod_id = ?) t30 "+
						"where t30.prod_id = t90.prod_id "+
						")";
				
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, String.valueOf(select));
				ps.setString(2, sale.getSale_id());
				ps.setString(3, sale.getProd_id());
				ps.setString(4, String.valueOf(select));
				ps.setString(5, sale.getSale_id());
				ps.setString(6, sale.getProd_id());
			}
			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Vector sale_select(String date1, String date2){	
		try{
			conn = getConn();
			
			sql = "select t10.sale_id, t20.prod_name, t10.sale_cnt, to_char(t20.prod_price,'FM999,999,999,999') as prod_price, to_char((t10.sale_cnt * t20.prod_price),'FM999,999,999,999') as price "
					+"from sale t10, product t20 "
					+"where t10.prod_id = t20.prod_id "
					+"and to_date(t10.sale_date) between to_date(?) and to_date(?) "
					+"order by to_number(t10.sale_id)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, date1);
			ps.setString(2, date2);
			rs = ps.executeQuery();
			while(rs.next()){
				String sale_id = rs.getString("sale_id");
				String prod_name = rs.getString("prod_name");
				String sale_cnt = rs.getString("sale_cnt");
				String prod_price = rs.getString("prod_price");
				String price = rs.getString("price");
				
				Vector row = new Vector();
				
				row.add(sale_id);
				row.add(prod_name);
				row.add(sale_cnt);
				row.add(prod_price);
				row.add(price);
				
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	public JFreeChart sale_chart(String date1, String date2){	
		JFreeChart pieChart = null;
		try{
			conn = getConn();
			
			sql = "select t40.prod_main_category, sum(t30.price) "
					+"from (select t10.prod_id, sum(t10.sale_cnt * t20.prod_price) as price "
					+"from sale t10, product t20 "
					+"where t10.prod_id = t20.prod_id "
					+"and to_char(t10.sale_date,'yyyy-mm-dd') between to_char('"+date1+"') and to_char('"+date2+"') " 
					+"group by t10.prod_id) t30, product t40 "
					+"where t30.prod_id = t40.prod_id "
					+"group by t40.prod_main_category";
			PieDataset pieDataset = new JDBCPieDataset(conn, sql);
			
			pieChart = ChartFactory.createPieChart("대분류별 판매금액", pieDataset, true, true, false);
			pieChart.getTitle().setFont(new Font("굴림", Font.BOLD,20));
			pieChart.getLegend().setItemFont(new Font("굴림", Font.PLAIN, 20));
			PiePlot plot = (PiePlot) pieChart.getPlot();
			plot.setLabelFont(new Font("굴림", Font.PLAIN, 20));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return pieChart;
	}
	
	public JFreeChart sale_chart_1(String date,PlotOrientation plot,String group){	
		JFreeChart categoryChart = null;
		try{
			conn = getConn();
			if(group.equals("분기별")){
				sql = "with T as( "+
						  "  select '1분기' as quarter, sum(t10.sale_cnt * t20.prod_price) 판매금액 from sale t10, product t20 where t10.prod_id = t20.prod_id and to_char(t10.sale_date,'yyyy-mm') between '"+date+"-01' and '"+date+"-03' UNION ALL "+
						  "  select '2분기' as quarter, sum(t10.sale_cnt * t20.prod_price) 판매금액 from sale t10, product t20 where t10.prod_id = t20.prod_id and to_char(t10.sale_date,'yyyy-mm') between '"+date+"-04' and '"+date+"-06' UNION ALL "+
						  "  select '3분기' as quarter, sum(t10.sale_cnt * t20.prod_price) 판매금액 from sale t10, product t20 where t10.prod_id = t20.prod_id and to_char(t10.sale_date,'yyyy-mm') between '"+date+"-07' and '"+date+"-09' UNION ALL "+
						  "  select '4분기' as quarter, sum(t10.sale_cnt * t20.prod_price) 판매금액 from sale t10, product t20 where t10.prod_id = t20.prod_id and to_char(t10.sale_date,'yyyy-mm') between '"+date+"-10' and '"+date+"-12' "+
						") "+
						"select * "+
						"from T";
			}
			else{
				sql="select to_char(t10.sale_date,'mm')||'월' as sale_date, sum(t10.sale_cnt * t20.prod_price) as 판매금액 "+ 
						"from sale t10, product t20 "+
						"where t10.prod_id = t20.prod_id "+
						"and to_char(t10.sale_date,'yyyy') = '"+date+"' "+
						"group by to_char(t10.sale_date,'mm') "+
						"order by to_char(t10.sale_date,'mm')";
			}
			CategoryDataset barDataset = new JDBCCategoryDataset(conn, sql);
			
			categoryChart = ChartFactory.createBarChart(group+" 판매금액", group,"판매금액",barDataset,plot,true,true,false);
			categoryChart.getTitle().setFont(new Font("굴림", Font.BOLD,20));
			categoryChart.getLegend().setItemFont(new Font("굴림", Font.PLAIN, 20));
			CategoryPlot plot1 = (CategoryPlot) categoryChart.getPlot();
			plot1.getDomainAxis().setLabelFont(new Font("굴림", Font.BOLD,20));
			plot1.getDomainAxis().setTickLabelFont(new Font("굴림", Font.BOLD,15));
			plot1.getRangeAxis().setLabelFont(new Font("굴림", Font.BOLD,20));
	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return categoryChart;
	}
	
	public Vector sale_all_price(String date1, String date2){	
		try{
			conn = getConn();
			
			sql = "select to_char(sum(t20.prod_wearing_price * t10.sale_cnt),'FM999,999,999,999') as wearing_price, to_char(sum(t20.prod_price * t10.sale_cnt),'FM999,999,999,999') as price, to_char((sum(t20.prod_price * t10.sale_cnt) - sum(t20.prod_wearing_price * t10.sale_cnt)),'FM999,999,999,999') as allprice "
					+"from sale t10, product t20 "
					+"where t10.prod_id = t20.prod_id "
					+"and to_char(t10.sale_date,'yyyy-mm-dd') between to_char(?) and to_char(?) ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, date1);
			ps.setString(2, date2);
			rs = ps.executeQuery();
			while(rs.next()){
				String wearing_price = rs.getString("wearing_price");
				String price = rs.getString("price");
				String allprice = rs.getString("allprice");
				
				Vector row = new Vector();
				
				row.add(wearing_price);
				row.add(price);
				row.add(allprice);
				
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	public JFreeChart sale_chart(String date1, String date2, String category){	
		JFreeChart pieChart = null;
		try{
			conn = getConn();
			
			sql = "select t40.prod_sub_category, sum(t30.price) "
					+"from (select t10.prod_id, sum(t10.sale_cnt * t20.prod_price) as price "
					+"from sale t10, product t20 "
					+"where t10.prod_id = t20.prod_id "
					+"and to_date(t10.sale_date) between to_date('"+date1+"') and to_date('"+date2+"') " 
					+"group by t10.prod_id) t30, product t40 "
					+"where t30.prod_id = t40.prod_id "
					+"and t40.prod_main_category = '"+category+"'"
					+"group by t40.prod_sub_category";
			PieDataset pieDataset = new JDBCPieDataset(conn, sql);
			
			pieChart = ChartFactory.createPieChart("중분류별 판매금액", pieDataset, true, true, false);
			pieChart.getTitle().setFont(new Font("굴림", Font.BOLD,20));
			pieChart.getLegend().setItemFont(new Font("굴림", Font.PLAIN, 20));
			PiePlot plot = (PiePlot) pieChart.getPlot();
			plot.setLabelFont(new Font("굴림", Font.PLAIN, 20));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return pieChart;
	}
	
	public String sb_cnt(Shopping_basket_model smodel){	
		String temp="";
		try{
			conn = getConn();
			
			sql = "select * from shopping_basket where prod_id = ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, smodel.getProd_id());
			rs=ps.executeQuery();
			
			while(rs.next()){
				temp = rs.getString("sb_cnt");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return temp;
	}
	
	public Vector sale_date(){	
		try{
			conn = getConn();
			
			sql = "select to_char(sale_date,'yyyy') as sale_date "+
					"from sale "+
					"group by to_char(sale_date,'yyyy')";

			ps = conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()){
				String sale_date = rs.getString("sale_date");
				
				Vector row = new Vector();
				
				row.add(sale_date);
				
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	public Vector sale_category(String date1, String date2){	
		try{
			conn = getConn();
			
			sql = "select t20.prod_main_category "+
					"from sale t10, product t20 "+
					"where t10.prod_id = t20.prod_id "+
					"and to_date(t10.sale_date) between to_date('"+date1+"') and to_date('"+date2+"') "+
					"group by t20.prod_main_category";

			ps = conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()){
				String prod_main_category = rs.getString("prod_main_category");
				
				Vector row = new Vector();
				
				row.add(prod_main_category);
				
				data.add(row);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
}
