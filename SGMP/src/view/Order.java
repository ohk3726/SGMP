package view;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.Color;
import java.awt.Image;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

import model.Product_Wearing_temp_model;
import model.Shopping_basket_model;
import database.Product_Wearing_temp_db;
import database.Product_db;
import database.Sale_db;
import database.ShoppingBasket_db;

public class Order extends JPanel {
	Vector v,v1,cols,cols1;//테이블 데이터,테이블 컬럼
	DefaultTableModel model,model1;//테이블 모델 선언
	private JTable shopping_basket;//장바구니 테이블
	private JTable product;//제품 목록 테이블
	private JLabel product_pic;//제품 사진 레이블
	private JComboBox category;//분류 콤보박스
	SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField price;//장바구니 가격 텍스트필드
	private DefaultTableCellRenderer cellcenter = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer cellright = new DefaultTableCellRenderer();
	private TableColumnModel tcmSchedule;
	private JTable prod_wearing_temp;
	private JTable product_db;
	/**
	 * Create the panel.
	 */
	public Order(String com_code) {
		setSize(1600, 900);
		setLayout(null);
		Product_db proddb = new Product_db();
		
		product_pic = new JLabel("");
		product_pic.setHorizontalAlignment(SwingConstants.CENTER);
		product_pic.setBorder(new LineBorder(new Color(0, 0, 0)));
		product_pic.setBounds(507, 66, 342, 353);
		add(product_pic);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setBounds(12, 429, 837, 408);
		add(scrollPane);
		
		Product_Wearing_temp_db PWTdb = new Product_Wearing_temp_db();
		Product_Wearing_temp_model pwt = new Product_Wearing_temp_model();
		pwt.setProd_wearing_company_id(com_code);
		v = PWTdb.PWT_select(pwt);
		cols = getColumn();
		model = new DefaultTableModel(v,cols){
			//내용수정불가
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		
		prod_wearing_temp = new JTable(model);
		prod_wearing_temp.setRowHeight(40);
		prod_wearing_temp.setFont(new Font("굴림", Font.PLAIN, 20));
		prod_wearing_temp.getTableHeader().setReorderingAllowed(false);
		prod_wearing_temp.getTableHeader().setResizingAllowed(false);
		
		cellcenter.setHorizontalAlignment(SwingConstants.CENTER);
		cellright.setHorizontalAlignment(SwingConstants.RIGHT);
		tcmSchedule = prod_wearing_temp.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			if(i == 3){
				tcmSchedule.getColumn(i).setCellRenderer(cellright);
			}
			else{
				tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
			}
		}
		
		scrollPane.setViewportView(prod_wearing_temp);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new LineBorder(Color.BLACK));
		scrollPane_1.setBounds(861, 66, 713, 812);
		add(scrollPane_1);
		
		Product_db prod_db = new Product_db();
		v1 = prod_db.prod_select_name_2("",com_code);
		cols1 = getColumn1();
		
		model1 = new DefaultTableModel(v1,cols1){
			//내용수정불가
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		
		product_db = new JTable(model1);
		product_db.setRowHeight(40);
		product_db.setFont(new Font("굴림", Font.PLAIN, 20));
		product_db.getTableHeader().setReorderingAllowed(false);
		product_db.getTableHeader().setResizingAllowed(false);
		
		tcmSchedule = product_db.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			if(i == 2){
				tcmSchedule.getColumn(i).setCellRenderer(cellright);
			}
			else{
				tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
			}
		}
		
		scrollPane_1.setViewportView(product_db);
		
		JLabel label_2 = new JLabel("\uCD1D\uAC00\uACA9");
		label_2.setFont(new Font("굴림", Font.PLAIN, 20));
		label_2.setBounds(529, 847, 121, 31);
		add(label_2);
		
		price = new JTextField();
		price.setEnabled(false);
		price.setColumns(10);
		price.setBounds(662, 847, 187, 31);
		add(price);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 66, 483, 353);
		add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\uB300\uBD84\uB958");
		label.setBounds(69, 85, 121, 31);
		panel.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.PLAIN, 20));
		
		category = new JComboBox();
		category.setBounds(241, 85, 187, 31);
		panel.add(category);
		category.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uD558\uC138\uC694"}));
		category.setSelectedIndex(0);
		category.setFont(new Font("굴림", Font.PLAIN, 20));
		proddb.prod_main_category(category);
		category.setSelectedIndex(0);
		category.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox cb = (JComboBox) e.getSource();
				String category = cb.getSelectedItem().toString();
				
				if(cb.getSelectedIndex()==0){
					Product_db prod_db = new Product_db();
					v1 = prod_db.prod_select_name_1("");
					cols1 = getColumn1();
					model1.setDataVector(v1,cols1);
					
					tcmSchedule = product_db.getColumnModel();
					for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
						if(i == 2){
							tcmSchedule.getColumn(i).setCellRenderer(cellright);
						}
						else{
							tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
						}
					}
				}
				else{
					Product_db prod_db = new Product_db();
					v1 = prod_db.prod_select_category(category);
					cols1 = getColumn1();
					model1.setDataVector(v1,cols1);
					
					tcmSchedule = product_db.getColumnModel();
					for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
						if(i == 2){
							tcmSchedule.getColumn(i).setCellRenderer(cellright);
						}
						else{
							tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
						}
					}
				}
			}
		});
		
		JButton button = new JButton("\uC8FC\uBB38");
		button.setBounds(69, 209, 161, 45);
		panel.add(button);
		button.setFont(new Font("굴림", Font.PLAIN, 20));
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result =0;
				result = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?");
				
				if(result == 0){
					Date time = new Date();
					Product_Wearing_temp_model pwtmodel = new Product_Wearing_temp_model();
					Product_Wearing_temp_db pwtdb = new Product_Wearing_temp_db();
					for(int i=0;i < prod_wearing_temp.getRowCount();i++){
						pwtmodel.setProd_id(prod_wearing_temp.getValueAt(i, 0).toString());
						pwtmodel.setProd_wearing_cnt(prod_wearing_temp.getValueAt(i, 2).toString());
						pwtdb.PWT_TO_PRODUCT_INSERT(pwtmodel, SDF.format(time),com_code);
					}
					Product_Wearing_temp_db PWT = new Product_Wearing_temp_db();
					v = PWT.PWT_select(pwt);
					cols = getColumn();
					model.setDataVector(v, cols);
							
					tcmSchedule = prod_wearing_temp.getColumnModel();
					for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
						if(i == 3){
							tcmSchedule.getColumn(i).setCellRenderer(cellright);
						}
						else{
							tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
						}
					}
					
					
				}
			}
		});
		
		JButton button_1 = new JButton("\uCDE8\uC18C");
		button_1.setBounds(267, 209, 161, 45);
		panel.add(button_1);
		button_1.setFont(new Font("굴림", Font.PLAIN, 20));
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = 0;
				result = JOptionPane.showConfirmDialog(null, "취소하시겠습니까?");
				
				if(result == 0){
					Product_Wearing_temp_db sb_db = new Product_Wearing_temp_db();	
					sb_db.PWT_delete(com_code);
					
					v = sb_db.PWT_select(pwt);
					cols=getColumn1();
					model.setDataVector(v, cols);
					
					price.setText("");
				}
			}
		});
		
		product_db.addMouseListener(new prod_wearing(com_code));
		prod_wearing_temp.addMouseListener(new product_wearing(com_code));
		Product_Wearing_temp_db pwtdb = new Product_Wearing_temp_db();
		
		price.setText(pwtdb.PWT_price(pwt));
	}
	public Vector getColumn(){
		Vector col = new Vector();
		col.add("품목번호");
		col.add("품목명");
		col.add("주문수량");
		col.add("품목가격");
		
		return col;
	}
	//장바구니 컬럼명 벡터
	public Vector getColumn1(){
		Vector col = new Vector();
		col.add("품목번호");
		col.add("품목명");
		col.add("품목가격");
		col.add("재고수량");
		col.add("이미지경로");
		
		return col;
	}
	private class prod_wearing extends MouseAdapter {
		String com_code1;
		Product_Wearing_temp_model pt = new Product_Wearing_temp_model();
		public prod_wearing(String com_code) {
			com_code1 = com_code;
			pt.setProd_wearing_company_id(com_code);
		}
	    @Override
	    public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 1) { 
		    	int row = product_db.getSelectedRow();
		    	ImageIcon ic1 = new ImageIcon((product_db.getValueAt(row, 4)).toString());
		    	Image img1 = ic1.getImage();
		    	Image img2 = img1.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH);
		    	ImageIcon ic2 = new ImageIcon(img2);
		    	
		    	product_pic.setIcon(ic2);
		    }
		    if (e.getClickCount() == 2) { 
		    	int row = product_db.getSelectedRow();
		    	Product_Wearing_temp_model PWT_model = new Product_Wearing_temp_model();
		    	Product_Wearing_temp_db PWT = new Product_Wearing_temp_db();
		    	PWT_model.setProd_id(product_db.getValueAt(row, 0).toString());
		    	PWT_model.setProd_wearing_company_id(com_code1);
		    	PWT.tempInsert(PWT_model);
					    
				v = PWT.PWT_select(pt);
				cols = getColumn();
				model.setDataVector(v, cols);
						
				tcmSchedule = prod_wearing_temp.getColumnModel();
				for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
					if(i == 3){
						tcmSchedule.getColumn(i).setCellRenderer(cellright);
					}
					else{
						tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
					}
				}
				Product_Wearing_temp_db pwtdb = new Product_Wearing_temp_db();
				
				price.setText(pwtdb.PWT_price(pt));
		    }
	    }
	}
	private class product_wearing extends MouseAdapter{
		String com_code1;
		Product_Wearing_temp_model pt = new Product_Wearing_temp_model();
		public product_wearing(String com_code) {
			com_code1 = com_code;
			pt.setProd_wearing_company_id(com_code);
		}
		@Override
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount() == 2){
				int row = prod_wearing_temp.getSelectedRow();
				
				String cnt =JOptionPane.showInputDialog("수량을 입력해주세요.");
				Product_Wearing_temp_db pdb = new Product_Wearing_temp_db();
				int check1=0;
				char temp;
				for(int i=0;i<cnt.length();i++){
					temp = cnt.charAt(i);
					if(temp >= 0x30 && temp <= 0x39) {
						check1=1;
					}
					else{
						check1=0;
						JOptionPane.showMessageDialog(null, "수량을 잘못입력하셨습니다.");
						break;
					}
				}
				
				if(check1==1){
					Product_Wearing_temp_model sbmodel = new Product_Wearing_temp_model();
					sbmodel.setProd_id(prod_wearing_temp.getValueAt(row, 0).toString());
					sbmodel.setProd_wearing_cnt(cnt);
					pdb.PWT_update(sbmodel);
						
					v = pdb.PWT_select(pt);
					cols = getColumn();
					model.setDataVector(v, cols);
							
					tcmSchedule = prod_wearing_temp.getColumnModel();
					for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
						if(i == 3){
							tcmSchedule.getColumn(i).setCellRenderer(cellright);
						}
						else{
							tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
						}
					}
				}
			}
		}
	}
}
