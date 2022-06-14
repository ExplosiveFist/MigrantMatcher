package pt.migrantmatcher.domain;

public class Alojamento extends Ajuda {
	
	private int numPessoas;
	
	public Alojamento(int numPessoas) {	
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
