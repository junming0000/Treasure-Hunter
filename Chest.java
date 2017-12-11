//done by PUA JUN MING 1141128767

import java.awt.*;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Chest
{
	private boolean[] containKey = {false, false, false, false, false};
	OperatingFactory factory = new OperatingFactory();

	//check player has collect all the keys.
	public boolean checkKey(Player win)
	{
		LinkedList<Key> key = win.getInventory();
		String[] tempKey = {"icon/a.gif", "icon/b.gif", "icon/c.gif", "icon/d.gif", "icon/e.gif"};
		for(int i=0; i<5; i++){
			for(int j=0; j<key.size(); j++){
				if((key.get(j)).getKeyName().equals(tempKey[i])){
					containKey[i] = true;
				}
			}
		}

		return checkWin(win);
	}
	
	//check the winning condition
	public boolean checkWin(Player player)
	{	
		if(player.getCurPosition()[0] == 4 && player.getCurPosition()[1] == 4){
			if(containKey[0] && containKey[1] && containKey[2] && containKey[3] && containKey[4]){
				int playerIndex = factory.getPlayerIndex(player);
				JOptionPane.showMessageDialog(null, "Player " + (playerIndex+1) + " win", "Congratulations!!!!!", JOptionPane.INFORMATION_MESSAGE);
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "You haven't collect all the keys!!!!", "Warning!!", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		} else {
			return false;
		}
	}
}