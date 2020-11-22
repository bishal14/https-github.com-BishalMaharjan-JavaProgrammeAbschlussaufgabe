package edu.kit.informatik;

/**
 * 
 * @author Bishal Maharjan
 *
 
 */
public abstract class GameBoard {
	/**
	 * data field of GameBoard. We have length, breath and double array board of
	 * Field.
	 */
	private Field[] fields;

	private Field[][] board;
	private final int breadth;
	private final int length;
	private static int count = 0;

	/**
	 * This is constructor which construct a GameBaord object having breadth,length
	 * and number Of fields.
	 * 
	 * @param breadth
	 *            is integer type data of object.
	 * @param length
	 *            is integer type data of object.
	 * @param playerCount
	 *            is number of Players in game. Here create an number of Field in
	 *            an array .
	 */
	protected GameBoard(int breadth, int length, int playerCount) {
		this.length = length;
		this.breadth = breadth;

		fields = new Field[playerCount];
		for (int i = 0; i < playerCount; i++) {
			fields[i] = new Field(Integer.toString(i + 1));
		}

		board = new Field[length][breadth];
	}

	/**
	 * It is getter method for double array of Field board Object.
	 * 
	 * @return
	 */

	public Field[][] getBoard() {
		return board;
	}

	/**
	 * it is to get the number of step moved by all player.
	 * 
	 * @return number of steps together.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * it is getter method for breadth. Because it is square, length and breadth is
	 * same.
	 * 
	 * @return the size of length.
	 */
	public int getBreadth() {
		return breadth;
	}

	/**
	 * it is getter method for length. Because it is square, length and breadth is
	 * same.
	 * 
	 * @return the size of length.
	 */
	public int getLength() {
		return length;
	}

	/**
	 * it is an abstract method of getContetAt at certain row and column number.
	 * 
	 * @param rowNumber
	 *            is in X axis.
	 * @param columnNumber
	 *            is in Y axis.
	 * @return return a player object.
	 */
	public abstract Field getContentAt(int rowNumber, int columnNumber);

	/**
	 * it is an abstract method printColumn of non returning method type.
	 * 
	 * @param index
	 *            a number from which number is it going to be column printed.
	 */
	public abstract void printColumn(int index);

	/**
	 * it is an abstract method rowColumn non returning method type.
	 * 
	 * @param index
	 *            a number from which number is it going to be row printed.
	 */

	public abstract void printRow(int index);

	/**
	 * It is an abstract method to check if it is empty or already player has filled
	 * here.
	 * 
	 * @param row
	 *            is in X axis.
	 * @param col
	 *            is in Y axis.
	 * @return true if empty and false if is already filled. It has but no method
	 *         body, because this method body implementation is for other two other
	 *         board type.
	 */
	public abstract boolean isEmpty(int row, int col);

	/**
	 * it is an abstract method to get value of box.
	 * 
	 * @param row
	 *            is in x axis.
	 * @param col
	 *            is in y axis.
	 * @return either "**" or player token.
	 */
	public abstract String getState(int row, int col);

	/**
	 * it is method to calculate area of board.
	 * 
	 * @return gives number of spaces in board.
	 */
	public int boardArea() {
		return length * breadth;
	}

	/**
	 * it prints the board.
	 */
	public void boardPrint() {
		for (int i = 0; i < board.length; i++) {
			String connection = " ";
			for (int j = 0; j < board[i].length; j++) {
				String field = isEmpty(i, j) ? "**" : "P" + board[i][j].getToken();
				connection += field + " ";
			}
			Terminal.printLine(connection);
		}
	}

	/**
	 * it is method to restart a new game. it creates a new board with new player
	 * turn.
	 */
	public void reset() {
		board = new Field[length][breadth];
		count = 0;
		Terminal.printLine("OK");
	}

	/**
	 * this is an abstract method to get left diagonal of board.
	 * 
	 * @param row
	 *            is in x axis.
	 * @param column
	 *            is in y axis.
	 * @return player array, which player is in which box in diagonal.
	 */
	public abstract Field[] getLeftDiagonal(int row, int column);

	/**
	 * this is an abstract method to get right diagonal of board.
	 * 
	 * @param row
	 *            is in x axis.
	 * @param column
	 *            is in y axis.
	 * @return player array, which player is in which box in diagonal.
	 */
	public abstract Field[] getRightDiagonal(int row, int column);

	/**
	 * this is an abstract method to place in box.
	 * 
	 * @param firstRow
	 *            is in x axis.
	 * @param firstColumn
	 *            is y axis.
	 * @param secondRow
	 *            x axis.
	 * @param secondCol
	 *            y axis.
	 */
	public abstract void placingMethod(int firstRow, int firstColumn, int secondRow, int secondCol);

	/**
	 * it is an abstract method. it checks if number is negative.
	 * 
	 * @param number
	 *            is integer type.
	 * @return true if number is smaller than zero.
	 */
	public boolean isNegative(int number) {
		return number < 0;
	}

	/**
	 * it is an abstract method. it gets player in an array from column.
	 * 
	 * @param col
	 *            is in board column.
	 * @return player object in an array.
	 */
	public abstract Field[] getColumn(int col);

	/**
	 * it is an abstract method. it gets player in an array from row.
	 * 
	 * @param rownumber
	 *            is in board row.
	 * @return player object in an array.
	 */
	public abstract Field[] getRow(int rowNumber);

	/**
	 * it checks if one box number is positive or negative.
	 * 
	 * @param row
	 *            is in x axis.
	 * @param col
	 *            is in y axis.
	 * @return if one of them is negative.
	 */
	public boolean isOneNegative(int row, int col) {
		return (row < 0 || col < 0);
	}

	/**
	 * it checks if the number is greater than the board length.
	 * 
	 * @param number
	 *            is of integer type.
	 * @return true if number is greater than the board size.
	 */
	public boolean isGreaterThanBoardLength(int number) {
		return number > this.getLength() - 1;
	}

	/**
	 * it gives a player object.
	 * 
	 * @return current player.
	 */
	public Field getCurrentPlayer() {
		return fields[count % fields.length];
	}

	/**
	 * it checks if a board is full.
	 * 
	 * @return true if number of step is equal to number of box size.
	 */
	public boolean isBoardFull() {
		if (count == this.boardArea()) {
			return true;
		}
		return false;
	}

	/**
	 * it print draw if is board full and no one has won the game.
	 * 
	 * @param rowNumber
	 *            is in x axis.
	 * @param colNumber
	 *            is in y axis.
	 * @param currentPlayer
	 *            is player turn.
	 */
	public void drawMethod(int rowNumber, int colNumber, Field currentPlayer) {
		if (isBoardFull() && !hasWon(rowNumber, colNumber, currentPlayer)) {
			Terminal.printLine("draw");
		}
	}

	/**
	 * it is an abstract method. it is to set a stone in board.
	 * 
	 * @param row
	 *            is x axis of board where stone are going to be placed.
	 * @param col
	 *            is of y axis of board where stone are going to be placed.
	 * @param field
	 *            put stone in their own turn.
	 */
	public abstract void setBoard(int row, int col, Field field);

	public boolean isInSixRow(Field[] array, Field field) {
		String token = "P" + field.getToken();
		array = new Field[board.length];
		int matchingNumber = 0;
		for (int i = 0; i < array.length; i++) {
			if (token.equals(array[i])) {
				matchingNumber++;
				if (matchingNumber >= 5) {
					Terminal.printLine("currentPlayer P" + token + "wins");
					return true;
				}
			} else {
				matchingNumber = 0;
			}
		}
		return false;
	}
	
	
	public  int turns() {
	 return count++;
	}

	/**
	 * it is an abstract method. it is to check who has won the game.
	 * 
	 * @param rowNumber
	 *            is x axis.
	 * @param colNumber
	 *            is y axis.
	 * @param currentPlayer
	 *            is player type.
	 * @return true if any one has won the match by placing six stone in column or
	 *         row or in diagonal form.
	 */
	public abstract boolean hasWon(int rowNumber, int colNumber, Field currentPlayer);
}
