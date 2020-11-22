package edu.kit.informatik;

/**
 * 
 * @author Bishal Maharjan
 *
 */
public class MainClass {
	/*
	 * two class variable .
	 */
	private static boolean word = false;
	public static GameBoard board;

	/**
	 * this is main method and main class of this project.
	 * 
	 * @param args
	 *            of String type. it reads the command line arguments. it checks if
	 *            the command line arguments are valid or not. If command line
	 *            arguments are not valid then it will print either Wrong board type
	 *            or command line argument is false. Command line arguments contains
	 *            board type, number of board length and number of player going to
	 *            play. It creates a new board according to command line argument
	 *            type. In this main method, it checks instructions.If the
	 *            instructions are not in valid written then it will print according
	 *            to mistake an error message. The instructions are split in an
	 *            array. first part of instruction is to call specific action and
	 *            other part of an array is to place, to find,to reset,to quit and
	 *            to print board. Here , it reads instruction from console and run
	 *            method according to instructions. If instruction that read from
	 *            console is not right one, then it print error message. It run
	 *            until instruction quit or until board is full or anyone has won
	 *            the match.
	 * 
	 *            All methods except Main method are private and static. It is
	 *            private because we need this method only in this class and it is
	 *            static so that we can call it in main method.
	 */
	public static void main(String[] args) {
		try {
			int playerNumber = Integer.parseInt(args[2]);
			int boardSize = Integer.parseInt(args[1]);
			boolean isBoardSize = isValidBoardNumber(boardSize);
			boolean isPlayerNumber = isValidPlayerNumber(playerNumber);
			String boardType = args[0];
			if (isBoardSize && isPlayerNumber) {
				switch (boardType) {
				case "standard":
					board = new StandardGameBoard(boardSize, boardSize, playerNumber);
					break;
				case "torus":

					board = new TorusGameBoard(boardSize, boardSize, playerNumber);
					break;
				default:
					Terminal.printError("Wrong BoardType");
				}
			} else {
				Terminal.printError("Command line Argument is false");
			}
		} catch (NumberFormatException ex) {
			Terminal.printError("Numberformatexception occur " + ex);
			return;
		}
		while (!word) {
			String input = Terminal.readLine();
			String[] inputParts = input.split(" ");
			String command = inputParts[0];
			switch (command) {
			case "place":
				if (inputParts.length == 2) {
					MainClass.place(inputParts[1]);
				} else {
					Terminal.printError("Instruction is not in format.");
				}
				break;
			case "rowprint":
				if (inputParts.length == 2) {
					MainClass.rowPrint(inputParts[1]);
				} else {
					Terminal.printError("Instruction is not in format.");
				}
				break;

			case "colprint":
				if (inputParts.length == 2) {
					MainClass.colPrint(inputParts[1]);
				} else {
					Terminal.printError("Instruction is not in format.");
				}
				break;
			case "print":
				if (inputParts.length == 1) {
					MainClass.print();
				} else {
					Terminal.printError("Instruction is not in format.");
				}
				break;

			case "state":
				if (inputParts.length == 2) {
					MainClass.state(inputParts[1]);
				} else {
					Terminal.printError("Instruction is not in format.");
				}
				break;
			case "reset":
				board.reset();
				break;
			case "quit":
				MainClass.quit();
				break;
			default:
				Terminal.printError("Wrong command");
			}

		}

	}

	/**
	 * it is a private method which contains a command in an array.
	 * 
	 * @param command
	 *            is of String in an array which contains of Integer type data. it
	 *            checks if length of data is right or not. if right, then let it to
	 *            place, if not they it will print a error message. Numbers which
	 *            are String type are converted in an integer with the help of
	 *            method. This method calls placing method. it then raised count by
	 *            two as each player can place stone two times at single time. it
	 *            controls number format and data format .
	 */
	private static void place(String command) {
		String[] placeRowColumn = command.split(";");
		if (placeRowColumn.length == 4) {

			int count = 0;
			try {

				int firstRow = Integer.parseInt(placeRowColumn[0]);
				int firstColumn = Integer.parseInt(placeRowColumn[1]);
				int secondRow = Integer.parseInt(placeRowColumn[2]);
				int secondCol = Integer.parseInt(placeRowColumn[3]);
				
				
				
				int standardCount = 0;
			    if (firstColumn == secondCol && secondRow == firstRow) {
			      Terminal.printError("First place and secpnd place is same");
			      return;
			    }

			board.placingMethod(firstRow, firstColumn, secondRow, secondCol);
				count = count + 2;

			} catch (NumberFormatException exp) {
				Terminal.printError("Number format error");
			}

		} else

		{
			Terminal.printError("Data is not in format");
		}
	}

	/**
	 * it is static method of non returning type. it print row of board. it calls
	 * method printrow .
	 * 
	 * @param command
	 *            of String type.
	 * 
	 */

	private static void rowPrint(String command) {

		try {
			int rowNumber = Integer.parseInt(command);
			board.printRow(rowNumber);

		} catch (NumberFormatException ex) {
			Terminal.printError("Number format error");
		}

	}

	/**
	 * It is a static method. This method is to print column of board.
	 * 
	 * @param command
	 *            of String type which contains number of board that going to be
	 *            printed.
	 */
	private static void colPrint(String command) {

		try {

			int colNumber = Integer.parseInt(command);
			board.printColumn(colNumber);
		} catch (NumberFormatException ex) {
			System.out.println("Number format error");
		}
	}

	/**
	 * this method is print the actual board.
	 */
	private static void print() {
		board.boardPrint();

	}

	/**
	 * this method is to give contents of certain row and column.
	 * 
	 * @param command
	 *            contains row and column of board.
	 */
	private static void state(String command) {
		String[] stateCommand = command.split(";");
		if (stateCommand.length == 2) {
			try {

				int rowNumber = Integer.parseInt(stateCommand[0]);
				int colNumber = Integer.parseInt(stateCommand[1]);
				Terminal.printLine(board.getState(rowNumber, colNumber));

			} catch (NumberFormatException ex) {
				System.out.println("Number format error");
			}

		} else {
			Terminal.printError("State command data is not in format");
		}
	}

	/**
	 * this method is to check if player number is valid or not. With this method
	 * help, we can check if the number in command line argument is valid.
	 * 
	 * @param number
	 *            is number of player
	 * @return true if number is greater than one and smaller than five. if the
	 *         number is invalid board size number, then it print error message
	 *         which keep running program without exiting.
	 */
	public static boolean isValidPlayerNumber(int number) {
		if (number > 1 && number < 5) {
			return true;
		}
		Terminal.printError("Invalid player number.");
		return false;
	}

	/**
	 * it quits game.
	 */
	private static void quit() {
		word = true;
	}

	/**
	 * this method is to check if the board size is valid.
	 * 
	 * @param number
	 *            is length of board.
	 * @return true if board size is greater seventeen and smaller than twenty one
	 *         and even number. if the number is invalid board size number, then it
	 *         print error message which keep running program without exiting.
	 */
	public static boolean isValidBoardNumber(int number) {
		if (number < 21 && number > 17 && number % 2 == 0) {
			return true;
		}
		Terminal.printError("Invalid Board Number");
		return false;
	}
}
