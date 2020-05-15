package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import databaseobjects.SelectedCharacter;
import databaseobjects.SelectedStage;

public class AddStage extends JPanel{

	String game;
	JButton addButton;
	JButton discard;
	JLabel nameLabel;
	JLabel originLabel;
	JLabel musicLabel;
	JTextField name;
	JTextArea origin;
	JTextField music;


	public AddStage(String game) {
		addLabels(game);
	}
	
	public AddStage(String game, SelectedStage stage) {
		addLabels(game);
		
		this.name.setText(stage.getNameText());
		this.origin.setText(stage.getOriginText());
		this.music.setText(stage.getMusicText());
		
		this.revalidate();
		this.repaint();
	}
	
	
	public void addLabels(String game) {
		this.setSize(400, 475);
		this.setLayout(null);
		this.setBackground(Color.DARK_GRAY);
		this.game = game;
		this.addButton = new JButton("Add");
		this.add(this.addButton);
		this.nameLabel = new JLabel("Name:");
		this.discard = new JButton("Discard");
		this.add(discard);
		this.add(nameLabel);
		this.nameLabel.setForeground(Color.WHITE);
		this.originLabel = new JLabel("Origin:");
		this.add(originLabel);
		this.originLabel.setForeground(Color.WHITE);
		this.musicLabel = new JLabel("Music:");
		this.add(musicLabel);
		this.musicLabel.setForeground(Color.WHITE);
		this.nameLabel.setFont(new Font("Serif", Font.BOLD, 24));
		this.originLabel.setFont(new Font("Serif", Font.BOLD, 24));
		this.musicLabel.setFont(new Font("Serif", Font.BOLD, 24));
		
		this.name = new JTextField();
		this.add(name);
		this.origin = new JTextArea();
		this.add(origin);
		this.music = new JTextField();
		this.add(music);
		
		this.addButton.setBounds(300, 450, 75, 20);
		this.discard.setBounds(175, 450, 100, 20);
		this.nameLabel.setBounds(10, 20, 150, 40);
		this.name.setBounds(100, 20, 150, 40);
		this.originLabel.setBounds(10, 100, 150, 40);
		this.origin.setBounds(100, 100, 150, 100);
		this.musicLabel.setBounds(10, 250, 150, 40);
		this.music.setBounds(100, 250, 150, 40);;
	}
	
	
}
