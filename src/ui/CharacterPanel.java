package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTextField;
import databaseobjects.GameCharacter;
import databaseobjects.SelectedCharacter;
import services.CharacterService;
import services.MoveService;

public class CharacterPanel extends ViewPanel {

	CharacterService characterService;
	MoveService moveService;
	SelectedCharacter selectedCharacter;
	AddCharacter addCharacter;
	ArrayList<GameCharacter> characters;
	int page;
	JButton leftButton;
	JButton rightButton;
	JButton addCharacterButton;
	JTextField search;
	
	public CharacterPanel(CharacterService characterService, MoveService moveService) {
		super();
		activeViewName.setText("Characters");
		this.characterService = characterService;
		this.moveService = moveService;
		page=0;
		this.leftButton = new JButton("<");
		this.rightButton = new JButton(">");
		this.addCharacterButton = new JButton("+");
		this.search = new JTextField();
		this.search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setConnected(game.getText());
			}
			
		});
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
		this.search.setBounds(860, 5, 200, 25);
		this.addCharacter = new AddCharacter(null);
		this.selectedCharacter = new SelectedCharacter(moveService, "", "", "", 0,0);
	}
	
	@Override
	public void setConnected(String currentGame){
		this.removeAll();
		super.setConnected(currentGame);
		this.add(leftButton);
		this.add(rightButton);
		this.add(addCharacterButton);
		this.add(search);
		this.characters = characterService.getCharacters(currentGame);
		this.drawCharacters();
		this.revalidate();
		this.repaint();
	}
	
	public void drawSelectedCharacter(String name) {
		this.selectedCharacter = characterService.getCharacter(moveService, game.getText(), name);
		this.removeAll();
		this.setConnected(this.game.getText());
		this.add(selectedCharacter);
		this.selectedCharacter.setLocation(10,40);
		
		this.selectedCharacter.getDelete().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(characterService.deleteCharacter(game.getText(), name)) setConnected(game.getText());
			}

		});
		
		this.selectedCharacter.getUpdate().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addCharacter = new AddCharacter(game.getText(), selectedCharacter);
				remove(selectedCharacter);
				add(addCharacter);
				addCharacter.setLocation(10,40);
				revalidate();
				repaint();
				addCharacter.addButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {

						int speed = Integer.valueOf(addCharacter.speed.getText());
						int weight = Integer.valueOf(addCharacter.weight.getText());
							
						if(characterService.updateCharacter(game.getText(), addCharacter.name.getText(), addCharacter.origin.getText(), speed, weight)) {
							characters = characterService.getCharacters(game.getText());
							drawCharacters();	
							drawSelectedCharacter(addCharacter.name.getText());
						}
						
					}

				});
				
				addCharacter.discard.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						setConnected(game.getText());
					}

				});
				
				
			}

		});
		
		this.selectedCharacter.getCompare().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CompareWindow cw = new CompareWindow(moveService, name, characterService);
				cw.draw();
			}
			
		});
		
		this.selectedCharacter.revalidate();
		this.selectedCharacter.repaint();
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
					drawSelectedCharacter(addCharacter.name.getText());
				}
				
			}

		});
		addCharacter.discard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setConnected(game.getText());
			}

		});
	}
	
	public void drawCharacters() {
		ArrayList<GameCharacter> filtered = new ArrayList<GameCharacter>();
		for(GameCharacter c : characters) {
			if(c.getNameText().contains(search.getText())) {
				filtered.add(c);
			}
		}
		
		int hPos = 410;
		int vPos = 40;
		int consec = 0;
		for(int i =page*48; i < page*48+48&&i<filtered.size(); i++) {
			GameCharacter c = filtered.get(i);
			
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
					drawSelectedCharacter(c.getNameText());
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
