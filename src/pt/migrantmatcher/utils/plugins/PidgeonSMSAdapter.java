package pt.migrantmatcher.utils.plugins;



import com.pidgeonsmssender.sdk.PidgeonSMSSender;



public class PidgeonSMSAdapter implements SMSProvider {
	
	

	@Override
	public void send(int num, String txt) {
		
		new PidgeonSMSSender().send(String.valueOf(num), txt);
		
	}
	
	
}
