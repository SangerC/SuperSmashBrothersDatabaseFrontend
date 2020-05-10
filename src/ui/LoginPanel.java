package ui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class LoginPanel extends JPanel {

	JLabel loginStatus;
	JLabel userNameText;
	JLabel passwordText;
	JTextField usernameField;
	JPasswordField passwordField;
	JButton loginButton;
	JButton disconnectButton;
	JButton registerButton;
	
	public LoginPanel() {
		this.setBackground(Color.darkGray);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setSize(1280, 50);
		this.setLayout(null);
		
		//Add Components
		this.loginStatus = new JLabel();
		this.add(this.loginStatus);
		this.loginStatus.setBounds(20, 15, 500, 20);
		
		this.usernameField = new JTextField();
		this.add(this.usernameField);
		this.usernameField.setBounds(550, 10, 200, 30);
		
		this.userNameText = new JLabel("Username");
		this.userNameText.setForeground(Color.BLACK);
		this.add(this.userNameText);
		this.userNameText.setBounds(465, 15, 85, 20);
		
		this.passwordText = new JLabel("Password");
		this.passwordText.setForeground(Color.BLACK);
		this.add(this.passwordText);
		this.passwordText.setBounds(775, 15, 85, 20);
		
		this.passwordField = new JPasswordField();
		this.add(this.passwordField);
		this.passwordField.setBounds(860, 10, 200, 30);
		
		this.loginButton = new JButton("Login");
		this.add(this.loginButton);
		this.loginButton.setBounds(1070, 10, 75, 30);
		
		this.disconnectButton = new JButton("Disconnect");
		this.add(this.disconnectButton);
		this.disconnectButton.setBounds(1150, 10, 115, 30);
		
		this.registerButton = new JButton("Register");
		this.add(this.registerButton);
		this.registerButton.setBounds(1150, 10, 115, 30);
		this.setDisconnected();
	}
	
	public void setDisconnected() {
		this.loginStatus.setForeground(Color.RED);
		this.loginStatus.setText("Disconnected");
		this.remove(this.disconnectButton);
		this.add(loginButton);
		this.add(registerButton);
		this.revalidate();
		this.repaint();
	}
	
	public void setConnected(String username) {
		this.loginStatus.setForeground(Color.GREEN);
		this.loginStatus.setText("Connected As: "+ username);
		this.remove(loginButton);
		this.remove(registerButton);
		this.add(disconnectButton);
		this.revalidate();
		this.repaint();
	}
	
}