package pt.migrantmatcher.strategies;

import java.util.ArrayList;
import java.util.List;

import pt.migrantmatcher.facade.dto.AjudasDTO;

public class SortByType implements SortStrategy {


	@Override
	public void sortHelps(List<AjudasDTO> helplist) {
		
		List<AjudasDTO> h = new ArrayList<AjudasDTO>();
		
		for (int i = 0; i < helplist.size(); i++) {
			 
			if(helplist.get(i).getType().equals("Alojamento")) {
				
				h.add(0,helplist.get(i));
			}
			if(helplist.get(i).getType().equals("Doação")) {	
				h.add(helplist.get(i));
				}
		}
		helplist.clear();
		helplist.addAll(h);
		
	}

}
