package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JDateChooser;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import database.Product_db;
import database.Sale_db;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Accounting extends JPanel {
	Vector v,v1,cols,cols1;
	DefaultTableModel model,model1;
	SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	private JTable money;
	private JTable allslae;
	private JComboBox comboBox;
	private JFreeChart pieChart1 = null;
	private JFreeChart pieChart2 = null;
	private DefaultTableCellRenderer cellcenter = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer cellright = new DefaultTableCellRenderer();
	private TableColumnModel tcmSchedule;
	/**
	 * Create the panel.
	 */
	public Accounting() {
		setSize(1600, 900);
		setLayout(null);
		
		Date time = new Date();
		JDateChooser date2 = new JDateChooser();
		date2.setBounds(473, 66, 198, 34);
		date2.setDate(time);
		add(date2);
		
		JDateChooser date1 = new JDateChooser();
		date1.setBounds(198, 66, 198, 34);
		date1.setDate(time);
		add(date1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setBounds(12, 110, 858, 685);
		add(scrollPane);
		
		cols=getColumn();
		model = new DefaultTableModel(v,cols){
			//내용수정불가
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		
		money = new JTable(model);
		money.setRowHeight(40);
		money.setFont(new Font("굴림", Font.PLAIN, 20));
		money.getTableHeader().setReorderingAllowed(false);
		money.getTableHeader().setResizingAllowed(false);
		scrollPane.setViewportView(money);
		
		cellcenter.setHorizontalAlignment(SwingConstants.CENTER);
		cellright.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label = new JLabel("~");
		label.setFont(new Font("굴림", Font.PLAIN, 20));
		label.setBounds(423, 66, 26, 34);
		add(label);
		
		JLabel lblNewLabel = new JLabel("\uAE30       \uAC04");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setBounds(41, 66, 99, 34);
		add(lblNewLabel);
		
		JButton btnSelect = new JButton("\uC870       \uD68C");
		btnSelect.setFont(new Font("굴림", Font.PLAIN, 20));
		btnSelect.setBounds(716, 66, 154, 34);
		add(btnSelect);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(Color.BLACK));
		scrollPane_1.setBounds(12, 805, 858, 62);
		add(scrollPane_1);
		
		cols1=getColumn1();
		model1 = new DefaultTableModel(v1,cols1){
			//내용수정불가
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		allslae = new JTable(model1);
		allslae.setRowHeight(40);
		allslae.setFont(new Font("굴림", Font.PLAIN, 20));
		allslae.getTableHeader().setReorderingAllowed(false);
		allslae.getTableHeader().setResizingAllowed(false);
		scrollPane_1.setViewportView(allslae);
		
		ChartPanel chartPanel = new ChartPanel(null);
		chartPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		chartPanel.setBounds(882, 66, 690, 393);
		chartPanel.setLayout(null);
		add(chartPanel);
		
		ChartPanel chartPanel_1 = new ChartPanel((JFreeChart) null);
		chartPanel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		chartPanel_1.setBounds(882, 469, 690, 398);
		add(chartPanel_1);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD\uD558\uC138\uC694"}));
		comboBox.setFont(new Font("굴림", Font.PLAIN, 20));
		comboBox.setBounds(490, 45, 173, 30);
		chartPanel.add(comboBox);
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String category = comboBox.getSelectedItem().toString();
				Sale_db saledb3 = new Sale_db();
				pieChart1 = saledb3.sale_chart(SDF.format(date1.getDate()), SDF.format(date2.getDate()),category);
				
				chartPanel_1.setChart(pieChart1);
			}
		});
		comboBox.setVisible(false);
		
		
		
		
		btnSelect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result=1;
				
				
				if(!date1.getDate().equals(null)){
					if(!date2.getDate().equals(null)){
						result=0;
					}
				}
				
				if(result == 0){
					Sale_db saledb1 = new Sale_db();
					v=saledb1.sale_select(SDF.format(date1.getDate()), SDF.format(date2.getDate()));
					model.setDataVector(v, cols);
					
					tcmSchedule = money.getColumnModel();
					for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
						if(i == 3 || i == 4){
							tcmSchedule.getColumn(i).setCellRenderer(cellright);
						}
						else{
							tcmSchedule.getColumn(i).setCellRenderer(cellcenter);
						}
						
					}
					
					Sale_db saledb2 = new Sale_db();
					v1=saledb2.sale_all_price(SDF.format(date1.getDate()), SDF.format(date2.getDate()));
					model1.setDataVector(v1, cols1);
					
					tcmSchedule = allslae.getColumnModel();
					for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
						tcmSchedule.getColumn(i).setCellRenderer(cellright);
						
					}
					
					Sale_db saledb3 = new Sale_db();
					pieChart1 = saledb3.sale_chart(SDF.format(date1.getDate()), SDF.format(date2.getDate()));
					
					chartPanel.setChart(pieChart1);
					
					Vector temp = new Vector();
					Sale_db sdb = new Sale_db();
					temp = sdb.sale_category(SDF.format(date1.getDate()), SDF.format(date2.getDate()));
					
					int combocnt = comboBox.getItemCount();
					for(int i = combocnt-1;i>=1;i--){
						comboBox.removeItemAt(i);
					}
					for(int i = 0; i<temp.size();i++){
						comboBox.addItem(temp.get(i).toString().replace("[", "").replace("]", ""));
					}
					
					comboBox.setVisible(true);
				}
				
			}
		});
		
		
		setVisible(true);
	}
	public Vector getColumn(){
		Vector col = new Vector();
		col.add("구매번호");
		col.add("품목명");
		col.add("수량");
		col.add("단가");
		col.add("구매가격");
		
		return col;
	}
	public Vector getColumn1(){
		Vector col = new Vector();
		col.add("총입고가격");
		col.add("총판매가격");
		col.add("총순이익가격");
		
		return col;
	}
	private class chartclick extends MouseAdapter {
		 
	    @Override
	    public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 2) { 
		    	
		    }
	    }
	}
}
