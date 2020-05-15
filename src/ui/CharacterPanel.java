package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;

import databaseobjects.GameCharacter;
import databaseobjects.SelectedCharacter;
import services.CharacterService;

public class CharacterPanel extends ViewPanel {

	CharacterService characterService;
	SelectedCharacter selectedCharacter;
	AddCharacter addCharacter;
	ArrayList<GameCharacter> characters;
	int page;
	JButton leftButton;
	JButton rightButton;
	JButton addCharacterButton;
	
	public CharacterPanel(CharacterService characterService) {
		super();
		activeViewName.setText("Characters");
		this.characterService = characterService;
		page=0;
		this.leftButton = new JButton("<");
		this.rightButton = new JButton(">");
		this.addCharacterButton = new JButton("+");
		this.leftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(page>0) {
					removeCharacters();
					page--;
					drawCharacters();
					revalidate();
					repaint();
				}
			}
		});
		this.rightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(page<characters.size()/48) {
					removeCharacters();
					page++;
					drawCharacters();
					revalidate();
					repaint();
				}
			}
		});
		this.addCharacterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				drawAddCharacter();
			}
		});
		this.leftButton.setBounds(1145, 5, 50, 25);
		this.rightButton.setBounds(1200, 5, 50, 25);
		this.addCharacterButton.setBounds(1090, 5, 50, 25);
		this.addCharacter = new AddCharacter(null);
		this.selectedCharacter = new SelectedCharacter("");
	}
	
	@Override
	public void setConnected(String currentGame){
		this.removeAll();
		super.setConnected(currentGame);
		this.add(leftButton);
		this.add(rightButton);
		this.add(addCharacterButton);
		this.characters = characterService.getCharacters(currentGame);
		this.drawCharacters();
		this.revalidate();
		this.repaint();
	}
	
	public void drawSelectedCharacter() {
		this.remove(addCharacter);
		this.add(selectedCharacter);
		this.selectedCharacter.setLocation(10,40);
		this.revalidate();
		this.repaint();
	}
	
	public void drawAddCharacter() {
		this.remove(selectedCharacter);
		this.addCharacter = new AddCharacter(this.game.getText());
		this.add(addCharacter);
		this.addCharacter.setLocation(10,40);
		this.addCharacter.addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int speed = Integer.valueOf(addCharacter.speed.getText());
				int weight = Integer.valueOf(addCharacter.weight.getText());
					
				if(characterService.addCharacter(game.getText(), addCharacter.name.getText(), addCharacter.origin.getText(), speed, weight)) {
					characters = characterService.getCharacters(game.getText());
					drawCharacters();	
				}
				
			}

		});
	}
	
	public void drawCharacters() {
		int hPos = 410;
		int vPos = 40;
		int consec = 0;
		for(int i =page*48; i < page*48+48&&i<characters.size(); i++) {
			GameCharacter c = characters.get(i);
			
			this.add(c);
			c.setLocation(hPos, vPos);
			if(consec >= 7) {
				vPos+=80;
				hPos=410;
				consec=0;
			}
			else {
				hPos+= 105;
				consec++;
			}
			
			this.characters.get(i).addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					selectedCharacter = new SelectedCharacter(c.getNameText());
					drawSelectedCharacter();
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
		}
	}
	
	public void removeCharacters() {
		for(int i =page*48; i < page*48+48&&i<characters.size(); i++) {
			this.remove(characters.get(i));
		}
	}

}
