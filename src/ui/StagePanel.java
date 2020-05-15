package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import databaseobjects.GameCharacter;
import databaseobjects.GameStage;
import databaseobjects.SelectedCharacter;
import databaseobjects.SelectedStage;
import services.StageService;

public class StagePanel extends ViewPanel {

	StageService stageService;
	SelectedStage selectedStage;
	ArrayList<GameStage> stages;
	JButton leftButton;
	JButton rightButton;
	JButton addStageButton;
	AddStage addStage;
	int page = 0;

	public StagePanel(StageService stageService) {
		super();
		// this.remove(game);
		activeViewName.setText("Stages");
		this.stageService = stageService;
		this.leftButton = new JButton("<");
		this.rightButton = new JButton(">");
		this.addStageButton = new JButton("+");
		this.leftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(page>0) {
					removeStages();
					page--;
					drawStages();
					revalidate();
					repaint();
				}
			}
		});

		this.rightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (page < stages.size() / 48) {
					removeStages();
					page++;
					drawStages();
					revalidate();
					repaint();
				}
			}
		});
		this.addStageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				drawAddStage();
			}
		});
		this.leftButton.setBounds(1145, 5, 50, 25);
		this.rightButton.setBounds(1200, 5, 50, 25);
		this.addStageButton.setBounds(1090, 5, 50, 25);
		this.addStage = new AddStage(null);
		this.selectedStage = new SelectedStage("", "", "");
	}

	public void setConnected(String currentGame) {
		this.removeAll();
		super.setConnected(currentGame);
		this.add(leftButton);
		this.add(rightButton);
		this.add(addStageButton);
		this.stages = stageService.getStages(currentGame);
		this.revalidate();
		this.repaint();
	}
	
	public void drawSelectedStage(String name) {
		this.selectedStage = stageService.getStage(game.getText(), name);
		this.removeAll();
		this.setConnected(this.game.getText());
		this.add(selectedStage);
		this.selectedStage.setLocation(10,40);
		
		this.selectedStage.getDelete().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(stageService.deleteStage(game.getText(), name)) setConnected(game.getText());
			}

		});
		this.selectedStage.getUpdate().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				addStage = new AddStage(game.getText(), selectedStage);
				remove(selectedStage);
				add(addStage);
				addStage.setLocation(10,40);
				revalidate();
				repaint();
				addStage.addButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
							
						if(stageService.updateStages(addStage.name.getText(), addStage.origin.getText(), addStage.music.getText(), game.getText())) {
							stages = stageService.getStages(game.getText());
							drawStages();	
							drawSelectedStage(addStage.name.getText());
						}
						
					}

				});
				
				addStage.discard.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						setConnected(game.getText());
					}

				});
				
				
			}

		});
		
		this.selectedStage.revalidate();
		this.selectedStage.repaint();
		this.revalidate();
		this.repaint();
	}


	public void drawAddStage() {
		this.remove(selectedStage);
		this.addStage = new AddStage(this.game.getText());
		this.add(addStage);
		this.addStage.setLocation(10, 40);
		this.addStage.addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (stageService.addStage(addStage.name.getText(), addStage.origin.getText(), addStage.music.getText(),
						game.getText())) {
					stages = stageService.getStages(game.getText());
					drawStages();
					drawSelectedStage(addStage.name.getText());
				}

			}

		});
		addStage.discard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setConnected(game.getText());
			}

		});
	}

	public void drawStages() {
		int hPos = 410;
		int vPos = 40;
		int consec = 0;
		for (int i = page * 48; i < page * 48 + 48 && i < stages.size(); i++) {
			GameStage c = stages.get(i);

			this.add(c);
			c.setLocation(hPos, vPos);
			if (consec >= 7) {
				vPos += 80;
				hPos = 410;
				consec = 0;
			} else {
				hPos += 105;
				consec++;
			}

			this.stages.get(i).addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					drawSelectedStage(c.getNameText());
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

	public void removeStages() {
		for (int i = page * 48; i < page * 48 + 48 && i < stages.size(); i++) {
			this.remove(stages.get(i));
		}
	}

}
