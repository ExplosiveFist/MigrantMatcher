package pt.migrantmatcher.facade.handlers;

import java.util.List;

import pt.migrantmatcher.domain.Migrante;
import pt.migrantmatcher.domain.catalogos.CatalogoAjudas;
import pt.migrantmatcher.domain.catalogos.CatalogoRegioes;
import pt.migrantmatcher.domain.catalogos.CatalogoUtilizadores;
import pt.migrantmatcher.exceptions.NullMigrantException;
import pt.migrantmatcher.facade.dto.AjudasDTO;
import pt.migrantmatcher.facade.dto.RegionDTO;

public class MigranteHandler {

	private CatalogoRegioes catRegions;
	private CatalogoAjudas catAjudas;
	private Migrante m_corrente = null;
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

	public void registerMember(String nome) throws NullMigrantException {
		
		if (m_corrente == null) {
			throw new NullMigrantException("No migrant is currently on this session");
		}
		boolean isFull = m_corrente.addFamilyMember(nome);
		if(isFull){
			System.out.println("No more slots for familiy registry");
		}
		
	}

	public List<RegionDTO> pedirListaRegioes() {
		
		return catRegions.getRegions();
	}

	public List<AjudasDTO> escolherRegiao(RegionDTO regionDTO) throws NullMigrantException {
		
		if (m_corrente == null) {
			throw new NullMigrantException("No migrant is currently on this session");
		}
		
		List<AjudasDTO> availableHelps = catAjudas.getAvailableHelps(catRegions.getRegion(regionDTO));
		
		return availableHelps;
		
	}

	public void notifyMe(RegionDTO regionDTO) throws NullMigrantException {
		
		if (m_corrente == null) {
			throw new NullMigrantException("No migrant is currently on this session");
		}
		catRegions.getRegion(regionDTO).addObserver(m_corrente);
		
	}

	public void escolherTipoDeAjuda(AjudasDTO ajudasDTO) {
		
		boolean success = catAjudas.addRequested(ajudasDTO);
		if(success) {
			System.out.println("Help '" + ajudasDTO.getType() + "' chosen successfully");
		}
		
	}

	public void confirmar() throws NullMigrantException {
		if (m_corrente == null) {
			throw new NullMigrantException("No migrant is currently on this session");
		}
		m_corrente.saveHelpList(catAjudas.getRequested());
		catAjudas.sendSMS();
		
	}

}
