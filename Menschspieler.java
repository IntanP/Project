package VierGewinnt;

import java.util.Scanner;

public class Menschspieler implements Spieler {
	private String name;
	private int spielerNr;
	Scanner s = new Scanner(System.in);

	public Menschspieler(int spielerNr) {
		this.spielerNr = spielerNr;
		System.out.println("Wie heisst du?");
		name = s.next();
	}
	
	public int getSpielerNr(){
		return this.spielerNr;
	}

	public String getName() {
		return this.name;
	}

	public int frageSpielerNachSpalte(Spielfeld sf) {
		System.out.println(name + " Bitte Spalte(1-7)eingeben");
		int spalte = s.nextInt() - 1;
		return spalte;
	}

}
