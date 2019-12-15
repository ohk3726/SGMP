package view;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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

import model.Product_model;
import database.Product_db;

import java.awt.event.KeyAdapter;

public class Product extends JPanel {
	Vector v,v1,cols,cols1;
	DefaultTableModel model,model1;
	
	private JTable product;
	private JTable product_Wearing;
	private JTextField prod_id;
	private JTextField prod_name;
	private JTextField prod_cnt;
	private JTextField prod_price;
	private JComboBox prod_main_category;
	private JComboBox prod_sub_category;
	private JTextField prod_Ssub_category;
	private JTextField prod_ex;
	private JTextField prod_date;
	private JComboBox prod_flag;
	private JLabel prod_pic;
	private DefaultTableCellRenderer cellcenter = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer cellright = new DefaultTableCellRenderer();
	private TableColumnModel tcmSchedule;
	private String comcode;
	private JFrame frame;
	/**
	 * Create the panel.
	 */
	public Product(String com_code) {
		comcode = com_code;
		setSize(1600, 900);
		setLayout(null);
		Product_db pro_db = new Product_db();
		cols=getColumn();
		v=pro_db.prod_select(0,com_code);
		model = new DefaultTableModel(v,cols){
			//내용수정불가
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		product = new JTable(model);
		product.setRowHeight(40);
		product.setFont(new Font("굴림", Font.PLAIN, 20));
		product.getTableHeader().setReorderingAllowed(false);
		product.getTableHeader().setResizingAllowed(false);
		
		cellcenter.setHorizontalAlignment(SwingConstants.CENTER);
		cellright.setHorizontalAlignment(SwingConstants.RIGHT);
		tcmSchedule = product.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			if(i == 4 || i==5){
				tcmSchedule.getColumn(i).setCellRenderer(cellright);
			}
			else{
				tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
			}
			
		}
		
		cols1=getColumn1();
		model1 = new DefaultTableModel(v1,cols1){
			//내용수정불가
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		
		JScrollPane scroll_product = new JScrollPane(product);
		scroll_product.setBorder(new LineBorder(Color.BLACK));
		scroll_product.setBounds(12, 449, 1137, 425);
		add(scroll_product);
		
		product_Wearing = new JTable(model1);
		product_Wearing.setRowHeight(40);
		product_Wearing.setFont(new Font("굴림", Font.PLAIN, 20));
		product_Wearing.getTableHeader().setReorderingAllowed(false);
		product_Wearing.getTableHeader().setResizingAllowed(false);
		
		tcmSchedule = product_Wearing.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
				tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
		}
		
		JScrollPane scroll_product_wearing = new JScrollPane(product_Wearing);
		scroll_product_wearing.setBorder(new LineBorder(Color.BLACK));
		scroll_product_wearing.setBounds(1161, 449, 411, 425);
		add(scroll_product_wearing);
		
		JPanel product_Explanation = new JPanel();
		product_Explanation.setBorder(new LineBorder(new Color(0, 0, 0)));
		product_Explanation.setBounds(12, 62, 1137, 377);
		add(product_Explanation);
		product_Explanation.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uD488\uBAA9\uBC88\uD638");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setBounds(61, 19, 121, 45);
		product_Explanation.add(lblNewLabel);
		
		prod_id = new JTextField();
		prod_id.setFont(new Font("굴림", Font.PLAIN, 20));
		prod_id.setEditable(false);
		prod_id.setBounds(194, 19, 277, 45);
		product_Explanation.add(prod_id);
		prod_id.setColumns(10);
		
		JLabel label = new JLabel("\uD488\uBAA9\uBA85");
		label.setFont(new Font("굴림", Font.PLAIN, 20));
		label.setBounds(61, 74, 121, 45);
		product_Explanation.add(label);
		
		prod_name = new JTextField();
		prod_name.setFont(new Font("굴림", Font.PLAIN, 20));
		prod_name.setColumns(10);
		prod_name.setBounds(194, 74, 277, 45);
		product_Explanation.add(prod_name);
		
		JLabel label_1 = new JLabel("\uC7AC\uACE0\uC218\uB7C9");
		label_1.setFont(new Font("굴림", Font.PLAIN, 20));
		label_1.setBounds(61, 136, 121, 45);
		product_Explanation.add(label_1);
		
		prod_cnt = new JTextField();
		prod_cnt.setFont(new Font("굴림", Font.PLAIN, 20));
		prod_cnt.setColumns(10);
		prod_cnt.setBounds(194, 136, 277, 45);
		product_Explanation.add(prod_cnt);
		
		JLabel label_2 = new JLabel("\uD310\uB9E4\uAC00\uACA9");
		label_2.setFont(new Font("굴림", Font.PLAIN, 20));
		label_2.setBounds(61, 246, 121, 45);
		product_Explanation.add(label_2);
		
		prod_price = new JTextField();
		prod_price.setFont(new Font("굴림", Font.PLAIN, 20));
		
		prod_price.setColumns(10);
		prod_price.setBounds(194, 246, 277, 45);
		product_Explanation.add(prod_price);
		prod_price.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DecimalFormat dc = new DecimalFormat("###,###,###,###");
				String ch = dc.format(prod_price.getText());
				prod_price.setText(ch);
			}
		});
		
		JLabel label_4 = new JLabel("\uB300\uBD84\uB958");
		label_4.setFont(new Font("굴림", Font.PLAIN, 20));
		label_4.setBounds(512, 19, 102, 45);
		product_Explanation.add(label_4);
		Product_db proddb = new Product_db();
		
		JLabel label_5 = new JLabel("\uC911\uBD84\uB958");
		label_5.setFont(new Font("굴림", Font.PLAIN, 20));
		label_5.setBounds(512, 74, 102, 45);
		product_Explanation.add(label_5);
		
		JLabel label_6 = new JLabel("\uC18C\uBD84\uB958");
		label_6.setFont(new Font("굴림", Font.PLAIN, 20));
		label_6.setBounds(512, 136, 102, 45);
		product_Explanation.add(label_6);
		
		prod_Ssub_category = new JTextField();
		prod_Ssub_category.setFont(new Font("굴림", Font.PLAIN, 20));
		prod_Ssub_category.setColumns(10);
		prod_Ssub_category.setBounds(626, 136, 277, 45);
		product_Explanation.add(prod_Ssub_category);
		
		JLabel label_8 = new JLabel("\uCD5C\uADFC\uC218\uC815\uC77C");
		label_8.setFont(new Font("굴림", Font.PLAIN, 20));
		label_8.setBounds(512, 301, 102, 45);
		product_Explanation.add(label_8);
		
		prod_date = new JTextField();
		prod_date.setFont(new Font("굴림", Font.PLAIN, 20));
		prod_date.setEditable(false);
		prod_date.setColumns(10);
		prod_date.setBounds(626, 301, 277, 45);
		product_Explanation.add(prod_date);
		
		JButton btnDelete = new JButton("\uC0AD     \uC81C");
		btnDelete.setFont(new Font("굴림", Font.PLAIN, 20));
		btnDelete.setBounds(966, 191, 161, 45);
		product_Explanation.add(btnDelete);
		
		JButton btnUpdate = new JButton("\uC218      \uC815");
		btnUpdate.setFont(new Font("굴림", Font.PLAIN, 20));
		btnUpdate.setBounds(966, 74, 161, 45);
		product_Explanation.add(btnUpdate);
		
		JLabel label_3 = new JLabel("\uC11C\uBE44\uC2A4\uC0C1\uD0DC");
		label_3.setFont(new Font("굴림", Font.PLAIN, 20));
		label_3.setBounds(61, 301, 121, 45);
		product_Explanation.add(label_3);
		
		prod_flag = new JComboBox();
		prod_flag.setFont(new Font("굴림", Font.PLAIN, 20));
		prod_flag.setBounds(194, 301, 277, 45);
		product_Explanation.add(prod_flag);
		prod_flag.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uD558\uC138\uC694", "\uD310\uB9E4\uC911", "\uD310\uB9E4\uC911\uC9C0"}));
		prod_flag.setSelectedIndex(0);
		
		prod_main_category = new JComboBox();
		prod_main_category.setFont(new Font("굴림", Font.PLAIN, 20));
		prod_main_category.setBounds(626, 19, 277, 45);
		product_Explanation.add(prod_main_category);
		prod_main_category.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uD558\uC138\uC694"}));
		Product_db proddb1 = new Product_db();
		proddb1.prod_main_category(prod_main_category);
		prod_main_category.setSelectedIndex(0);
		
		prod_sub_category = new JComboBox();
		prod_sub_category.setFont(new Font("굴림", Font.PLAIN, 20));
		prod_sub_category.setBounds(626, 74, 277, 45);
		product_Explanation.add(prod_sub_category);
		prod_sub_category.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uD558\uC138\uC694"}));
		prod_sub_category.setSelectedIndex(0);
		
		JLabel label_7 = new JLabel("\uD488\uBAA9\uC774\uBBF8\uC9C0");
		label_7.setFont(new Font("굴림", Font.PLAIN, 20));
		label_7.setBounds(512, 191, 102, 45);
		product_Explanation.add(label_7);
		
		prod_pic_address = new JTextField();
		prod_pic_address.setFont(new Font("굴림", Font.PLAIN, 20));
		prod_pic_address.setEditable(false);
		prod_pic_address.setColumns(10);
		prod_pic_address.setBounds(626, 191, 187, 45);
		product_Explanation.add(prod_pic_address);
		
		JButton btnPicadd = new JButton("\uCD94\uAC00");
		btnPicadd.setFont(new Font("굴림", Font.PLAIN, 20));
		btnPicadd.setBounds(811, 191, 92, 45);
		product_Explanation.add(btnPicadd);
		
		JLabel label_9 = new JLabel("\uC785\uACE0\uAC00\uACA9");
		label_9.setFont(new Font("굴림", Font.PLAIN, 20));
		label_9.setBounds(512, 246, 102, 45);
		product_Explanation.add(label_9);
		
		prod_wearing_price = new JTextField();
		prod_wearing_price.setFont(new Font("굴림", Font.PLAIN, 20));
		prod_wearing_price.setColumns(10);
		prod_wearing_price.setBounds(626, 246, 277, 45);
		product_Explanation.add(prod_wearing_price);
		
		JLabel label_10 = new JLabel("\uCD5C\uC18C\uC7AC\uACE0\uC218\uB7C9");
		label_10.setFont(new Font("굴림", Font.PLAIN, 20));
		label_10.setBounds(61, 191, 121, 45);
		product_Explanation.add(label_10);
		
		prod_min_cnt = new JTextField();
		prod_min_cnt.setFont(new Font("굴림", Font.PLAIN, 20));
		prod_min_cnt.setColumns(10);
		prod_min_cnt.setBounds(194, 191, 277, 45);
		product_Explanation.add(prod_min_cnt);
		
		prod_main_category.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				prod_sub_category.removeAllItems();
				prod_sub_category.addItem("선택하세요.");
				proddb.prod_sub_category(prod_sub_category,prod_main_category.getSelectedItem().toString());
			}
		});
		
		prod_pic = new JLabel("");
		prod_pic.setBorder(new LineBorder(new Color(0, 0, 0)));
		prod_pic.setHorizontalAlignment(SwingConstants.CENTER);
		prod_pic.setBounds(1161, 62, 411, 377);
		add(prod_pic);
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = 0;
				if(prod_id.getText().equals("")){
					result = 1;
					JOptionPane.showMessageDialog(null, "선택된 품목이 없습니다.");
				}
				else{
					result = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?");
				}
				
				
				if(result == 0){
					Product_model pro_model = new Product_model();
					pro_model.setProd_id(prod_id.getText());	
					pro_db.prod_delete(pro_model);
					
					Product_db pro_db = new Product_db();
					v = pro_db.prod_select(0,com_code);
					
					model.setDataVector(v, cols);
					
					TableColumnModel tcmSchedule = product.getColumnModel();
					for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
						if(i == 4 || i==5){
							tcmSchedule.getColumn(i).setCellRenderer(cellright);
						}
						else{
							tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
						}
						
					}
					JOptionPane.showMessageDialog(null, "선택된 항목이 삭제 되었습니다.");
					seting();
				}
			}
		});
		
		
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = 0;
				result = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?");
				if(prod_id.getText().equals("")){
					JOptionPane.showMessageDialog(null, "선택된 품목이 없습니다.");
					result=1;
				}
				else{
					if(prod_flag.getSelectedIndex()==0){
						JOptionPane.showMessageDialog(null, "서비스 상태를 다시 선택하세요.");
						result = 1;	
					}
					else{
						if(prod_main_category.getSelectedIndex()==0){
							JOptionPane.showMessageDialog(null, "대분류를 다시 선택하세요.");
							result = 1;	
						}
						else{
							if(prod_sub_category.getSelectedIndex()==0){
								JOptionPane.showMessageDialog(null, "중분류를 다시 선택하세요.");
								result = 1;
							}
						}
					}
				}
				int check1=0,check2=0,check3=0,check4=0;
				char temp;
				for(int i=0;i<prod_price.getText().replace(",", "").length();i++){
					temp = prod_price.getText().replace(",", "").charAt(i);
					if(temp >= 0x30 && temp <= 0x39) {
						check1=1;
					}
					else{
						check1=0;
						JOptionPane.showMessageDialog(null, "판매가격을 잘못 입력하셨습니다.");
						break;
					}
				}
				for(int i=0;i<prod_wearing_price.getText().replace(",", "").length();i++){
					temp = prod_wearing_price.getText().replace(",", "").charAt(i);
					if(temp >= 0x30 && temp <= 0x39) {
						check2=1;
					}
					else{
						check2=0;
						JOptionPane.showMessageDialog(null, "입고가격을 잘못 입력하셨습니다.");
						break;
					}
				}
				for(int i=0;i<prod_cnt.getText().replace(",", "").length();i++){
					temp = prod_cnt.getText().replace(",", "").charAt(i);
					if(temp >= 0x30 && temp <= 0x39) {
						check3=1;
					}
					else{
						check3=0;
						JOptionPane.showMessageDialog(null, "재고수량을 잘못 입력하셨습니다.");
						break;
					}
				}
				for(int i=0;i<prod_min_cnt.getText().replace(",", "").length();i++){
					temp = prod_min_cnt.getText().replace(",", "").charAt(i);
					if(temp >= 0x30 && temp <= 0x39) {
						check4=1;
					}
					else{
						check4=0;
						JOptionPane.showMessageDialog(null, "최소재고수량을 잘못 입력하셨습니다.");
						break;
					}
				}
				if(check1 == 1 && check2 == 1 && check3 == 1 && check4 == 1){
					if(result == 0){
						String imagePath = prod_pic_address.getText();
						String imagePath1 = "C:\\MakeProgram\\SGMP\\img\\"+imagePath.substring(imagePath.lastIndexOf("\\")+1);
						
						Product_model pro_model = new Product_model();
						pro_model.setProd_id(prod_id.getText());
						pro_model.setProd_name(prod_name.getText());
						pro_model.setProd_cnt(prod_cnt.getText());
						pro_model.setProd_price(prod_price.getText().replace(",", ""));
						pro_model.setProd_cnt_min(prod_min_cnt.getText());
						pro_model.setProd_wearing_price(prod_wearing_price.getText().replace(",", ""));
						pro_model.setProd_main_category(prod_main_category.getSelectedItem().toString());
						pro_model.setProd_sub_category(prod_sub_category.getSelectedItem().toString());
						pro_model.setProd_Ssub_category(prod_Ssub_category.getText());
						pro_model.setProd_flag(prod_flag.getSelectedItem().toString());
						pro_model.setProd_pic(imagePath1);
						
						int result1 = pro_db.prod_update(pro_model);
						
						if(result1 == 1){
							String fileNm = imagePath;
					    	File file = new File(fileNm);
					    	File newFile = new File(imagePath1);
					    	
					    	if(file.exists()){
					    		file.renameTo(newFile);
					    	}
							
							Product_db pro_db = new Product_db();
							v = pro_db.prod_select(0,com_code);
							
							model.setDataVector(v, cols);
							
							TableColumnModel tcmSchedule = product.getColumnModel();
							for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
								if(i == 4 || i==5){
									tcmSchedule.getColumn(i).setCellRenderer(cellright);
								}
								else{
									tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
								}
								
							}
							JOptionPane.showMessageDialog(null, "수정되었습니다.");
							seting();
						}
					}
				}	
			}
		});
		FileDialog dlg = new FileDialog(frame,"저장",FileDialog.LOAD);
		btnPicadd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dlg.setSize(300,200);
				dlg.show();
				
				prod_pic_address.setText(dlg.getDirectory() + dlg.getFile());
				String imagePath = prod_pic_address.getText();
				ImageIcon ic1 = new ImageIcon(imagePath);
		    	Image img1 = ic1.getImage();
		    	Image img2 = img1.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH);
		    	ImageIcon ic2 = new ImageIcon(img2);
		    	
		    	prod_pic.setIcon(ic2);
			}
		});
		product.addMouseListener(new MyMouseListener());
		
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false);
		prod_name.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "ENTER");
		prod_name.getActionMap().put("ENTER", ok);
		
	}
	private String settext(Object temp){
		String result = "";
		if(temp != null){
			result = temp.toString();
		}
		else{
			result = "";
		}
		
		return result;
	}
	private class MyMouseListener extends MouseAdapter {
		 
	    @Override
	    public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 2) { 
		    	int row = product.getSelectedRow();
		    	prod_id.setText(settext(product.getValueAt(row, 0)));
		    	prod_name.setText(settext(product.getValueAt(row, 1)));
		    	prod_cnt.setText(settext(product.getValueAt(row, 2)));
		    	prod_min_cnt.setText(settext(product.getValueAt(row, 3)));
		    	prod_price.setText(settext(product.getValueAt(row, 4)));
		    	prod_wearing_price.setText(settext(product.getValueAt(row, 5)));
		    	
		    	ImageIcon ic1 = new ImageIcon(settext(product.getValueAt(row, 6)));
		    	Image img1 = ic1.getImage();
		    	Image img2 = img1.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH);
		    	ImageIcon ic2 = new ImageIcon(img2);
		    	
		    	prod_pic.setIcon(ic2);
		    	prod_main_category.setSelectedItem(settext(product.getValueAt(row, 7)));
		    	prod_sub_category.setSelectedItem(settext(product.getValueAt(row, 8)));
		    	prod_Ssub_category.setText(settext(product.getValueAt(row, 9)));
		    	prod_date.setText(settext(product.getValueAt(row, 10)));
		    	prod_flag.setSelectedItem(settext(product.getValueAt(row, 11)));	
		    	
		    	prod_pic_address.setText(product.getValueAt(row, 6).toString());
		    	
		    	Product_db pro_db = new Product_db();
				v1 = pro_db.prod_select(product.getValueAt(row, 0).toString());
				
				model1.setDataVector(v1, cols1);
		    }
	    }
	}

	public Vector getColumn(){
		Vector col = new Vector();
		col.add("품목번호");
		col.add("품목명");
		col.add("재고수량");
		col.add("최소재고수량");
		col.add("판매가격");
		col.add("입고가격");
		col.add("이미지경로");
		col.add("대분류");
		col.add("중분류");
		col.add("소분류");
		col.add("등록일");
		col.add("서비스상태");
		
		return col;
	}
	
	public Vector getColumn1(){
		Vector col = new Vector();
		col.add("주문일");
		col.add("수량");
		col.add("입고상태");
		
		return col;
	}
	
	Action ok = new AbstractAction(){
		@Override
		public void actionPerformed(ActionEvent arg0){
			Product_db prod_db = new Product_db();
			v = prod_db.prod_select_name(prod_name.getText());
			cols=getColumn();
			model.setDataVector(v, cols);
			
			TableColumnModel tcmSchedule = product.getColumnModel();
			for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
				if(i == 4 || i==5){
					tcmSchedule.getColumn(i).setCellRenderer(cellright);
				}
				else{
					tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
				}
				
			}
			
			seting();
			
		}
	};
	
	public void seting(){
		prod_id.setText("");
		prod_name.setText("");
		prod_cnt.setText("");
		prod_date.setText("");
		prod_main_category.setSelectedIndex(0);;
		prod_price.setText("");
		prod_Ssub_category.setText("");
		prod_sub_category.setSelectedIndex(0);
		prod_flag.setSelectedIndex(0);
		prod_pic_address.setText("");
		prod_min_cnt.setText("");
		prod_wearing_price.setText("");;
		prod_pic.setIcon(new ImageIcon());
	}
	private JTextField prod_pic_address;
	private JTextField prod_wearing_price;
	private JTextField prod_min_cnt;
}
