import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(50, 70, 63, 25);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(50, 125, 63, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(179, 72, 121, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(179, 122, 121, 20);
		panel.add(passwordField);
		
		JCheckBox checkB = new JCheckBox("show password");
		checkB.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(checkB.isSelected())
					passwordField.setEchoChar((char)0);
				else
					passwordField.setEchoChar('*');
			}
		});
		checkB.setFont(new Font("Tahoma", Font.PLAIN, 9));
		checkB.setBounds(179, 149, 111, 14);
		panel.add(checkB);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cnxString = "jdbc:oracle:thin:@localhost:1521:xe";
				String username = "system";
				String password = "yassuo2001.";
				try {
					Connection con = DriverManager.getConnection(cnxString,username,password);
					CallableStatement cs1 = con.prepareCall("{? = call checkLogin(?, ?)}");
					cs1.setString(2, textField.getText());
					cs1.setString(3, String.valueOf(passwordField.getPassword()));
					
				    cs1.registerOutParameter(1, java.sql.Types.INTEGER);
					cs1.executeQuery();
					if(cs1.getInt(1)==1) {
						System.out.println("welcome");
					}
					else
						System.out.println("oops...");

					con.close();

				} catch (SQLException ex) {
					System.out.println("oops..., error");
					ex.printStackTrace();
				}
			}
		});
		
		btnNewButton.setBackground(new Color(216, 191, 216));
		btnNewButton.setBounds(144, 196, 111, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("don't have an account ? sign up ");
		lblNewLabel_2.setBounds(95, 24, 196, 14);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("HERE !");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new registration();
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBackground(new Color(216, 191, 216));
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_1.setBounds(298, 21, 85, 20);
		panel.add(btnNewButton_1);
	}
}
