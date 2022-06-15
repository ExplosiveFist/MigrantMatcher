package pt.migrantmatcher.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import pt.migrantmatcher.utils.plugins.SMSProvider;

public class Configuration {

	private static Configuration INSTANCE = new Configuration();
	private SMSProvider provider;
	
	public static Configuration getInstance() {
		return INSTANCE;
	}
	
	protected Configuration() {
		
		Properties prop = new Properties();
		
		try {
			
			prop.load(new FileInputStream(new File("smsprovider.properties")));
			
			String className =  prop.getProperty("smsprovider");
			@SuppressWarnings("unchecked")
			Class<SMSProvider> klass = (Class<SMSProvider>) Class.forName(className);
			Constructor <SMSProvider> cons = klass.getConstructor();
			provider = cons.newInstance();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public SMSProvider getProvider() {
		return this.provider;
	}
	
	
	
}
