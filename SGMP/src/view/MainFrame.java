package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class MainFrame extends JFrame {
	private CardLayout cards = new CardLayout();
	private JPanel contentPane;
	private String name;
	public String com_code;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Com_Code frame = new Com_Code();
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
	public MainFrame() {
		setResizable(false);
		//������ ����
		setTitle("\uC2A4\uD3EC\uCE20\uC6A9\uD488\uAD00\uB9AC\uD504\uB85C\uADF8\uB7A8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//������ ������ ����
		setBounds(100, 100, 1600, 930);
		//���� �г� ����
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//�޴��� ���� �� ����
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1600, 30);
		contentPane.add(menuBar);
		//�ǸŸ޴� ���� �� ����
		JMenu menu = new JMenu("\uD310\uB9E4");
		menu.setFont(new Font("���� ���", Font.PLAIN, 20));
		menuBar.add(menu);
		//�ǸŸ޴� ���� �޴� ���� �� ����
		JMenuItem sale_manegement = new JMenuItem("\uD310\uB9E4");
		sale_manegement.setFont(new Font("���� ���", Font.PLAIN, 20));
		menu.add(sale_manegement);
		//�Ǹ� �гη� �̵��ϴ� �̺�Ʈ
		sale_manegement.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getContentPane().remove(1);
				getContentPane().add(new Shopping_Basket(),1);
				getContentPane().revalidate();
				getContentPane().repaint();
			}
		});
		//�ǸŰ��� �޴� ���� �� ����
		JMenu menu_1 = new JMenu("\uD310\uB9E4\uAD00\uB9AC");
		menu_1.setFont(new Font("���� ���", Font.PLAIN, 20));
		menuBar.add(menu_1);
		//�ǸŰ��� ���� �޴� ���� �� ����
		JMenuItem cust_sale_manegement = new JMenuItem("\uD310\uB9E4\uAD00\uB9AC");
		cust_sale_manegement.setFont(new Font("���� ���", Font.PLAIN, 20));
		menu_1.add(cust_sale_manegement);
		//�ǸŰ��� �гη� �̵��ϴ� �̺�Ʈ
		cust_sale_manegement.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getContentPane().remove(1);
				getContentPane().add(new Cus_sale(),1);
				getContentPane().revalidate();
				getContentPane().repaint();
			}
		});
		//������ �޴� ���� �� ����
		JMenu menu_2 = new JMenu("\uC7AC\uACE0\uAD00\uB9AC");
		menu_2.setFont(new Font("���� ���", Font.PLAIN, 20));
		menuBar.add(menu_2);
		//������ ���� �޴� ���� �� ����
		JMenuItem product_manegement_1 = new JMenuItem("\uC7AC\uACE0\uAD00\uB9AC");
		product_manegement_1.setFont(new Font("���� ���", Font.PLAIN, 20));
		menu_2.add(product_manegement_1);
		//������� ���� �޴��� ǰ���� �޴� ���� �� ����
		JMenuItem product_insert = new JMenuItem("\uC0C1\uD488\uB4F1\uB85D");
		product_insert.setFont(new Font("���� ���", Font.PLAIN, 20));
		menu_2.add(product_insert);
		//ǰ���� �޴��� ���̾˷α׷� ����
		Insert insert = new Insert(this);
		//ǰ���� �޴� ���̾�α� �̵��ϴ� �̺�Ʈ
		product_insert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				insert.RemoveTxt();
				insert.setModal(true);
				insert.setVisible(true);
				
				getContentPane().remove(1);
				getContentPane().add(new Product(com_code),1);
				getContentPane().revalidate();
				getContentPane().repaint();
			}
		});
		//������ �гη� �̵��ϴ� �̺�Ʈ
		product_manegement_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getContentPane().remove(1);
				getContentPane().add(new Product(com_code),1);
				getContentPane().revalidate();
				getContentPane().repaint();
			}
		});
		//���� �� ���� �޴� ���� �� ����
		JMenu menu_3 = new JMenu("\uACAC\uC801\uBC0F\uBC1C\uC8FC\uAD00\uB9AC");
		menu_3.setFont(new Font("���� ���", Font.PLAIN, 20));
		menuBar.add(menu_3);
		//���ְ��� �޴� ����
		JMenuItem Auto_Order_menu = new JMenuItem("\uC790\uB3D9 \uBC1C\uC8FC \uAD00\uB9AC");
		Auto_Order_menu.setFont(new Font("���� ���", Font.PLAIN, 20));
		menu_3.add(Auto_Order_menu);
		
		JMenuItem Order_menu = new JMenuItem("\uACAC\uC801 / \uBC1C\uC8FC \uAD00\uB9AC");
		Order_menu.setFont(new Font("���� ���", Font.PLAIN, 20));
		menu_3.add(Order_menu);
		//���ְ��� �޴��� �̵��ϴ� �̺�Ʈ
		Auto_Order_menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getContentPane().remove(1);
				getContentPane().add(new Auto_Order(com_code),1);
				getContentPane().revalidate();
				getContentPane().repaint();
			}
		});
		
		Order_menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getContentPane().remove(1);
				getContentPane().add(new Order(com_code),1);
				getContentPane().revalidate();
				getContentPane().repaint();
			}
		});
		//ȸ����� ���� �� ����
		JMenu menu_4 = new JMenu("\uD68C\uACC4\uAD00\uB9AC");
		menu_4.setFont(new Font("���� ���", Font.PLAIN, 20));
		menuBar.add(menu_4);
		//ȸ����� ���� �޴� ���� �� ����
		JMenuItem Accounting_Management = new JMenuItem("\uC9C0\uC815\uAE30\uAC04 \uD310\uB9E4");
		Accounting_Management.setFont(new Font("���� ���", Font.PLAIN, 20));
		menu_4.add(Accounting_Management);
		
		JMenuItem Accounting_Group = new JMenuItem("\uC6D4\uBCC4, \uBD84\uAE30\uBCC4 \uD310\uB9E4");
		Accounting_Group.setFont(new Font("���� ���", Font.PLAIN, 20));
		menu_4.add(Accounting_Group);
		//ȸ����� �гη� �̵��ϴ� �̺�Ʈ
		Accounting_Management.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getContentPane().remove(1);
				getContentPane().add(new Accounting(),1);
				getContentPane().revalidate();
				getContentPane().repaint();
			}
		});
		Accounting_Group.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getContentPane().remove(1);
				getContentPane().add(new Accounting_Group(),1);
				getContentPane().revalidate();
				getContentPane().repaint();
			}
		});
		//������ �޴� ���� �� ����
		JMenu menu_5 = new JMenu("\uACE0\uAC1D\uAD00\uB9AC");
		menu_5.setFont(new Font("���� ���", Font.PLAIN, 20));
		menuBar.add(menu_5);
		//������ ���� �޴� ���� �� ����
		JMenuItem Customer_Management = new JMenuItem("\uACE0\uAC1D\uAD00\uB9AC");
		Customer_Management.setFont(new Font("���� ���", Font.PLAIN, 20));
		menu_5.add(Customer_Management);
		//������ �������� �̵��ϴ� �̺�Ʈ
		Customer_Management.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getContentPane().remove(1);
				getContentPane().add(new Customer_Management(),1);
				getContentPane().revalidate();
				getContentPane().repaint();
			}
		});
		 
		getContentPane().add(new Shopping_Basket(),1);
		System.out.println(com_code);
		
		setVisible(true);
	}
}
