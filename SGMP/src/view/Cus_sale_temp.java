package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import action.CusDB;

public class Customer extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer frame = new Customer();
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
	public Customer() {
		CusDB cusdb = new CusDB();
		setTitle("\uC2A4\uD3EC\uCE20\uC6A9\uD488\uAD00\uB9AC\uD504\uB85C\uADF8\uB7A8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 900);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\uD310\uB9E4");
		menu.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu);
		
		JMenu menu_1 = new JMenu("\uD310\uB9E4\uAD00\uB9AC");
		menu_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu_1);
		
		JMenu menu_2 = new JMenu("\uC7AC\uACE0\uAD00\uB9AC");
		menu_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu_2);
		
		JMenu menu_3 = new JMenu("\uACAC\uC801\uBC0F\uBC1C\uC8FC\uAD00\uB9AC");
		menu_3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu_3);
		
		JMenu menu_4 = new JMenu("\uD68C\uACC4\uAD00\uB9AC");
		menu_4.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu_4);
		
		JMenu menu_5 = new JMenu("\uACE0\uAC1D\uAD00\uB9AC");
		menu_5.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu_5);
		
		JMenu menu_6 = new JMenu("\uD658\uACBD\uC124\uC815");
		menu_6.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(menu_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		String columnNames[] = {"¹øÈ£","°í°´¹øÈ£","°í°´¸í","ÁÖ¼Ò","ÇÒÀÎÀ²"};
		String data[][] ={};
		
		DefaultTableModel model =
		        new DefaultTableModel(data,columnNames){
			//³»¿ë¼öÁ¤ºÒ°¡
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		model.setNumRows(0);
		Object record[] = new Object[7];
		try{
			ArrayList list = cusdb.getCusDB();
			int a = list.size();
			int cnt=1;
			int i=0;
			for(int index = 0;index < a; index++){
				if(list.get(index)!="|"){
					if(i==0){
						record[i]=(index/7)+1;
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
	}

}
