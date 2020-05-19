package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

import services.ProfileService;

public class ProfilePanel extends ViewPanel {
	
	ProfileService profileService; 
 	String username;
	JLabel user;
	JLabel fGame;
	JLabel fChar;
	

	public ProfilePanel(ProfileService profileService) {
		super();
		this.remove(game);
		activeViewName.setText("Profile");
		this.profileService = profileService;
		
	}
	
	public void draw(){
		this.add(user);
		user.setBounds(250, 50, 1000, 30);
		user.setForeground(Color.WHITE);
		
		this.add(fGame);
		fGame.setBounds(250, 150, 1000, 30);
		fGame.setForeground(Color.WHITE);
		this.add(fChar);
		fChar.setBounds(250, 250, 1000, 30);
		fChar.setForeground(Color.WHITE);
	}
	
	
	public void setConnected(String username) {
		this.add(activeViewName);
		this.username = username;
		user = new JLabel("User: " + username);
		fGame = new JLabel("Favorite Game: " + profileService.getFavGame(username));
		fChar = new JLabel("Favorite Character: " + profileService.getFavChar(username));
		this.draw();
		this.revalidate();
		this.repaint();
	}
	
	@Override
	public void setDisconnected() {
		this.remove(activeViewName);
		this.revalidate();
		this.repaint();
	}
	
	
}
