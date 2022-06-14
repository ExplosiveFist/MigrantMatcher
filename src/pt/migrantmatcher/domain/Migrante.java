package pt.migrantmatcher.domain;

import java.util.List;
import java.util.ArrayList;


public class Migrante extends Utilizador {
	
	private String nome;
	private List<String> family; 
	
	public Migrante(String nome, int telemovel, int numFamiliares) {
		super(telemovel);
		this.nome = nome;
		
		if (numFamiliares > 0) {
			family = new ArrayList<String>(numFamiliares);
		}
	}
	
	public void addFamilyMember(String name) {
		family.add(name);
	}
	
	public int getTelephoneNumber() {
		return super.getTelephoneNumber();
	}
	
	public String getName() {
		return nome;
	}
}
