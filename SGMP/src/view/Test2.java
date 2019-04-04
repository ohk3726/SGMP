package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Panel;

import javax.swing.JTable;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;

import action.Dbtest;

public class Test2 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField cusno;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private static String temp;
	private ArrayList list;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test2 frame = new Test2(temp);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void testShow(String temp){
		this.show();
		this.temp = temp;
		
		cusno.setText(temp);
		
	}
	/**
	 * Create the frame.
	 */
	public Test2(String temp) {
		Dbtest dbtest = new Dbtest();
		try {
			list = dbtest.getDbtest2(temp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setResizable(false);
		setTitle("\uC2A4\uD3EC\uCE20\uC6A9\uD488\uAD00\uB9AC\uD504\uB85C\uADF8\uB7A8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 789, 628);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		setJMenuBar(menuBar);
		
		JMenu menu_6 = new JMenu("\uD310\uB9E4");
		menu_6.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu_6);
		
		JMenu menu = new JMenu("\uD310\uB9E4\uAD00\uB9AC");
		menu.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu);
		
		JMenu menu_1 = new JMenu("\uC7AC\uACE0\uAD00\uB9AC");
		menu_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu_1);
		
		JMenu menu_2 = new JMenu("\uACAC\uC801\uBC0F\uBC1C\uC8FC\uAD00\uB9AC");
		menu_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu_2);
		
		JMenu menu_3 = new JMenu("\uD68C\uACC4\uAD00\uB9AC");
		menu_3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu_3);
		
		JMenu menu_4 = new JMenu("\uACE0\uAC1D\uAD00\uB9AC");
		menu_4.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu_4);
		
		JMenu menu_5 = new JMenu("\uD658\uACBD\uC124\uC815");
		menu_5.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu_5);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 488, 400);
		contentPane.add(scrollPane);
		
		String columnNames[] = {"¹øÈ£","Ç°¸ñ¸í","¼ö·®","´Ü°¡","ÇÒÀÎÀ²"};
		String data[][] ={};
		
		DefaultTableModel model =
		        new DefaultTableModel(data,columnNames){
			//³»¿ë¼öÁ¤ºÒ°¡
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		
		Object record[] = new Object[5];
		try{
			int a = list.size();
			int cnt=1;
			int i=0;
			for(int index = 0;index < a; index++){
				if(list.get(index)!="|"){
					if(i==0){
						record[i]=(index/5)+1;
						i++;
					}
						record[i]=list.get(index);
					i++;
				}
				else{
					if(cnt!=1){
						model.addRow(record);
					}
					i=0;
					cnt++;
				}
			}
		}
		catch(Exception error){
			error.printStackTrace();
		}
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblNewLabel = new JLabel("\uCD1D\uAC00\uACA9");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lblNewLabel.setBounds(96, 410, 99, 29);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(207, 410, 183, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(207, 449, 183, 29);
		contentPane.add(textField_1);
		
		JLabel label_1 = new JLabel("\uACE0\uAC1D\uBC88\uD638");
		label_1.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label_1.setBounds(500, 10, 89, 29);
		contentPane.add(label_1);
		
		cusno = new JTextField();
		cusno.setColumns(10);
		cusno.setBounds(601, 10, 160, 29);
		contentPane.add(cusno);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(601, 49, 160, 29);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(207, 488, 183, 29);
		contentPane.add(textField_4);
		
		JButton btnNewButton = new JButton("7");
		btnNewButton.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		btnNewButton.setBounds(525, 124, 65, 61);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("8");
		button.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		button.setBounds(602, 124, 65, 61);
		contentPane.add(button);
		
		JButton button_1 = new JButton("9");
		button_1.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		button_1.setBounds(679, 124, 65, 61);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("4");
		button_2.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		button_2.setBounds(525, 195, 65, 61);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("5");
		button_3.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		button_3.setBounds(602, 195, 65, 61);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("6");
		button_4.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		button_4.setBounds(679, 195, 65, 61);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("1");
		button_5.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		button_5.setBounds(525, 266, 65, 61);
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("2");
		button_6.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		button_6.setBounds(602, 266, 65, 61);
		contentPane.add(button_6);
		
		JButton button_7 = new JButton("3");
		button_7.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		button_7.setBounds(679, 266, 65, 61);
		contentPane.add(button_7);
		
		JButton button_9 = new JButton("0");
		button_9.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		button_9.setBounds(602, 337, 65, 61);
		contentPane.add(button_9);
		
		JLabel label = new JLabel("\uD560\uC778");
		label.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label.setBounds(96, 449, 99, 29);
		contentPane.add(label);
		
		JLabel label_3 = new JLabel("\uBC1B\uC740\uAE08\uC561");
		label_3.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label_3.setBounds(96, 486, 99, 29);
		contentPane.add(label_3);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(207, 529, 183, 29);
		contentPane.add(textField_5);
		
		JLabel label_4 = new JLabel("\uAC70\uC2A4\uB984\uB3C8");
		label_4.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label_4.setBounds(96, 527, 99, 29);
		contentPane.add(label_4);
		
		JLabel label_2 = new JLabel("\uACE0\uAC1D\uBA85");
		label_2.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label_2.setBounds(500, 49, 89, 29);
		contentPane.add(label_2);
		
		JButton btnNewButton_1 = new JButton("\uACB0\uC81C");
		btnNewButton_1.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		btnNewButton_1.setBounds(525, 432, 219, 46);
		contentPane.add(btnNewButton_1);
		
		JButton button_8 = new JButton("\uCDE8\uC18C");
		button_8.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		button_8.setBounds(525, 491, 219, 46);
		contentPane.add(button_8);
	}
	
}
