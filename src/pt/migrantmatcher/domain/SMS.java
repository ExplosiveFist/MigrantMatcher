package pt.migrantmatcher.domain;

import pt.migrantmatcher.utils.plugins.SMSProvider;

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

	public void send() {
		// TODO Auto-generated method stub
		
	}
}
