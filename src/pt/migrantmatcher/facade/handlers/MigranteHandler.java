package pt.migrantmatcher.facade.handlers;

import java.util.List;

import pt.migrantmatcher.domain.Migrante;
import pt.migrantmatcher.domain.catalogos.CatalogoAjudas;
import pt.migrantmatcher.domain.catalogos.CatalogoRegioes;
import pt.migrantmatcher.domain.catalogos.CatalogoUtilizadores;
import pt.migrantmatcher.exceptions.NullMigrantException;
import pt.migrantmatcher.exceptions.WrongRegionException;
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
		System.out.println("Please give state your name and phone number or if you want to register with a family,"
				+ " please give the number of family members you want to register aswell");
		
	}

	public void registarMigrante(String nome, int num) {
		m_corrente = catUser.addMigrante(nome,num);
	}

	public void registarFamily(int familyNumber, String nome, int num) throws NullMigrantException {
		
		if(familyNumber == 0) {
			throw new NullMigrantException("If you do not want to register with a family please dont use this option (family size inputed was 0) ");
		}
		if(familyNumber < 0) {
			throw new NullMigrantException("Insert positive number for the family members amount");
		}
		
		m_corrente = catUser.addMigrante(nome,num,familyNumber);
		
	}

	public void registerMember(String nome) throws NullMigrantException {
		
		if (m_corrente == null || m_corrente.getFam() == null) {
			throw new NullMigrantException("No migrant is currently on this session or didnt register as Family Host");
		}
		boolean isFull = m_corrente.addFamilyMember(nome);
		if(isFull){
			System.out.println("No more slots for familiy registry");
		}

		
	}

	public List<RegionDTO> pedirListaRegioes() {
		
		return catRegions.getRegions();
	}

	public List<AjudasDTO> escolherRegiao(RegionDTO regionDTO) throws NullMigrantException, WrongRegionException {
		
		if (m_corrente == null) {
			throw new NullMigrantException("No migrant is currently on this session");
		}
		
		List<AjudasDTO> availableHelps = catAjudas.getAvailableHelps(catRegions.getRegion(regionDTO));
		if(availableHelps == null) {
			throw new WrongRegionException("The region you chose is not available");
		}
		if(availableHelps.isEmpty()) {
			System.out.println("No available helps in the region at the moment... Notification for update in region available");
			return null;
		}
		System.out.println("Please choose the helps you want to get:");
		for (AjudasDTO ajuda : availableHelps) {
			System.out.println(" -----> " + ajuda.getDesc());
		}
		
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
		else {
			System.out.println("The help you chose is not correct or it doesn't exist");
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
