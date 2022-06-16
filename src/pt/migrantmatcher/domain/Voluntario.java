package pt.migrantmatcher.domain;

import java.util.List;
import java.util.Random;

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

	public void createAlojamento(int i, String date) {
		
		currentHelp = new Alojamento(i, date);
		
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
	
	public void sendSMS(String msg) {
		
		SMS sms = new SMS(getTelephoneNumber());
		sms.setMsg(msg);
		sms.send();
		
	}

	private String generateCode() {
		
		Random r = new Random();

	    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    StringBuilder bob = new StringBuilder();
	    
	    for (int i = 0; i < 5; i++) {
	        bob.append(alphabet.charAt(r.nextInt(alphabet.length())));
	    }
		return bob.toString(); 
	}
	

	public SMSDTO createDoacao(String desc, String date) {
		currentHelp = new Doacao(desc, date);
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
