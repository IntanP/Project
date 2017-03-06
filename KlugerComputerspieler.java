package VierGewinnt;

import java.util.Random;

public class KlugerComputerspieler implements Spieler {
	private Random r = new Random();
	private int spielerNr;
	Spielleiter s = new Spielleiter();

	public KlugerComputerspieler(int spielerNr) {
		this.spielerNr = spielerNr;
	}

	public int getSpielerNr() {
		return this.spielerNr;
	}

	public int frageSpielerNachSpalte(Spielfeld sf) {
		System.out.println();
		System.out.println("kluger PC ist dran");
		int wahl = r.nextInt(7);
		int beste1 = besteWahl(sf, 1);
		int beste2 = besteWahl(sf, 2);
		if (beste1 != 0) {
			return beste1;
		} else if (beste2 != 0) {
			return beste2;
		}
		return wahl;

	}

	public int besteWahl(Spielfeld sf, int x) {
		for (int i = 0; i < 7; i++) {
			Spielfeld sf2 = new Spielfeld(sf);
			sf2.setze(x, i);
			if (sf2.teste(x)) {
				return i;
			}
			sf2 = new Spielfeld(sf);
		}
		return 0;
	}

	public String getName() {

		return "kluger PC";
	}

}
