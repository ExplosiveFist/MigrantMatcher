package pt.migrantmatcher.domain;

public class Alojamento extends Ajuda {
	
	private int numPessoas;
	
	public Alojamento(Voluntario owner, Regiao region, int numPessoas) {
		super(owner, region);
		this.numPessoas = numPessoas;
	}
	
	public Regiao getRegiaoAjuda() {
		return super.getRegiaoAjuda();
	}
	
	public int getNumPessoasAlojamento() {
		return numPessoas;
	}
	
}
