package Lala;

import javax.swing.JOptionPane;

public class VierGewinntKonsole {
	private char[][] feld;
	private int spieler;
	private String symbol = "XO";
	private int[] reihe;

	public VierGewinntKonsole() {
		feld = new char[6][7];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				feld[i][j] = '-';
			}
		}
		spieler = 0;
		reihe = new int[] { 0, 0, 0, 0, 0, 0, 0 };
	}

	private boolean platziereStein(int spieler, int spalte) {
		if (spalte < 0 || spalte > 6) {
			return false;
		}
		int i = 5 - reihe[spalte];
		if (reihe[spalte] < 6) {
			feld[i][spalte] = symbol.charAt(spieler);
			reihe[spalte]++;
			return true;
		}
		return false;
	}

	private boolean pruefeGewinn(int spieler, int spalte) {
		int i = 6 - reihe[spalte];
		int startlinks = spalte - 3;
		int startoben = i - 3;
		char c = symbol.charAt(spieler);
		int countHorizontal = 0;
		int countVertikal = 0;
		int countDiagonalLinks = 0;
		int countDiagonalRechts = 0;
		for (int j = 0; j < 7; j++) {
			if (startlinks + j >= 0 && startlinks + j < 7) {
				char d = feld[i][startlinks + j];
				if (c == d) {
					countHorizontal++;
				} else {
					if (countHorizontal == 4) {
						return true;
					}
					countHorizontal = 0;
				}
			}
			if (startoben + j >= 0 && startoben + j < 6) {
				char d = feld[startoben + j][spalte];
				if (c == d) {
					countVertikal++;
				} else {
					if (countVertikal == 4) {
						return true;
					}
					countVertikal = 0;
				}
			}
			int indexReihe = (j + startoben);
			int indexSpalte = (j + startlinks);
			if (indexSpalte >= 0 && indexSpalte < 7 && indexReihe >= 0 && indexReihe < 6) {
				char d = feld[indexReihe][indexSpalte];
				if (c == d) {
					countDiagonalLinks++;
				} else {
					if (countDiagonalLinks == 4) {
						return true;
					}
					countDiagonalLinks = 0;
				}
			}
			indexReihe = (j + startoben);
			indexSpalte = 6 + (-j + startlinks);
			if ((indexSpalte) >= 0 && (indexSpalte) < 7 && (indexReihe) >= 0 && (indexReihe) < 6) {
				char d = feld[indexReihe][indexSpalte];
				if (c == d) {
					countDiagonalRechts++;
				} else {
					if (countDiagonalRechts == 4) {
						return true;
					}
					countDiagonalRechts = 0;
				}
			}
		}

		if (countHorizontal == 4 || countVertikal == 4 || countDiagonalLinks == 4 || countDiagonalRechts == 4) {
			return true;
		}
		return false;
	}

	private void gibSpielfeldAus() {
		int count = 0;
		for (char[] c : feld) {
			count++;
			for (char d : c) {
				System.out.print(d + " ");
			}
			if (count == 3) {
				System.out.print("Spieler 1: X");
			} else if (count == 4) {
				System.out.print("Spieler 2: O");
			}
			System.out.println();
		}
		System.out.println("0 1 2 3 4 5 6");
		System.out.println();
	}

	public void spiele() {
		System.out.println(
				"Beginn des Spiels. Spieler 1 fängt an.\nEin Spieler hat gewonnen, wenn er vier Steine in eine Reihe bekommen hat.\nDie Reihe kann sowohl waagerecht als auch senkrecht oder diagonal sein.");
		gibSpielfeldAus();
		int count = 0;
		boolean gewinn = false;
		outerloop: while (!gewinn && count < 42) {
			int i = 0;
			String message = "Spieler " + (spieler + 1) + " gibt Zahl 0-6: ";
			int countFehler = 0;
			while (true) {
				String s = JOptionPane.showInputDialog(message);
				if (s != null) {
					boolean steinGeworfen = false;
					try {
						i = Integer.parseInt(s);
					} catch (NumberFormatException e) {
						continue;
					}
					steinGeworfen = platziereStein(spieler, i);
					if (!steinGeworfen) {
						countFehler++;
						if (countFehler == 1) {
							message = "Stein ist nicht reinzuwerfen!\n" + message;
						}
						continue;
					}
					break;
				} else {
					break outerloop;
				}
			}
			gibSpielfeldAus();
			System.out.println("Spieler " + (spieler + 1) + " hat den Stein in die Spalte " + i + " rein geworfen.\n");
			gewinn = pruefeGewinn(spieler, i);
			spieler = 1 - spieler;
			count++;
		}
		if (gewinn) {
			System.out.println("Ende des Spiels. Spieler " + (spieler) + " hat gewonnen!");
		} else if (!gewinn) {
			System.out.println("Das Spiel wurde abgebrochen!");
		} else if (count == 42) {
			System.out.println("Ende des Spiels. Keiner hat gewonnen!");
		}
	}

	public static void main(String[] args) {
		VierGewinntKonsole test = new VierGewinntKonsole();
		test.spiele();
	}
}