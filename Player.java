//done by CHAN HOH YUE 1141128352
import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class Player{
	private String name;
	private int[] currentPosition = new int[2];
	Movement playerMovement = new Movement();
	private LinkedList<Key> inventory = new LinkedList<Key>();

	public Player(String name, int row, int column){
		this.name = name;
		currentPosition[0] = row;
		currentPosition[1] = column;
	}

	public void addKey(Key key){

		for(int i=0; i < inventory.size(); i++){
			Key temp = inventory.get(i);
			if(key.getKeyName() == temp.getKeyName()){
				inventory.remove(i);
			}
		}

		inventory.addFirst(key);
	}

	public int getInventorySize(){
		return inventory.size();
	}

	public void clearInventory(){
		inventory.clear();
	}

	public LinkedList<Key> getInventory(){
		return inventory;
	}

	public void setInventory(LinkedList<Key> inventory){
		 this.inventory=inventory;
	}

	public String getName(){
		return name;
	}

	public int[] getCurPosition(){
		return currentPosition;
	}

	public void setCurPosition(int x, int y){

		currentPosition[0] = x;
		currentPosition[1] = y;
	}

	public boolean checkKey(int destinationX, int destinationY){

		if(getInventorySize()<1){
			return playerMovement.playerDefaultMove(currentPosition, destinationX, destinationY);
		} else {
			Key temp = inventory.get(0);
			if((temp.getKeyName()).equals("icon/a.gif")){
				return playerMovement.playerPinkeyMove(currentPosition, destinationX, destinationY);

			} else if ((temp.getKeyName()).equals("icon/b.gif")){
				return playerMovement.playerDonkeyMove(currentPosition, destinationX, destinationY);

			} else if ((temp.getKeyName()).equals("icon/c.gif")){
				return playerMovement.playerKeydiskMove(currentPosition, destinationX, destinationY);

			} else if ((temp.getKeyName()).equals("icon/d.gif")){
				return playerMovement.playerKeynoteMove(currentPosition, destinationX, destinationY);

			} else if ((temp.getKeyName()).equals("icon/e.gif")){
				return playerMovement.playerMonkeyMove(currentPosition, destinationX, destinationY);
			} else {
				return true;
			}

		}
	}

	public void getKeyInfo(JLabel keyInfo){
		if(getInventorySize()<1){
			keyInfo.setText("can move up to 2 squares in any direction");
		} else {
			Key temp = inventory.get(0);
			if((temp.getKeyName()).equals("icon/a.gif")){
				keyInfo.setText("can move only 1 square in any direction");

			} else if ((temp.getKeyName()).equals("icon/b.gif")){
				keyInfo.setText("can move up to 3 squares diagonally");

			} else if ((temp.getKeyName()).equals("icon/c.gif")){
				keyInfo.setText("can move up to 3 squares horizontally or vertically");

			} else if ((temp.getKeyName()).equals("icon/d.gif")){
				keyInfo.setText("can move only 2 square in any direction");

			} else  {
				keyInfo.setText("can move up to 3 squares in any direction");
			}

		}
	}
}