package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import services.ProfileService;

public class ProfilePanel extends ViewPanel {
	
	MainWindow window;
	ProfileService profileService; 
 	String username;
	JLabel user;
	JLabel rank;
	JLabel fGame;
	JLabel fChar;
	JLabel eGame;
	JLabel eChar;
	JButton edit;
	JButton delete;
	JButton save;
	JButton discard;
	JTextField editGame;
	JTextField editChar;
	
	
	

	public ProfilePanel(ProfileService profileService) {
		super();
		this.remove(game);
		activeViewName.setText("Profile");
		this.profileService = profileService;
		
	}
	
	public void draw(){
		user = new JLabel("User: " + username);
		this.add(user);
		user.setBounds(250, 50, 1000, 30);
		user.setForeground(Color.WHITE);
		
		rank = new JLabel("Rank: " + profileService.getRank(username));
		this.add(rank);
		rank.setBounds(250, 150, 1000, 30);
		rank.setForeground(Color.WHITE);
		
		fGame = new JLabel("Favorite Game: " + profileService.getFavGame(username));
		this.add(fGame);
		fGame.setBounds(250, 250, 1000, 30);
		fGame.setForeground(Color.WHITE);
		
		fChar = new JLabel("Favorite Character: " + profileService.getFavChar(username));
		this.add(fChar);
		fChar.setBounds(250, 350, 1000, 30);
		fChar.setForeground(Color.WHITE);
		
		edit = new JButton("Edit Favorites");
		this.add(edit);
		edit.setBounds(250, 425, 200, 30);
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				edit();
			}});
		
		delete = new JButton("Delete Profile");
		this.add(delete);
		delete.setBounds(500, 425, 200, 30);
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					window.disconnect();
					profileService.deleteProfile(username);
			}});
		
		eGame = new JLabel("Enter favorite game");
		eGame.setBounds(250, 240, 200, 30);
		eGame.setForeground(Color.WHITE);
		
		eChar = new JLabel("Enter favorite character");
		eChar.setBounds(250, 340, 200, 30);
		eChar.setForeground(Color.WHITE);
		
		editGame = new JTextField();
		editGame.setBounds(250, 270, 200, 30);
		
		editChar = new JTextField();
		editChar.setBounds(250, 370, 200, 30);
		
		save = new JButton("Save Changes");
		save.setBounds(500, 425, 200, 30);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				profileService.updateFav(username, editGame.getText(), editChar.getText());
				updateFavs();
			}});
		
		discard = new JButton("Discard Changes");
		discard.setBounds(250, 425, 200, 30);
		discard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				discard();
			}});
	}
	
	public void edit() {
		this.remove(fGame);
		this.remove(fChar);
		this.remove(edit);
		this.remove(delete);
		this.add(editGame);
		this.add(editChar);
		this.add(eGame);
		this.add(eChar);
		this.add(save);
		this.add(discard);
		this.repaint();
	}
	
	public void discard() {
		this.add(fGame);
		this.add(fChar);
		this.add(edit);
		this.add(delete);
		this.remove(editGame);
		this.remove(editChar);
		this.remove(eGame);
		this.remove(eChar);
		this.remove(save);
		this.remove(discard);
		this.repaint();
	}
	
	public void updateFavs() {
		fGame.setText("Favorite Game: " + profileService.getFavGame(username));
		fChar.setText("Favorite Character: " + profileService.getFavChar(username));
		discard();
	}
	
	
	public void setConnected(String username, MainWindow window) {
		this.add(activeViewName);
		this.username = username;
		this.window = window;
		this.draw();
		this.revalidate();
		this.repaint();
	}
	
	@Override
	public void setDisconnected() {
		this.remove(activeViewName);
		this.remove(user);
		this.remove(rank);
		this.remove(fGame);
		this.remove(fChar);
		this.remove(edit);
		this.remove(delete);
		this.remove(editGame);
		this.remove(editChar);
		this.remove(eGame);
		this.remove(eChar);
		this.remove(save);
		this.remove(discard);
		this.revalidate();
		this.repaint();
	}
	
	
}
