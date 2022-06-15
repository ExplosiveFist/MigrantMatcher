package pt.migrantmatcher.strategies;

import java.util.List;


import pt.migrantmatcher.facade.dto.AjudasDTO;

public interface SortStrategy {
	
	public void sortHelps(List<AjudasDTO> helplist);

}
