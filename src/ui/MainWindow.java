package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import listeners.Listener;
import services.DatabaseConnection;
import services.GameService;
import utils.Reader;


public class MainWindow {	
	
	JFrame frame;
	DatabaseConnection dbConnection;
	LoginPanel loginPanel;
	GamesPanel gamesPanel;
	
	public MainWindow() {
		//frame settings
		frame = new JFrame("Super Smash Database");
		frame.setSize(1280, 800);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//create connection
		dbConnection = new DatabaseConnection();
		Listener listener = new Listener(dbConnection);
		frame.addWindowListener(listener);
		
		
		this.addLoginPanel();
		this.addGamePanel();
		

		
		
		
		
		
		
		
		
		

	}
	
	public void addLoginPanel() {
		loginPanel = new LoginPanel();
		frame.add(loginPanel);
		loginPanel.setLocation(0,0);
		loginPanel.disconnectButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(dbConnection.closeConnection())disconnect();
			}	
		});
		
		loginPanel.loginButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(dbConnection.connect(Reader.getAttribute("serverName"),Reader.getAttribute("databaseName"),loginPanel.usernameField.getText(), new String(loginPanel.passwordField.getPassword()))) {
					connect(loginPanel.usernameField.getText());
				}
			}	
		});
		
		loginPanel.registerButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}	
		});
	}
	
	public void addGamePanel() {
		gamesPanel = new GamesPanel(new GameService(dbConnection));
		frame.add(gamesPanel);
		gamesPanel.setLocation(0,600);

	}
	
	
	
	
	
	public void disconnect() {
		loginPanel.setDisconnected();
		gamesPanel.depopulate();
	}	
	
	public void connect(String username) {
		loginPanel.setConnected(username);
		gamesPanel.populate();
	}
	
	public void show() {
		frame.setVisible(true);
		//attempt login with defaults
		if(dbConnection.connect(Reader.getAttribute("serverName"), Reader.getAttribute("databaseName"), Reader.getAttribute("defaultUsername"), Reader.getAttribute("defaultPassword"))){
			this.connect(Reader.getAttribute("defaultUsername"));
		}
	}

}
