package pt.migrantmatcher.domain;

public class Doacao extends Ajuda {
	
	private String descricao;
	
	public Doacao(Voluntario owner, Regiao region, String descricao) {
		super(owner, region);
		this.descricao = descricao;
	}
	
	public Regiao getRegiaoDoacao() {
		return super.getRegiaoAjuda();
	}
	
	public String getDescricao() {
		return descricao;
	}
}
