package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import databaseobjects.SelectedCharacter;

public class AddCharacter extends JPanel {
	
	String game;
	JButton addButton;
	JButton discard;
	JLabel nameLabel;
	JLabel originLabel;
	JLabel speedLabel;
	JLabel weightLabel;
	JTextField name;
	JTextArea origin;
	JTextField speed;
	JTextField weight;
	
	public AddCharacter(String game) {
		addLabels(game);
	}
	
	public AddCharacter(String game, SelectedCharacter character) {
		addLabels(game);
		
		this.name.setText(character.getNameText());
		this.origin.setText(character.getOriginText());
		this.speed.setText(character.getSpeedText());
		this.weight.setText(character.getWeightText());
		
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
		this.speedLabel = new JLabel("Speed:");
		this.add(speedLabel);
		this.speedLabel.setForeground(Color.WHITE);
		this.weightLabel = new JLabel("Weight:");
		this.add(weightLabel);
		this.weightLabel.setForeground(Color.WHITE);
		this.nameLabel.setFont(new Font("Serif", Font.BOLD, 24));
		this.originLabel.setFont(new Font("Serif", Font.BOLD, 24));
		this.speedLabel.setFont(new Font("Serif", Font.BOLD, 24));
		this.weightLabel.setFont(new Font("Serif", Font.BOLD, 24));
		
		this.name = new JTextField();
		this.add(name);
		this.origin = new JTextArea();
		this.add(origin);
		this.speed = new JTextField();
		this.add(speed);
		this.weight = new JTextField();
		this.add(weight);
		
		this.addButton.setBounds(300, 450, 75, 20);
		this.discard.setBounds(175, 450, 100, 20);
		this.nameLabel.setBounds(10, 20, 150, 40);
		this.name.setBounds(100, 20, 150, 40);
		this.originLabel.setBounds(10, 100, 150, 40);
		this.origin.setBounds(100, 100, 150, 100);
		this.speedLabel.setBounds(10, 250, 150, 40);
		this.speed.setBounds(100, 250, 150, 40);
		this.weightLabel.setBounds(10, 300, 150, 40);
		this.weight.setBounds(100, 300, 150, 40);
	}
	
	
}
