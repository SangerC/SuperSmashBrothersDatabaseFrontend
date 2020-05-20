package databaseobjects;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SelectedCharacter extends JPanel{
	
	JLabel name;
	JLabel origin;
	JLabel originLabel;
	JLabel speed;
	JLabel speedLabel;
	JLabel weight;
	JLabel weightLabel;
	JButton delete;
	JButton update;
	
	JLabel DamageFrames;
	JLabel directionLabel;
	JLabel typeLabel;
	JLabel directionExamples;
	JLabel typeExamples;
	JTextField moveDirection;
	JTextField moveType;
	JButton findMove;

	public SelectedCharacter(String nameText, String origin, int speed, int weight) {
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
		
		speedLabel = new JLabel("Speed:");
		this.add(speedLabel);
		this.speedLabel.setFont(new Font("Serif", Font.BOLD, 24));
		this.speedLabel.setForeground(Color.WHITE);
		
		weightLabel = new JLabel("Weight:");
		this.add(weightLabel);
		this.weightLabel.setFont(new Font("Serif", Font.BOLD, 24));
		this.weightLabel.setForeground(Color.WHITE);
		
		moveDirection = new JTextField();
		this.add(moveDirection);
		
		moveType = new JTextField();
		this.add(moveType);
		
		directionLabel = new JLabel("Enter a move direction");
		this.add(directionLabel);
		this.directionLabel.setForeground(Color.WHITE);
		
		typeLabel = new JLabel("Enter a move type");
		this.add(typeLabel);
		this.typeLabel.setForeground(Color.WHITE);
		
		directionExamples = new JLabel("(up, down, left, right)");
		this.add(directionExamples);
		this.directionExamples.setForeground(Color.WHITE);
		
		typeExamples = new JLabel("(strong, smash, special, aerial)");
		this.add(typeExamples);
		this.typeExamples.setForeground(Color.WHITE);
		
		this.delete = new JButton("X");
		this.add(delete);
		
		this.update = new JButton("Edit");
		this.add(update);
		
		this.findMove = new JButton("Get move");
		this.add(findMove);
		
		this.origin = new JLabel(origin);
		this.origin.setForeground(Color.WHITE);
		this.add(this.origin);
		this.speed = new JLabel(Integer.toString(speed));
		this.speed.setForeground(Color.WHITE);
		this.add(this.speed);
		this.weight = new JLabel(Integer.toString(weight));
		this.weight.setForeground(Color.WHITE);
		this.add(this.weight);
		
		this.name.setBounds(130, 5, 150, 35);
		this.originLabel.setBounds(5, 200, 100, 200);
		this.origin.setBounds(100, 200, 100, 200);
		this.speed.setBounds(100, 300, 150, 200);
		this.speedLabel.setBounds(5, 300, 150, 200);
		this.weight.setBounds(100, 325, 150, 200);
		this.weightLabel.setBounds(5, 325, 150, 200);
		this.delete.setBounds(340, 440, 50, 25);
		this.update.setBounds(250, 440, 80, 25);
		
		this.typeLabel.setBounds(240, 200, 500, 25);
		this.typeExamples.setBounds(205, 215, 500, 25);
		this.moveType.setBounds(240, 240, 100, 25);
		this.directionLabel.setBounds(230, 280, 500, 25);
		this.directionExamples.setBounds(235, 295, 500, 25);
		this.moveDirection.setBounds(240, 320, 100, 25);
		this.findMove.setBounds(240, 360, 100, 25);
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
	
	public String getWeightText() {
		return weight.getText();
	}
	
	public String getSpeedText() {
		return speed.getText();
	}
	
}
