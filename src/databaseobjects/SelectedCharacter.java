package databaseobjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import services.MoveService;
import ui.CompareWindow;

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
	JButton compare;
	
	JLabel damageFrames;
	JLabel directionLabel;
	JLabel typeLabel;
	JLabel directionExamples;
	JLabel typeExamples;
	JLabel frames;
	JTextField bFrames;
	JTextField eFrames;
	JTextField moveDirection;
	JTextField moveType;
	JButton findMove;
	JButton editMove;
	JButton deleteMove;
	JButton discard;
	JButton save;

	public SelectedCharacter(MoveService moveService, String game, String nameText, String origin, int speed, int weight) {
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
		
		damageFrames = new JLabel("");
		this.add(damageFrames);
		this.damageFrames.setForeground(Color.WHITE);
		
		moveDirection = new JTextField();
		this.add(moveDirection);
		
		moveType = new JTextField();
		this.add(moveType);
		
		bFrames = new JTextField();
		
		eFrames = new JTextField();
		
		directionLabel = new JLabel("Enter a move direction");
		this.add(directionLabel);
		this.directionLabel.setForeground(Color.WHITE);
		
		typeLabel = new JLabel("Enter a move type");
		this.add(typeLabel);
		this.typeLabel.setForeground(Color.WHITE);
		
		directionExamples = new JLabel("(up, down, forward, back)");
		this.add(directionExamples);
		this.directionExamples.setForeground(Color.WHITE);
		
		typeExamples = new JLabel("(strong, smash, special, aerial)");
		this.add(typeExamples);
		this.typeExamples.setForeground(Color.WHITE);
		
		delete = new JButton("X");
		this.add(delete);
		
		update = new JButton("Edit");
		this.add(update);
		
		compare = new JButton("Compare");
		this.add(compare);

		frames = new JLabel("Insert start/end frames");
		this.frames.setForeground(Color.WHITE);
		
		findMove = new JButton("Get move");
		this.add(findMove);
		findMove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					damageFrames.setText(moveService.getFrameData(nameText, game, moveType.getText(), moveDirection.getText()));
			}});
		
		editMove = new JButton("Add/edit move");
		this.add(editMove);
		editMove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				edit();
			}});
		
		deleteMove = new JButton("Delete move");
		this.add(deleteMove);
		deleteMove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveService.deleteMove(nameText, game, moveType.getText(), moveDirection.getText());
			}});
		
		save = new JButton("Save changes");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveService.updateMove(nameText, game, moveType.getText(), moveDirection.getText(), bFrames.getText(), eFrames.getText());
				discard();
			}});
		
		discard = new JButton("Back");
		discard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				discard();
			}});
		
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
		this.compare.setBounds(140, 440, 100, 25);
		
		this.damageFrames.setBounds(200, 140, 500, 25);
		this.typeLabel.setBounds(240, 160, 500, 25);
		this.typeExamples.setBounds(205, 175, 500, 25);
		this.moveType.setBounds(240, 200, 100, 25);
		this.directionLabel.setBounds(230, 230, 500, 25);
		this.directionExamples.setBounds(220, 245, 500, 25);
		this.moveDirection.setBounds(240, 270, 100, 25);
		this.findMove.setBounds(230, 320, 120, 25);
		this.editMove.setBounds(230, 350, 120, 25);
		this.deleteMove.setBounds(230, 380, 120, 25);
		this.frames.setBounds(230, 300, 200, 25);
		this.save.setBounds(230, 380, 120, 25);
		this.discard.setBounds(230, 350, 120, 25);
		this.bFrames.setBounds(245, 320, 40, 25);
		this.eFrames.setBounds(295, 320, 40, 25);
	}
	
	public void edit() {
		this.remove(findMove);
		this.remove(editMove);
		this.remove(deleteMove);
		this.add(save);
		this.add(discard);
		this.add(frames);
		this.add(bFrames);
		this.add(eFrames);
		repaint();
	}
	
	public void discard() {
		this.add(findMove);
		this.add(editMove);
		this.add(deleteMove);
		this.remove(save);
		this.remove(discard);
		this.remove(frames);
		this.remove(bFrames);
		this.remove(eFrames);
		repaint();
	}

	public JButton getDelete(){
		return delete;
	}
	
	public JButton getUpdate(){
		return update;
	}
	
	public JButton getCompare(){
		return compare;
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
