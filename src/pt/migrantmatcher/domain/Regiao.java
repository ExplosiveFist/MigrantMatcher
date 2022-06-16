package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

import pt.migrantmatcher.utils.Observable;
import pt.migrantmatcher.utils.Observer;

public class Regiao implements Observable {

	private String nome;
	private List<Observer> obs;
	
	public Regiao(String n) {
		this.nome = n;
		this.obs = new ArrayList<Observer>();
	}
	
	public String getNome() {
		return nome;
	}

	@Override
	public void addObserver(Observer o) {
		this.obs.add(o);
		System.out.println("Foi subscrito à região :" + this.nome);
		
	}

	@Override
	public void notifyObservers(String msg) {
		if(!obs.isEmpty()) {
			for (Observer observer : obs) {
				observer.update(msg);
			}
		}
	}

	@Override
	public void removeObserver(Observer observer) {
		this.obs.remove(observer);
		
	}
}
