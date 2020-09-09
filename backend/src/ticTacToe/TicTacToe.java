package ticTacToe;

import java.nio.file.AtomicMoveNotSupportedException;
import java.util.Scanner;

import org.omg.CORBA.TRANSACTION_UNAVAILABLE;

public class TicTacToe {
	static Scanner scan = new Scanner(System.in);
	static char turn;
	static char[] superMatrix = new char [9];
	static int nextMatrix = 9;

	static char[][] matrix = new char[9][9];
	
	static void initialize() {
		turn = 'X';
		for (int i = 0; i < superMatrix.length; i++) {
			superMatrix[i] = ' ';
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = ' ';
			}
		}
	}

	static void assign() {
		char[] thisMatrix = matrix[nextMatrix];
		int row = nextMatrix / 3 + 1;
		int column = nextMatrix - (row - 1) * 3 + 1;
		System.out.println("MATRIX at row " + row + ", column " + column + ": ");
		System.out.println("Please input row: ");
		if (!scan.hasNextInt()) {
			System.out.println("Invalid Input!");
			assign();
		} else {
			int x = scan.nextInt();
			System.out.println("Please input column: ");
			if (!scan.hasNextInt()) {
				System.out.println("Invalid Input!");
				assign();
			} else {
				int y = scan.nextInt();
				int index = (x - 1) * 3 + y - 1;
				if (index >= thisMatrix.length) {
					System.out.println("Invalid Input!");
					assign();
				} else if (thisMatrix[index] == ' ') {
					thisMatrix[index] = turn;
					nextMatrix = index;
				} else {
					System.out.println("Already occupied!");
					assign();
				}
			}
		}
	}

	static void assignSuperMatrix() {
		for (int i = 0; i < superMatrix.length; i++) {
			if (winOrTie(matrix[i]) == 'X') {
				superMatrix[i] = 'X';
				// matrix[i][0] = '\\';
				// matrix[i][1] = ' ';
				// matrix[i][2] = '/';
				// matrix[i][3] = ' ';
				// matrix[i][4] = 'X';
				// matrix[i][5] = ' ';
				// matrix[i][6] = '/';
				// matrix[i][7] = ' ';
				// matrix[i][8] = '\\';
			} else if (winOrTie(matrix[i]) == 'O') {
				superMatrix[i] = 'O';
				// matrix[i][0] = '/';
				// matrix[i][1] = '-';
				// matrix[i][2] = '\\';
				// matrix[i][3] = '|';
				// matrix[i][4] = ' ';
				// matrix[i][5] = '|';
				// matrix[i][6] = '\\';
				// matrix[i][7] = '-';
				// matrix[i][8] = '/';
			} else if (winOrTie(matrix[i]) == '/') {
				superMatrix[i] = '/';
				// matrix[i][0] = ' ';
				// matrix[i][1] = ' ';
				// matrix[i][2] = '/';
				// matrix[i][3] = ' ';
				// matrix[i][4] = '/';
				// matrix[i][5] = ' ';
				// matrix[i][6] = '/';
				// matrix[i][7] = ' ';
				// matrix[i][8] = ' ';
			}
		}
	}

	static void determineNextMatrix() {
		System.out.println(turn + "'s turn.");
		if (nextMatrix == 9 || superMatrix[nextMatrix] != ' ') {
			System.out.println("Please input row of next matrix: ");
			if (!scan.hasNextInt()) {
				System.out.println("Invalid Input!");
				determineNextMatrix();
			} else {
				int x = scan.nextInt();
				System.out.println("Please input column of next matrix: ");
				if (!scan.hasNextInt()) {
					System.out.println("Invalid Input!");
					determineNextMatrix();
				} else {
					int y = scan.nextInt();
					int a = (x - 1) * 3 + y - 1;
					if (superMatrix[a] != ' ') {
						System.out.println("This matrix cannot be filled anymore.");
						determineNextMatrix();
					} else {
						nextMatrix = a;
					}
				}
			}
		}
	}

	static char winOrTie(char[] matrix) {
		if ((matrix[0] == 'X' && matrix[1] == 'X' && matrix[2] == 'X')
				|| (matrix[3] == 'X' && matrix[4] == 'X' && matrix[5] == 'X')
				|| (matrix[6] == 'X' && matrix[7] == 'X' && matrix[8] == 'X')
				|| (matrix[0] == 'X' && matrix[3] == 'X' && matrix[6] == 'X')
				|| (matrix[1] == 'X' && matrix[4] == 'X' && matrix[7] == 'X')
				|| (matrix[2] == 'X' && matrix[5] == 'X' && matrix[8] == 'X')
				|| (matrix[0] == 'X' && matrix[4] == 'X' && matrix[8] == 'X')
				|| (matrix[2] == 'X' && matrix[4] == 'X' && matrix[6] == 'X')) {
			return 'X';
		} else if ((matrix[0] == 'O' && matrix[1] == 'O' && matrix[2] == 'O')
				|| (matrix[3] == 'O' && matrix[4] == 'O' && matrix[5] == 'O')
				|| (matrix[6] == 'O' && matrix[7] == 'O' && matrix[8] == 'O')
				|| (matrix[0] == 'O' && matrix[3] == 'O' && matrix[6] == 'O')
				|| (matrix[1] == 'O' && matrix[4] == 'O' && matrix[7] == 'O')
				|| (matrix[2] == 'O' && matrix[5] == 'O' && matrix[8] == 'O')
				|| (matrix[0] == 'O' && matrix[4] == 'O' && matrix[8] == 'O')
				|| (matrix[2] == 'O' && matrix[4] == 'O' && matrix[6] == 'O')) {
			return 'O';
		} else {
			for (int i = 0; i < matrix.length; i++) {
				if (matrix[i] == ' ') {
					return 0;
				}
			}
			return '/';
		}
	}

	static void printSuperMatrix() {
		System.out.println("SUPERMATRIX: ");
		System.out.println(superMatrix[0] + " | " + superMatrix[1] + " | " + superMatrix[2]);
		System.out.println("--|---|--");
		System.out.println(superMatrix[3] + " | " + superMatrix[4] + " | " + superMatrix[5]);
		System.out.println("--|---|--");
		System.out.println(superMatrix[6] + " | " + superMatrix[7] + " | " + superMatrix[8]);
	}

	static void printMatrix() {
		System.out.println("MATRICES: ");
		System.out.println(matrix[0][0] + " | " + matrix[0][1] + " | " + matrix[0][2] + " || " + matrix[1][0] + " | "
				+ matrix[1][1] + " | " + matrix[1][2] + " || " + matrix[2][0] + " | " + matrix[2][1] + " | "
				+ matrix[2][2]);
		System.out.println("--|---|---||---|---|---||---|---|--");
		System.out.println(matrix[0][3] + " | " + matrix[0][4] + " | " + matrix[0][5] + " || " + matrix[1][3] + " | "
				+ matrix[1][4] + " | " + matrix[1][5] + " || " + matrix[2][3] + " | " + matrix[2][4] + " | "
				+ matrix[2][5]);
		System.out.println("--|---|---||---|---|---||---|---|--");
		System.out.println(matrix[0][6] + " | " + matrix[0][7] + " | " + matrix[0][8] + " || " + matrix[1][6] + " | "
				+ matrix[1][7] + " | " + matrix[1][8] + " || " + matrix[2][6] + " | " + matrix[2][7] + " | "
				+ matrix[2][8]);
		System.out.println("==|===|===||===|===|===||===|===|==");
		System.out.println(matrix[3][0] + " | " + matrix[3][1] + " | " + matrix[3][2] + " || " + matrix[4][0] + " | "
				+ matrix[4][1] + " | " + matrix[4][2] + " || " + matrix[5][0] + " | " + matrix[5][1] + " | "
				+ matrix[5][2]);
		System.out.println("--|---|---||---|---|---||---|---|--");
		System.out.println(matrix[3][3] + " | " + matrix[3][4] + " | " + matrix[3][5] + " || " + matrix[4][3] + " | "
				+ matrix[4][4] + " | " + matrix[4][5] + " || " + matrix[5][3] + " | " + matrix[5][4] + " | "
				+ matrix[5][5]);
		System.out.println("--|---|---||---|---|---||---|---|--");
		System.out.println(matrix[3][6] + " | " + matrix[3][7] + " | " + matrix[3][8] + " || " + matrix[4][6] + " | "
				+ matrix[4][7] + " | " + matrix[4][8] + " || " + matrix[5][6] + " | " + matrix[5][7] + " | "
				+ matrix[5][8]);
		System.out.println("==|===|===||===|===|===||===|===|==");
		System.out.println(matrix[6][0] + " | " + matrix[6][1] + " | " + matrix[6][2] + " || " + matrix[7][0] + " | "
				+ matrix[7][1] + " | " + matrix[7][2] + " || " + matrix[8][0] + " | " + matrix[8][1] + " | "
				+ matrix[8][2]);
		System.out.println("--|---|---||---|---|---||---|---|--");
		System.out.println(matrix[6][3] + " | " + matrix[6][4] + " | " + matrix[6][5] + " || " + matrix[7][3] + " | "
				+ matrix[7][4] + " | " + matrix[7][5] + " || " + matrix[8][3] + " | " + matrix[8][4] + " | "
				+ matrix[8][5]);
		System.out.println("--|---|---||---|---|---||---|---|--");
		System.out.println(matrix[6][6] + " | " + matrix[6][7] + " | " + matrix[6][8] + " || " + matrix[7][6] + " | "
				+ matrix[7][7] + " | " + matrix[7][8] + " || " + matrix[8][6] + " | " + matrix[8][7] + " | "
				+ matrix[8][8]);
	}

	static void flipTurn() {
		if (turn == 'X') {
			turn = 'O';
		} else if (turn == 'O') {
			turn = 'X';
		}
	}
	
	static boolean[][] availableMatrix = new boolean[9][9];
	
	static void aiCheckAvailable() {
		
		// init, all false
		for (int i = 0; i < availableMatrix.length; i++) {
			for (int j = 0; j < availableMatrix[i].length; j++) {
				availableMatrix[i][j] = false;
			}
		}

		
		if (nextMatrix == 9 || superMatrix[nextMatrix] != ' ') {
			for (int i = 0; i < superMatrix.length; i++) {
				if (superMatrix[i] == ' ') {
					System.out.println(i + ", " + superMatrix[i]);
					for (int j = 0; j < availableMatrix[i].length; j++) {
						availableMatrix[i][j] = true;
					}
				}
			}
		}
		else {
			for (int i = 0; i < matrix[nextMatrix].length; i++) {
				availableMatrix[nextMatrix][i] = true;
			}
		}
		
		for (int i = 0; i < availableMatrix.length; i++) {
			for (int j = 0; j < availableMatrix[i].length; j++) {
				if (matrix[i][j] != ' ') {
					availableMatrix[i][j] = false;
				}
			}
		}
		
		for (int i = 0; i < availableMatrix.length; i++) {
			for (int j = 0; j < availableMatrix[i].length; j++) {
				if (availableMatrix[i][j] == true) {
					System.out.println((i + 1) + ", " + (j + 1));
				}
			}
		}
	}

	public static void main(String[] args) {
		initialize();
		while (true) {
			printSuperMatrix();
			printMatrix();
			if (turn == 'O') {
				aiCheckAvailable();
			}
		    determineNextMatrix();
		    assign();
			assignSuperMatrix();
			if (winOrTie(superMatrix) != 0) {
				if (winOrTie(superMatrix) == 'X') {
					printSuperMatrix();
					printMatrix();
					System.out.println("X wins! ");
					break;
				} else if (winOrTie(superMatrix) == 'O') {
					printSuperMatrix();
					printMatrix();
					System.out.println("O wins! ");
					break;
				} else if (winOrTie(superMatrix) == '/') {
					printSuperMatrix();
					printMatrix();
					System.out.println("Tie! ");
					break;
				}
			}
			flipTurn();
		}
	}
}
