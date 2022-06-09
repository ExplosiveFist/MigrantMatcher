package pt.migrantmatcher.domain;

import java.util.List;
import java.util.ArrayList;


public class Voluntario {
	
	List<Ajuda> ajudas;
	
	public Voluntario(int codigo, int telemovel) {
		super(codigo, telemovel);
		ajudas = new ArrayList<Ajuda>();
	}
	
}
