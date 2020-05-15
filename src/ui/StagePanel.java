package ui;

import javax.swing.JLabel;

import services.StageService;

public class StagePanel extends ViewPanel {

	StageService stageService;

	public StagePanel(StageService stageService) {
		super();
		this.remove(game);
		activeViewName.setText("Stages");
		this.stageService = stageService;
	}

	public void setConnected() {
		this.add(activeViewName);
		this.revalidate();
		this.repaint();
	}

	@Override
	public void setDisconnected() {
		this.remove(activeViewName);
		this.revalidate();
		this.repaint();
	}

}
