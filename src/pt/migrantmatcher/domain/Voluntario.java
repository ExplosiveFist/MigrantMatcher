package pt.migrantmatcher.domain;

import java.util.List;

import pt.migrantmatcher.facade.dto.SMSDTO;

import java.util.ArrayList;


public class Voluntario extends Utilizador {
	
	private String codigoInserido;
	private List<Ajuda> ajudas;
	
	public Voluntario(int codigo, int telemovel) {
		super(telemovel);
		ajudas = new ArrayList<Ajuda>();
	}
	
	public int getTelephoneNumber() {
		return super.getTelephoneNumber();
	}
	
	public void addAjuda(Ajuda ajuda) {
		ajudas.add(ajuda);
	}

	public void createAlojamento(int i) {
		// TODO Auto-generated method stub
		
	}

	public SMSDTO setRegionAloj(Regiao region) {
		// TODO Auto-generated method stub
		return null;
	}

	public SMSDTO createDoacao(String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	public void verificarCodigo(String code) {
		// TODO Auto-generated method stub
		
	}
}
