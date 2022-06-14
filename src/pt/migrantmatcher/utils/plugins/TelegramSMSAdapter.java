package pt.migrantmatcher.utils.plugins;

import com.telegramsms.TelegramSMSSender;

public class TelegramSMSAdapter implements SMSProvider {
	
	TelegramSMSSender ts = new TelegramSMSSender();

	@Override
	public void send(String num, String txt) {
		
		ts.setNumber(num);
		ts.setText(txt);
		ts.send();
		
	}
	

}
