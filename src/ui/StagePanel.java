package ui;

import services.StageService;

public class StagePanel extends ViewPanel {
	
	StageService stageService;

	public StagePanel(StageService stageService) {
		super();
		activeViewName.setText("Stages");
		this.stageService = stageService;
	}
	
	
	
}
