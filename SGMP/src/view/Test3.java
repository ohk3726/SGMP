package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;

public class Test3 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test3 frame = new Test3();
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
	public Test3() {
		setTitle("\uC18C\uD3EC\uCE20\uC6A9\uD488\uAD00\uB9AC\uD504\uB85C\uADF8\uB7A8");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 789, 628);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\uD310\uB9E4");
		menuBar.add(menu);
		
		JMenu menu_1 = new JMenu("\uD310\uB9E4\uAD00\uB9AC");
		menuBar.add(menu_1);
		
		JMenu menu_2 = new JMenu("\uC7AC\uACE0\uAD00\uB9AC");
		menuBar.add(menu_2);
		
		JMenu menu_3 = new JMenu("\uACAC\uC801\uBC0F\uBC1C\uC8FC\uAD00\uB9AC");
		menuBar.add(menu_3);
		
		JMenu menu_4 = new JMenu("\uD68C\uACC4\uAD00\uB9AC");
		menuBar.add(menu_4);
		
		JMenu menu_5 = new JMenu("\uACE0\uAC1D\uAD00\uB9AC");
		menuBar.add(menu_5);
		
		JMenu menu_6 = new JMenu("\uD658\uACBD\uC124\uC815");
		menuBar.add(menu_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("\uC870\uD68C");
		button.setBounds(0, 0, 70, 37);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\uB2EB\uAE30");
		button_1.setBounds(410, 0, 70, 37);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\uC778\uC1C4");
		button_2.setBounds(342, 0, 70, 37);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("\uC5D1\uC140");
		button_3.setBounds(274, 0, 70, 37);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("\uC800\uC7A5");
		button_4.setBounds(206, 0, 70, 37);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("\uC0AD\uC81C");
		button_5.setBounds(138, 0, 70, 37);
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("\uCD94\uAC00");
		button_6.setBounds(69, 0, 70, 37);
		contentPane.add(button_6);
		
		JLabel label = new JLabel("\uD488\uBAA9\uBC88\uD638");
		label.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label.setBounds(218, 72, 80, 38);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(310, 72, 158, 38);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(310, 130, 158, 37);
		contentPane.add(textField_1);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(22, 59, 163, 170);
		contentPane.add(canvas);
		
		JLabel label_1 = new JLabel("\uD488\uBAA9\uBA85");
		label_1.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label_1.setBounds(218, 127, 80, 38);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\uB2E8\uAC00");
		label_2.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label_2.setBounds(218, 189, 80, 38);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(310, 192, 158, 37);
		contentPane.add(textField_2);
	}
}
