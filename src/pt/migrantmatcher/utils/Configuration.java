package pt.migrantmatcher.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import pt.migrantmatcher.strategies.SortStrategy;
import pt.migrantmatcher.utils.plugins.SMSProvider;


public class Configuration {

	private static Configuration INSTANCE = new Configuration();
	private SMSProvider provider;
	private SortStrategy strategy;
	
	public static Configuration getInstance() {
		return INSTANCE;
	}
	
	protected Configuration() {
		
		Properties prop = new Properties();
		
		try {
			
			prop.load(new FileInputStream(new File("smsprovider.properties")));
			
			String className =  prop.getProperty("smsprovider");
			String className2 = prop.getProperty("sortstrategy");
			
			Class<?> klass = Class.forName(className);
			Constructor <?> cons = klass.getConstructor();
			provider = (SMSProvider) cons.newInstance();
			
			Class<?> klass2 = Class.forName(className2);
			Constructor <?> cons2 = klass2.getConstructor();
			strategy = (SortStrategy) cons2.newInstance();
			
			
			
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
	
	public SortStrategy getStrategy() {
		return this.strategy;
	}
	
	
	
	
}
