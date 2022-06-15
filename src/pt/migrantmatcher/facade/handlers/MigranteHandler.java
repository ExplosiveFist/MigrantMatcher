package pt.migrantmatcher.facade.handlers;

import java.util.List;

import pt.migrantmatcher.domain.Migrante;
import pt.migrantmatcher.domain.catalogos.CatalogoAjudas;
import pt.migrantmatcher.domain.catalogos.CatalogoRegioes;
import pt.migrantmatcher.domain.catalogos.CatalogoUtilizadores;
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
		
		boolean isFull = m_corrente.addFamilyMember(nome);
		if(isFull){
			//Need exception?
		}
		
	}

	public List<RegionDTO> pedirListaRegioes() {
		
		return catRegions.getRegions();
	}

	public List<AjudasDTO> escolherRegiao(RegionDTO regionDTO) {
		
		
		List<AjudasDTO> availableHelps = catAjudas.getAvailableHelps(catRegions.getRegion(regionDTO));
		
		
		return availableHelps;
		
	}

	public void notifyMe(RegionDTO regionDTO) {
		//TODO
		
	}

	public void escolherTipoDeAjuda(AjudasDTO ajudasDTO) {
		
		boolean success = catAjudas.addRequested(ajudasDTO);
		if(success) {
			System.out.println("Help '" + ajudasDTO.getType() + "' chosen successfully");
		}
		
	}

	public void confirmar() {
			
		m_corrente.saveHelpList(catAjudas.getRequested());
		catAjudas.sendSMS();
		
	}

}
