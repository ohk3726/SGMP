package view;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;

import database.Product_Wearing_db;
import database.Product_db;
import database.Sale_db;
import database.ShoppingBasket_db;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.Product_Wearing_model;
import model.Shopping_basket_model;

public class Auto_Order extends JPanel {
	Vector v,v1,cols,cols1;
	DefaultTableModel model,model1;
	private JTable prod_auto_order;
	private JTable prod_order;
	SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	private DefaultTableCellRenderer cellcenter = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer cellright = new DefaultTableCellRenderer();
	private TableColumnModel tcmSchedule;
	private JTextField txt_order_price;
	private JDateChooser dateChooser_1;

	/**
	 * Create the panel.
	 */
	public Auto_Order(String com_code) {
		setSize(1600, 900);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setBounds(12, 41, 764, 787);
		add(scrollPane);
		
		Product_Wearing_model PWM1 = new Product_Wearing_model();
		Date time = new Date();
		PWM1.setProd_wearing_date(SDF.format(time));
		PWM1.setProd_wearing_company_id(com_code);
		Product_Wearing_db PWD1 = new Product_Wearing_db();
		cols=getColumn();
		v=PWD1.prod_auto_order(PWM1);
		model = new DefaultTableModel(v,cols){
			//내용수정불가
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		
		cellcenter.setHorizontalAlignment(SwingConstants.CENTER);
		cellright.setHorizontalAlignment(SwingConstants.RIGHT);
		
		prod_auto_order = new JTable(model);
		prod_auto_order.setFont(new Font("굴림", Font.PLAIN, 20));
		prod_auto_order.setRowHeight(40);
		prod_auto_order.getTableHeader().setReorderingAllowed(false);
		prod_auto_order.getTableHeader().setResizingAllowed(false);
		scrollPane.setViewportView(prod_auto_order);
		
		tcmSchedule = prod_auto_order.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			if(i == 3 || i == 4){
				tcmSchedule.getColumn(i).setCellRenderer(cellright);
			}
			else{
				tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
			}
		}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(Color.BLACK));
		scrollPane_1.setBounds(805, 89, 764, 787);
		add(scrollPane_1);	
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setFont(new Font("굴림", Font.PLAIN, 20));
		dateChooser_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		dateChooser_1.setBounds(805, 41, 442, 38);
		dateChooser_1.setDate(time);
		add(dateChooser_1);
		
		Product_Wearing_db PWD = new Product_Wearing_db();
		Product_Wearing_model PWM = new Product_Wearing_model();
		PWM.setProd_wearing_date(SDF.format(dateChooser_1.getDate()));
		PWM.setProd_wearing_company_id(com_code);
		cols1=getColumn1();
		v1=PWD.prod_wearing_select(PWM);
		
		model1 = new DefaultTableModel(v1,cols1){
			//내용수정불가
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		
		prod_order = new JTable(model1);
		prod_order.setFont(new Font("굴림", Font.PLAIN, 20));
		prod_order.setRowHeight(40);
		prod_order.getTableHeader().setReorderingAllowed(false);
		prod_order.getTableHeader().setResizingAllowed(false);
		scrollPane_1.setViewportView(prod_order);
		
		tcmSchedule = prod_order.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			if(i == 3 || i == 4){
				tcmSchedule.getColumn(i).setCellRenderer(cellright);
			}
			else{
				tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
			}
		}
		
		JButton btnOrderInsert = new JButton("\uBC1C   \uC8FC   \uB4F1   \uB85D");
		btnOrderInsert.setFont(new Font("굴림", Font.PLAIN, 20));
		btnOrderInsert.setBounds(466, 838, 310, 38);
		add(btnOrderInsert);
		
		txt_order_price = new JTextField();
		txt_order_price.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_order_price.setFont(new Font("굴림", Font.PLAIN, 20));
		txt_order_price.setEnabled(false);
		txt_order_price.setBounds(170, 838, 284, 38);
		add(txt_order_price);
		txt_order_price.setColumns(10);
		
		Product_Wearing_db PWD2 = new Product_Wearing_db();
		txt_order_price.setText(PWD2.prod_auto_order_price(PWM1));
		
		btnOrderInsert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Product_Wearing_db PWD3 = new Product_Wearing_db();
				Product_Wearing_model PWM = new Product_Wearing_model();
				Date time = new Date();
				PWM.setProd_wearing_date(SDF.format(time).toString());
				PWM.setProd_wearing_company_id(com_code);
				String check = PWD3.order_check(PWM);
				
				if(!check.equals("0")){
					PWD3.prod_wearing_insert(PWM);
					JOptionPane.showMessageDialog(null, "자동발주 되었습니다.");
					Product_Wearing_db PWD1 = new Product_Wearing_db();
					PWM1.setProd_wearing_date(SDF.format(time));
					cols=getColumn();
					v=PWD1.prod_auto_order(PWM1);
					model.setDataVector(v, cols);
					
					Product_Wearing_db PWD = new Product_Wearing_db();
					Product_Wearing_model PWM1 = new Product_Wearing_model();
					PWM1.setProd_wearing_date(SDF.format(dateChooser_1.getDate()));
					cols1=getColumn1();
					v1=PWD.prod_wearing_select(PWM1);
					model1.setDataVector(v1, cols1);
					
					tcmSchedule = prod_order.getColumnModel();
					for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
						if(i == 3 || i == 4){
							tcmSchedule.getColumn(i).setCellRenderer(cellright);
						}
						else{
							tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
						}
					}
					txt_order_price.setText("");
				}
				else{
					JOptionPane.showMessageDialog(null, "자동발주 상품이 없습니다.");
				}
				
				
			}
		});
		
		JButton btnSelect = new JButton("\uC870         \uD68C");
		btnSelect.setFont(new Font("굴림", Font.PLAIN, 20));
		btnSelect.setBounds(1259, 41, 310, 38);
		add(btnSelect);
		
		btnSelect.addActionListener(new ActionListener() {
			int result=1;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!dateChooser_1.getDate().equals(null)){
					result=0;
				}
				if(result == 0){
					Product_Wearing_db PWD = new Product_Wearing_db();
					Product_Wearing_model PWM = new Product_Wearing_model();
					PWM.setProd_wearing_date(SDF.format(dateChooser_1.getDate()));
					PWM.setProd_wearing_company_id(com_code);
					cols1=getColumn1();
					v1=PWD.prod_wearing_select(PWM);
					model1.setDataVector(v1, cols1);
					
					tcmSchedule = prod_order.getColumnModel();
					for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
						if(i == 3 || i == 4){
							tcmSchedule.getColumn(i).setCellRenderer(cellright);
						}
						else{
							tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
						}
					}
				}
			}
		});
		
		prod_order.addMouseListener(new order_result(com_code));
		
		JLabel lblNewLabel = new JLabel("\uC8FC\uBB38 \uCD1D \uAC00\uACA9");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 838, 146, 38);
		add(lblNewLabel);
	}
	public Vector getColumn(){
		Vector col = new Vector();
		col.add("품목번호");
		col.add("품목명");
		col.add("주문수량");
		col.add("단가");
		col.add("주문가격");
		
		return col;
	}
	public Vector getColumn1(){
		Vector col = new Vector();
		col.add("주문번호");
		col.add("품목번호");
		col.add("품목명");
		col.add("주문수량");
		col.add("주문가격");
		col.add("주문상태");
		return col;
	}
	private class order_result extends MouseAdapter {
		String com_code1;
		public order_result(String com_code) {
			com_code1 = com_code;
		} 
	    @Override
	    public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 2) { 
		    	Date time = new Date();
		    	if(SDF.format(dateChooser_1.getDate()).equals(SDF.format(time))){
		    		JOptionPane.showMessageDialog(null, "오늘 주문한 상품은 입고완료 처리를 할 수 없습니다.");
		    	}
		    	else{
		    		int row = prod_order.getSelectedRow();
			    	if(prod_order.getValueAt(row, 6).toString().equals("배송완료")){
			    		int result = JOptionPane.showConfirmDialog(null, "선택 품목의 주문 수량이 모두 들어왔습니까?");
			    		if(result == 0){
			    			Product_Wearing_db PWDb = new Product_Wearing_db();
				    		Product_db pdb = new Product_db();
				    		
				    		PWDb.product_wearing_result(prod_order.getValueAt(row, 0).toString());
				    		pdb.product_update(com_code1,prod_order.getValueAt(row, 1).toString(), prod_order.getValueAt(row, 3).toString());
				    		JOptionPane.showMessageDialog(null, "입고가 완료되었습니다.");
				    		
				    		Product_Wearing_db PWD = new Product_Wearing_db();
							Product_Wearing_model PWM1 = new Product_Wearing_model();
							PWM1.setProd_wearing_date(SDF.format(dateChooser_1.getDate()));
							cols1=getColumn1();
							v1=PWD.prod_wearing_select(PWM1);
							model1.setDataVector(v1, cols1);
							
							tcmSchedule = prod_order.getColumnModel();
							for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
								if(i == 3 || i == 4){
									tcmSchedule.getColumn(i).setCellRenderer(cellright);
								}
								else{
									tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
								}
							}
			    		}	
			    	}
			    	else if(prod_order.getValueAt(row, 6).toString().equals("입고완료")) {
			    		JOptionPane.showMessageDialog(null, "입고가 완료된 품목입니다.");
			    	}
			    	else{
			    		JOptionPane.showMessageDialog(null, "배송이 완료 되지 않았습니다.");
			    	}
		    	}	
		    }
	    }
	}
}
