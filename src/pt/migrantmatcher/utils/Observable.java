package pt.migrantmatcher.utils;

public interface Observable {


	public void addObserver(Observer o); 
	
	public void notifyObservers(String msg);
	
	public void removeObserver(Observer observer);
	
}
