package VierGewinnt;

import java.util.Random;

public class EinfacherComputerspieler implements Spieler {
	private Random r = new Random();
	private int spielerNr;

	public EinfacherComputerspieler(int spielerNr) {
		this.spielerNr = spielerNr;
	}
	
	public int getSpielerNr(){
		return this.spielerNr;
	}

	public int frageSpielerNachSpalte(Spielfeld sf) {
		System.out.println();
		System.out.println("PC ist dran");
		int spalte = r.nextInt(7);
		return spalte;
	}

	public String getName() {
		return "PC";
	}

}
