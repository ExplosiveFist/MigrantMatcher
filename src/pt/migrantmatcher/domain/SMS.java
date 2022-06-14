package pt.migrantmatcher.domain;

public class SMS {
	
	public String code;
	public int num;
	public String txt;
	
	public SMS(Voluntario v) {
		
		this.num = v.getTelephoneNumber();
	
	}

}
