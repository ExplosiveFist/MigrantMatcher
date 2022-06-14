package pt.migrantmatcher.domain;


public class Ajuda {
	
	private Regiao region;
	private Voluntario owner;

	public Ajuda(Voluntario owner) {
		this.owner = owner;
	}
	
	public Regiao getRegiaoAjuda() {
		return region;
	}
	
	public Voluntario getAjudaOwner() {
		return owner;
	}
	
}
