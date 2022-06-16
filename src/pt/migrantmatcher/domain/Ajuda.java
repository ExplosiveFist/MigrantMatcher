package pt.migrantmatcher.domain;

import pt.migrantmatcher.exceptions.WrongDateFormatException;

public class Ajuda {
	
	private Regiao region;
	private Voluntario owner;
	private String code;
	protected String date;

	public Ajuda() {
		
	}
	
	public Regiao getRegiaoAjuda() {
		return region;
	}
	
	public Voluntario getAjudaOwner() {
		return owner;
	}

	public void setRegion(Regiao r) {
		this.region = r;
		r.notifyObservers("Novo Alojamento na região de(o) " + r.getNome() + " !");
		
	}


	public void setOwner(Voluntario v) {
		
		this.owner = v;
		
	}
	
	public void setCode(String s) {
		this.code = s;
	}
	public String getCode() {
		return code;
	}
	public String getDate() {
		return date;
	}
	
	public void checkDate(String date) throws WrongDateFormatException {
		String copy = date;
		String [] div = copy.split("/");
		if(div.length == 3) {
			if(div[0].length() == 2 && div[1].length() == 2 && div[2].length() == 4) {
				try {
					@SuppressWarnings("unused")
					int d,m,y;
					d = Integer.parseInt(div[0]);
					m = Integer.parseInt(div[1]);
					y = Integer.parseInt(div[2]);
					
				}catch(NumberFormatException e) {
					throw new WrongDateFormatException("!!!   Wrong date format! (Correct format: 09/09/1999)  !!!");
				}

			}else{
				throw new WrongDateFormatException("!!!   Wrong date format! (Correct format: 09/09/1999)  !!!");
			}
		}else {
			throw new WrongDateFormatException("!!!  Wrong date format! (Correct format: 09/09/1999)  !!!");
		}
		
	}
	
}
