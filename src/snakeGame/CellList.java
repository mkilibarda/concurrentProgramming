package snakeGame;

import java.util.ArrayList;
import java.util.List;


public class CellList {
	List<Cell> cellList = new ArrayList<Cell>();
	int height;
	int width;
	
	public CellList(int height, int width) {
		this.height = height;
		this.width = width;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cellList.add(new Cell(i, j));
				
			}
		}
	}
	
	public Cell getCell(int x, int y) {
		for (Cell cell : cellList) {
			if (cell.getX() == x && cell.getY() == y) {
				return cell;
			}
		}
		return null;
	}
	
	public Cell getCell(int[] xy) {
		return getCell(xy[0], xy[1]);
	}
	
	public void fillInitialFood(int playerNum) {
		int foodNum = (int) Math.ceil((double) playerNum / 10.0);		
		for (int i = 0; i < foodNum; i++) {
			Cell cell = getRandomCellwithBoundaries();
			if (cell.isEmpty() && !(cell.hasFood())) {
				cell.placeFood();
			}
		}
	}
	
	public int[] getRandomCellwithBoundariesXY() {
		Cell cell = getRandomCellwithBoundaries();
		int[] res = new int[] { cell.getX(), cell.getY() };
		return res;
	}
	
	public Cell getRandomCellwithBoundaries() {
		int minHeight = (int) (height * 0.05);
		int maxHeight = (int) (height * 0.95);
		
		int minWidth = (int) (width * 0.05);
		int maxWidth = (int) (width * 0.95);
		
		int randHeight = randomWithRange(minHeight, maxHeight);
		int randWidth = randomWithRange(minWidth, maxWidth);
		
		return getCell(randWidth, randHeight);
	}
	
	private int randomWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}
	
}
