package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.Customer_db;
import model.Customer_model;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;

public class Com_Code extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the dialog.
	 */
	public Com_Code() {
		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(false);
		setTitle("\uC2A4\uD3EC\uCE20\uC6A9\uD488\uAD00\uB9AC");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		//지점 번호 입력
		textField = new JTextField();
		textField.setBounds(36, 73, 354, 34);
		contentPanel.add(textField);
		textField.setColumns(10);
		//지점 접속
		JButton btnCom = new JButton("\uC811\uC18D");
		btnCom.setBounds(36, 217, 155, 34);
		contentPanel.add(btnCom);
		btnCom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String login ="";
				Customer_db cdb = new Customer_db();
				login=cdb.login(textField.getText(), passwordField.getText());
				
				if(!login.equals("0")) {
					mainFrame.com_code = textField.getText();
					mainFrame.setVisible(true);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "ID나 PW를 확인하세요.");
				}
				
			}
		});
		//프로그램 종료
		JButton btnExit = new JButton("\uC885\uB8CC");
		btnExit.setBounds(235, 217, 155, 34);
		contentPanel.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("\uC9C0\uC810\uBA85");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setBounds(36, 29, 354, 34);
		contentPanel.add(lblNewLabel);
		
		JLabel label = new JLabel("\uBE44\uBC00\uBC88\uD638");
		label.setFont(new Font("굴림", Font.PLAIN, 20));
		label.setBounds(36, 117, 354, 34);
		contentPanel.add(label);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(36, 170, 354, 37);
		contentPanel.add(passwordField);
		
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowColsing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
