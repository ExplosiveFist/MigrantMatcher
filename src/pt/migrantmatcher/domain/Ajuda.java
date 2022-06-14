package pt.migrantmatcher.domain;


public class Ajuda {
	
	private Regiao region;
	private Voluntario owner;

	public Ajuda(Voluntario owner, Regiao region) {
		this.owner = owner;
		this.region = region;
	}
	
	public Regiao getRegiaoAjuda() {
		return region;
	}
	
	public Voluntario getAjudaOwner() {
		return owner;
	}
	
}
