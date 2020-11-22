package edu.kit.informatik;

import java.util.Arrays;

/**
 * this class is subclass of GameBoard. It extends Gameboard from which it gets
 * attributes and methods.
 * 
 * @author Bishal Maharjan
 *
 */
public class StandardGameBoard extends GameBoard {
  /**
   * attributes are from Gameboard.
   * 
   * @param breadth
   *          breadth of board.
   * @param length
   *          is length of board.
   * @param playerNumber
   *          is number of player.
   * 
   */
  public StandardGameBoard(int breadth, int length, int playerNumber) {
    super(breadth, length, playerNumber);
  }

  /**
   * this is an implementation of abstract method from Gameboard. It put stone in
   * two boxes. It checks if the numbers are valid or not. If numbers are valid,
   * then it put stone.
   */
  public void placingMethod(int row1, int column1, int row2, int col2) {
    int standardCount = 0;
//    if (column1 == col2 && row2 == row1) {
//      Terminal.printError("First row and first col is equal");
//      return;
//    }

    Field currentPlayer = getCurrentPlayer();

    if (isValidInputNumber(row1, column1) || isValidInputNumber(row2, col2)) {
      Terminal.printError("It is invalid number");
    } else {
      if (isEmpty(row1, column1) && (isEmpty(row2, col2))) {
        setBoard(row1, column1, currentPlayer);
        setBoard(row2, col2, currentPlayer);
        turns();
        if (hasWon(row1, column1, currentPlayer) || hasWon(row2, col2, currentPlayer)) {
          Terminal.printLine("P" + currentPlayer.getToken() + " wins");
        } else {
          Terminal.printLine("OK");
        }
      } else {
        Terminal.printError("it is not empty.");
      }

    }
  }

  /**
   * this is an implementation of abstract method from Gameboard. It put stone in
   * two boxes. It checks if the numbers are valid or not. If numbers are valid,
   * then it put stone.
   */
  public boolean hasWon(int rowNumber, int colNumber, Field currentPlayer) {
    boolean columnWin = isInSixRow(getColumn(colNumber), currentPlayer);

    System.out.println("ARrays of getColumn is " + Arrays.toString(getColumn(colNumber)));

    boolean rowWin = isInSixRow(getRow(rowNumber), currentPlayer);
    boolean leftDiagonalWin = isInSixRow(getLeftDiagonal(rowNumber, colNumber), currentPlayer);
    boolean rightDiagonalWin = isInSixRow(getRightDiagonal(rowNumber, colNumber), currentPlayer);
    return columnWin || rowWin || rightDiagonalWin || leftDiagonalWin;
  }

  /**
   * this is an implementation of abstract method from Gameboard. it gives an
   * array of Field from right diagonal of board.
   * 
   */
  public Field[] getRightDiagonal(int row, int column) {

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
   * this is an implementation of abstract method from Gameboard. it gives an
   * array of Field from left diagonal of board.
   */
  public Field[] getLeftDiagonal(int row, int column) {

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
   * negative, then it is invalid. this method is to get an array of Field type.
   * This method gives a column of an array of Field.
   */
  public Field[] getColumn(int col) {
    if (isNegative(col) || col > super.getBoard().length - 1) {
      Terminal.printError("Wrong input for colPrint");
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
   * negative, then it is invalid. this method is to get an array of Field type.
   * It gives a row of an array of Field.
   */
  public Field[] getRow(int rowNumber) {
    if (isNegative(rowNumber) || rowNumber > super.getBoard().length - 1) {
      Terminal.printError("Wrong input for colPrint");
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
   * negative, then it is invalid. It prints "**" if there is null.
   *
   */
  public void printRow(int index) {
    if (isNegative(index) || index > super.getBoard().length) {

      Terminal.printError("Invalid input number ");
    } else {
      String connection = "";
      Field[] row = new Field[this.getLength()];
      for (int i = 0; i < super.getBoard().length; i++) {
        for (int j = 0; j < super.getBoard()[i].length; j++) {
          if (index == i) {
            String field = isEmpty(index, j) ? "**" : "P" + super.getBoard()[index][j].getToken();
            row[j] = super.getBoard()[index][j];
            connection += field + " ";
          }
        }
      }
      Terminal.printLine(connection);
    }

  }

  /**
   * this method check if a square is empty or not. At first the numbers are
   * checked if they are valid or not. if the numbers are greater than the board
   * length and is negative, then it is invalid. it gives true if content is null,
   * as there is free space.
   */
  public boolean isEmpty(int row, int column) {
    if (isGreaterThanBoardLength(row) || isGreaterThanBoardLength(column) || isOneNegative(row, column)) {

      Terminal.printError("Invalid input ");
    }
    return getContentAt(row, column) == null;

  }

  /**
   * this method gives state of box at number row and column. if the numbers are
   * not valid , then it prints Error.
   */
  public String getState(int row, int column) {
    if (isGreaterThanBoardLength(row) || isGreaterThanBoardLength(column) || isOneNegative(row, column)) {
      Terminal.printError("Error");
    } else {
      String field = isEmpty(row, column) ? "**" : "P" + super.getBoard()[row][column].getToken();
      return field;
    }
    return " ";

  }

  /**
   * this method print column of an index of board. if number is not in valid,
   * then it print error message. this method prints "**" if there is no any
   * player in the box.
   */
  public void printColumn(int index) {
    if (isNegative(index) || index > super.getBoard().length) {

      Terminal.printError("Invalid input ");
    } else {
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
  }

  /*
   * this method is an implementation of abstract method in Gameboard. this method
   * is to get player content in box. it checks if the number is valid or not. if
   * not, then it prints error message.
   * 
   */
  public Field getContentAt(int rowNumber, int columnNumber) {
    if ((isGreaterThanBoardLength(rowNumber) || isGreaterThanBoardLength(columnNumber))
        || (isOneNegative(rowNumber, columnNumber))) {
      Terminal.printError("Invalid Number format");
    }
    return super.getBoard()[rowNumber][columnNumber];

  }

  /**
   * this method is to set stone from player in box in row and column of board.
   */
  public void setBoard(int row, int col, Field field) {
    super.getBoard()[row][col] = field;
  }

  /**
   * it checks if the number is valid to put in box. the number can be greater
   * than the board size or negative.
   * 
   * @param row
   *          number of board in x axis.
   * @param column
   *          of board in y axis.
   * @return if one of them number is invalid it returns true.
   */
  public boolean isValidInputNumber(int row, int column) {
    return (isGreaterThanBoardLength(row) || isGreaterThanBoardLength(column) || isOneNegative(row, column));

  }
}
