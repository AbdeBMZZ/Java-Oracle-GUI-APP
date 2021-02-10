import java.awt.EventQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class registration {

	private JFrame frmAbdellahBoumaiza;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JButton btnNewButton_1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registration window = new registration();
					window.frmAbdellahBoumaiza.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public registration() {
		initialize();
	}
	private void initialize() {
		frmAbdellahBoumaiza = new JFrame();
		frmAbdellahBoumaiza.setTitle("app");
		frmAbdellahBoumaiza.setResizable(false);
		frmAbdellahBoumaiza.setBounds(100, 100, 599, 376);
		frmAbdellahBoumaiza.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAbdellahBoumaiza.getContentPane().setLayout(null);
		frmAbdellahBoumaiza.setVisible(true);
		JPanel panel = new JPanel();
		panel.setBounds(24, 11, 541, 257);
		frmAbdellahBoumaiza.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("username");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel.setBounds(97, 64, 68, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(97, 104, 68, 21);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Repeat your Password");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(97, 141, 129, 33);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(265, 64, 130, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(265, 107, 130, 20);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(265, 150, 130, 21);
		panel.add(passwordField_1);
		

		
		JLabel lblNewLabel_3 = new JLabel("Create your account !");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(203, 11, 147, 33);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Your account has been created successfully");
		lblNewLabel_4.setForeground(new Color(0, 128, 0));
		lblNewLabel_4.setBackground(Color.GREEN);
		lblNewLabel_4.setBounds(145, 196, 287, 16);
		lblNewLabel_4.setVisible(false);
		panel.add(lblNewLabel_4);
		
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().equals("") && !String.valueOf(passwordField.getPassword()).equals("")
						&& !String.valueOf(passwordField_1.getPassword()).equals("")) {
					if(String.valueOf(passwordField_1.getPassword()).equals(String.valueOf(passwordField.getPassword())))
					{
						InsertIntodb(textField.getText(), String.valueOf(passwordField.getPassword()));
					}else
						System.out.println("passwords do not match");

				}	
				else
					System.out.println("please check your information");

			}
		});
		
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnNewButton.setBackground(new Color(216, 191, 216));
		btnNewButton.setBounds(188, 223, 130, 34);
		panel.add(btnNewButton);
		
		lblNewLabel_5 = new JLabel("you can't use this username");
		lblNewLabel_5.setForeground(new Color(255, 0, 0));
		lblNewLabel_5.setBounds(168, 185, 205, 14);
		lblNewLabel_5.setVisible(false);
		panel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Already have an account ?");
		lblNewLabel_6.setBounds(38, 304, 176, 14);
		frmAbdellahBoumaiza.getContentPane().add(lblNewLabel_6);
		
		btnNewButton_1 = new JButton("Login");
		btnNewButton_1.setBackground(new Color(216, 191, 216));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new login();
				frmAbdellahBoumaiza.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(211, 297, 88, 28);
		frmAbdellahBoumaiza.getContentPane().add(btnNewButton_1);
	}
	
	
	public void InsertIntodb(String x, String y) {
		String cnxString = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "system";
		String password = "yassuo2001.";

		try {
			Connection con = DriverManager.getConnection(cnxString,username,password);
			CallableStatement cs1 = con.prepareCall("{? = call checkUsername(?)}");
			cs1.setString(2, x);
		    cs1.registerOutParameter(1, java.sql.Types.INTEGER);
			cs1.executeQuery();
			
			if(cs1.getInt(1)==1) {
				CallableStatement cs = con.prepareCall("{call Registration_proc(?,?)}");
				cs.setString(1, x);
				cs.setString(2, y);
				cs.executeQuery();
				lblNewLabel_4.setVisible(true);
				lblNewLabel_5.setVisible(false);
			}
			else {
				lblNewLabel_5.setVisible(true);
				lblNewLabel_4.setVisible(false);

			}
			con.close();

		} catch (SQLException e) {
			System.out.println("oops..., error");
			e.printStackTrace();
		}
	}
	

}
