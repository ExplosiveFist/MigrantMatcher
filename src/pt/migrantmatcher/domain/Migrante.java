package pt.migrantmatcher.domain;

import java.util.List;



public class Migrante extends Utilizador {
	
	private String nome;
	private String [] family;
	private List<Ajuda> ajudas;

	public Migrante(String nome, int telemovel) {
		super(telemovel);
		this.nome = nome;
		
		
	}
	
	public void setFamily(int numFamiliares) {
		
		if (numFamiliares > 0) {
			family = new String[numFamiliares];
		}
		
	}
	
	public boolean addFamilyMember(String name) {
		for (int i = 0; i < family.length; i++) {
			if(family[i] == null) {
				family[i] = name;
				return true;
			}
		}
		return false;
	}
	
	public void addAjuda(Ajuda ajuda) {
		ajudas.add(ajuda);
	}
	
	public int getTelephoneNumber() {
		return super.getTelephoneNumber();
	}
	
	public String getName() {
		return nome;
	}

	public void saveHelpList(List<Ajuda> requested) {
		this.ajudas = requested;
		
	}
	
}
