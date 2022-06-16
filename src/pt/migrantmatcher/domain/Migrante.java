package pt.migrantmatcher.domain;

import java.util.List;

import pt.migrantmatcher.utils.Observer;



public class Migrante extends Utilizador implements Observer {
	
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
		
		if(family != null) {
			for (int i = 0; i < family.length; i++) {
				if(family[i] == null) {
					family[i] = name;
					return false;
				}
			}
		}
		return true;
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

	@Override
	public void update(String msg) {
		System.out.println("\n--------------------------------------\n");
		System.out.println("Notificação para " + this.nome + " (" + this.telemovel + ") :");
		System.out.println(msg);
		System.out.println("\n--------------------------------------\n");
		
	}
	
}
