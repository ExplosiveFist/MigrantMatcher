package pt.migrantmatcher.facade.handlers;

import java.util.List;

import pt.migrantmatcher.domain.CatalogoAjudas;
import pt.migrantmatcher.domain.CatalogoRegioes;
import pt.migrantmatcher.domain.CatalogoUtilizadores;
import pt.migrantmatcher.domain.Migrante;

import pt.migrantmatcher.facade.dto.AjudasDTO;
import pt.migrantmatcher.facade.dto.RegionDTO;

public class MigranteHandler {

	private CatalogoRegioes catRegions;
	private CatalogoAjudas catAjudas;
	private Migrante m_corrente;
	private CatalogoUtilizadores catUser;
	
	public MigranteHandler(CatalogoAjudas catA, CatalogoRegioes catR, CatalogoUtilizadores catU) {
		this.catRegions = catR;
		this.catAjudas =  catA;
		this.catUser = catU;
		
	}

	public void registarMigrante(String nome, int num) {
		m_corrente = catUser.addMigrante(nome,num);
	}

	public void registarFamily(int familyNumber, String nome, int num) {
		
		m_corrente = catUser.addMigrante(nome,num,familyNumber);
		
	}

	public void registerMember(String nome) {
		
		m_corrente.registarMembro(nome); //Need check?
		
	}

	public List<RegionDTO> pedirListaRegioes() {
		
		return catRegions.getRegions();
	}

	public List<AjudasDTO> escolherRegiao(RegionDTO regionDTO) {
		
		
		List<AjudasDTO> availableHelps = catAjudas.getAvailableHelps(regionDTO);
		
		
		return availableHelps;
		
	}

	public void notifyMe(RegionDTO regionDTO) {
		//TODO
		
	}

	public void escolherTipoDeAjuda(AjudasDTO ajudasDTO) {
		
		catAjudas.addRequested(ajudasDTO);
		
	}

	public void confirmar() {
			
		m_corrente.saveHelpList(catAjudas.getRequested());
		catAjudas.sendSMS();
		
	}

}
