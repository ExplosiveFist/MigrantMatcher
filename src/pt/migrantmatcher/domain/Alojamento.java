package pt.migrantmatcher.domain;

public class Alojamento extends Ajuda {
	
	private int numPessoas;
	
	public Alojamento(Voluntario owner, int numPessoas) {
		super(owner);
		this.numPessoas = numPessoas;
	}
	
	public Regiao getRegiaoAlojamento() {
		return super.getRegiaoAjuda();
	}
	
	public Voluntario getAlojamentoOwner() {
		return super.getAjudaOwner();
	}
	
	public int getNumPessoasAlojamento() {
		return numPessoas;
	}
	
}
