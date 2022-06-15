package pt.migrantmatcher.domain;


public class Ajuda {
	
	private Regiao region;
	private Voluntario owner;
	private String code;

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


	public void setOwner(Voluntario v) {
		
		this.owner = v;
		
	}
	
	public void setCode(String s) {
		this.code = s;
	}
	public String getCode() {
		return code;
	}
	
}
