//done by LEE AIK PHENG 1141128168
public class Key {
	private String keyName;
	private int[] keyPosition = new int[2];

	public Key(String name){
		keyName = name;
	}

	public String getKeyName(){
		return keyName;
	}

	public int[] getKeyPosition(){
		return keyPosition;
	}

	public void setKeyPosition(int x, int y){

		keyPosition[0] = x;
		keyPosition[1] = y;
	}
}