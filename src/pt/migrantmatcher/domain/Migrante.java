package pt.migrantmatcher.domain;

import java.util.List;
import java.util.ArrayList;


public class Migrante extends Utilizador {
	
	private ArrayList<Familia> fam;
	
	public Migrante(String nome, int telemovel) {
		super(nome, telemovel);
	}
	
	
}
