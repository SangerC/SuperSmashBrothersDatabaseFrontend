package ui;

import services.CharacterService;

public class CharacterPanel extends ViewPanel {

	CharacterService characterService;
	
	public CharacterPanel(CharacterService characterService) {
		super();
		activeViewName.setText("Characters");
		this.characterService = characterService;
	}
	
	
}
