package pt.migrantmatcher.domain;

import java.util.List;
import java.util.ArrayList;


public class CatalogoAjudas {

	List<Ajuda> ajudas;
	
	public CatalogoAjudas() {
		this.ajudas = new ArrayList<Ajuda>();
	}

	public void registerHelp(Ajuda help) {
		ajudas.add(help);
		
	}
}
