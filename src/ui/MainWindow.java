package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import databaseobjects.Game;
import listeners.Listener;
import services.CharacterService;
import services.DatabaseConnection;
import services.GameService;
import services.ItemService;
import services.ProfileService;
import services.StageService;
import utils.Reader;


public class MainWindow {	
	
	JFrame frame;
	DatabaseConnection dbConnection;
	LoginPanel loginPanel;
	GamesPanel gamesPanel;
	TabPanel tabPanel;
	ViewPanel characterPanel;
	ViewPanel stagePanel;
	ViewPanel itemPanel;
	ProfilePanel profilePanel;
	String currentGame;
	
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
		
		//add panels
		this.addLoginPanel();
		this.addViewPanels();
		this.addTabPanel();
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
	
	public void addTabPanel() {
		this.tabPanel = new TabPanel();
		frame.add(tabPanel);
		tabPanel.setLocation(0,50);
		tabPanel.getCharacters().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.add(characterPanel);
				frame.remove(stagePanel);
				frame.remove(itemPanel);
				frame.remove(profilePanel);
				frame.revalidate();
				frame.repaint();
			}
		});
		tabPanel.getStages().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.remove(characterPanel);
				frame.add(stagePanel);
				frame.remove(itemPanel);
				frame.remove(profilePanel);
				frame.revalidate();
				frame.repaint();
			}
		});
		tabPanel.getItems().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.remove(characterPanel);
				frame.remove(stagePanel);
				frame.add(itemPanel);
				frame.remove(profilePanel);
				frame.revalidate();
				frame.repaint();
			}
		});
		tabPanel.getProfile().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.remove(characterPanel);
				frame.remove(stagePanel);
				frame.remove(itemPanel);
				frame.add(profilePanel);
				frame.revalidate();
				frame.repaint();
			}
		});
		
	}
	
	public void addViewPanels() {
		this.characterPanel = new CharacterPanel(new CharacterService(dbConnection));
		this.stagePanel = new StagePanel(new StageService(dbConnection));
		this.itemPanel = new ItemPanel(new ItemService(dbConnection));
		this.profilePanel = new ProfilePanel(new ProfileService(dbConnection));
		this.characterPanel.setLocation(0, 80);
		this.stagePanel.setLocation(0, 80);
		this.itemPanel.setLocation(0, 80);
		this.profilePanel.setLocation(0, 80);
		frame.add(characterPanel);
	}
	
	public void changeCurrentGame(String gameName) {
		this.currentGame = gameName;
		characterPanel.setConnected(gameName);
		itemPanel.setConnected(gameName);
		stagePanel.setConnected(gameName);
	}
	
	public void disconnect() {
		loginPanel.setDisconnected();
		gamesPanel.setDisconnected();
		tabPanel.setDisconnected();
		characterPanel.setDisconnected();
		itemPanel.setDisconnected();
		stagePanel.setDisconnected();
		profilePanel.setDisconnected();
	}	
	
	public void connect(String username) {
		loginPanel.setConnected(username);
		gamesPanel.setConnected();
		tabPanel.setConnected();
		profilePanel.setConnected();
		for(Game G : gamesPanel.games) {
			G.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					changeCurrentGame(G.getNameText());
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

			});
		}
		
	}
	
	public void show() {
		frame.setVisible(true);
		//attempt login with defaults
		if(dbConnection.connect(Reader.getAttribute("serverName"), Reader.getAttribute("databaseName"), Reader.getAttribute("defaultUsername"), Reader.getAttribute("defaultPassword"))){
			this.connect(Reader.getAttribute("defaultUsername"));
		}
	}

}
