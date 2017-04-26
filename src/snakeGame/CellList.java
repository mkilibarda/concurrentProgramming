package snakeGame;

import java.util.ArrayList;
import java.util.List;

public class CellList {
	List<Cell> cellList = new ArrayList<Cell>();

	public CellList(int height, int width) {
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				cellList.add(new Cell(i,j));
			}
		}
	}

}
