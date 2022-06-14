package pt.migrantmatcher.utils.plugins;

import com.pidgeonsmssender.sdk.*;



public class PidgeonSMSAdapter implements SMSProvider {
	
	PidgeonSMSSender ps = new PidgeonSMSSender();

	@Override
	public void send(String num, String txt) {
		
		ps.send(num, txt);
		
	}
	
	
}
