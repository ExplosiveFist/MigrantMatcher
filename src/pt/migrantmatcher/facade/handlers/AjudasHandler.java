package pt.migrantmatcher.facade.handlers;

import java.util.List;

import pt.migrantmatcher.domain.CatalogoRegioes;
import pt.migrantmatcher.domain.CatalogoUtilizadores;
import pt.migrantmatcher.domain.Regiao;
import pt.migrantmatcher.facade.dto.RegionDTO;
import pt.migrantmatcher.facade.dto.SMSDTO;
import pt.migrantmatcher.domain.SMS;
import pt.migrantmatcher.domain.Voluntario;

public class AjudasHandler {
	
	private CatalogoUtilizadores catUser;
	private CatalogoRegioes catRegions;
	private Voluntario v_corrente;
	

	public AjudasHandler(CatalogoUtilizadores catU, CatalogoRegioes catR) {
		this.catUser = catU;
		this.catRegions = catR;
	}

	public void verificarUtilizador(int num) {
		
		v_corrente = catUser.getVoluntario(num);
		
		if(v_corrente == null) {
			System.out.println("Exception"); //TODO
		}
		
	}

	public List<RegionDTO> numPessoasAlojamento(int i) {
		
		v_corrente.createAlojamento(i);
		
		return catRegions.getRegions();
		
	}

	public SMSDTO regiaoPaisAlojamento(RegionDTO regionDTO) {
		
		Regiao region = catRegions.getRegion(regionDTO);
		
		SMSDTO sms = v_corrente.setRegionAloj(region);
		
		return sms;
	}

	public SMSDTO descricaoItem(String desc) {
		
		SMSDTO sms = v_corrente.createDoacao(desc);
		return sms;
		
		
	}

	public void inserirCodigoUnico(String code) {
		
	      v_corrente.verificarCodigo(code); //Exceção wrong code
		
	}

}
