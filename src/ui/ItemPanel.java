package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import databaseobjects.GameItem;
import databaseobjects.SelectedItem;
import services.ItemService;


public class ItemPanel extends ViewPanel {

	ItemService itemService;
	SelectedItem selectedItem;
	ArrayList<GameItem> items;
	JButton leftButton;
	JButton rightButton;
	JButton addItemButton;
	AddItem addItem;
	int page = 0;

	public ItemPanel(ItemService itemService) {
		super();
		activeViewName.setText("Items");
		this.itemService = itemService;
		this.leftButton = new JButton("<");
		this.rightButton = new JButton(">");
		this.addItemButton = new JButton("+");
		this.leftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(page>0) {
					removeItems();
					page--;
					drawItems();
					revalidate();
					repaint();
				}
			}
		});

		this.rightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (page < items.size() / 48) {
					removeItems();
					page++;
					drawItems();
					revalidate();
					repaint();
				}
			}
		});
		this.addItemButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				drawAddStage();
			}
		});
		this.leftButton.setBounds(1145, 5, 50, 25);
		this.rightButton.setBounds(1200, 5, 50, 25);
		this.addItemButton.setBounds(1090, 5, 50, 25);
		this.addItem = new AddItem(null);
		this.selectedItem = new SelectedItem("", "", "");
	}

	public void setConnected(String currentGame) {
		this.removeAll();
		super.setConnected(currentGame);
		this.add(leftButton);
		this.add(rightButton);
		this.add(addItemButton);
		this.items = itemService.getItems(currentGame);
		this.drawItems();
		this.revalidate();
		this.repaint();
	}
	
	public void drawSelectedStage(String name) {
		this.selectedItem = itemService.getItem(game.getText(), name);
		this.removeAll();
		this.setConnected(this.game.getText());
		this.add(selectedItem);
		this.selectedItem.setLocation(10,40);
		
		this.selectedItem.getDelete().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(itemService.deleteItem(game.getText(), name)) setConnected(game.getText());
			}

		});
		this.selectedItem.getUpdate().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				addItem = new AddItem(game.getText(), selectedItem);
				remove(selectedItem);
				add(addItem);
				addItem.setLocation(10,40);
				revalidate();
				repaint();
				addItem.addButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
							
						if(itemService.updateItem(addItem.name.getText(), addItem.origin.getText(), addItem.type.getText(), game.getText())) {
							items = itemService.getItems(game.getText());
							drawItems();	
							drawSelectedStage(addItem.name.getText());
						}
						
					}

				});
				
				addItem.discard.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						setConnected(game.getText());
					}

				});
				
				
			}

		});
		
		this.selectedItem.revalidate();
		this.selectedItem.repaint();
		this.revalidate();
		this.repaint();
	}


	public void drawAddStage() {
		this.remove(selectedItem);
		this.addItem = new AddItem(this.game.getText());
		this.add(addItem);
		this.addItem.setLocation(10, 40);
		this.addItem.addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (itemService.addItem(addItem.name.getText(), addItem.origin.getText(), addItem.type.getText(),
						game.getText())) {
					items = itemService.getItems(game.getText());
					drawItems();
					drawSelectedStage(addItem.name.getText());
				}

			}

		});
		addItem.discard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setConnected(game.getText());
			}

		});
	}

	public void drawItems() {
		int hPos = 410;
		int vPos = 40;
		int consec = 0;
		for (int i = page * 48; i < page * 48 + 48 && i < items.size(); i++) {
			GameItem c = items.get(i);

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

			this.items.get(i).addMouseListener(new MouseListener() {

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

	public void removeItems() {
		for (int i = page * 48; i < page * 48 + 48 && i < items.size(); i++) {
			this.remove(items.get(i));
		}
	}

}

