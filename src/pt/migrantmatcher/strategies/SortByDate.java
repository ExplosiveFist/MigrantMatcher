package pt.migrantmatcher.strategies;


import java.util.ArrayList;
import java.util.List;


import pt.migrantmatcher.facade.dto.AjudasDTO;

public class SortByDate implements SortStrategy {
	


	@Override
	public void sortHelps(List<AjudasDTO> helplist) {
		
		List<Integer> same_years;
		same_years = sortByYear(helplist);
		
		if(!same_years.isEmpty()) {
			
			List<AjudasDTO> copy = new ArrayList<AjudasDTO>(helplist);
			List<Integer> samemonth = new ArrayList<Integer>();
			
			for (Integer year : same_years) {
				
				boolean sameMonthYearFound;
				int init = samemonth.size();
				List<AjudasDTO> repeatedYear = new ArrayList<AjudasDTO>();
				List<Integer> index = new ArrayList<Integer>();
				
				for (int i = 0; i < helplist.size(); i++) {
					
					if(getYear(helplist.get(i)) == year) {
						repeatedYear.add(helplist.get(i));
						copy.set(i,null);
						index.add(i);
					}
				}
				samemonth.addAll(sortByMonths(repeatedYear));
				sameMonthYearFound = !(init == samemonth.size());
				
				if(sameMonthYearFound) {
					sortByDays(repeatedYear);
					//System.out.println(repeatedYear);
				}
				
				for (int j = 0; j < index.size(); j++) {
					copy.set(index.get(j), repeatedYear.get(j));
				}

			}
			helplist = copy;
		}
		
	}
	
	private void sortByDays(List<AjudasDTO> list) {
		
		
		int key,j;
		
	    for (int i = 1; i < list.size(); i++){
	        key = getDay(list.get(i));
	        AjudasDTO hKey = list.get(i);
	        j = i - 1;
	        
	        while (j >= 0 && getDay(list.get(j)) > key){
	        	
	            list.set(j + 1, list.get(j));
	            j--;
	        }
	        list.set(j + 1, hKey);
	    }
	}

	private List<Integer> sortByMonths(List<AjudasDTO> list) {
		
		List<Integer> samemonths = new ArrayList<Integer>();
		int key,j;
		
	    for (int i = 1; i < list.size(); i++){
	        key = getMonth(list.get(i));
	        AjudasDTO hKey = list.get(i);
	        j = i - 1;
	        
	        while (j >= 0 && getMonth(list.get(j)) > key){
	        	
	        	if(key == getMonth(list.get(j))) {
	        		samemonths.add(key);
	        	}
	            list.set(j + 1, list.get(j));
	            j--;
	        }
	        list.set(j + 1, hKey);
	    } 
	    return samemonths;
	}

	private List<Integer> sortByYear(List<AjudasDTO> helplist) {
		
		List<Integer> sameyears = new ArrayList<Integer>();
		int key,j;
		
	    for (int i = 1; i < helplist.size(); i++){
	        key = getYear(helplist.get(i));
	        AjudasDTO hKey = helplist.get(i);
	        j = i - 1;
	        
	        while (j >= 0 && getYear(helplist.get(j)) > key){
	        	
	        	if(key == getYear(helplist.get(j))) {
	        		sameyears.add(key);
	        	}
	            helplist.set(j + 1,helplist.get(j));
	            j--;
	        }
	        helplist.set(j + 1, hKey);
	    }
	    
	    return sameyears;
	}
	

	private int getYear(AjudasDTO ajuda) {
		String data = ajuda.getDate();
		String [] div = data.split("/");
		return Integer.parseInt(div[2]);
	}
	
	private int getMonth(AjudasDTO ajuda) {
		String data = ajuda.getDate();
		String [] div = data.split("/");
		return Integer.parseInt(div[1]);
	}
	
	private int getDay(AjudasDTO ajuda) {
		String data = ajuda.getDate();
		String [] div = data.split("/");
		return Integer.parseInt(div[0]);
	}
	


}
