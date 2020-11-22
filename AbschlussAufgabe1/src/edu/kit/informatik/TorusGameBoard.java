package edu.kit.informatik;

//Actung hier funktionert eine method für zwei action.
/**
 * This method is subclass of Gameboard. It inherits a methods and attributes
 * from its parent class.
 * 
 * @author Bishal Maharjan
 *
 */
public class TorusGameBoard extends GameBoard {

  /**
   * this is constructor of TorusGameboard. It gets attribute from its parent
   * class.
   * 
   * @param breadth
   *          is of integer type.
   * @param length
   *          is of integer type.
   * @param playerNumber
   *          are number of players on game.
   */
  public TorusGameBoard(int breadth, int length, int playerNumber) {
    super(breadth, length, playerNumber);

  }

  /**
   * this method change number greater than board length to modulo of board
   * length.
   * 
   * @param number
   *          is going to be changed in modulo number.
   * @return greater number into modulo number, which is less than the board
   *         length.
   */
  public int moduloToBigNumber(int number) {
    int firstNumber = 0;
    if (number > this.getLength()) {
      firstNumber = number % this.getLength();
    }
    return firstNumber;
  }

  /**
   * this method is to place stone in torus game board. In torus game board,
   * number can be greater than the board length and can be negative integer.
   * therefore if the number is greater than board length and negative, then they
   * are changed in modulo numbers by using specific method. in this method,
   * player can not put stone in the box, which is already placed.
   */
  public void placingMethod(int row1, int column1, int row2, int col2) {
    int torusCount = 0;
    if (isModuloSame(row1, column1, row2, col2)) {
      Terminal.printError("First row and first col is equal");
      return;
    }
    Field field = getCurrentPlayer();
    if (isEmpty(row1, column1) && (isEmpty(row2, col2))) {
      setBoard(row1, column1, field);
      setBoard(row2, col2, field);
turns();
      if (hasWon(row1, column1, field) || hasWon(row2, col2, field)) {
        Terminal.printLine("P" + field.getToken() + " wins");
      } else {
        Terminal.printLine("OK");
      }

    } else {
      Terminal.printError("it is not empty.");
    }

    if (hasWon(row1, column1, field) || hasWon(row2, col2, field)) {

    }
    Terminal.printLine("OK");

    torusCount++;
    if (isBoardFull() && !hasWon(row1, column1, field) || hasWon(row2, col2, field)) {
      Terminal.printLine("draw");
    }

  }

  /**
   * this method is to get status of box at row and at column. if the row and
   * column number is invalid, then it is converted into valid number by using
   * specific method. if the box is empty, then it gives "**" if not,then it gives
   * Field number.
   */
  public String getState(int row, int column) {
    if (isGreaterThanBoardLength(row)) {
      row = moduloToBigNumber(row);
    }
    if (isGreaterThanBoardLength(column)) {

      column = moduloToBigNumber(column);
    }
    String field = isEmpty(row, column) ? "**" : "P" + super.getBoard()[row][column].getToken();
    return field;
  }

  /**
   * this method is to put stone from player at row and column.
   * 
   */
  public void setBoard(int row, int col, Field field) {
    row = negativeToModulo(row);
    col = negativeToModulo(col);
    super.getBoard()[row][col] = field;
  }

  /**
   * this method is to creat an array of boarder in a way that they are adjacent
   * to each other. in torus game board board at border are adjacent to each
   * other.
   * 
   * @param b
   *          is an array, which is going to passed in method.
   * @return a new array having same length, but the order of element is
   *         different.
   */
  public static int[] divideAndReflectArray(int[] b) {
    int[] c = new int[b.length];
    int e = b.length / 2;

    for (int i = 0; i < e; i++) {
      System.out.println(b[i]);
      c[i] = b[e - 1 - i];

    }

    for (int i = 0; i < e; i++) {
      System.out.println(b[i]);

      c[e + i] = b[b.length - 1 - i];
    }
    return c;
  }

  
  
  
  
  public  Field [] getRowColumnModulo(int number) {
    Field [] columnTorus =getColumn(number);
    return columnTorus;
  }
  /**
   * this method is to check if the invalid numbers have same module number or
   * not. if they are same, it is not allowed to place stone in same place two
   * time. it prints an error message.
   * 
   * @param firstRow
   *          is row of board.
   * @param firsCol
   *          is column of board.
   * @param secondRow
   *          is row of board.
   * @param secondCol
   *          is column of board.
   * @return true if they are equal, if not then false.
   */
  public boolean isModuloSame(int firstRow, int firsCol, int secondRow, int secondCol) {
    if (firsCol % super.getLength() == secondCol % super.getLength()
        && secondRow % super.getLength() == firstRow % super.getLength()) {
      return true;
    }
    return false;
  }

  /**
   * this method change negative integer to positive integer modulo to board
   * length.
   * 
   * @param number
   *          is number which is going to be changed in modulo.it change also, if
   *          the number is higher than the board length.
   * @return positive and valid number.
   */
  public int negativeToModulo(int number) {
    int positiveNumber = number;
    int firstNumber = 0;
    if (isNegative(number)) {
      firstNumber = number % super.getLength();
      positiveNumber = super.getLength() + firstNumber;
    }
    return positiveNumber % super.getLength();
  }

  /**
   * this boolean method is to check if any player from row, column or diagonal
   * won the game. IsinSix method is boolean type and it returns also boolean
   * type. we can check boolean condition from four methods assigning to boolean
   * variable, if one of them have won or not. if one of them have won, then it
   * will print true.
   */
  public boolean hasWon(int rowNumber, int colNumber, Field currentPlayer) {
    boolean columnModuloWin =isInSixRow(getColumn(colNumber), currentPlayer);
    boolean rowModuloWin = isInSixRow(getRow(rowNumber),currentPlayer);
    
    boolean columnWin = isInSixRow(getColumn(colNumber), currentPlayer);
    boolean rowWin = isInSixRow(getRow(rowNumber), currentPlayer);
    boolean leftDiagonalWin = isInSixRow(getLeftDiagonal(rowNumber, colNumber), currentPlayer);
    boolean rightDiagonalWin = isInSixRow(getRightDiagonal(rowNumber, colNumber), currentPlayer);
    return columnWin || rowWin || rightDiagonalWin || leftDiagonalWin||columnModuloWin||rowModuloWin;
  }

  /**
   * this is an implementation of abstract method from Gameboard. it gives an
   * array of Field from right diagonal of board. if the row number and column
   * number is not valid, then they are changed into a valid number. at the end we
   * get an array of Field object.
   *
   */
  public Field[] getRightDiagonal(int row, int column) {
    if (isGreaterThanBoardLength(row)) {
      row = moduloToBigNumber(row);
    }
    if (isGreaterThanBoardLength(column)) {

      column = moduloToBigNumber(column);
    }
    if (isOneNegative(row, column)) {
      row = negativeToModulo(row);
      column = negativeToModulo(column);
    }
    Field[] rightDiganonal = new Field[super.getBoard().length];

    for (int i = row, j = column; (i > 0) && (j > 0); i--, j--) {
      row--;
      column--;
    }
    for (int i = row, j = column; (i <= (super.getBoard().length - 1)
        && (j <= super.getBoard().length - 1)); i++, j++) {
      rightDiganonal[i] = super.getBoard()[i][j];

    }

    return rightDiganonal;
  }

  /**
   * this method is to check if box at row and column is free or not. if the
   * number of row and column is invalid, then it changes first and later checked
   * , if it is free or not. it gives true if there is null.
   */
  public boolean isEmpty(int row, int column) {
    if (isGreaterThanBoardLength(row)) {
      row = moduloToBigNumber(row);
    }
    if (isGreaterThanBoardLength(column)) {

      column = moduloToBigNumber(column);
    }
    if (isOneNegative(row, column)) {
      row = negativeToModulo(row);
      column = negativeToModulo(column);
    }
    return getContentAt(row, column) == null;

  }

  /**
   * this is an implementation of abstract method from Gameboard. it gives an
   * array of Field from left diagonal of board. if the row number and column
   * number is not valid, then they are changed into a valid number. at the end we
   * get an array of Field object.
   *
   */
  public Field[] getLeftDiagonal(int row, int column) {
    if (isGreaterThanBoardLength(row)) {
      row = moduloToBigNumber(row);
    }
    if (isGreaterThanBoardLength(column)) {

      column = moduloToBigNumber(column);
    }
    if (isOneNegative(row, column)) {
      row = negativeToModulo(row);
      column = negativeToModulo(column);
    }

    Field[] leftDiganonal = new Field[super.getBoard().length];

    for (int i = row, j = column; i > 0 && j < (super.getBoard().length - 1); i--, j++) {
      row--;
      column++;

    }
    for (int i = row, j = column; (j >= 0 && i < super.getBoard().length); i++, j--) {
      leftDiganonal[i] = super.getBoard()[i][j];
    }
    return leftDiganonal;
  }

  /**
   * this method print column of board.At first the numbers are checked if they
   * are valid or not. if the numbers are greater than the board length and is
   * negative, then it is changed into valid number. this method is to get an
   * array of Field type. It gives a row of an array of Field.
   */
  public Field[] getColumn(int col) {
    if (isNegative(col)) {
      col = negativeToModulo(col);
    }
    if (isGreaterThanBoardLength(col)) {
      col = moduloToBigNumber(col);
    }

    Field[] column = new Field[super.getBoard().length];
    for (int i = 0; i < super.getBoard().length; i++) {
      column[i] = super.getBoard()[i][col];
    }
    return column;
  }

  /**
   * this method print row of board.At first the numbers are checked if they are
   * valid or not. if the numbers are greater than the board length and is
   * negative, then it is changed into valid number. this method is to get an
   * array of Field type. It gives a row of an array of Field.
   */

  public Field[] getRow(int rowNumber) {
    if (isNegative(rowNumber)) {
      rowNumber = negativeToModulo(rowNumber);
    }
    if (isGreaterThanBoardLength(rowNumber)) {
      rowNumber = moduloToBigNumber(rowNumber);
    }
    Field[] row = new Field[super.getBoard().length];
    for (int i = 0; i < super.getBoard().length; i++) {
      row[i] = super.getBoard()[rowNumber][i];
    }
    return row;
  }

  /**
   * this method print row of board.At first the numbers are checked if they are
   * valid or not. if the numbers are greater than the board length and is
   * negative, then it is changed into valid number. It prints "**" if there is
   * null.
   *
   */
  public void printRow(int index) {
    if (isNegative(index)) {
      index = negativeToModulo(index);
    }
    if (isGreaterThanBoardLength(index)) {
      index = moduloToBigNumber(index);
    }
    String connection = "";
    for (int i = 0; i < super.getBoard().length; i++) {
      for (int j = 0; j < super.getBoard()[i].length; j++) {
        if (index == i) {
          String field = isEmpty(index, j) ? "**" : "P" + super.getBoard()[index][j].getToken();
          connection += field + " ";
        }
      }
    }
    Terminal.printLine(connection);

  }

  /**
   * this method print column of board.At first the numbers are checked if they
   * are valid or not. if the numbers are greater than the board length and is
   * negative, then it is changed into valid number. It prints "**" if there is
   * null.
   *
   */
  public void printColumn(int index) {
    if (isNegative(index)) {
      index = negativeToModulo(index);
    }
    if (isGreaterThanBoardLength(index)) {
      index = moduloToBigNumber(index);
    }
    String connection = "";
    for (int i = 0; i < super.getBoard().length; i++) {

      for (int j = 0; j < super.getBoard()[i].length; j++) {
        if (index == j) {
          String field = isEmpty(i, index) ? "**" : "P" + super.getBoard()[i][index].getToken();
          connection += field + " ";

        }

      }

    }
    Terminal.printLine(connection);

  }

  /**
   * this method is an implementation of abstract method in Gameboard. this method
   * is to get player content in box. it checks if the number is valid or not. if
   * not, then it prints error message.
   * 
   *
   */
  public Field getContentAt(int row, int column) {
    if (isGreaterThanBoardLength(row)) {
      row = moduloToBigNumber(row);
    }
    if (isGreaterThanBoardLength(column)) {

      column = moduloToBigNumber(column);
    }
    if (isOneNegative(row, column)) {
      row = negativeToModulo(row);
      column = negativeToModulo(column);
    }

    return super.getBoard()[row][column];
  }

}
