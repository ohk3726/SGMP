package view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import database.Sale_db;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

public class Accounting_Group extends JPanel {
	Vector v,cols;
	DefaultTableModel model;
	SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	private JFreeChart barChart1 = null;
	private JFreeChart pieChart1 = null;
	private JComboBox comboBox;
	private JTable table;
	private DefaultTableCellRenderer cellcenter = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer cellright = new DefaultTableCellRenderer();
	private TableColumnModel tcmSchedule;
	/**
	 * Create the panel.
	 */
	public Accounting_Group() {
		setSize(1600, 900);
		setLayout(null);
		
		JLabel label = new JLabel("\uC5F0       \uB3C4");
		label.setFont(new Font("굴림", Font.PLAIN, 20));
		label.setBounds(12, 90, 99, 34);
		add(label);
		
		cellcenter.setHorizontalAlignment(SwingConstants.CENTER);
		cellright.setHorizontalAlignment(SwingConstants.RIGHT);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uD558\uC138\uC694"}));
		comboBox.setBounds(140, 90, 123, 34);
		Vector temp = new Vector();
		Sale_db sdb = new Sale_db();
		temp = sdb.sale_date();
		for(int i = 0; i<temp.size();i++){
			comboBox.addItem(temp.get(i).toString().replace("[", "").replace("]", ""));
		}
		add(comboBox);
		
		JLabel label_1 = new JLabel("\uAD6C       \uBD84");
		label_1.setFont(new Font("굴림", Font.PLAIN, 20));
		label_1.setBounds(292, 90, 99, 34);
		add(label_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uD558\uC138\uC694", "\uBD84\uAE30\uBCC4", "\uC6D4\uBCC4"}));
		comboBox_1.setBounds(403, 90, 123, 34);
		add(comboBox_1);
		
		ChartPanel chartPanel = new ChartPanel((JFreeChart) null);
		chartPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		chartPanel.setBounds(12, 164, 764, 707);
		add(chartPanel);
		chartPanel.setLayout(null);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setVisible(false);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uD558\uC138\uC694", "1\uBD84\uAE30", "2\uBD84\uAE30", "3\uBD84\uAE30", "4\uBD84\uAE30"}));
		comboBox_2.setBounds(559, 0, 104, 30);
		chartPanel.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setVisible(false);
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uD558\uC138\uC694", "01\uC6D4", "02\uC6D4", "03\uC6D4", "04\uC6D4", "05\uC6D4", "06\uC6D4", "07\uC6D4", "08\uC6D4", "09\uC6D4", "10\uC6D4", "11\uC6D4", "12\uC6D4"}));
		comboBox_3.setBounds(559, 0, 104, 30);
		chartPanel.add(comboBox_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setBounds(701, 77, 858, 62);
		add(scrollPane);
		
		cols=getColumn1();
		model = new DefaultTableModel(v,cols){
			//내용수정불가
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		
		table = new JTable(model);
		table.setRowHeight(40);
		table.setFont(new Font("굴림", Font.PLAIN, 20));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		scrollPane.setViewportView(table);
		
		ChartPanel chartPanel_1 = new ChartPanel((JFreeChart) null);
		chartPanel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		chartPanel_1.setBounds(795, 164, 764, 707);
		add(chartPanel_1);
		
		JButton btnSelect = new JButton("\uAC80    \uC0C9");
		btnSelect.setFont(new Font("굴림", Font.PLAIN, 20));
		btnSelect.setBounds(552, 90, 123, 34);
		add(btnSelect);
		btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String select = comboBox.getSelectedItem().toString();
				String group = comboBox_1.getSelectedItem().toString();
				if(select.equals("선택하세요")){
					JOptionPane.showMessageDialog(null, "연도를 선택하세요.");
				}
				else{
					if(group.equals("선택하세요")){
						JOptionPane.showMessageDialog(null, "구분을 선택하세요.");
					}
					else{
						Sale_db saledb3 = new Sale_db();
						barChart1 = saledb3.sale_chart_1(select,PlotOrientation.VERTICAL,group);
						
						Sale_db saledb2 = new Sale_db();
						String date1=select+"-01-01";
						String date2=select+"-12-31";
						
						v=saledb2.sale_all_price(date1, date2);
						cols=getColumn1();
						model.setDataVector(v, cols);
						
						tcmSchedule = table.getColumnModel();
						for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
							tcmSchedule.getColumn(i).setCellRenderer(cellright);
							
						}
						
						chartPanel.setChart(barChart1);
						if(group.equals("분기별")){
							comboBox_2.setSelectedIndex(0);
							comboBox_2.setVisible(true);
							comboBox_3.setVisible(false);
						}
						else{
							comboBox_3.setSelectedIndex(0);
							comboBox_2.setVisible(false);
							comboBox_3.setVisible(true);
						}
						
						
					}
				}
			}
		});
		comboBox_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String select = comboBox.getSelectedItem().toString();
				Sale_db saledb3 = new Sale_db();
				String date1=select+"-01-01",date2=select+"-12-31";
				if(comboBox_2.getSelectedIndex()==1){
					date1 = select+"-01-01";
					date2 = select+"-03-31";
				}
				else if(comboBox_2.getSelectedIndex()==2){
					date1 = select+"-04-01";
					date2 = select+"-06-31";
				}
				else if(comboBox_2.getSelectedIndex()==3){
					date1 = select+"-07-01";
					date2 = select+"-09-31";
				}
				else if(comboBox_2.getSelectedIndex()==4){
					date1 = select+"-10-01";
					date2 = select+"-12-31";
				}
				Sale_db saledb2 = new Sale_db();
				v=saledb2.sale_all_price(date1, date2);
				cols=getColumn1();
				model.setDataVector(v, cols);
				
				tcmSchedule = table.getColumnModel();
				for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
					tcmSchedule.getColumn(i).setCellRenderer(cellright);
					
				}
				
				pieChart1=saledb3.sale_chart(date1, date2);
				chartPanel_1.setChart(pieChart1);
			}
		});
		
		comboBox_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String select = comboBox.getSelectedItem().toString();
				String month = comboBox_3.getSelectedItem().toString().replace("월", "");
				Sale_db saledb3 = new Sale_db();
				String date1=select+"-01-01",date2=select+"-12-31";
				if(comboBox_3.getSelectedIndex() != 0){
					date1 = select+"-"+month+"-01";
					date2 = select+"-"+month+"-31";
				}
				
				
				Sale_db saledb2 = new Sale_db();
				v=saledb2.sale_all_price(date1, date2);
				cols=getColumn1();
				model.setDataVector(v, cols);
				
				tcmSchedule = table.getColumnModel();
				for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
					tcmSchedule.getColumn(i).setCellRenderer(cellright);
					
				}
				pieChart1=saledb3.sale_chart(date1, date2);
				chartPanel_1.setChart(pieChart1);
			}
		});
	}
	public Vector getColumn1(){
		Vector col = new Vector();
		col.add("총입고가격");
		col.add("총판매가격");
		col.add("총순이익가격");
		
		return col;
	}
}
