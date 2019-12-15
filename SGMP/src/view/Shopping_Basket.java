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
	Vector v,v1,cols,cols1;//���̺� ������,���̺� �÷�
	DefaultTableModel model,model1;//���̺� �� ����
	private JTable shopping_basket;//��ٱ��� ���̺�
	private JTable product;//��ǰ ��� ���̺�
	private JLabel prod_pic;//��ǰ ���� ���̺�
	private JComboBox prod_main_category;//�з� �޺��ڽ�
	private JTextField allPrice;//��ٱ��� ���� �ؽ�Ʈ�ʵ�
	private DefaultTableCellRenderer cellcenter = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer cellright = new DefaultTableCellRenderer();
	private TableColumnModel tcmSchedule;
	
	/**
	 * Create the panel.
	 */
	public Shopping_Basket() { 
		setSize(1600, 900);
		setLayout(null);
		//��ٱ��� ��� �ҷ�����
		ShoppingBasket_db sb_db = new ShoppingBasket_db();
		v1 = sb_db.SB_select();
		cols1=getColumn1();
		model1 = new DefaultTableModel(v1,cols1){
			//��������Ұ�
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		
		//��ٱ��� ���̺� ���� �� ������ ���
		shopping_basket = new JTable(model1);
		shopping_basket.setRowHeight(40);
		shopping_basket.setFont(new Font("����", Font.PLAIN, 20));
		shopping_basket.getTableHeader().setReorderingAllowed(false);
		shopping_basket.getTableHeader().setResizingAllowed(false);
		//��ٱ��� ���̺� ���� ����
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
		//��ٱ������̺��� ��ũ���гο� �־��ֱ�
		JScrollPane scrollPane = new JScrollPane(shopping_basket);
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setBounds(12, 428, 837, 408);
		add(scrollPane);
		//��ǰ��� �ҷ�����
		Product_db prod_db = new Product_db();
		v = prod_db.prod_select_name_1("");
		cols = getColumn();
		
		model = new DefaultTableModel(v,cols){
			//��������Ұ�
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		//��ǰ ���̺� ���� �� ������ ���
		product = new JTable(model);
		product.setRowHeight(40);
		product.setFont(new Font("����", Font.PLAIN, 20));
		product.getTableHeader().setReorderingAllowed(false);
		product.getTableHeader().setResizingAllowed(false);
		//��ǰ ���̺� ���� ����
		tcmSchedule = product.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			if(i == 2){
				tcmSchedule.getColumn(i).setCellRenderer(cellright);
			}
			else{
				tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
			}
		}
		//��ǰ ���̺� ��ũ���г��� �־��ֱ�
		JScrollPane scrollPane_1 = new JScrollPane(product);
		scrollPane_1.setBorder(new LineBorder(Color.BLACK));
		scrollPane_1.setBounds(861, 65, 713, 812);
		add(scrollPane_1);
		//��ǰ ���� ǥ�ø� ���� ���̺� ����
		prod_pic = new JLabel("");
		prod_pic.setHorizontalAlignment(SwingConstants.CENTER);
		prod_pic.setBorder(new LineBorder(new Color(0, 0, 0)));
		prod_pic.setBounds(507, 65, 342, 353);
		add(prod_pic);
		Product_db proddb = new Product_db();
		
		JLabel label_3 = new JLabel("\uCD1D\uAC00\uACA9");
		label_3.setFont(new Font("����", Font.PLAIN, 20));
		label_3.setBounds(529, 846, 121, 31);
		add(label_3);
		//��ٱ��� �� ���� ǥ�ø� ���� �ؽ�Ʈ �ʵ� ����
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
		label_2.setFont(new Font("����", Font.PLAIN, 20));
		//�з� �˻��� ���� �޺��ڽ� ����
		prod_main_category = new JComboBox();
		prod_main_category.setFont(new Font("����", Font.PLAIN, 20));
		prod_main_category.setBounds(233, 92, 187, 31);
		panel.add(prod_main_category);
		prod_main_category.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uD558\uC138\uC694"}));
		proddb.prod_main_category(prod_main_category);
		prod_main_category.setSelectedIndex(0);
		//���� ��ư
		JButton btnCommit = new JButton("\uACB0\uC81C");
		btnCommit.setBounds(61, 216, 161, 45);
		panel.add(btnCommit);
		btnCommit.setFont(new Font("����", Font.PLAIN, 20));
		//��ҹ�ư
		JButton btnDelete = new JButton("\uCDE8\uC18C");
		btnDelete.setBounds(259, 216, 161, 45);
		panel.add(btnDelete);
		btnDelete.setFont(new Font("����", Font.PLAIN, 20));
		//������� ��ư Ŭ���� ��ٱ��� ���� �̺�Ʈ
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = 0;
				result = JOptionPane.showConfirmDialog(null, "����Ͻðڽ��ϱ�?");
				
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
		//������ư Ŭ���� ������ �����ϴ� �̺�Ʈ
		btnCommit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result =0;
				result = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?");
				
				if(result == 0){
					String cus = "";
					cus = JOptionPane.showInputDialog("ȸ����ȣ�� �Է����ּ���.");
					if(!cus.equals("")){
						Customer_db cdb = new Customer_db();
						String check=cdb.cust_id(cus);
						if(!check.equals("")){
							//��ٱ��� ���� �κ�
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
							result1 = JOptionPane.showConfirmDialog(null, "��ȸ������ �����Ͻðڽ��ϱ�?");
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
						result1 = JOptionPane.showConfirmDialog(null, "��ȸ������ �����Ͻðڽ��ϱ�?");
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
		//�з� ���ý� �з��� ���� ��ǰ��� �˻�
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
		//��ǰ��Ͽ��� ��ǰ�� Ŭ���� ��ٱ��Ͽ� �߰��Ǵ� �̺�Ʈ
		product.addMouseListener(new prod_sale());
		setVisible(true);
		
		shopping_basket.addMouseListener(new shopping_basket());
		
		ShoppingBasket_db sb1 = new ShoppingBasket_db();
		allPrice.setText(sb1.sb_Allprice());
	}
	//��ǰ ��� �÷��� ����
	public Vector getColumn(){
		Vector col = new Vector();
		col.add("ǰ���ȣ");
		col.add("ǰ���");
		col.add("ǰ�񰡰�");
		col.add("������");
		col.add("�̹������");
		col.add("���񽺻���");
		
		return col;
	}
	//��ٱ��� �÷��� ����
	public Vector getColumn1(){
		Vector col = new Vector();
		col.add("ǰ���ȣ");
		col.add("ǰ���");
		col.add("ǰ�񰡰�");
		col.add("���ż���");
		
		return col;
	}
	private class shopping_basket extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount() == 2){
				int row = shopping_basket.getSelectedRow();
				
				String cnt =JOptionPane.showInputDialog("������ �Է����ּ���.");
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
						JOptionPane.showMessageDialog(null, "������ �߸��Է��ϼ̽��ϴ�.");
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
						JOptionPane.showMessageDialog(null, "������ �̻����� �ֹ��� �Ҽ� �����ϴ�.");
					}
				}
			}
		}
	}
	//��ٱ��� �߰� �̺�Ʈ
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
		    	if(product.getValueAt(row, 5).toString().equals("�Ǹ���")){
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
					    	JOptionPane.showMessageDialog(null, "���� ��� �����ϴ�.");
					    }
					    else{
						    if(!temp.equals("")){
						    	if(Integer.parseInt(temp) >= Integer.parseInt(product.getValueAt(row, 3).toString())){
						    		JOptionPane.showMessageDialog(null, "������ �̻����� �ֹ��Ҽ� �����ϴ�.");
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
		    		JOptionPane.showMessageDialog(null, "�Ǹ����� ��ǰ�� �ƴմϴ�.");
		    	}
		    }
	    }
	}
}