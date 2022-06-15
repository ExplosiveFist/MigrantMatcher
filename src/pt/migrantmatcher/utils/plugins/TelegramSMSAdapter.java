package pt.migrantmatcher.utils.plugins;

import com.telegramsms.TelegramSMSSender;

public class TelegramSMSAdapter implements SMSProvider {
	
	TelegramSMSSender ts = new TelegramSMSSender();

	@Override
	public void send(int num, String txt) {
		
		ts.setNumber(String.valueOf(num));
		ts.setText(txt);
		ts.send();
		
	}
	

}
