package pt.migrantmatcher.facade.handlers;

import java.util.List;

import pt.migrantmatcher.domain.Ajuda;
import pt.migrantmatcher.facade.dto.RegionDTO;
import pt.migrantmatcher.facade.dto.SMSDTO;
import pt.migrantmatcher.domain.Voluntario;
import pt.migrantmatcher.domain.catalogos.CatalogoAjudas;
import pt.migrantmatcher.domain.catalogos.CatalogoRegioes;
import pt.migrantmatcher.domain.catalogos.CatalogoUtilizadores;
import pt.migrantmatcher.exceptions.NullHelpException;
import pt.migrantmatcher.exceptions.VoluntaryNumberException;
import pt.migrantmatcher.exceptions.WrongCodeException;
import pt.migrantmatcher.exceptions.WrongRegionException;

public class AjudasHandler {
	
	private CatalogoUtilizadores catUser;
	private CatalogoRegioes catRegions;
	private Voluntario v_corrente;
	private CatalogoAjudas catAjudas;
	

	public AjudasHandler(CatalogoUtilizadores catU, CatalogoRegioes catR,CatalogoAjudas catA) {
		this.catUser = catU;
		this.catRegions = catR;
		this.catAjudas = catA;
		System.out.println("Please introduce your phone number");
	}

	public void verificarUtilizador(int num) throws VoluntaryNumberException {
		
		v_corrente = catUser.getVoluntario(num);
		
		if(v_corrente == null) {
			throw new VoluntaryNumberException("There is no voluntary with this number!");
		}
		else {
			System.out.println("Success! Please indicate your help type:");
		}
		
	}

	public List<RegionDTO> numPessoasAlojamento(int i, String date) throws VoluntaryNumberException {
		 if(v_corrente == null) {
			 throw new VoluntaryNumberException("No voluntary currently identificated");
		 }
		v_corrente.createAlojamento(i,date);
		System.out.println("Please choose a region for your residency");
		return catRegions.getRegions();
		
	}

	public SMSDTO regiaoPaisAlojamento(RegionDTO regionDTO) throws VoluntaryNumberException {
		 if(v_corrente == null) {
			 throw new VoluntaryNumberException("No voluntary currently identificated");
		 }
		SMSDTO sms = null;
		try {
			sms = v_corrente.setRegionAloj(catRegions.getRegion(regionDTO));
			
		} catch (NullHelpException e) {
			System.out.println(e.getMessage());
		} catch (WrongRegionException e) {
			System.out.println(e.getMessage());
		}
	
		return sms;
	}

	public SMSDTO descricaoItem(String desc, String date) throws VoluntaryNumberException {
		 if(v_corrente == null) {
			 throw new VoluntaryNumberException("No voluntary currently identificated");
		 }
		SMSDTO sms = v_corrente.createDoacao(desc,date);
		catRegions.publish();
		return sms;
		
		
	}

	public void inserirCodigoUnico(String code) throws WrongCodeException, VoluntaryNumberException {
		
		 if(v_corrente == null) {
			 throw new VoluntaryNumberException("No voluntary currently identificated");
		 }
		   else {
	         Ajuda help = v_corrente.verificarCodigo(code);
	         if(help == null){//Exceção wrong code
	    	    throw new WrongCodeException("Wrong code, check for the sms again");
	      }
	          else {
	            help.setOwner(v_corrente);
	            help.setCode(code);
	            catAjudas.registerHelp(help);
	      }
		 }
	}

}
