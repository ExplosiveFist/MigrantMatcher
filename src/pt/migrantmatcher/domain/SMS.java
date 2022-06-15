package pt.migrantmatcher.domain;

import pt.migrantmatcher.utils.Configuration;
import pt.migrantmatcher.utils.plugins.SMSProvider;

public class SMS {
	
	private String code;
	private int num;
	private String txt;
	
	public SMS(int num) {
		
		this.num = num;
	
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public void setMsg(String msg) {
		this.txt = msg;
	}
	
	public String getCode() {
		return this.code;
	}

	public void send() {
		SMSProvider p = Configuration.getInstance().getProvider();
		p.send(num, txt);
	}
}
