package pt.migrantmatcher.domain;

import pt.migrantmatcher.exceptions.WrongDateFormatException;

public class Doacao extends Ajuda {
	
	private String descricao;
	
	public Doacao(String descricao, String date) throws WrongDateFormatException {
		this.descricao = descricao;
		try {
			super.checkDate(date);
			this.date = date;
		}catch(WrongDateFormatException e) {
			throw e;
		}
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
