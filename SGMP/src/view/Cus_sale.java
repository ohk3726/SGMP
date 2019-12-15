package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
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

import model.Sale_model;
import database.Sale_db;

public class Cus_sale extends JPanel {
	Vector v,v1,cols,cols1;
	DefaultTableModel model,model1;
	private JTable sale;
	private JTextField sale_id;
	private JTextField cust_id;
	private JTable sale_cust;
	private DefaultTableCellRenderer cellcenter = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer cellright = new DefaultTableCellRenderer();
	private TableColumnModel tcmSchedule;
	/**
	 * Create the panel.
	 */
	public Cus_sale() {
		setSize(1600, 900);
		setLayout(null);
		
		Sale_db sale_db = new Sale_db();
		v1 = sale_db.sale_select();
		cols1 = getColumn1();
		
		model1 = new DefaultTableModel(v1,cols1){
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		
		sale = new JTable(model1);
		sale.setRowHeight(40);
		sale.setFont(new Font("굴림", Font.PLAIN, 20));
		sale.getTableHeader().setReorderingAllowed(false);
		sale.getTableHeader().setResizingAllowed(false);
		
		
		cellcenter.setHorizontalAlignment(SwingConstants.CENTER);
		cellright.setHorizontalAlignment(SwingConstants.RIGHT);
		tcmSchedule = sale.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			if(i == 3 || i == 5){
				tcmSchedule.getColumn(i).setCellRenderer(cellright);
			}
			else{
				tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
			}
		}
		
		JScrollPane scrollPane = new JScrollPane(sale);
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setBounds(769, 47, 810, 822);
		add(scrollPane);
		
		sale_id = new JTextField();
		sale_id.setBounds(155, 47, 176, 44);
		add(sale_id);
		sale_id.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\uAD6C\uB9E4\uBC88\uD638");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setBounds(58, 47, 179, 44);
		add(lblNewLabel);
		
		JLabel label = new JLabel("\uACE0\uAC1D\uBC88\uD638");
		label.setFont(new Font("굴림", Font.PLAIN, 20));
		label.setBounds(413, 47, 179, 44);
		add(label);
		
		cust_id = new JTextField();
		cust_id.setColumns(10);
		cust_id.setBounds(510, 47, 176, 44);
		add(cust_id);
		
		model = new DefaultTableModel(v,cols){
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		
		sale_cust = new JTable(model);
		sale_cust.setRowHeight(40);
		sale_cust.setFont(new Font("굴림", Font.PLAIN, 20));
		sale_cust.getTableHeader().setReorderingAllowed(false);
		sale_cust.getTableHeader().setResizingAllowed(false);
		
		tcmSchedule = sale_cust.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
		}
		
		JScrollPane scrollPane_1 = new JScrollPane(sale_cust);
		scrollPane_1.setBorder(new LineBorder(Color.BLACK));
		scrollPane_1.setBounds(12, 112, 745, 757);
		add(scrollPane_1);
		
		sale_cust.addMouseListener(new MyMouseListener());
		sale.addMouseListener(new sale_delete());
		
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false);
		cust_id.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter,"ENTER");
		cust_id.getActionMap().put("ENTER", cust_enter);
		
		enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false);
		sale_id.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter,"ENTER");
		sale_id.getActionMap().put("ENTER", sale_enter);
	}
	public Vector getColumn(){
		Vector col = new Vector();
		col.add("구매번호");
		col.add("구매날짜");
		
		return col;
	}
	public Vector getColumn1(){
		Vector col = new Vector();
		col.add("구매번호");
		col.add("품목번호");
		col.add("품목명");
		col.add("품목가격");
		col.add("구매수량");
		col.add("구매가격");
		
		return col;
	}
	Action cust_enter = new AbstractAction(){
		@Override
		public void actionPerformed(ActionEvent arg0){
			if(!cust_id.getText().toString().equals("")){
				Sale_db sale_db = new Sale_db();
				String cnt = sale_db.custid_select_1(cust_id.getText());
				if(cnt.equals("1")){
					v = sale_db.custid_select(cust_id.getText());
					cols = getColumn();
					model.setDataVector(v, cols);
					
					tcmSchedule = sale_cust.getColumnModel();
					for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
						tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "등록되지 않은 사용자 입니다.");
				}
				
			}
			else{
				Sale_db sale_db = new Sale_db();
				v.clear();
				cols = getColumn();
				model.setDataVector(v, cols);
				
				tcmSchedule = sale_cust.getColumnModel();
				for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
					tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
				}
				
				v1 = sale_db.sale_select();
				cols1 = getColumn1();
				model1.setDataVector(v1, cols1);
				
				tcmSchedule = sale.getColumnModel();
				for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
					if(i == 3 || i == 5){
						tcmSchedule.getColumn(i).setCellRenderer(cellright);
					}
					else{
						tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
					}
				}
			}
		}
	};
	Action sale_enter = new AbstractAction(){
		@Override
		public void actionPerformed(ActionEvent arg0){
			if(!sale_id.getText().toString().equals("")){
				Sale_db sale_db = new Sale_db();
				String cnt = sale_db.sale_select2(sale_id.getText());
				if(Integer.parseInt(cnt) >= 1){
					v = sale_db.sale_select1(sale_id.getText());
					cols = getColumn();
					model.setDataVector(v, cols);	
					
					tcmSchedule = sale_cust.getColumnModel();
					for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
						tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "없는 영수증 번호입니다.");
				}
			}
			else{
				Sale_db sale_db = new Sale_db();
				v.clear();
				cols = getColumn1();
				model.setDataVector(v, cols);
				
				tcmSchedule = sale_cust.getColumnModel();
				for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
					tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
				}
				
				v1 = sale_db.sale_select();
				cols1 = getColumn1();
				model1.setDataVector(v1, cols1);
				
				tcmSchedule = sale.getColumnModel();
				for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
					if(i == 3 || i == 5){
						tcmSchedule.getColumn(i).setCellRenderer(cellright);
					}
					else{
						tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
					}
				}
			}
		}
	};
	private class MyMouseListener extends MouseAdapter {
		 
	    @Override
	    public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 2) { 
		    	int row = sale_cust.getSelectedRow();
		    	Sale_db sale_db = new Sale_db();
		    	v1 = sale_db.sale_select(sale_cust.getValueAt(row, 0).toString());
		    	cols1=getColumn1();
		    	model1.setDataVector(v1, cols1);
		    	
		    	tcmSchedule = sale.getColumnModel();
				for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
					if(i == 3 || i == 5){
						tcmSchedule.getColumn(i).setCellRenderer(cellright);
					}
					else{
						tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
					}
				}
		    }
	    }
	}
	private class sale_delete extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e){
			Sale_model sale_model = new Sale_model();
			Sale_db sale_db = new Sale_db();
			int row_cust = sale.getSelectedRow();
			if(e.getClickCount() == 2){
				int result =0;
				result = JOptionPane.showConfirmDialog(null, "전체를 환불하시겠습니까?");
				if(result == 0){
					//전체환불
					sale_model.setSale_id(sale.getValueAt(row_cust, 0).toString());
					sale_db.prod_update(sale_model, 0);
					sale_db.sale_delete(sale_model, 0);
					JOptionPane.showMessageDialog(null, "환불처리되었습니다.");
				}
				else if(result == 1){
					//선택환불
					 String result1 = JOptionPane.showInputDialog("몇개를  환불하시겠습니까?");
					if(result1.equals("-1")){
						//환불처리
						sale_model.setSale_id(sale.getValueAt(row_cust, 0).toString());
						sale_model.setProd_id(sale.getValueAt(row_cust, 1).toString());
						sale_model.setSale_cnt(sale.getValueAt(row_cust, 4).toString());
						sale_db.prod_update(sale_model, -1);
						sale_db.sale_delete(sale_model, -1);
						JOptionPane.showMessageDialog(null, "환불처리되었습니다.");
					}
					else{
						sale_model.setSale_id(sale.getValueAt(row_cust, 0).toString());
						sale_model.setProd_id(sale.getValueAt(row_cust, 1).toString());
						sale_model.setSale_cnt(sale.getValueAt(row_cust, 4).toString());
						result1 = String.valueOf(Integer.parseInt(sale.getValueAt(row_cust, 4).toString())-Integer.parseInt(result1));

						sale_db.prod_update(sale_model, Integer.parseInt(result1));
						sale_db.sale_delete(sale_model, Integer.parseInt(result1));
						JOptionPane.showMessageDialog(null, "환불처리되었습니다.");
					}
				}
				v1 = sale_db.sale_select();
		    	model1.setDataVector(v1, cols1);
		    	
				tcmSchedule = sale.getColumnModel();
				for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
					if(i == 3 || i == 5){
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
