package ui;

import services.ItemService;

public class ItemPanel extends ViewPanel {
	
	ItemService itemService;

	public ItemPanel(ItemService itemService) {
		super();
		this.remove(game);
		activeViewName.setText("Items");
		this.itemService = itemService;
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
