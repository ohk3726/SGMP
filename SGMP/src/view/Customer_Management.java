package view;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Image;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import database.Customer_db;
import database.Product_db;

import javax.swing.border.LineBorder;

import model.Customer_model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Customer_Management extends JPanel {
	Vector v,cols;
	DefaultTableModel model;
	private JTextField selecttext;
	private JTable customer;
	private DefaultTableCellRenderer cellcenter = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer cellright = new DefaultTableCellRenderer();
	private TableColumnModel tcmSchedule;
	private JTextField cust_id;
	private JTextField cust_name;
	private JTextField cust_address;

	/**
	 * Create the panel.
	 */
	public Customer_Management() {
		setSize(1600, 900);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 84, 774, 788);
		add(scrollPane);
		
		Customer_db cust_db = new Customer_db();
		cols=getColumn();
		v=cust_db.cust_select();
		model = new DefaultTableModel(v,cols){
			//내용수정불가
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		
		customer = new JTable(model);
		customer.setRowHeight(40);
		customer.setFont(new Font("굴림", Font.PLAIN, 20));
		customer.getTableHeader().setReorderingAllowed(false);
		customer.getTableHeader().setResizingAllowed(false);
		customer.addMouseListener(new MyMouseListener());
		
		scrollPane.setViewportView(customer);
		
		cellcenter.setHorizontalAlignment(SwingConstants.CENTER);
		cellright.setHorizontalAlignment(SwingConstants.RIGHT);
		TableColumnModel tcmSchedule = customer.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
				tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
		}
		
		selecttext = new JTextField();
		selecttext.setFont(new Font("굴림", Font.PLAIN, 20));
		selecttext.setBounds(171, 44, 484, 30);
		add(selecttext);
		selecttext.setColumns(10);
		
		JComboBox selectcomboBox = new JComboBox();
		selectcomboBox.setFont(new Font("굴림", Font.PLAIN, 20));
		selectcomboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uC774\uB984", "\uD734\uB300\uD3F0\uBC88\uD638"}));
		selectcomboBox.setBounds(12, 44, 147, 30);
		add(selectcomboBox);
		
		JButton btnSelect = new JButton("\uAC80   \uC0C9");
		btnSelect.setFont(new Font("굴림", Font.PLAIN, 20));
		btnSelect.setBounds(667, 44, 119, 30);
		add(btnSelect);
		btnSelect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Customer_db cust_db1 = new Customer_db();
				cols=getColumn();
				v=cust_db1.cust_select(selecttext.getText(),selectcomboBox.getSelectedItem().toString());
				model.setDataVector(v, cols);
				
				TableColumnModel tcmSchedule = customer.getColumnModel();
				for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
						tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
				}
			}
		});
		
			
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(896, 214, 575, 432);
		add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\uD734\uB300\uD3F0\uBC88\uD638");
		label.setBounds(48, 129, 129, 30);
		panel.add(label);
		label.setFont(new Font("굴림", Font.PLAIN, 20));
		
		JLabel label_1 = new JLabel("\uC8FC\uC18C");
		label_1.setBounds(48, 267, 129, 30);
		panel.add(label_1);
		label_1.setFont(new Font("굴림", Font.PLAIN, 20));
		
		cust_address = new JTextField();
		cust_address.setBounds(215, 267, 328, 30);
		panel.add(cust_address);
		cust_address.setFont(new Font("굴림", Font.PLAIN, 20));
		cust_address.setColumns(10);
		
		cust_name = new JTextField();
		cust_name.setBounds(215, 194, 328, 30);
		panel.add(cust_name);
		cust_name.setFont(new Font("굴림", Font.PLAIN, 20));
		cust_name.setColumns(10);
		
		cust_id = new JTextField();
		cust_id.setBounds(215, 129, 328, 30);
		panel.add(cust_id);
		cust_id.setFont(new Font("굴림", Font.PLAIN, 20));
		cust_id.setColumns(10);
		
		JButton btnInserOrUpdate = new JButton("\uB4F1\uB85D / \uC218\uC815");
		btnInserOrUpdate.setBounds(396, 366, 147, 38);
		panel.add(btnInserOrUpdate);
		btnInserOrUpdate.setFont(new Font("굴림", Font.PLAIN, 20));
		btnInserOrUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Customer_model CM = new Customer_model();
				CM.setCust_id(cust_id.getText());
				CM.setCust_name(cust_name.getText());
				CM.setCust_address(cust_address.getText());
				
				if(!cust_id.getText().equals("")){
					if(!cust_name.getText().equals("")){
						if(!cust_address.getText().equals("")){
							int check1=0;
							char temp;
							for(int i=0;i<cust_id.getText().length();i++){
								temp = cust_id.getText().charAt(i);
								if(temp >= 0x30 && temp <= 0x39) {
									check1=1;
								}
								else{
									check1=0;
									JOptionPane.showMessageDialog(null, "휴대폰 번호는 숫자만 입력가능합니다.");
									break;
								}
							}
							if(check1==1){
								Customer_db cust_db = new Customer_db();
								cust_db.customer_insert_or_update(CM);
								
								JOptionPane.showMessageDialog(null, "완료되었습니다.");
								
								v=cust_db.cust_select();
								model.setDataVector(v, cols);
								
								TableColumnModel tcmSchedule = customer.getColumnModel();
								for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
										tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
								}
								
								cust_id.setText("");
								cust_name.setText("");
								cust_address.setText("");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "주소를 입력하지 않았습니다.");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "이름을 입력하지 않았습니다.");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "휴대폰번호를 입력하지 않았습니다.");
				}
				
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("\uACE0\uAC1D \uB4F1\uB85D \uBC0F \uC218\uC815");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(48, 10, 495, 50);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("\uACE0\uAC1D\uBA85");
		lblNewLabel.setBounds(48, 194, 129, 30);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		
		JButton btnReset = new JButton("\uCD08\uAE30\uD654");
		btnReset.setFont(new Font("굴림", Font.PLAIN, 20));
		btnReset.setBounds(48, 366, 147, 38);
		panel.add(btnReset);
		
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = 0;
				result = JOptionPane.showConfirmDialog(null, "초기화하시겠습니까?");
				
				if(result == 0){
					cust_id.setText("");
					cust_name.setText("");
					cust_address.setText("");
				}
				
			}
		});
	}
	public Vector getColumn(){
		Vector col = new Vector();
		col.add("휴대폰번호");
		col.add("고객명");
		col.add("주소");
		
		return col;
	}
	
	private class MyMouseListener extends MouseAdapter {
		 
	    @Override
	    public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 2) { 
		    	cust_id.setText("");
		    	cust_name.setText("");
		    	cust_address.setText("");
		    	int row = customer.getSelectedRow();
		    	cust_id.setText(customer.getValueAt(row, 0).toString());
		    	cust_name.setText(customer.getValueAt(row, 1).toString());
		    	cust_address.setText(customer.getValueAt(row, 2).toString());
		    	
		    }
	    }
	}
}
