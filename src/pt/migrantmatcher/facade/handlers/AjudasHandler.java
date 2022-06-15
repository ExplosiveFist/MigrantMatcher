package pt.migrantmatcher.facade.handlers;

import java.util.List;

import pt.migrantmatcher.domain.Ajuda;
import pt.migrantmatcher.facade.dto.RegionDTO;
import pt.migrantmatcher.facade.dto.SMSDTO;
import pt.migrantmatcher.domain.Voluntario;
import pt.migrantmatcher.domain.catalogos.CatalogoAjudas;
import pt.migrantmatcher.domain.catalogos.CatalogoRegioes;
import pt.migrantmatcher.domain.catalogos.CatalogoUtilizadores;

public class AjudasHandler {
	
	private CatalogoUtilizadores catUser;
	private CatalogoRegioes catRegions;
	private Voluntario v_corrente;
	private CatalogoAjudas catAjudas;
	

	public AjudasHandler(CatalogoUtilizadores catU, CatalogoRegioes catR,CatalogoAjudas catA) {
		this.catUser = catU;
		this.catRegions = catR;
		this.catAjudas = catA;
	}

	public void verificarUtilizador(int num) {
		
		v_corrente = catUser.getVoluntario(num);
		
		if(v_corrente == null) {
			System.out.println("Exception"); //TODO Não tem voluntário registado com este número
		}
		else {
			System.out.println("Success!");
		}
		
	}

	public List<RegionDTO> numPessoasAlojamento(int i, String date) {
		
		v_corrente.createAlojamento(i,date);
		
		return catRegions.getRegions();
		
	}

	public SMSDTO regiaoPaisAlojamento(RegionDTO regionDTO) {
		
		SMSDTO sms = v_corrente.setRegionAloj(catRegions.getRegion(regionDTO));
		
		return sms;
	}

	public SMSDTO descricaoItem(String desc, String date) {
		
		SMSDTO sms = v_corrente.createDoacao(desc,date);
		return sms;
		
		
	}

	public void inserirCodigoUnico(String code) {
		
	      Ajuda help = v_corrente.verificarCodigo(code); //Exceção wrong code
	      help.setOwner(v_corrente);
	      help.setCode(code);
	      catAjudas.registerHelp(help);
		
	}

}
