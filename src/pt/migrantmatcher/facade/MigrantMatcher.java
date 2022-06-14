package pt.migrantmatcher.facade;


import pt.migrantmatcher.domain.CatalogoAjudas;
import pt.migrantmatcher.domain.CatalogoRegioes;
import pt.migrantmatcher.domain.CatalogoUtilizadores;
import pt.migrantmatcher.facade.handlers.AjudasHandler;
import pt.migrantmatcher.facade.handlers.MigranteHandler;



//SISTEMA SINGLETON

public class MigrantMatcher {
	
	private CatalogoUtilizadores catU = new CatalogoUtilizadores();
	private CatalogoAjudas catA = new CatalogoAjudas();
	private CatalogoRegioes catR = new CatalogoRegioes();
	
	private static MigrantMatcher INSTANCE = new MigrantMatcher();
	
	//private CatalogoHandlers catH = newCatalogoHandlers();
	
	protected MigrantMatcher() {
		
	}
	
	public AjudasHandler getAjudasHandler() {
		
		return new AjudasHandler(catU,catR,catA);
		
	}
	
	public MigranteHandler getMigranteHandler() {
		
		return new MigranteHandler(catA,catR);
		
	}
	
	public static MigrantMatcher getInstance() {
		
		return INSTANCE;
	}
}
