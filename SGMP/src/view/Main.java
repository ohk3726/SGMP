package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JToolBar;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Panel;
import java.awt.FlowLayout;
import javax.swing.JCheckBoxMenuItem;
import java.awt.List;
import java.awt.Button;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import javax.swing.JSplitPane;
import java.awt.Choice;

public class Main extends JFrame {
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(241, 311, 1011, 360);
		getContentPane().add(table);
		
		JLabel label = new JLabel("\uB4F1\uB85D\uC77C");
		label.setBounds(241, 251, 120, 50);
		label.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(373, 251, 217, 50);
		textField.setColumns(10);
		getContentPane().add(textField);
		
		JLabel label_1 = new JLabel("\uD488\uBAA9\uBA85");
		label_1.setBounds(241, 191, 120, 50);
		label_1.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(373, 191, 217, 50);
		textField_1.setColumns(10);
		getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("\uAC80\uC0C9");
		btnNewButton.setBounds(1093, 251, 159, 50);
		btnNewButton.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		getContentPane().add(btnNewButton);
		
		JLabel label_2 = new JLabel("\uD488\uBAA9\uBC88\uD638");
		label_2.setBounds(241, 131, 120, 50);
		label_2.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		getContentPane().add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(373, 131, 217, 50);
		textField_2.setColumns(10);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(636, 251, 217, 50);
		textField_3.setColumns(10);
		getContentPane().add(textField_3);
		
		JLabel label_3 = new JLabel("~");
		label_3.setBounds(602, 251, 32, 50);
		label_3.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		getContentPane().add(label_3);
		
		JList list = new JList();
		list.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"\u25B6\uD310\uB9E4\uAD00\uB9AC", "\u25B6\uC7AC\uACE0\uAD00\uB9AC", "       - \uC804\uCCB4\uD488\uBAA9", "       - \uC785\uACE0\uC77C\uAD04\uB4F1\uB85D", "       - \uD488\uBAA9\uB4F1\uB85D", "       - \uD488\uBAA9\uC218\uC815", "       - \uD488\uBAA9\uC0AD\uC81C", "\u25B6\uACAC\uC801\uBC0F\uBC1C\uC8FC\uAD00\uB9AC", "       - \uACAC\uC801\uB4F1\uB85D", "       - \uACAC\uC801\uAD00\uB9AC", "       - \uBC1C\uC8FC\uB4F1\uB85D", "       - \uBC1C\uC8FC\uAD00\uB9AC", "\u25B6\uD68C\uACC4\uAD00\uB9AC", "       - \uAE30\uAC04\uBCC4\uB9E4\uCD9C", "       - \uBBF8\uC218\uAE08\uAD00\uB9AC", "\u25B6\uACE0\uAC1D\uAD00\uB9AC", "       - \uC804\uCCB4\uACE0\uAC1D", "       - \uACE0\uAC1D\uB4F1\uB85D", "       - \uACE0\uAC1D\uC218\uC815", "       - \uACE0\uAC1D\uC0AD\uC81C"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(12, 131, 217, 540);
		getContentPane().add(list);
		
		JButton btnNewButton_1 = new JButton("\uBD88\uB7EC\uC624\uAE30");
		btnNewButton_1.setBounds(12, 10, 125, 111);
		getContentPane().add(btnNewButton_1);
		
		JButton button = new JButton("\uC800\uC7A5");
		button.setBounds(149, 10, 125, 111);
		getContentPane().add(button);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\uD30C\uC77C");
		mnNewMenu.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\uC800\uC7A5");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_2 = new JMenu("\uC885\uB8CC");
		mnNewMenu_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_2);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
