package databaseobjects;

import java.awt.Color;
import java.awt.Image;
import java.sql.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import utils.Reader;

public class Game extends JPanel{

	JLabel name;
	JLabel dateMade;
	JLabel consoles;
	ImageIcon image;
	JLabel imageLabel;
	JButton remove;
	JButton edit;
	
	public Game(String name, Date dateMade, String consoles, Image image){
		this.name = new JLabel(name);
		this.dateMade = new JLabel(dateMade.toString());
		this.consoles = new JLabel(consoles);
		this.name.setForeground(Color.WHITE);
		this.name.setBackground(Color.DARK_GRAY);
		this.name.setOpaque(true);
		this.dateMade.setForeground(Color.WHITE);
		this.dateMade.setBackground(Color.DARK_GRAY);
		this.dateMade.setOpaque(true);
		this.consoles.setForeground(Color.WHITE);
		this.consoles.setBackground(Color.DARK_GRAY);
		this.consoles.setOpaque(true);

		this.setBorder(new LineBorder(Color.BLACK));
		this.setLayout(null);

		if(image==null) {
			this.image = new ImageIcon(Reader.resizedImage("assets/defaultGameImage.jpg", 196, 121));
		}
		else {
			this.image=new ImageIcon(image.getScaledInstance(196, 121, Image.SCALE_SMOOTH));
		}
		this.imageLabel = new JLabel(this.image);
		this.imageLabel.setBackground(Color.BLACK);
		this.imageLabel.setOpaque(true);
		
		this.edit = new JButton("Edit");
		this.remove = new JButton("X");
		
		this.setSize(200, 125);
		this.draw();
	}
	
	public void draw() {
		this.add(edit);
		this.edit.setBounds(80, 103, 60, 15);
		
		this.add(remove);
		this.remove.setBounds(147, 103, 45, 15);
		
		this.add(name);
		this.name.setBounds(5,5,190,20);
		
		this.add(consoles);
		this.consoles.setBounds(5,85,190,20);
		
		this.add(dateMade);
		this.dateMade.setBounds(5,100,190,20);

		this.add(imageLabel);
		this.imageLabel.setBounds(2,2,196,121);
		
	}	
	
	public String getNameText() {
		return this.name.getText();
	}
	
	public String getDateText() {
		return this.dateMade.getText();
	}
	
	public String getConsolesText() {
		return this.consoles.getText();
	}
	
	public JButton getRemove() {
		return remove;
	}
	
	public JButton getEdit() {
		return edit;
	}
	
}
