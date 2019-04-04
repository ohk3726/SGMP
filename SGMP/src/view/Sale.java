package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JScrollPane;

import java.awt.Panel;
import java.awt.Button;

import javax.swing.JTextField;

import java.awt.Scrollbar;

import javax.swing.JTable;

import java.awt.ScrollPane;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JScrollBar;

import action.Dbtest;
import controller.Contoller;
import net.proteanit.sql.DbUtils;

public class Sale extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_6;
	private JTextField cusno;
	private JTextField textField;
	private Dbtest dbtest;
	private Test2 test2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sale frame = new Sale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Sale() {
		Dbtest dbtest = new Dbtest();
		setResizable(false);
		setTitle("\uC2A4\uD3EC\uCE20\uC6A9\uD488\uAD00\uB9AC\uD504\uB85C\uADF8\uB7A8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 900);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu_5 = new JMenu("\uD310\uB9E4");
		menu_5.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu_5);
		
		JMenu mnNewMenu = new JMenu("\uD310\uB9E4\uAD00\uB9AC");
		mnNewMenu.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(mnNewMenu);
		
		JMenu menu = new JMenu("\uC7AC\uACE0\uAD00\uB9AC");
		menu.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu);
		
		JMenu menu_1 = new JMenu("\uACAC\uC801\uBC0F\uBC1C\uC8FC\uAD00\uB9AC");
		menu_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu_1);
		
		JMenu menu_2 = new JMenu("\uD68C\uACC4\uAD00\uB9AC");
		menu_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu_2);
		
		JMenu menu_3 = new JMenu("\uACE0\uAC1D\uAD00\uB9AC");
		menu_3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu_3);
		
		JMenu menu_4 = new JMenu("\uD658\uACBD\uC124\uC815");
		menu_4.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu_4);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSelect = new JButton("\uC870\uD68C");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSelect.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		btnSelect.setBounds(0, 0, 104, 54);
		contentPane.add(btnSelect);
		
		
		JButton button = new JButton("\uCD94\uAC00");
		button.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		button.setBounds(104, 0, 104, 54);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\uC0AD\uC81C");
		button_1.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		button_1.setBounds(208, 0, 104, 54);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\uC800\uC7A5");
		button_2.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		button_2.setBounds(311, 0, 104, 54);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("\uC5D1\uC140");
		button_3.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		button_3.setBounds(414, 0, 104, 54);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("\uC778\uC1C4");
		button_4.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		button_4.setBounds(517, 0, 104, 54);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("\uB2EB\uAE30");
		button_5.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		button_5.setBounds(619, 0, 104, 54);
		contentPane.add(button_5);
		
		Panel panel = new Panel();
		panel.setBounds(0, 60, 1594, 164);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(118, 13, 214, 38);
		panel.add(textField_1);
		
		JLabel label = new JLabel("\uD488\uBAA9\uBC88\uD638");
		label.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label.setBounds(12, 13, 94, 38);
		panel.add(label);
		
		JLabel label_2 = new JLabel("\uD488\uBAA9\uBA85");
		label_2.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label_2.setBounds(12, 61, 94, 38);
		panel.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		textField_3.setColumns(10);
		textField_3.setBounds(118, 61, 214, 38);
		panel.add(textField_3);
		
		JLabel label_5 = new JLabel("\uD310\uB9E4\uC77C\uC790");
		label_5.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label_5.setBounds(12, 116, 94, 38);
		panel.add(label_5);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		textField_6.setColumns(10);
		textField_6.setBounds(118, 116, 214, 38);
		panel.add(textField_6);
		
		JLabel label_6 = new JLabel("\uACE0\uAC1D\uBC88\uD638");
		label_6.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label_6.setBounds(435, 13, 94, 38);
		panel.add(label_6);
		
		cusno = new JTextField();
		cusno.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		cusno.setColumns(10);
		cusno.setBounds(541, 13, 214, 38);
		panel.add(cusno);
		
		JLabel label_1 = new JLabel("\uACE0\uAC1D\uBA85");
		label_1.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label_1.setBounds(435, 62, 94, 38);
		panel.add(label_1);
		
		textField = new JTextField();
		textField.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(541, 62, 214, 38);
		panel.add(textField);
		
		String columnNames[] = {"±¸¸Å¹øÈ£","Ç°¸ñ¸í","¼ö·®","´Ü°¡","°í°´¹øÈ£","ÇÒÀÎÀ²"};
		String data[][] ={};
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 230, 1594, 586);
		contentPane.add(scrollPane_1);

		DefaultTableModel model =
		        new DefaultTableModel(data,columnNames){
			//³»¿ë¼öÁ¤ºÒ°¡
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		
		JTable table_1 = new JTable(model);
		scrollPane_1.setViewportView(table_1);
		table_1.setRowSelectionAllowed(false);
		table_1.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setRowHeight(50);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(85);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(85);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(85);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(85);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(85);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(85);
		
		model.setNumRows(0);
		Object record[] = new Object[7];
		try{
			ArrayList list = dbtest.getDbtest();
			for(int index = 0;index < list.size(); index++){
				for(int i=0;i<6;i++){
					record[i]=list.get(index);
					if(i<5){
						index++;
					}
				}
				model.addRow(record);
			}
		}
		catch(Exception error){
			error.printStackTrace();
		}
		
		//Á¶È¸ ¹öÆ° Å¬¸¯½Ã ÀÌº¥Æ®
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				model.setNumRows(0);
				Object record[] = new Object[7];
				try{
					ArrayList list = dbtest.getDbtest(cusno.getText());
					for(int index = 0;index < list.size(); index++){
						for(int i=0;i<6;i++){
							record[i]=list.get(index);
							if(i<5){
								index++;
							}
						}
						model.addRow(record);
					}
				}
				catch(Exception error){
					error.printStackTrace();
				}
			}
		};
		btnSelect.addActionListener(listener);
		//Å×ÀÌºí ¸¶¿ì½º Å¬¸¯ ÀÌº¥Æ®
		class MyMouseListener extends MouseAdapter{
			@Override
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==2){
					int row = table_1.getSelectedRow();
					if(row != -1){
						test2.testShow(table_1.getValueAt(row, 1).toString());
						//new Test2(table_1.getValueAt(row, 1).toString());
					}
				}
			}
		}
		//¸¶¿ì½º ´õºíÅ¬¸¯ ÀÌº¥Æ® ¸®½º³Ê
		table_1.addMouseListener(new MyMouseListener());
		
	}
	
}
