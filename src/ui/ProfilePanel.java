package ui;

import services.ProfileService;

public class ProfilePanel extends ViewPanel {
	
	ProfileService profileService; 

	public ProfilePanel(ProfileService profileService) {
		super();
		this.remove(game);
		activeViewName.setText("Profile");
		this.profileService = profileService;
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
