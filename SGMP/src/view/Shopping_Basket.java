package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import model.Shopping_basket_model;
import database.Customer_db;
import database.Product_db;
import database.Sale_db;
import database.ShoppingBasket_db;



public class Shopping_Basket extends JPanel {
	Vector v,v1,cols,cols1;//테이블 데이터,테이블 컬럼
	DefaultTableModel model,model1;//테이블 모델 선언
	private JTable shopping_basket;//장바구니 테이블
	private JTable product;//제품 목록 테이블
	private JLabel prod_pic;//제품 사진 레이블
	private JComboBox prod_main_category;//분류 콤보박스
	private JTextField allPrice;//장바구니 가격 텍스트필드
	private DefaultTableCellRenderer cellcenter = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer cellright = new DefaultTableCellRenderer();
	private TableColumnModel tcmSchedule;
	
	/**
	 * Create the panel.
	 */
	public Shopping_Basket() { 
		setSize(1600, 900);
		setLayout(null);
		//장바구니 목록 불러오기
		ShoppingBasket_db sb_db = new ShoppingBasket_db();
		v1 = sb_db.SB_select();
		cols1=getColumn1();
		model1 = new DefaultTableModel(v1,cols1){
			//내용수정불가
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		
		//장바구니 테이블 선언 및 데이터 출력
		shopping_basket = new JTable(model1);
		shopping_basket.setRowHeight(40);
		shopping_basket.setFont(new Font("굴림", Font.PLAIN, 20));
		shopping_basket.getTableHeader().setReorderingAllowed(false);
		shopping_basket.getTableHeader().setResizingAllowed(false);
		//장바구니 테이블 글자 정렬
		cellcenter.setHorizontalAlignment(SwingConstants.CENTER);
		cellright.setHorizontalAlignment(SwingConstants.RIGHT);
		tcmSchedule = shopping_basket.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			if(i == 2){
				tcmSchedule.getColumn(i).setCellRenderer(cellright);
			}
			else{
				tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
			}
		}
		//장바구니테이블을 스크롤패널에 넣어주기
		JScrollPane scrollPane = new JScrollPane(shopping_basket);
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setBounds(12, 428, 837, 408);
		add(scrollPane);
		//제품목록 불러오기
		Product_db prod_db = new Product_db();
		v = prod_db.prod_select_name_1("");
		cols = getColumn();
		
		model = new DefaultTableModel(v,cols){
			//내용수정불가
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		//제품 테이블 선언 및 데이터 출력
		product = new JTable(model);
		product.setRowHeight(40);
		product.setFont(new Font("굴림", Font.PLAIN, 20));
		product.getTableHeader().setReorderingAllowed(false);
		product.getTableHeader().setResizingAllowed(false);
		//제품 테이블 글자 정렬
		tcmSchedule = product.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			if(i == 2){
				tcmSchedule.getColumn(i).setCellRenderer(cellright);
			}
			else{
				tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
			}
		}
		//제품 테이블 스크롤패널의 넣어주기
		JScrollPane scrollPane_1 = new JScrollPane(product);
		scrollPane_1.setBorder(new LineBorder(Color.BLACK));
		scrollPane_1.setBounds(861, 65, 713, 812);
		add(scrollPane_1);
		//제품 사진 표시를 위한 레이블 설정
		prod_pic = new JLabel("");
		prod_pic.setHorizontalAlignment(SwingConstants.CENTER);
		prod_pic.setBorder(new LineBorder(new Color(0, 0, 0)));
		prod_pic.setBounds(507, 65, 342, 353);
		add(prod_pic);
		Product_db proddb = new Product_db();
		
		JLabel label_3 = new JLabel("\uCD1D\uAC00\uACA9");
		label_3.setFont(new Font("굴림", Font.PLAIN, 20));
		label_3.setBounds(529, 846, 121, 31);
		add(label_3);
		//장바구니 총 가격 표시를 위한 텍스트 필드 설정
		allPrice = new JTextField();
		allPrice.setEnabled(false);
		allPrice.setColumns(10);
		allPrice.setBounds(662, 846, 187, 31);
		add(allPrice);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 65, 486, 353);
		add(panel);
		panel.setLayout(null);
		
		JLabel label_2 = new JLabel("\uB300\uBD84\uB958");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(61, 92, 121, 31);
		panel.add(label_2);
		label_2.setFont(new Font("굴림", Font.PLAIN, 20));
		//분류 검색을 위한 콤보박스 설정
		prod_main_category = new JComboBox();
		prod_main_category.setFont(new Font("굴림", Font.PLAIN, 20));
		prod_main_category.setBounds(233, 92, 187, 31);
		panel.add(prod_main_category);
		prod_main_category.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uD558\uC138\uC694"}));
		proddb.prod_main_category(prod_main_category);
		prod_main_category.setSelectedIndex(0);
		//결제 버튼
		JButton btnCommit = new JButton("\uACB0\uC81C");
		btnCommit.setBounds(61, 216, 161, 45);
		panel.add(btnCommit);
		btnCommit.setFont(new Font("굴림", Font.PLAIN, 20));
		//취소버튼
		JButton btnDelete = new JButton("\uCDE8\uC18C");
		btnDelete.setBounds(259, 216, 161, 45);
		panel.add(btnDelete);
		btnDelete.setFont(new Font("굴림", Font.PLAIN, 20));
		//결제취소 버튼 클릭시 장바구니 삭제 이벤트
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = 0;
				result = JOptionPane.showConfirmDialog(null, "취소하시겠습니까?");
				
				if(result == 0){
					ShoppingBasket_db sb_db = new ShoppingBasket_db();	
					sb_db.SB_delete();
					
					v1 = sb_db.SB_select();
					cols1=getColumn1();
					model1.setDataVector(v1, cols1);
					
					allPrice.setText("");
				}
			}
		});
		//결제버튼 클릭시 결제를 진행하는 이벤트
		btnCommit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result =0;
				result = JOptionPane.showConfirmDialog(null, "결제하시겠습니까?");
				
				if(result == 0){
					String cus = "";
					cus = JOptionPane.showInputDialog("회원번호를 입력해주세요.");
					if(!cus.equals("")){
						Customer_db cdb = new Customer_db();
						String check=cdb.cust_id(cus);
						if(!check.equals("")){
							//장바구니 결제 부분
							ShoppingBasket_db sb = new ShoppingBasket_db();
							sb.SB_TO_SALE_INSERT(cus);
							v1 = sb.SB_select();
					    	model1.setDataVector(v1,cols1);
					    	
					    	Product_db prod_db = new Product_db();
							v = prod_db.prod_select_name_1("");
							cols = getColumn();
							model.setDataVector(v,cols);
							tcmSchedule = product.getColumnModel();
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
							int result1 = 0;
							result1 = JOptionPane.showConfirmDialog(null, "비회원으로 구매하시겠습니까?");
							if(result1 == 0){
								ShoppingBasket_db sb = new ShoppingBasket_db();
								sb.SB_TO_SALE_INSERT("9999");
								v1 = sb.SB_select();
						    	model1.setDataVector(v1,cols1);
						    	
						    	Product_db prod_db = new Product_db();
								v = prod_db.prod_select_name_1("");
								cols = getColumn();
								model.setDataVector(v,cols);
								tcmSchedule = product.getColumnModel();
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
						
					}
					else{
						int result1 = 0;
						result1 = JOptionPane.showConfirmDialog(null, "비회원으로 구매하시겠습니까?");
						if(result1 == 0){
							ShoppingBasket_db sb = new ShoppingBasket_db();
							sb.SB_TO_SALE_INSERT("9999");
							v1 = sb.SB_select();
					    	model1.setDataVector(v1,cols1);
					    	
					    	Product_db prod_db = new Product_db();
							v = prod_db.prod_select_name_1("");
							cols = getColumn();
							model.setDataVector(v,cols);
							tcmSchedule = product.getColumnModel();
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
				}
			}
		});
		//분류 선택시 분류에 대한 제품목록 검색
		prod_main_category.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox cb = (JComboBox) e.getSource();
				String category = cb.getSelectedItem().toString();
				if(cb.getSelectedIndex()==0){
					Product_db prod_db = new Product_db();
					v = prod_db.prod_select_name_1("");
					cols = getColumn();
					model.setDataVector(v,cols);
					
					tcmSchedule = product.getColumnModel();
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
					v = prod_db.prod_select_category(category);
					cols = getColumn();
					model.setDataVector(v,cols);
					
					tcmSchedule = product.getColumnModel();
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
		//제품목록에서 제품을 클릭시 장바구니에 추가되는 이벤트
		product.addMouseListener(new prod_sale());
		setVisible(true);
		
		shopping_basket.addMouseListener(new shopping_basket());
		
		ShoppingBasket_db sb1 = new ShoppingBasket_db();
		allPrice.setText(sb1.sb_Allprice());
	}
	//제품 목록 컬럼명 벡터
	public Vector getColumn(){
		Vector col = new Vector();
		col.add("품목번호");
		col.add("품목명");
		col.add("품목가격");
		col.add("재고수량");
		col.add("이미지경로");
		col.add("서비스상태");
		
		return col;
	}
	//장바구니 컬럼명 벡터
	public Vector getColumn1(){
		Vector col = new Vector();
		col.add("품목번호");
		col.add("품목명");
		col.add("품목가격");
		col.add("구매수량");
		
		return col;
	}
	private class shopping_basket extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount() == 2){
				int row = shopping_basket.getSelectedRow();
				
				String cnt =JOptionPane.showInputDialog("수량을 입력해주세요.");
				Product_db pdb = new Product_db();
				String prod_cnt = pdb.prod_select_1(shopping_basket.getValueAt(row, 0).toString());
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
					if(Integer.parseInt(cnt) <= Integer.parseInt(prod_cnt)){
						ShoppingBasket_db sbdb = new ShoppingBasket_db();
						Shopping_basket_model sbmodel = new Shopping_basket_model();
						sbmodel.setProd_id(shopping_basket.getValueAt(row, 0).toString());
						sbmodel.setSb_cnt(cnt);
						sbdb.basketupdate(sbmodel);
						
						ShoppingBasket_db sb = new ShoppingBasket_db();
						v1 = sb.SB_select();
				    	model1.setDataVector(v1,cols1);
						
						tcmSchedule = shopping_basket.getColumnModel();
						for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
							if(i == 2){
								tcmSchedule.getColumn(i).setCellRenderer(cellright);
							}
							else{
								tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
							}
						}
						ShoppingBasket_db sb1 = new ShoppingBasket_db();
						allPrice.setText(sb1.sb_Allprice());
						
					}
					else{
						JOptionPane.showMessageDialog(null, "재고수량 이상으로 주문을 할수 없습니다.");
					}
				}
			}
		}
	}
	//장바구니 추가 이벤트
	private class prod_sale extends MouseAdapter {
		 
	    @Override
	    public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 1) { 
		    	int row = product.getSelectedRow();
		    	ImageIcon ic1 = new ImageIcon((product.getValueAt(row, 4)).toString());
		    	Image img1 = ic1.getImage();
		    	Image img2 = img1.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH);
		    	ImageIcon ic2 = new ImageIcon(img2);
		    	
		    	prod_pic.setIcon(ic2);
		    }
		    if (e.getClickCount() == 2) { 
		    	int row = product.getSelectedRow();
		    	if(product.getValueAt(row, 5).toString().equals("판매중")){
		    		int result = 0;
		    		if(result == 0){
		    			Shopping_basket_model sale_model = new Shopping_basket_model();
		    			ShoppingBasket_db sb = new ShoppingBasket_db();
		    			Sale_db sdb = new Sale_db();
					    sale_model.setProd_id(product.getValueAt(row, 0).toString());
					    sale_model.setProd_name(product.getValueAt(row, 1).toString());
					    sale_model.setProd_Price(product.getValueAt(row, 2).toString().replace(",", ""));
					    
					    String temp = sdb.sb_cnt(sale_model);
					    if(product.getValueAt(row, 3).toString().equals("0")){
					    	JOptionPane.showMessageDialog(null, "현재 재고가 없습니다.");
					    }
					    else{
						    if(!temp.equals("")){
						    	if(Integer.parseInt(temp) >= Integer.parseInt(product.getValueAt(row, 3).toString())){
						    		JOptionPane.showMessageDialog(null, "재고수량 이상으로 주문할수 없습니다.");
							    }
						    	else{
						    		sb.basketInsert(sale_model);
						    	}
						    }
						    else{
						    	sb.basketInsert(sale_model);
						    }
						    
					    	v1 = sb.SB_select();
					    	cols1=getColumn1();
					    	model1.setDataVector(v1,cols1);
					    	allPrice.setText(sb.sb_Allprice());
					    	
					    	tcmSchedule = shopping_basket.getColumnModel();
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
		    		
		    	}
		    	else{
		    		JOptionPane.showMessageDialog(null, "판매중인 상품이 아닙니다.");
		    	}
		    }
	    }
	}
}