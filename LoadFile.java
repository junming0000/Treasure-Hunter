//done by NICHOLAS HO WAI KEN 1141128
import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class LoadFile {
	private Scanner x;
	private String line;
	private int counter;
	private int count;
	private String playerName;
	private int playerPositionX;
	private int playerPositionY;
	private LinkedList<Key> inventoryBan = new LinkedList<Key>();
	private LinkedList<Key> inventoryArk = new LinkedList<Key>();
	private LinkedList<Key> inventoryCan = new LinkedList<Key>();
	private LinkedList<Key> inventoryDoz = new LinkedList<Key>();
	private String KeyName;
	private int KeyPositionX;
	private int KeyPositionY;
	
	public boolean OpenFile(){
		try{
			x = new Scanner(new File("temp.txt"));
			return true;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "No save file", "Alert: Error", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}
	
	public void LoadFilePlayer(Player player){
		//get line by line
		line=x.nextLine();
		//System.out.println(line);
		if(line.equals(player.getName()))
		{
			playerName=line;
			line=x.nextLine();
			playerPositionX = Integer.parseInt(line);
			line=x.nextLine();
			playerPositionY = Integer.parseInt(line);
			player.setCurPosition(playerPositionX,playerPositionY);
		}
	}

	public void LoadPlayerInventory(Player player){
		String tempName = player.getName();

		while(x.hasNext()){
			line=x.nextLine();

			if(line.equals(player.getName()+"Inventory"))
			{
				line=x.nextLine();
				counter = Integer.parseInt(line);
				for( int i = 0 ; i < counter; i++ )
				{
					line=(x.nextLine()).toString();
					Key tempkey = new Key(line);
					if(tempName == "icon/1.gif"){
						inventoryBan.add(tempkey);
					} else if(tempName == "icon/2.gif"){
						inventoryArk.add(tempkey);
					} else if(tempName == "icon/3.gif"){
						inventoryCan.add(tempkey);
					} else if(tempName == "icon/4.gif"){
						inventoryDoz.add(tempkey);
					} else {
						;
					}
				}
			}
		
			if(tempName == "icon/1.gif"){
				player.setInventory(inventoryBan);
			} else if(tempName == "icon/2.gif"){
				player.setInventory(inventoryArk);
			} else if(tempName == "icon/3.gif"){
				player.setInventory(inventoryCan);
			} else if(tempName == "icon/4.gif"){
				player.setInventory(inventoryDoz);
			} else {
				;
			}
		}
	}

	public void LoadFileKeyPosition(Key key){
		while(x.hasNext())
		{
			line=x.nextLine();
			if(line.equals("icon/4.gifInventory"))
			{
				line=x.nextLine();
				counter = Integer.parseInt(line);
				for( int i = 0 ; i < counter ; i++ )
				{
					line=x.nextLine();
				}
				while(x.hasNext())
				{
					line=x.nextLine();
					if (line.equals(key.getKeyName()))
					{
						KeyName=line;
						line=x.nextLine();
						KeyPositionX=Integer.parseInt(line);
						line=x.nextLine();
						KeyPositionY=Integer.parseInt(line);
						key.setKeyPosition(KeyPositionX,KeyPositionY);
					}
					//put this to the key
				}
			}
		}
	}
	
	public int LoadFilePlayerTurn(){
		while(x.hasNext()) {
			line=x.nextLine();

			if(line.equals("icon/4.gif"))
			{
				line=x.nextLine();
				line=x.nextLine();
				line=x.nextLine();
				count=Integer.parseInt(line);
				
			}
		}
		return count;
	}

	public void CloseFile(){
		x.close();
	}
}

