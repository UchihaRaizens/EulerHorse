package model;

/**
 * Euler Horse - program to solve Euler Horse problem
 * @version 1.0
 * @author Lukas Resutik
 */
public class EulerHorse {
	private Integer size; //size of chess board
	private Integer move; //the current move
	private Integer iteration; //number of iteration
	private Integer[][] array;	//chess board represented by array

	
	/*Setters and getters*/
	public void setSize(Integer size) {
		this.size = size;
	}

	public void setMove(Integer move) {
		this.move = move;
	}

	public Integer getIteration() {
		return iteration;
	}

	public void setIteration(Integer iteration) {
		this.iteration = iteration;
	}

	public Integer[][] getArray() {
		return array;
	}

	public void setArray(Integer[][] array) {
		this.array = array;
	}

	public Integer getSize() {
		return size;
	}

	public Integer getMove() {
		return move;
	}

	/**
	 * Method create 2D array with size of chessBoard 
	 */
	public void createChessBoard() {
		this.array = new Integer[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				array[i][j] = 0;          //set all 0
			}

		}
		this.move = 0;
		this.iteration = 0;
	}
	
	/**
	 * Method create String array which represented 
	 * move of horse across chess board
	 * @return String of solution
	 */
	public String testArray() {
		String text = "";
		for (int i = 0; i < size; i++) { 
			for (int j = 0; j < size; j++) {
				if (array[i][j] > 9) { //if number is more then 9
					text += " " + array[i][j] + "   ";
				} else {			   //if number is less then 9
					text += "   " + array[i][j] + "   "; 
				}
			}
			text += "\n";
		}
		return text;
	}

	/**
	 * Create a sequence of movement of horses
	 * @return String of final sequence
	 */
	public String findSequence() {
		String text = "";
		Integer step = 1;
		while (step < size * size) {  
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (array[i][j] == step) { 
						text += step + ". krok " + (i+1) + " " + (j+1) + "\n"; //create format string sequence
						step++;
					}
				}
			}
		}
		return text;
	}

	/**
	 * Method set start point of horse
	 * @param x X position on chess board
	 * @param y Y position on chess board
	 */
	public void setStartPoint(Integer x, Integer y) {
		array[x][y] = ++move;

	}

	/**
	 * Method validates position
	 * @param x X position on chess board
	 * @param y Y position on chess board
	 * @return Boolean
	 */
	public boolean isValid(Integer x, Integer y) {
		if (x >= 0 && x < size && y >= 0 && y < size) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method create array of all possible move of horse
	 * from actual position
	 * @param x X position on chess board
	 * @param y Y position on chess board
	 * @return Array of position
	 */	
	public Integer[] nextPositionOfHorse(Integer x, Integer y) {
		Integer[] positions = new Integer[16];
		Integer arrayLenght = new Integer(0);
		if (isValid(x - 1, y - 2) && array[x - 1][y - 2] == 0) { //horse can go to 8 different places 
			positions[arrayLenght++] = x - 1;					 //if horse is not at the edge of the chess board
			positions[arrayLenght++] = y - 2;
		}
		if (isValid(x - 2, y - 1) && array[x - 2][y - 1] == 0) { //second move
			positions[arrayLenght++] = x - 2;
			positions[arrayLenght++] = y - 1;
		}
		if (isValid(x - 2, y + 1) && array[x - 2][y + 1] == 0) { //third move
			positions[arrayLenght++] = x - 2;
			positions[arrayLenght++] = y + 1;
		}
		if (isValid(x - 1, y + 2) && array[x - 1][y + 2] == 0) { //fourth move
			positions[arrayLenght++] = x - 1;
			positions[arrayLenght++] = y + 2;
		}
		if (isValid(x + 1, y - 2) && array[x + 1][y - 2] == 0) { //fifth move
			positions[arrayLenght++] = x + 1;
			positions[arrayLenght++] = y - 2;
		}
		if (isValid(x + 2, y - 1) && array[x + 2][y - 1] == 0) { //sixth move
			positions[arrayLenght++] = x + 2;
			positions[arrayLenght++] = y - 1;
		}
		if (isValid(x + 2, y + 1) && array[x + 2][y + 1] == 0) { //seventh move
			positions[arrayLenght++] = x + 2;
			positions[arrayLenght++] = y + 1;
		}
		if (isValid(x + 1, y + 2) && array[x + 1][y + 2] == 0) { //eighth move
			positions[arrayLenght++] = x + 1;
			positions[arrayLenght++] = y + 2;
		}

		return positions;
	}

	/**
	 * DepthFirstSearch finds solution if exist
	 * @param x X position on chess board
	 * @param y Y position on chess board
	 * @return Boolean if can place horse
	 */
	public boolean depthFirstSearch(Integer x, Integer y) {
		boolean placed = false;

		array[x][y] = move++;	//on actual position set actual move
	
		iteration++;			
		if (iteration > 100000000) { //condition number of iterations
			return false;
		}

		if (move == size * size + 1) { //condition successful solution
			return true;
		}
		
		Integer[] next = nextPositionOfHorse(x, y); //create all possible move of horse

		for (int i = 0; i < next.length; i += 2) { //
			if (next[i] == null || next[i + 1] == null) { 
				break;
			}
			if (depthFirstSearch(next[i], next[i + 1])) { //DBS for all paths
				placed = true;							  //if true return true and break
				break;
			}

		}
		if (!placed) { //if can´t placed we reset values
			array[x][y] = 0;
			move--;
		}
		return placed;
	}

}
