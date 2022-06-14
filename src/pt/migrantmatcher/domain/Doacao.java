package pt.migrantmatcher.domain;

public class Doacao extends Ajuda {
	
	private String descricao;
	
	public Doacao(String descricao) {
		this.descricao = descricao;
	}
	
	public Regiao getRegiaoDoacao() {
		return super.getRegiaoAjuda();
	}
	
	public Voluntario getDoacaoOwner() {
		return super.getAjudaOwner();
	}
	
	public String getDescricao() {
		return descricao;
	}
}
