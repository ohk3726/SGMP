package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Product_model;
import database.Product_db;
import database.Sale_db;

public class Insert extends JDialog {
	private JTextField prod_name;
	private JTextField prod_main_category;
	private JTextField prod_sub_category;
	private JTextField prod_ssub_category;
	private JTextField prod_wearing_price;
	private JTextField prod_price;
	private JTextField prod_pic_address;
	private JLabel prod_pic; 
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Insert(JFrame frame) {
		setTitle("\uC2A4\uD3EC\uCE20\uC6A9\uD488\uAD00\uB9AC\uD504\uB85C\uADF8\uB7A8");
		setBounds(100, 100, 853, 521);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uD488\uBAA9\uBA85");
		lblNewLabel.setBounds(74, 113, 82, 39);
		contentPanel.add(lblNewLabel);
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		
		prod_name = new JTextField();
		prod_name.setBounds(155, 113, 116, 39);
		contentPanel.add(prod_name);
		prod_name.setColumns(10);
		
		JLabel label = new JLabel("\uB300\uBD84\uB958");
		label.setBounds(74, 162, 82, 39);
		contentPanel.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.PLAIN, 20));
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		prod_main_category = new JTextField();
		prod_main_category.setBounds(155, 162, 116, 39);
		contentPanel.add(prod_main_category);
		prod_main_category.setColumns(10);
		
		JLabel label_1 = new JLabel("\uC911\uBD84\uB958");
		label_1.setBounds(74, 211, 82, 39);
		contentPanel.add(label_1);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("굴림", Font.PLAIN, 20));
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		prod_sub_category = new JTextField();
		prod_sub_category.setBounds(155, 211, 116, 39);
		contentPanel.add(prod_sub_category);
		prod_sub_category.setColumns(10);
		
		JLabel label_2 = new JLabel("\uC18C\uBD84\uB958");
		label_2.setBounds(74, 260, 82, 39);
		contentPanel.add(label_2);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("굴림", Font.PLAIN, 20));
		label_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		prod_ssub_category = new JTextField();
		prod_ssub_category.setBounds(155, 260, 116, 39);
		contentPanel.add(prod_ssub_category);
		prod_ssub_category.setColumns(10);
		
		JLabel label_3 = new JLabel("\uC785\uACE0\uAC00\uACA9");
		label_3.setBounds(74, 309, 82, 39);
		contentPanel.add(label_3);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("굴림", Font.PLAIN, 20));
		label_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		prod_wearing_price = new JTextField();
		prod_wearing_price.setBounds(155, 309, 116, 39);
		contentPanel.add(prod_wearing_price);
		prod_wearing_price.setColumns(10);
		
		JLabel label_4 = new JLabel("\uD310\uB9E4\uAC00\uACA9");
		label_4.setBounds(74, 358, 82, 39);
		contentPanel.add(label_4);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("굴림", Font.PLAIN, 20));
		label_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		prod_price = new JTextField();
		prod_price.setBounds(155, 358, 116, 39);
		contentPanel.add(prod_price);
		prod_price.setColumns(10);
		
		prod_pic = new JLabel("");
		prod_pic.setBounds(329, 58, 477, 255);
		contentPanel.add(prod_pic);
		prod_pic.setHorizontalAlignment(SwingConstants.CENTER);
		prod_pic.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel label_5 = new JLabel("\uC0C1\uD488\uC774\uBBF8\uC9C0");
		label_5.setBounds(329, 335, 102, 39);
		contentPanel.add(label_5);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("굴림", Font.PLAIN, 20));
		label_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		prod_pic_address = new JTextField();
		prod_pic_address.setEnabled(false);
		prod_pic_address.setBounds(430, 335, 276, 39);
		contentPanel.add(prod_pic_address);
		prod_pic_address.setColumns(10);
		
		JButton btnPic = new JButton("\uCD94\uAC00");
		btnPic.setBounds(704, 335, 102, 39);
		contentPanel.add(btnPic);
		btnPic.setFont(new Font("굴림", Font.PLAIN, 20));
		
		JButton btnInsert = new JButton("\uC0C1\uD488\uB4F1\uB85D");
		btnInsert.setBounds(329, 402, 143, 39);
		contentPanel.add(btnInsert);
		btnInsert.setFont(new Font("굴림", Font.PLAIN, 20));
		
		JButton btnCancel = new JButton("\uB4F1\uB85D\uCDE8\uC18C");
		btnCancel.setBounds(663, 402, 143, 39);
		contentPanel.add(btnCancel);
		btnCancel.setFont(new Font("굴림", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("\uC0C1\uD488\uB4F1\uB85D");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 25));
		lblNewLabel_1.setBounds(74, 58, 197, 39);
		contentPanel.add(lblNewLabel_1);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		btnInsert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String imagePath = prod_pic_address.getText();
				String imagePath1 = "C:\\MakeProgram\\SGMP\\img\\"+imagePath.substring(imagePath.lastIndexOf("\\")+1);
				Product_db proddb = new Product_db();
				Product_model prod = new Product_model();
				
				prod.setProd_name(prod_name.getText());
				prod.setProd_price(prod_price.getText());
				prod.setProd_wearing_price(prod_wearing_price.getText());
				prod.setProd_pic(imagePath1);
				prod.setProd_main_category(prod_main_category.getText());
				prod.setProd_sub_category(prod_sub_category.getText());
				prod.setProd_Ssub_category(prod_ssub_category.getText());
				prod.setProd_wearing_price(prod_wearing_price.getText());
				int result=0,check1=0,check2=0;
				char temp;
				for(int i=0;i<prod.getProd_price().length();i++){
					temp = prod.getProd_price().charAt(i);
					if(temp >= 0x30 && temp <= 0x39) {
						check1=1;
					}
					else{
						check1=0;
						JOptionPane.showMessageDialog(null, "판매가격을 잘못 입력하셨습니다.");
						break;
					}
				}
				for(int i=0;i<prod.getProd_wearing_price().length();i++){
					temp = prod.getProd_wearing_price().charAt(i);
					if(temp >= 0x30 && temp <= 0x39) {
						check2=1;
					}
					else{
						check2=0;
						JOptionPane.showMessageDialog(null, "입고가격을 잘못 입력하셨습니다.");
						break;
					}
				}
				if(check1 == 1 && check2 == 1){
					result = proddb.prod_insert(prod);
					
					if(result == 1){
						
						String fileNm = imagePath;
				    	File file = new File(fileNm);
				    	File newFile = new File(imagePath1);
				    	
				    	if(file.exists()){
				    		file.renameTo(newFile);
				    	}
					}
					JOptionPane.showMessageDialog(null, "품목을 등록하였습니다.");
					dispose();
				}
			}
		});
		FileDialog dlg = new FileDialog(this,"저장",FileDialog.LOAD);
		btnPic.addActionListener(new ActionListener() {
			
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
	}
	public void actionPerformed(ActionEvent e){
		FileDialog dlg = new FileDialog(this,"저장",FileDialog.LOAD);
		
		dlg.setSize(300,200);
		dlg.show();
		
		prod_pic_address.setText(dlg.getDirectory() + dlg.getFile());
		String imagePath = prod_pic_address.getText();
		ImageIcon ic1 = new ImageIcon(imagePath);
    	Image img1 = ic1.getImage();
    	Image img2 = img1.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH);
    	ImageIcon ic2 = new ImageIcon(img2);
    	
    	prod_pic.setIcon(ic2);
    	//이미지 윈도우 폴더에 저장
    	/*String fileNm = imagePath;
    	File file = new File(fileNm);
    	File newFile = new File("C:\\MakeProgram\\SGMP\\img\\"+imagePath.substring(imagePath.lastIndexOf("\\")+1));
    	
    	if(file.exists()){
    		file.renameTo(newFile);
    	}*/
		
	}
	public void RemoveTxt(){
		prod_name.setText("");
		prod_main_category.setText("");
		prod_sub_category.setText("");
		prod_ssub_category.setText("");
		prod_wearing_price.setText("");
		prod_price.setText("");
		prod_pic_address.setText("");
		prod_pic.setIcon(null);
	}
}
