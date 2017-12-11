//concept is discuss by all group member
//code by CHAN HOH YUE 1141128352

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.util.Random;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class board{
	JFrame game = new JFrame("Treasure Hunter");
	JPanel board = new JPanel(new GridLayout(9,9,0,0));
	JPanel playerInventory = new JPanel();
	JPanel[] playerPanel = new JPanel[4];
	JLabel itemIcon[][] = new JLabel[4][5];
	JButton[][] button = new JButton[9][9];
	JMenuBar mb = new JMenuBar();
	JMenu menu = new JMenu("File");
	JMenuItem saveGame = new JMenuItem("Save");
	JMenuItem loadGame = new JMenuItem("Load");
	JPanel turnPanel = new JPanel();
	JLabel turns = new JLabel("");
	JLabel keyInfo = new JLabel("");
	JPanel keyInfoPanel = new JPanel();
	JLabel[] playerIcon = new JLabel[4];
	JLabel bag = new JLabel("Inventory");
	Font font = new Font("Courier", Font.BOLD,20);
	Random rand = new Random();

	private int moveX;
	private int moveY;
	private boolean gameTime = true;
	private int count = 1;
	private Player ban = new Player("icon/1.gif", 0, 0);
	private Player ark = new Player("icon/2.gif", 0, 8);
	private Player can = new Player("icon/3.gif", 8, 0);
	private Player doz = new Player("icon/4.gif", 8, 8);
	private OperatingFactory factory = new OperatingFactory();
	private Chest chest = new Chest();
	private Key pinkey = new Key("icon/a.gif");
	private Key donkey = new Key("icon/b.gif");
	private Key keydisk = new Key("icon/c.gif");
	private Key keynote = new Key("icon/d.gif");
	private Key monkey = new Key("icon/e.gif");

	public board(){
		String player[] = {"icon/1.gif", "icon/2.gif", "icon/3.gif", "icon/4.gif"};
		String image[] = {"icon/a.gif","icon/b.gif","icon/c.gif","icon/d.gif","icon/e.gif"};
		
		//create button
		for (int x=0;x<9;x++) {
			for(int y=0; y<9; y++) {
				button[x][y] = new JButton();
				board.add(button[x][y]);
			}
		}

		//add actionlistener to button
		for (int x=0;x<9;x++) {
			for(int y=0; y<9; y++) {
				final int finalx=x;
				final int finaly=y;
				button[x][y].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent evt){
						start(finalx, finaly, player);
						setKeyInfor(count, keyInfo);
					}
				});
			}
		}
		
		//preset the player location
		button[0][0].setIcon(new ImageIcon(player[0]));
		button[0][8].setIcon(new ImageIcon(player[1]));
		button[8][0].setIcon(new ImageIcon(player[2]));
		button[8][8].setIcon(new ImageIcon(player[3]));
		button[4][4].setIcon(new ImageIcon("icon/5.gif"));

		//random key position
		for (int i=0; i<5; i++){
			int x = rand.nextInt(9);
			int y = rand.nextInt(9); 

			while (button[x][y].getIcon() != null){
				x = rand.nextInt(9);
				y = rand.nextInt(9);
			}
			if(image[i] == "icon/a.gif"){
				pinkey.setKeyPosition(x, y);
			} else if (image[i] == "icon/b.gif"){
				donkey.setKeyPosition(x, y);
			} else if (image[i] == "icon/c.gif"){
				keydisk.setKeyPosition(x, y);
			} else if (image[i] == "icon/d.gif"){
				keynote.setKeyPosition(x, y);
			} else {
				monkey.setKeyPosition(x, y);
			}
			button[x][y].setIcon(new ImageIcon(image[i]));
		}

		bag.setFont(font);
		playerInventory.add(bag);

		//create player panel in inventory
		for (int i = 0; i < 4; i++) {
			playerPanel[i] = new JPanel();
			playerIcon[i] = new JLabel("Player " + (i+1));
			playerIcon[i].setIcon(new ImageIcon(player[i]));
			playerPanel[i].setPreferredSize(new Dimension(400, 50));
			playerInventory.add(playerPanel[i]);
			playerPanel[i].add(playerIcon[i]);

			for(int j=0; j<5; j++){
				itemIcon[i][j] = new JLabel();
				itemIcon[i][j].setIcon(null);
				playerPanel[i].add(itemIcon[i][j]);
			}
		}

		//save game function
		saveGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				SaveFile SF = new SaveFile();
				try{
					SF.openFile();
					SF.savePlayerPosition(ban);
					SF.savePlayerPosition(ark);
					SF.savePlayerPosition(can);
					SF.savePlayerPosition(doz);
					SF.savePlayerTurn(count);
					SF.savePlayerKeyInventory(ban);
					SF.savePlayerKeyInventory(ark);
					SF.savePlayerKeyInventory(can);
					SF.savePlayerKeyInventory(doz);
					SF.saveKeyPosition(pinkey);
					SF.saveKeyPosition(donkey);
					SF.saveKeyPosition(keydisk);
					SF.saveKeyPosition(keynote);
					SF.saveKeyPosition(monkey);
					SF.closeFile();
				}catch(Exception e){
					errorMsg("Cannot save file", "Error");
				}
			}
		});

		//load game function
		loadGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){

				ban.clearInventory();
				ark.clearInventory();
				can.clearInventory();
				doz.clearInventory();

				LoadFile LF = new LoadFile();
				if(LF.OpenFile()) {
					LF.LoadFilePlayer(ban);
					LF.LoadFilePlayer(ark);
					LF.LoadFilePlayer(can);
					LF.LoadFilePlayer(doz);
					LF.CloseFile();

					LF.OpenFile();
					count = LF.LoadFilePlayerTurn();
					LF.CloseFile();
					LF.OpenFile();
					LF.LoadPlayerInventory(ban);
					LF.CloseFile();
					LF.OpenFile();
					LF.LoadPlayerInventory(ark);
					LF.CloseFile();
					LF.OpenFile();
					LF.LoadPlayerInventory(can);
					LF.CloseFile();
					LF.OpenFile();
					LF.LoadPlayerInventory(doz);
					LF.CloseFile();
					LF.OpenFile();
					LF.LoadFileKeyPosition(pinkey);
					LF.CloseFile();
					LF.OpenFile();
					LF.LoadFileKeyPosition(donkey);
					LF.CloseFile();
					LF.OpenFile();
					LF.LoadFileKeyPosition(keydisk);
					LF.CloseFile();
					LF.OpenFile();
					LF.LoadFileKeyPosition(keynote);
					LF.CloseFile();
					LF.OpenFile();
					LF.LoadFileKeyPosition(monkey);
					LF.CloseFile();

					for(int i=0; i<9; i++) {
						for (int j=0; j<9; j++) {
							if(button[i][j].getIcon() != null) {
								String tempName = (button[i][j].getIcon()).toString();
								if(tempName == "icon/a.gif") {
									button[i][j].setIcon(null);
								} else if (tempName == "icon/b.gif") {
									button[i][j].setIcon(null);
								} else if (tempName == "icon/c.gif") {
									button[i][j].setIcon(null);
								} else if (tempName == "icon/d.gif") {
									button[i][j].setIcon(null);
								} else if (tempName == "icon/e.gif") {
									button[i][j].setIcon(null);
								} else if (tempName == "icon/1.gif") {
									button[i][j].setIcon(null);
								} else if (tempName == "icon/2.gif") {
									button[i][j].setIcon(null);
								} else if (tempName == "icon/3.gif") {
									button[i][j].setIcon(null);
								} else if (tempName == "icon/4.gif") {
									button[i][j].setIcon(null);
								} else {
									;
								}
							}
						}
					}

					button[pinkey.getKeyPosition()[0]][pinkey.getKeyPosition()[1]].setIcon(new ImageIcon("icon/a.gif"));
					button[donkey.getKeyPosition()[0]][donkey.getKeyPosition()[1]].setIcon(new ImageIcon("icon/b.gif"));
					button[keydisk.getKeyPosition()[0]][keydisk.getKeyPosition()[1]].setIcon(new ImageIcon("icon/c.gif"));
					button[keynote.getKeyPosition()[0]][keynote.getKeyPosition()[1]].setIcon(new ImageIcon("icon/d.gif"));
					button[monkey.getKeyPosition()[0]][monkey.getKeyPosition()[1]].setIcon(new ImageIcon("icon/e.gif"));
					button[ban.getCurPosition()[0]][ban.getCurPosition()[1]].setIcon(new ImageIcon("icon/1.gif"));
					button[ark.getCurPosition()[0]][ark.getCurPosition()[1]].setIcon(new ImageIcon("icon/2.gif"));
					button[can.getCurPosition()[0]][can.getCurPosition()[1]].setIcon(new ImageIcon("icon/3.gif"));
					button[doz.getCurPosition()[0]][doz.getCurPosition()[1]].setIcon(new ImageIcon("icon/4.gif"));

					if(count%4 == 0) {
						turns.setText("Player turn: Player 4");
					} else {
						turns.setText("Player turn: Player " + (count%4));
					}
					game.setTitle("Treasure Hunter " + "- Round: " + (count/4));

					setInventoryPanel(ban);
					setInventoryPanel(ark);
					setInventoryPanel(can);
					setInventoryPanel(doz);

					setKeyInfor(count, keyInfo);
				}
			}
		});

		game.setTitle("Treasure Hunter " + "- Round: " + (count/4+1));
		keyInfo.setText("can move up to 2 squares in any direction");
		turns.setText("Player turn: Player " + (count%4));

		menu.add(saveGame);
		menu.add(loadGame);
		mb.add(menu);
		turnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		turnPanel.add(turns);
		keyInfoPanel.setLayout(new FlowLayout());
		keyInfoPanel.add(keyInfo);
		mb.add(keyInfoPanel);
		mb.add(turnPanel);
		game.setJMenuBar(mb);
		board.setPreferredSize(new Dimension(1000, 750));
		playerInventory.setPreferredSize(new Dimension(400, 750));
		game.add(board, BorderLayout.CENTER);
		game.add(playerInventory, BorderLayout.EAST);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.pack();
		game.setSize(1050,770);
		game.setLocationRelativeTo(null);
		game.setVisible(true);
	}

	//start function. when click button this function will called
	public void start (int x, int y, String[] player){
		if (count%4 == 1){
			movePlayer(ban, x, y, player);

		} else if (count%4 == 2) {
			movePlayer(ark, x, y, player);

		} else if (count%4 == 3) {
			movePlayer(can, x, y, player);

		} else {
			movePlayer(doz, x, y, player);
		}
	}

	//desplay the key information to user
	public void setKeyInfor(int count, JLabel keyInfo){
		if (count%4 == 1){
			ban.getKeyInfo(keyInfo);

		} else if (count%4 == 2) {
			ark.getKeyInfo(keyInfo);

		} else if (count%4 == 3) {
			can.getKeyInfo(keyInfo);

		} else {
			doz.getKeyInfo(keyInfo);
		}
	}

	//random key again when the key has been taken
	public void randomKey(int finalx, int finaly){

		int tx = rand.nextInt(9);
		int ty = rand.nextInt(9);

		while (button[tx][ty].getIcon() != null){
			tx = rand.nextInt(9);
			ty = rand.nextInt(9);
		}

		String keyName = (button[finalx][finaly].getIcon()).toString();
		if(keyName == "icon/a.gif"){
			pinkey.setKeyPosition(tx, ty);
		} else if (keyName == "icon/b.gif"){
			donkey.setKeyPosition(tx, ty);
		} else if (keyName == "icon/c.gif"){
			keydisk.setKeyPosition(tx, ty);
		} else if (keyName == "icon/d.gif"){
			keynote.setKeyPosition(tx, ty);
		} else {
			monkey.setKeyPosition(tx, ty);
		}
		button[tx][ty].setIcon(button[finalx][finaly].getIcon());
	}

	//to determine the player movement 
	public void movePlayer(Player player, int x, int y, String[] ply) {
		boolean checkStatus = player.checkKey(x, y);
		if(button[x][y].getIcon() != null){
			if(((button[x][y].getIcon()).toString()) == ply[0]){
			} else if(((button[x][y].getIcon()).toString()) == ply[1]){
			} else if(((button[x][y].getIcon()).toString()) == ply[2]){
			} else if(((button[x][y].getIcon()).toString()) == ply[3]){
			} else if(!checkStatus){
			} else if(x==4 && y==4){
				if(player.getInventory().size()>4){
					button[x][y].setIcon(button[player.getCurPosition()[0]][player.getCurPosition()[1]].getIcon());
					player.setCurPosition(x,y);
					button[player.getCurPosition()[0]][player.getCurPosition()[1]].setIcon(null);
					if(chest.checkKey(player)){
						new board();
						game.dispose();
					}
				}
			} else {
				turns.setText("Player turn: Player " + (count%4+1));
				String item = (button[x][y].getIcon()).toString();
				Key tempKey = factory.getKeyInstance(item);
				player.addKey(tempKey);

				randomKey(x, y);

				button[x][y].setIcon(button[player.getCurPosition()[0]][player.getCurPosition()[1]].getIcon());
				button[player.getCurPosition()[0]][player.getCurPosition()[1]].setIcon(null);
				player.setCurPosition(x,y);
				game.setTitle("Treasure Hunter " + "- Round: " + (count/4+1));
				count++;

				setInventoryPanel(player);
			}
		} else {
			if(!checkStatus){
			} else {
				turns.setText("Player turn: Player " + (count%4+1));
				button[x][y].setIcon(button[player.getCurPosition()[0]][player.getCurPosition()[1]].getIcon());
				button[player.getCurPosition()[0]][player.getCurPosition()[1]].setIcon(null);
				player.setCurPosition(x,y);
				game.setTitle("Treasure Hunter " + "- Round: " + (count/4+1));
				count++;
			}
		}
	}

	//create player icon at inventory
	public void setInventoryPanel(Player player){
		int playerIndex = factory.getPlayerIndex(player);
		LinkedList<Key> tempItem = player.getInventory();
		for(int i=0; i<tempItem.size(); i++){
			String keyname = (tempItem.get(i)).getKeyName();
			itemIcon[playerIndex][i].setIcon(new ImageIcon(keyname));
		}
	}

	//alert message
	public static void errorMsg(String msg, String er)
    {
        JOptionPane.showMessageDialog(null, msg, "Alert: " + er, JOptionPane.INFORMATION_MESSAGE);
    }

}