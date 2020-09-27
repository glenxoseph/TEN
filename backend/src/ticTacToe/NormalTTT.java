package ticTacToe;

import java.util.Scanner;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

public class NormalTTT {
	
	static Scanner scan = new Scanner(System.in);
	static char turn;
	static char[] matrix = new char[9];
	
	static void initialize() {
		turn = 'X';
		for (int i = 0; i < matrix.length; i++) {
			matrix[i] = ' ';
		}
	}
	
	static void printMatrix() {
		System.out.println("MATRIX: ");
		System.out.println(matrix[0] + " | " + matrix[1] + " | " + matrix[2]);
		System.out.println("--|---|--");
		System.out.println(matrix[3] + " | " + matrix[4] + " | " + matrix[5]);
		System.out.println("--|---|--");
		System.out.println(matrix[6] + " | " + matrix[7] + " | " + matrix[8]);
	}
	
	static void flipTurn() {
		if (turn == 'X') {
			turn = 'O';
		}
		else if (turn == 'O') {
			turn = 'X';
		}
	}
	
	static void makeMove() {
		System.out.println("Please input row: ");
		if (!scan.hasNextInt()) {
			System.out.println("Invalid Input!");
			makeMove();
		} else {
			int x = scan.nextInt();
			System.out.println("Please input column: ");
			if (!scan.hasNextInt()) {
				System.out.println("Invalid Input!");
				makeMove();
			} else {
				int y = scan.nextInt();
				int index = (x - 1) * 3 + y - 1;
				if (index >= matrix.length) {
					System.out.println("Invalid Input!");
					makeMove();
				} else if (matrix[index] == ' ') {
					matrix[index] = turn;
				} else {
					System.out.println("Already occupied!");
					makeMove();
				}
			}
		}
	}
	
	static char checkWin() {
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
	
	static void ai() {
		char temp = turn;
		int bestScore = -10000;
		int bestMove = 10;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i] == ' ') {
				matrix[i] = temp;
				int score = minimax(-100, 100, false);
				System.out.println("SCORE at " + (i + 1) + " is " + score);
				matrix[i] = ' ';
				if (score > bestScore) {
					bestScore = score;
					bestMove = i;
				}
			}
		}
		if (bestMove > 9) {
			System.out.println("fail!");
		}
		else {
			matrix[bestMove] = temp;
			turn = temp;
			System.out.println(turn + " at " + bestMove);
		}
	}
	
	
	static int minimax(int alpha, int beta, boolean isMax) {
		char a = turn;
		char b = ' ';
		if (turn == 'O') {
			b = 'X';
		}
		else if (turn == 'X') {
			b = 'O';
		}
//		printMatrix();
		
		if (checkWin() == b) {
//			System.out.println("X loses here!!!!!!!");
			return -10;
		}
		else if (checkWin() == a) {
//			System.out.println("O loses here!!!!!!!");
			return 10;
		}
		else if (checkWin() == '/') {
//			System.out.println("Tie here!!!!!!!");
			return 0;
		}
		
		if (isMax) {
			int bestScore = -1000;
			for (int i = 0; i < matrix.length; i++) {
				if (matrix[i] == ' ') {
					matrix[i] = a;
//					System.out.println("should be o " + a);
//					System.out.println("O at " + i);
					int sc = minimax(alpha, beta, false);
					matrix[i] = ' ';
					alpha = Math.max(alpha, sc);
					bestScore = Math.max(sc, bestScore);
					if (beta <= alpha) {
						break;
					}
				}
			}
			return bestScore;
		}
		
		else if (!isMax) {
			int bestScore = 1000;
			for (int i = 0; i < matrix.length; i++) {
				if (matrix[i] == ' ') {
					matrix[i] = b;
//					System.out.println("should be x " + b);
//					System.out.println("X at " + i);
					int sc = minimax(alpha, beta, true);
					matrix[i] = ' ';
					alpha = Math.min(alpha, sc);
					bestScore = Math.min(sc, bestScore);
					if (beta <= alpha) {
						break;
					}
				}
			}
			return bestScore;
		}
		
		return 0;
	}
	
	public static void main(String[] args) {
		initialize();
		while(true) {
			System.out.println(turn + "'s turn! ");
			printMatrix();
			if (turn == 'X') {
				makeMove();
//				ai();
			}
			else if (turn == 'O') {
//				makeMove();
				ai();
			}
			if (checkWin() == 'X') {
				printMatrix();
				System.out.println("X won! ");
				break;
			}
			else if (checkWin() == 'O') {
				printMatrix();
				System.out.println("O won! ");
				break;
			}
			else if (checkWin() == '/') {
				printMatrix();
				System.out.println("Tie! ");
				break;
			}	
			flipTurn();
		}
	}
}
