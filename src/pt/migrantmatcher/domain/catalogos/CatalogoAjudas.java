package pt.migrantmatcher.domain.catalogos;

import java.util.List;

import pt.migrantmatcher.domain.Ajuda;
import pt.migrantmatcher.domain.Alojamento;
import pt.migrantmatcher.domain.Doacao;
import pt.migrantmatcher.domain.Regiao;
import pt.migrantmatcher.facade.dto.AjudasDTO;
import pt.migrantmatcher.facade.dto.AjudasDTOBuilder;
import pt.migrantmatcher.utils.Configuration;
import pt.migrantmatcher.strategies.SortStrategy;

import java.util.ArrayList;


public class CatalogoAjudas {

	List<Ajuda> ajudas;
	List<Ajuda> requested;
	
	public CatalogoAjudas() {
		this.ajudas = new ArrayList<Ajuda>();
		
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
								.setDate(ajuda.getDate())
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
								.setDate(ajuda.getDate())
								.build();
				
				help.setDescDono(((Doacao) ajuda).getDescricao());
				available.add(help);
			}
			
			
		}
		SortStrategy strategy  = Configuration.getInstance().getStrategy();
		strategy.sortHelps(available);
		return available;
	}

	public boolean addRequested(AjudasDTO ajudaDTO) {
		this.requested = new ArrayList<Ajuda>();
		for (Ajuda ajuda : ajudas) {
				if(isAjudaEqual(ajuda,ajudaDTO)) {
					this.requested.add(ajuda);
					return true;
				}	
		}
		return false;
	}

	private boolean isAjudaEqual(Ajuda ajuda, AjudasDTO ajudaDTO) {
		
		if(ajuda instanceof Alojamento && ajudaDTO.getType().equals("Alojamento")) {
			
		return 	   ajuda.getAjudaOwner().getTelephoneNumber() == ajudaDTO.getOwner()
				&& ajuda.getRegiaoAjuda().getNome().equals(ajudaDTO.getRegion())
				&& ajuda.getCode().equals(ajudaDTO.getCode())
				&& ((Alojamento) ajuda).getNumPessoasAlojamento() == ajudaDTO.getNum()
				&& ajuda.getDate().equals(ajudaDTO.getDate());
		}
  else  if(ajuda instanceof Doacao && ajudaDTO.getType().equals("Doação")){
	  
	  	return     ajuda.getAjudaOwner().getTelephoneNumber() == ajudaDTO.getOwner()
				&& ajuda.getCode().equals(ajudaDTO.getCode())
				&& ((Doacao) ajuda).getDescricao().equals(ajudaDTO.getDescDono())
				&& ajuda.getDate().equals(ajudaDTO.getDate());
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
