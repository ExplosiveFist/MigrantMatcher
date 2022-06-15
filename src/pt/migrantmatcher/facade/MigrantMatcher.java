package pt.migrantmatcher.facade;


import pt.migrantmatcher.domain.catalogos.CatalogoAjudas;
import pt.migrantmatcher.domain.catalogos.CatalogoRegioes;
import pt.migrantmatcher.domain.catalogos.CatalogoUtilizadores;
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
		
		return new MigranteHandler(catA,catR,catU);
		
	}
	
	public static MigrantMatcher getInstance() {
		
		return INSTANCE;
	}
}
