package pt.migrantmatcher.facade;

import pt.migrantmatcher.domain.*;
import pt.migrantmatcher.facade.handlers.AjudasHandler;
import pt.migrantmatcher.facade.handlers.MigranteHandler;

public class MigrantMatcher {
	
	private CatalogoUtilizadores catU = new CatalogoUtilizadores();
	private CatalogoAjudas catA = new CatalogoAjudas();
	private CatalogoRegioes catR = new CatalogoRegioes();
	
	//private CatalogoHandlers catH = newCatalogoHandlers();
	
	
	
	public AjudasHandler getAjudasHandler() {
		
		return new AjudasHandler(catU,catR);
		
	}
	
	public MigranteHandler getMigranteHandler() {
		
		return new MigranteHandler(catA,catR);
		
	}
}
