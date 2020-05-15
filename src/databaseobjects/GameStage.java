package databaseobjects;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import utils.Reader;

public class GameStage extends JPanel {
	
	ImageIcon image;
	JLabel imageLabel;
	JLabel name;
	
	public GameStage(String name, Image image) {
		this.setSize(100, 75);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		this.name= new JLabel(name);
		this.name.setForeground(Color.white);
		
		if(image==null) {
			this.image = new ImageIcon(Reader.resizedImage("assets/unknownCharacter.jpg", 90, 54));
		}
		else {
			this.image=new ImageIcon(image.getScaledInstance(196, 121, Image.SCALE_SMOOTH));
		}
		this.imageLabel = new JLabel(this.image);
		this.add(this.name);
		this.add(imageLabel);
		this.name.setBounds(15,-3,80,20);
		this.imageLabel.setBounds(5,17,90,54);
		
	}
	
	public String getNameText() {
		return name.getText();
	}
	
}
