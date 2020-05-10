package databaseobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import utils.Reader;

public class Game extends JComponent{

	JLabel name;
	JLabel dateMade;
	JLabel consoles;
	ImageIcon image;
	JLabel imageLabel;
	
	public Game(String name, Date dateMade, String consoles, ImageIcon image){
		this.name = new JLabel(name);
		this.dateMade = new JLabel(dateMade.toString());
		this.consoles = new JLabel(consoles);
		this.image = image;
		this.imageLabel = new JLabel(this.image);
		this.setForeground(Color.WHITE);
		this.setLayout(null);
		this.draw();
	}
	
	public Game(String name, Date dateMade, String consoles){
		this.name = new JLabel(name);
		this.dateMade = new JLabel(dateMade.toString());
		this.consoles = new JLabel(consoles);
		this.setForeground(Color.WHITE);
		this.setLayout(null);
		this.image = new ImageIcon(Reader.resizedImage("/smashDatabaseFrontend/assets/defaultGameImage.jpg",100, 100));
		this.imageLabel = new JLabel(this.image);
		this.draw();
	}
	
	public void draw() {
		this.add(name);
		this.name.setBounds(0,0,100,20);
		
		this.add(imageLabel);
		this.imageLabel.setBounds(0,30,100,100);
		
		this.add(consoles);
		this.consoles.setBounds(0,150,100,20);
		
		this.add(dateMade);
		this.dateMade.setBounds(0,180,100,20);
	}	
	
}
