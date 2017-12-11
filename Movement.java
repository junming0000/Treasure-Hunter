import java.awt.*;
import java.util.LinkedList;

public class Movement {
	private LinkedList<Integer> possibleX = new LinkedList<Integer>();
	private LinkedList<Integer> possibleY = new LinkedList<Integer>(); 

	public void calculateUpMoves(int[] position,int[] boxes){
		int axisX = position[0];
		int axisY = position[1];
		int tempX = axisX;
		for(int i = 0; i < boxes.length; i++)
		{
			axisX = tempX - boxes[i];
			setPossiblePosition(axisX, axisY);
		}
	}

	public void calculateUpRightMoves(int[] position,int[] boxes){
		int axisX = position[0];
		int axisY = position[1];
		int tempX = axisX;
		int tempY = axisY;

		for(int i = 0; i < boxes.length; i++)
		{
			axisX = tempX - boxes[i];
			axisY = tempY + boxes[i];
			setPossiblePosition(axisX, axisY);
		}
	}

	public void calculateRightMoves(int[] position,int[] boxes){
		int axisX = position[0];
		int axisY = position[1];
		int tempX = axisX;
		int tempY = axisY;

		for(int i = 0; i < boxes.length; i++)
		{
			axisY = tempY + boxes[i];
			setPossiblePosition(axisX, axisY);
		}
	}

	public void calculateDownRightMoves(int[] position,int[] boxes){
		int axisX = position[0];
		int axisY = position[1];
		int tempX = axisX;
		int tempY = axisY;

		for(int i = 0; i < boxes.length; i++)
		{
			axisX = tempX + boxes[i];
			axisY = tempY + boxes[i];
			setPossiblePosition(axisX, axisY);
		}
	}

	public void calculateDownMoves(int[] position,int[] boxes){
		int axisX = position[0];
		int axisY = position[1];
		int tempX = axisX;
		int tempY = axisY;

		for(int i = 0; i < boxes.length; i++)
		{
			axisX = tempX + boxes[i];
			setPossiblePosition(axisX, axisY);
		}
		
	}

	public void calculateDownLeftMoves(int[] position,int[] boxes){
		int axisX = position[0];
		int axisY = position[1];
		int tempX = axisX;
		int tempY = axisY;

		for(int i = 0; i < boxes.length; i++)
		{
			axisX = tempX + boxes[i];
			axisY = tempY - boxes[i];
			setPossiblePosition(axisX, axisY);
		}
	}

	public void calculateLeftMoves(int[] position,int[] boxes){
		int axisX = position[0];
		int axisY = position[1];
		int tempX = axisX;
		int tempY = axisY;

		for(int i = 0; i < boxes.length; i++)
		{
			axisY = tempY - boxes[i];
			setPossiblePosition(axisX, axisY);
		}
		
	}

	public void calculateUpLeftMoves(int[] position,int[] boxes){
		int axisX = position[0];
		int axisY = position[1];
		int tempX = axisX;
		int tempY = axisY;

		for(int i = 0; i < boxes.length; i++)
		{
			axisX = tempX - boxes[i];
			axisY = tempY - boxes[i];
			setPossiblePosition(axisX, axisY);
		}
	}

	public void setPossiblePosition(int axisX, int axisY){
		possibleX.add(axisX);
		possibleY.add(axisY);
	}

	public void clear(){
		possibleX.clear();
		possibleY.clear();
	}

	public boolean playerDefaultMove(int[] position, int destinationX, int destinationY){
		clear();
		int[] movableBoxes = { 1 , 2 };
		boolean status = false;
		calculateUpMoves(position,movableBoxes);
		calculateUpRightMoves(position,movableBoxes);
		calculateRightMoves(position,movableBoxes);
		calculateDownRightMoves(position,movableBoxes);
		calculateDownMoves(position,movableBoxes);
		calculateDownLeftMoves(position,movableBoxes);
		calculateLeftMoves(position,movableBoxes);
		calculateUpLeftMoves(position,movableBoxes);

		for(int i = 0 ; i < possibleX.size() ; i++){
			if (possibleX.get(i)==destinationX){
				if(possibleY.get(i)==destinationY){
					//System.out.println("Player move to "+destinationX+" , "+destinationY);
					status = true;
				}
			}
		}

		return status;
	}

	public boolean playerPinkeyMove(int[] position, int destinationX, int destinationY){
		clear();
		int[] movableBoxes = {1};
		boolean status = false;
		calculateUpMoves(position,movableBoxes);
		calculateUpRightMoves(position,movableBoxes);
		calculateRightMoves(position,movableBoxes);
		calculateDownRightMoves(position,movableBoxes);
		calculateDownMoves(position,movableBoxes);
		calculateDownLeftMoves(position,movableBoxes);
		calculateLeftMoves(position,movableBoxes);
		calculateUpLeftMoves(position,movableBoxes);

		for(int i = 0 ; i < possibleX.size() ; i++){
			if (possibleX.get(i)==destinationX){
				if(possibleY.get(i)==destinationY){
					//System.out.println("Player move to "+destinationX+" , "+destinationY);
					status = true;
				}
			}
		}

		return status;
	}

	public boolean playerDonkeyMove(int[] position, int destinationX, int destinationY){
		clear();
		int[] movableBoxes = {1, 2, 3};
		boolean status = false;
		calculateUpRightMoves(position,movableBoxes);
		calculateDownRightMoves(position,movableBoxes);
		calculateDownLeftMoves(position,movableBoxes);
		calculateUpLeftMoves(position,movableBoxes);

		for(int i = 0 ; i < possibleX.size() ; i++){
			if (possibleX.get(i)==destinationX){
				if(possibleY.get(i)==destinationY){
					//System.out.println("Player move to "+destinationX+" , "+destinationY);
					status = true;
				}
			}
		}

		return status;
	}
	
	public boolean playerKeydiskMove(int[] position, int destinationX, int destinationY){
		clear();
		int[] movableBoxes = {1 ,2, 3};
		boolean status = false;
		calculateUpMoves(position,movableBoxes);
		calculateRightMoves(position,movableBoxes);
		calculateDownMoves(position,movableBoxes);
		calculateLeftMoves(position,movableBoxes);

		for(int i = 0 ; i < possibleX.size() ; i++){
			if (possibleX.get(i)==destinationX){
				if(possibleY.get(i)==destinationY){
					//System.out.println("Player move to "+destinationX+" , "+destinationY);
					status = true;
				}
			}
		}

		return status;
	}

	public boolean playerKeynoteMove(int[] position, int destinationX, int destinationY){
		clear();
		int[] movableBoxes = {2};
		boolean status = false;
		calculateUpMoves(position,movableBoxes);
		calculateUpRightMoves(position,movableBoxes);
		calculateRightMoves(position,movableBoxes);
		calculateDownRightMoves(position,movableBoxes);
		calculateDownMoves(position,movableBoxes);
		calculateDownLeftMoves(position,movableBoxes);
		calculateLeftMoves(position,movableBoxes);
		calculateUpLeftMoves(position,movableBoxes);

		for(int i = 0 ; i < possibleX.size() ; i++){
			if (possibleX.get(i)==destinationX){
				if(possibleY.get(i)==destinationY){
					//System.out.println("Player move to "+destinationX+" , "+destinationY);
					status = true;
				}
			}
		}

		return status;
	}

	public boolean playerMonkeyMove(int[] position, int destinationX, int destinationY){
		clear();
		int[] movableBoxes = {1, 2, 3};
		boolean status = false;
		calculateUpMoves(position,movableBoxes);
		calculateUpRightMoves(position,movableBoxes);
		calculateRightMoves(position,movableBoxes);
		calculateDownRightMoves(position,movableBoxes);
		calculateDownMoves(position,movableBoxes);
		calculateDownLeftMoves(position,movableBoxes);
		calculateLeftMoves(position,movableBoxes);
		calculateUpLeftMoves(position,movableBoxes);

		for(int i = 0 ; i < possibleX.size() ; i++){
			if (possibleX.get(i)==destinationX){
				if(possibleY.get(i)==destinationY){
					//System.out.println("Player move to "+destinationX+" , "+destinationY);
					status = true;
				}
			}
		}

		return status;
	}

}