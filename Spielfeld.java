package VierGewinnt;

public class Spielfeld {
	private int feld[][] = new int[6][7];

	public Spielfeld() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				feld[i][j] = 0;
			}
		}
	}

	/**
	 * Copy-Konstruktor
	 */
	public Spielfeld(Spielfeld s) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				this.feld[i][j] = s.feld[i][j];
			}
		}
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
