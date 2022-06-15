package pt.migrantmatcher.domain;


import java.util.ArrayList;
import java.util.List;

import pt.migrantmatcher.facade.dto.RegionDTO;
import pt.migrantmatcher.domain.Regiao;

public class CatalogoRegioes {
	
	private List<Regiao> regions;
	private List<RegionDTO> regionsDTO;
	
	public CatalogoRegioes() {
		this.regions = new ArrayList<Regiao>();
		this.regionsDTO = new ArrayList<RegionDTO>();
		
		regions.add(new Regiao("Lisboa"));
		regions.add(new Regiao("Norte"));
		regions.add(new Regiao("Centro"));
		regions.add(new Regiao("Algarve"));
		regions.add(new Regiao("Alentejo"));
		regions.add(new Regiao("Açores"));
		regions.add(new Regiao("Madeira"));
		
	}

	public List<RegionDTO> getRegions() {
		
		if(regionsDTO.isEmpty()) {
			for (Regiao regiao : regions) {
				regionsDTO.add(new RegionDTO(regiao.getNome()));
			}
		}
		return regionsDTO;
	}

	public Regiao getRegion(RegionDTO regionDTO) {
		
		String name = regionDTO.getName();
		for (Regiao regiao : regions) {
			if(regiao.getNome().equals(name)) {
				return regiao;
			}
		}
		return null;
	}

}
