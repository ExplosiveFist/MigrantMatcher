package pt.migrantmatcher.domain;


public class Ajuda {
	
	private Regiao region;
	private Voluntario owner;

	public Ajuda() {
		
	}
	
	public Regiao getRegiaoAjuda() {
		return region;
	}
	
	public Voluntario getAjudaOwner() {
		return owner;
	}

	public void setRegion(Regiao r) {
		this.region = r;
		
	}

	public void registerHelp() {
		
		
	}

	public void setOwner(Voluntario v) {
		
		this.owner = v;
		
	}
	
}
