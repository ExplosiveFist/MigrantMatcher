package pt.migrantmatcher.domain;

public class Doacao extends Ajuda {
	
	private String descricao;
	
	public Doacao(String descricao, String date) {
		this.descricao = descricao;
		this.date = date;
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
