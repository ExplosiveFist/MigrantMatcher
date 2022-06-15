package pt.migrantmatcher.domain;

import java.util.List;

import pt.migrantmatcher.facade.dto.SMSDTO;

import java.util.ArrayList;


public class Voluntario extends Utilizador {
	
	private SMS sms;
	private List<Ajuda> ajudas;
	private Ajuda currentHelp;
	
	public Voluntario(int telemovel) {
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
		
		currentHelp = new Alojamento(i);
		
	}

	public SMSDTO setRegionAloj(Regiao region) {
		currentHelp.setRegion(region);
		
		return createSMS();
	}

	private SMSDTO createSMS() {
		SMS sms = new SMS(getTelephoneNumber());
		String code = generateCode();
		
		sms.setCode(code);
		sms.setMsg("Please confirm the code " + code);
		this.sms = sms;
		
		sms.send();
		
		return new SMSDTO(code);
	}

	private String generateCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public SMSDTO createDoacao(String desc) {
		currentHelp = new Doacao(desc);
		return createSMS();
	}

	public Ajuda verificarCodigo(String code) {
		if(this.sms.getCode().equals(code)) {
			addAjuda(this.currentHelp);
			return this.currentHelp;
		}
		return null;
	}
}
