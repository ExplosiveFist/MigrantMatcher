package pt.migrantmatcher.domain;

import java.util.List;

import pt.migrantmatcher.facade.dto.AjudasDTO;
import pt.migrantmatcher.facade.dto.AjudasDTOBuilder;
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
			
			if(ajuda instanceof Alojamento
			&& ajuda.getRegiaoAjuda().getNome().equals(regiao.getNome())) {
				
				AjudasDTO help = new AjudasDTOBuilder()
								.setType("Alojamento")
								.setOwnerPhone(ajuda.getAjudaOwner().getTelephoneNumber())
								.setCode(ajuda.getCode())
								.setRegion(ajuda.getRegiaoAjuda().getNome())
								.setDesc("Alojamento para " + 
										((Alojamento) ajuda).getNumPessoasAlojamento() + " pessoas")
								.build();
				
				help.setNum(((Alojamento) ajuda).getNumPessoasAlojamento());
				available.add(help);
			}
			if(ajuda instanceof Doacao) {
				
				AjudasDTO help = new AjudasDTOBuilder()
								.setType("Doação")
								.setOwnerPhone(ajuda.getAjudaOwner().getTelephoneNumber())
								.setCode(ajuda.getCode())
								.setDesc("Item disponível:  " + 
										((Doacao) ajuda).getDescricao())
								.build();
				
				help.setDescDono(((Doacao) ajuda).getDescricao());
				available.add(help);
			}
			
			
		}
		return available;
	}

	public void addRequested(AjudasDTO ajudaDTO) {
		
		for (Ajuda ajuda : ajudas) {
				if(isAjudaEqual(ajuda,ajudaDTO)) {
					this.requested.add(ajuda);
				}	
		}
		
	}

	private boolean isAjudaEqual(Ajuda ajuda, AjudasDTO ajudaDTO) {
		
		if(ajuda instanceof Alojamento && ajudaDTO.getType().equals("Alojamento")) {
			
		return 	   ajuda.getAjudaOwner().getTelephoneNumber() == ajudaDTO.getOwner()
				&& ajuda.getRegiaoAjuda().getNome().equals(ajudaDTO.getRegion())
				&& ajuda.getCode().equals(ajudaDTO.getCode())
				&& ((Alojamento) ajuda).getNumPessoasAlojamento() == ajudaDTO.getNum();
		}
  else  if(ajuda instanceof Doacao && ajudaDTO.getType().equals("Doação")){
	  
	  	return     ajuda.getAjudaOwner().getTelephoneNumber() == ajudaDTO.getOwner()
				&& ajuda.getCode().equals(ajudaDTO.getCode())
				&& ((Doacao) ajuda).getDescricao().equals(ajudaDTO.getDescDono());
		}
		
		
		return false;
	}

	public List<Ajuda> getRequested() {
		return this.requested;
	}

	public void sendSMS() {
		// TODO Auto-generated method stub
		
	}
}
