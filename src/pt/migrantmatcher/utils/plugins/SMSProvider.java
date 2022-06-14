package pt.migrantmatcher.utils.plugins;

public interface SMSProvider {

	public void send(String num, String txt);
	
}
