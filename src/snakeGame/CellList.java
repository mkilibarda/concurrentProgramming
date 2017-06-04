
package snakeGame;

import java.util.ArrayList;


public class CellList {
	ArrayList<Cell> cellList = new ArrayList<Cell>();
	int height;
	int width;
	
	/*
	 * creates the cellList to the size of the parameters. and sets the border to be filled (i.e.
	 * walls)
	 */
	public CellList(int height, int width) {
		this.height = height;
		this.width = width;
		// create the cells
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cellList.add(new Cell(i, j));
			}
		}
		// fill the border with Walls
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (i == 0 || j == 0 || i == 99 || j == 99) getCell(i, j).beingUsed();
			}
		}
	}
	
	/*
	 * returns the Cell at (x,y)
	 */
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
	
	/*
	 * fills the board with Food, based on how many players exist.
	 */
	public void fillInitialFood(int playerNum) {
		// Food:Snakes is 1:10 ratio
		int foodNum = (int) Math.ceil((double) playerNum / 10.0);
		for (int i = 0; i < foodNum; i++) {
			Cell cell = getRandomCellwithBoundaries();
			if (cell.isEmpty() && !(cell.hasFood())) {
				cell.placeFood();
			}
		}
	}
	
	/*
	 * returns a random cell from the gameBoard, but will only pick from within a boundary currently
	 * set to not return cells outside a 10% border width.
	 */
	public Cell getRandomCellwithBoundaries() {
		int minHeight = (int) (height * 0.05);
		int maxHeight = (int) (height * 0.95);
		
		int minWidth = (int) (width * 0.05);
		int maxWidth = (int) (width * 0.95);
		
		int randHeight = randomWithRange(minHeight, maxHeight);
		int randWidth = randomWithRange(minWidth, maxWidth);
		
		return getCell(randWidth, randHeight);
	}
	
	public int[] getRandomCellwithBoundariesXY() {
		Cell cell = getRandomCellwithBoundaries();
		int[] res = new int[] { cell.getX(), cell.getY() };
		return res;
	}
	
	/*
	 * stolen from the Weekly pracs
	 */
	private int randomWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}
	
}
