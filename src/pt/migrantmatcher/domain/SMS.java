package pt.migrantmatcher.domain;

public class SMS {
	
	public String code;
	public int num;
	public String txt;
	
	public SMS(int num) {
		
		this.num = num;
	
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public void setMsg(String msg) {
		this.txt = msg;
	}
}
