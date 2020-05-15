package databaseobjects;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectedStage extends JPanel{
	
	JLabel name;
	JLabel origin;
	JLabel originLabel;
	JLabel music;
	JLabel musicLabel;
	JButton delete;
	JButton update;

	public SelectedStage(String nameText, String origin, String music) {
		this.setBackground(Color.DARK_GRAY);
		this.setSize(400, 475);
		this.setLayout(null);
		
		name = new JLabel(nameText);
		this.add(name);
		this.name.setFont(new Font("Serif", Font.BOLD, 24));
		this.name.setForeground(Color.WHITE);
		
		originLabel = new JLabel("Origin:");
		this.add(originLabel);
		this.originLabel.setFont(new Font("Serif", Font.BOLD, 24));
		this.originLabel.setForeground(Color.WHITE);
		
		musicLabel = new JLabel("Music:");
		this.add(musicLabel);
		this.musicLabel.setFont(new Font("Serif", Font.BOLD, 24));
		this.musicLabel.setForeground(Color.WHITE);
				
		this.delete = new JButton("X");
		this.add(delete);
		
		this.update = new JButton("Edit");
		this.add(update);
		
		this.origin = new JLabel(origin);
		this.origin.setForeground(Color.WHITE);
		this.add(this.origin);
		this.music = new JLabel(music);
		this.music.setForeground(Color.WHITE);
		this.add(this.music);
		
		this.name.setBounds(130, 5, 150, 35);
		this.originLabel.setBounds(5, 200, 100, 200);
		this.origin.setBounds(100, 200, 100, 200);
		this.music.setBounds(100, 300, 150, 200);
		this.musicLabel.setBounds(5, 300, 150, 200);
		this.delete.setBounds(340, 440, 50, 25);
		this.update.setBounds(250, 440, 80, 25);
	}

	public JButton getDelete(){
		return delete;
	}
	
	public JButton getUpdate(){
		return update;
	}
	
	public String getNameText() {
		return name.getText();
	}
	
	public String getOriginText() {
		return origin.getText();
	}
	
	public String getMusicText() {
		return music.getText();
	}
	
}
