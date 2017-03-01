package VierGewinnt;

import java.util.Scanner;

//Zwei Spieler : Player 1(X) und Player 2(O)

public class VierGewinnt {

	private int feld[][];

	public VierGewinnt() {

		feld = new int[6][7];

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				feld[i][j] = 0;
			}
		}
	}

	public void frageUndSetze(int spielerNr) {
		boolean beendet = false;

		do {
			int spalte = frageSpielerNachSpalte(spielerNr);
			beendet = setze(spielerNr, spalte);
			if (!beendet) {
				System.out.println("Bitte andere Spalte eingeben");
			}
		} while (!beendet);

	}

	public boolean setze(int spielerNr, int spalte) {
		for (int x = feld.length - 1; x >= 0; x--) {
			if (feld[x][spalte] == 0) {
				feld[x][spalte] = spielerNr;
				return true;
			}
		}
		return false;
	}

	public int frageSpielerNachSpalte(int spielerNr) {
		System.out.println("Spieler " + spielerNr + " Bitte Spalte(0-6)eingeben");
		Scanner s = new Scanner(System.in);
		int spalte = s.nextInt();
		return spalte;
	}

	public void ausgabeSpielfeld() {
		char[][] array = new char[6][7];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (feld[i][j] == 1) {
					array[i][j] = 'X';
				} else if (feld[i][j] == 2) {
					array[i][j] = 'O';
				} else {
					array[i][j] = '-';
				}
				System.out.print("| " + array[i][j] + " |");
			}
			System.out.println();
		}
		System.out.println();

	}

	/**
	 * Anzahl von der Steine in erster Zeile.
	 */
	public int getAnzahlErsterZeile() {
		int anzSteine = 0;
		for (int j = 0; j < 6; j++) {
			if (feld[0][j] != 0) {
				anzSteine++;
			}
		}
		return anzSteine;
	}

	/**
	 * Ganzes Spiel steuern
	 */

	public void spiele() {
		ausgabeSpielfeld();
		do {
			System.out.println();
			frageUndSetze(1);
			ausgabeSpielfeld();
			if (teste(1)) {
				break;
			}
			frageUndSetze(2);
			ausgabeSpielfeld();
		} while (teste(1) != true && teste(2) != true && !unentschieden());

		if (teste(1)) {
			System.out.println("Player 1 hat gewonnen");
		} else if (teste(2)) {
			System.out.println("Player 2 hat gewonnen");
		} else if (unentschieden()) {
			System.out.println("unentschieden!");
		}
	}

	/**
	 * Wenn das Spielfeld schon voll ist, aber keiner gewinnt
	 */
	public boolean unentschieden() {
		return getAnzahlErsterZeile() == feld.length;
	}

	/**
	 * Alle 3 Methode zusammen.
	 */
	public boolean teste(int spielerNr) {
		boolean returnwert = false;

		if (getAnzahlErsterZeile() <= feld.length) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 7; j++) {
					if (testeHorizontal(i, j, spielerNr) || testeVertikal(i, j, spielerNr)
							|| testeDiagonal(i, j, spielerNr)) {
						returnwert = true;
					}

				}
			}
		}
		return returnwert;
	}

	/**
	 * 3 Methode, die von einem Punkt testen, ob die Spieler gewinnen.
	 */
	public boolean testeHorizontal(int x, int y, int Spieler) {
		return (y + 3 < feld[x].length && feld[x][y] == Spieler && feld[x][y + 1] == Spieler
				&& feld[x][y + 2] == Spieler && feld[x][y + 3] == Spieler);
	}

	public boolean testeVertikal(int x, int y, int Spieler) {
		return x + 3 < feld.length && feld[x][y] == Spieler && feld[x + 1][y] == Spieler && feld[x + 2][y] == Spieler
				&& feld[x + 3][y] == Spieler;
	}

	public boolean testeDiagonal(int x, int y, int Spieler) {

		return (x + 3 < feld.length && y + 3 < feld[x].length && feld[x][y] == Spieler && feld[x + 1][y + 1] == Spieler
				&& feld[x + 2][y + 2] == Spieler && feld[x + 3][y + 3] == Spieler)
				|| (x - 3 >= 0 && y + 3 < feld[x].length && feld[x][y] == Spieler && feld[x - 1][y + 1] == Spieler
						&& feld[x - 2][y + 2] == Spieler && feld[x - 3][y + 3] == Spieler);

	}
}
