package pt.migrantmatcher.utils.plugins;

public interface SMSProvider {

	public void send(int num, String txt);
	
}
