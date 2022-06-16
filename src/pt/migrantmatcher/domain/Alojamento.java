package pt.migrantmatcher.domain;

import pt.migrantmatcher.exceptions.WrongDateFormatException;

public class Alojamento extends Ajuda {
	
	private int numPessoas;
	
	public Alojamento(int numPessoas, String date) throws WrongDateFormatException {	
		this.numPessoas = numPessoas;
		
		try {
			super.checkDate(date);
			this.date = date;
		}catch(WrongDateFormatException e) {
			throw e;
		}
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
