package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import action.Product_db;
import javax.swing.ImageIcon;

public class Product extends JFrame {
	Vector v,v1,cols,cols1;
	DefaultTableModel model,model1;
	
	private JPanel contentPane;
	private JTable product;
	private JTable product_Wearing;
	private JTextField prod_id;
	private JTextField prod_name;
	private JTextField prod_cnt;
	private JTextField prod_price;
	private JTextField prod_main_category;
	private JTextField prod_sub_category;
	private JTextField prod_Ssub_category;
	private JTextField prod_ex;
	private JTextField prod_date;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product frame = new Product();
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
	public Product() {
		Product_db pro_db = new Product_db();
		v = pro_db.getMemberList();
		cols=getColumn();
		
		setTitle("\uC2A4\uD3EC\uCE20\uC6A9\uD488\uAD00\uB9AC\uD504\uB85C\uADF8\uB7A8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		model = new DefaultTableModel(v,cols){
			//³»¿ë¼öÁ¤ºÒ°¡
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		product = new JTable(model);
		product.setRowHeight(40);
		product.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		
		cols1=getColumn1();
		model1 = new DefaultTableModel(v1,cols1){
			//³»¿ë¼öÁ¤ºÒ°¡
			public boolean isCellEditable(int rowindex, int mcolldex){
				return false;
			}
		};
		
		JScrollPane scroll_product = new JScrollPane(product);
		scroll_product.setBounds(12, 350, 1084, 501);
		contentPane.add(scroll_product);
		
		product_Wearing = new JTable(model1);
		product_Wearing.setRowHeight(40);
		product_Wearing.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		
		JScrollPane scroll_product_wearing = new JScrollPane(product_Wearing);
		scroll_product_wearing.setBounds(1108, 350, 464, 501);
		contentPane.add(scroll_product_wearing);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1584, 29);
		contentPane.add(menuBar);
		
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
		
		JLabel product_pic = new JLabel("\uADF8\uB9BC");
		product_pic.setBounds(1343, 39, 229, 225);
		contentPane.add(product_pic);
		
		JPanel product_Explanation = new JPanel();
		product_Explanation.setBounds(12, 39, 1319, 301);
		contentPane.add(product_Explanation);
		product_Explanation.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uD488\uBAA9\uBC88\uD638");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lblNewLabel.setBounds(12, 47, 87, 31);
		product_Explanation.add(lblNewLabel);
		
		prod_id = new JTextField();
		prod_id.setBounds(111, 47, 187, 31);
		product_Explanation.add(prod_id);
		prod_id.setColumns(10);
		
		JLabel label = new JLabel("\uD488\uBAA9\uBA85");
		label.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label.setBounds(12, 88, 87, 31);
		product_Explanation.add(label);
		
		prod_name = new JTextField();
		prod_name.setColumns(10);
		prod_name.setBounds(111, 88, 187, 31);
		product_Explanation.add(prod_name);
		
		JLabel label_1 = new JLabel("\uC218\uB7C9");
		label_1.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label_1.setBounds(12, 129, 87, 31);
		product_Explanation.add(label_1);
		
		prod_cnt = new JTextField();
		prod_cnt.setColumns(10);
		prod_cnt.setBounds(111, 129, 187, 31);
		product_Explanation.add(prod_cnt);
		
		JLabel label_2 = new JLabel("\uB2E8\uAC00");
		label_2.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label_2.setBounds(12, 170, 87, 31);
		product_Explanation.add(label_2);
		
		prod_price = new JTextField();
		prod_price.setColumns(10);
		prod_price.setBounds(111, 170, 187, 31);
		product_Explanation.add(prod_price);
		
		JLabel label_4 = new JLabel("\uB300\uBD84\uB958");
		label_4.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label_4.setBounds(627, 47, 102, 31);
		product_Explanation.add(label_4);
		
		prod_main_category = new JTextField();
		prod_main_category.setColumns(10);
		prod_main_category.setBounds(741, 47, 187, 31);
		product_Explanation.add(prod_main_category);
		
		JLabel label_5 = new JLabel("\uC911\uBD84\uB958");
		label_5.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label_5.setBounds(627, 88, 102, 31);
		product_Explanation.add(label_5);
		
		prod_sub_category = new JTextField();
		prod_sub_category.setColumns(10);
		prod_sub_category.setBounds(741, 88, 187, 31);
		product_Explanation.add(prod_sub_category);
		
		JLabel label_6 = new JLabel("\uC18C\uBD84\uB958");
		label_6.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label_6.setBounds(627, 129, 102, 31);
		product_Explanation.add(label_6);
		
		prod_Ssub_category = new JTextField();
		prod_Ssub_category.setColumns(10);
		prod_Ssub_category.setBounds(741, 129, 187, 31);
		product_Explanation.add(prod_Ssub_category);
		
		JLabel label_7 = new JLabel("\uC124\uBA85");
		label_7.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label_7.setBounds(627, 170, 102, 31);
		product_Explanation.add(label_7);
		
		prod_ex = new JTextField();
		prod_ex.setColumns(10);
		prod_ex.setBounds(741, 170, 187, 31);
		product_Explanation.add(prod_ex);
		
		JLabel label_8 = new JLabel("\uCD5C\uADFC\uC218\uC815\uC77C");
		label_8.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label_8.setBounds(627, 211, 102, 31);
		product_Explanation.add(label_8);
		
		prod_date = new JTextField();
		prod_date.setColumns(10);
		prod_date.setBounds(741, 211, 187, 31);
		product_Explanation.add(prod_date);
		
		product.addMouseListener(new MyMouseListener());
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
		    	prod_price.setText(settext(product.getValueAt(row, 3)));
		    	prod_main_category.setText(settext(product.getValueAt(row, 4)));
		    	prod_sub_category.setText(settext(product.getValueAt(row, 5)));
		    	prod_Ssub_category.setText(settext(product.getValueAt(row, 6)));
		    	prod_ex.setText(settext(product.getValueAt(row, 7)));
		    	prod_date.setText(settext(product.getValueAt(row, 8)));
		    	
		    	Product_db pro_db = new Product_db();
				v1 = pro_db.getMemberList(product.getValueAt(row, 0).toString());
				
				model1.setDataVector(v1, cols1);;
		    }
	    }
	}

	public Vector getColumn(){
		Vector col = new Vector();
		col.add("Ç°¸ñ¹øÈ£");
		col.add("Ç°¸ñ¸í");
		col.add("¼ö·®");
		col.add("´Ü°¡");
		col.add("´ëºÐ·ù");
		col.add("ÁßºÐ·ù");
		col.add("¼ÒºÐ·ù");
		col.add("Ç°¸ñ¼³¸í");
		col.add("µî·ÏÀÏ");
		
		return col;
	}
	
	public Vector getColumn1(){
		Vector col = new Vector();
		col.add("ÁÖ¹®ÀÏ");
		col.add("¼ö·®");
		col.add("ÀÔ°í»óÅÂ");
		
		return col;
	}
}
