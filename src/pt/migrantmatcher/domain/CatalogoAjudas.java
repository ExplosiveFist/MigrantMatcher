package pt.migrantmatcher.domain;

import java.util.List;

import pt.migrantmatcher.facade.dto.AjudasDTO;
import pt.migrantmatcher.facade.dto.RegionDTO;

import java.util.ArrayList;


public class CatalogoAjudas {

	List<Ajuda> ajudas;
	List<Ajuda> requested;
	
	public CatalogoAjudas() {
		this.ajudas = new ArrayList<Ajuda>();
		this.requested = new ArrayList<Ajuda>();
	}

	public void registerHelp(Ajuda help) {
		ajudas.add(help);
		
	}

	public List<AjudasDTO> getAvailableHelps(Regiao regiao) {
		List<AjudasDTO> available = new ArrayList<AjudasDTO>();
		for (Ajuda ajuda : ajudas) {
			
			if(ajuda.getRegiaoAjuda().getNome().equals(regiao.getNome())) {
				AjudasDTO aj = new AjudasDTO("Alojamento");
				aj.setRegion(regiao.getNome());
				aj.setDesc("Casa para " + 
						  ((Alojamento) ajuda).getNumPessoasAlojamento() + 
							" pessoas");
				available.add(aj);
			}
			if(ajuda instanceof Doacao) {
				AjudasDTO aj = new AjudasDTO("Doação");
				aj.setDesc(((Doacao) ajuda).getDescricao());
				available.add(aj);
			}
			
			
		}
		return available;
	}

	public void addRequested(AjudasDTO ajudasDTO) {
		// TODO Auto-generated method stub
		
	}

	public List<Ajuda> getRequested() {
		// TODO Auto-generated method stub
		return null;
	}

	public void sendSMS() {
		// TODO Auto-generated method stub
		
	}
}
