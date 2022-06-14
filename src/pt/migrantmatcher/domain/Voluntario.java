package pt.migrantmatcher.domain;

import java.util.List;
import java.util.ArrayList;


public class Voluntario extends Utilizador {
	
	private String codigoInserido;
	private List<Ajuda> ajudas;
	
	public Voluntario(int codigo, int telemovel) {
		super(telemovel);
		ajudas = new ArrayList<Ajuda>();
	}
	
	public int getTelephoneNumber() {
		return super.getTelephoneNumber();
	}
	
	public void addAjuda(Ajuda ajuda) {
		ajudas.add(ajuda);
	}
}
