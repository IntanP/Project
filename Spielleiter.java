package VierGewinnt;

//Zwei Spieler : Player 1(X) und Player 2(O)

public class Spielleiter {
	private int feld[][];

	private Spieler p1 = new Menschspieler(1);
	private Spieler p2 = new EinfacherComputerspieler(2);
	Spielfeld sf = new Spielfeld();

	public Spielleiter() {
		feld = new int[6][7];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				feld[i][j] = 0;
			}
		}
	}

	public Spieler getSpieler(int spielerNr) {
		return ((spielerNr == 1) ? p1 : p2);
	}

	public void frageUndSetze(int spielerNr, Spieler p) {
		boolean beendet = false;
		do {
			int spalte = p.frageSpielerNachSpalte(sf);
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

	public void ausgabeSpielfeld() {
		char[][] array = new char[feld.length][feld[0].length];
		for (int i = 0; i < feld.length; i++) {
			for (int j = 0; j < feld[0].length; j++) {
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

		for (int x = 1; x <= feld[0].length; x++) {
			System.out.print(" (" + x + ") ");
		}
		System.out.println();
	}

	/**
	 * Anzahl von der Steine in erster Zeile.
	 */
	public int getAnzahlErsterZeile() {
		int anzSteine = 0;
		for (int j = 0; j < feld.length; j++) {
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
			frageUndSetze(1, p1);
			ausgabeSpielfeld();
			if (teste(1)) {
				break;
			}
			frageUndSetze(2, p2);
			ausgabeSpielfeld();
		} while (!teste(1) && !teste(2) && !unentschieden());
		System.out.println();

		if (teste(1)) {
			System.out.println(p1.getName() + " hat gewonnen");
		} else if (teste(2)) {
			System.out.println(p2.getName() + " hat gewonnen");
		} else {
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
			for (int i = 0; i < feld.length; i++) {
				for (int j = 0; j < feld[0].length; j++) {
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
	public boolean testeHorizontal(int x, int y, int spielerNr) {
		return (y + 3 < feld[x].length && feld[x][y] == spielerNr && feld[x][y + 1] == spielerNr
				&& feld[x][y + 2] == spielerNr && feld[x][y + 3] == spielerNr);
	}

	public boolean testeVertikal(int x, int y, int spielerNr) {
		return x + 3 < feld.length && feld[x][y] == spielerNr && feld[x + 1][y] == spielerNr
				&& feld[x + 2][y] == spielerNr && feld[x + 3][y] == spielerNr;
	}

	public boolean testeDiagonal(int x, int y, int spielerNr) {
		return (x + 3 < feld.length && y + 3 < feld[x].length && feld[x][y] == spielerNr
				&& feld[x + 1][y + 1] == spielerNr && feld[x + 2][y + 2] == spielerNr
				&& feld[x + 3][y + 3] == spielerNr)
				|| (x - 3 >= 0 && y + 3 < feld[x].length && feld[x][y] == spielerNr && feld[x - 1][y + 1] == spielerNr
						&& feld[x - 2][y + 2] == spielerNr && feld[x - 3][y + 3] == spielerNr);

	}
}
