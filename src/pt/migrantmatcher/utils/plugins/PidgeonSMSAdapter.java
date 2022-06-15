package pt.migrantmatcher.utils.plugins;

import com.pidgeonsmssender.sdk.*;



public class PidgeonSMSAdapter implements SMSProvider {
	
	PidgeonSMSSender ps = new PidgeonSMSSender();

	@Override
	public void send(int num, String txt) {
		
		ps.send(String.valueOf(num), txt);
		
	}
	
	
}
