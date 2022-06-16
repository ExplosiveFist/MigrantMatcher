package pt.migrantmatcher.strategies;


import java.util.List;


import pt.migrantmatcher.facade.dto.AjudasDTO;

public class SortByDate implements SortStrategy {
	


	@Override
	public void sortHelps(List<AjudasDTO> helplist) {
		
		int key,j;
		
	    for (int i = 1; i < helplist.size(); i++){
	        key = getYear(helplist.get(i));
	        AjudasDTO hKey = helplist.get(i);
	        j = i - 1;
	        
	        while (j >= 0 && getYear(helplist.get(j)) > key){
	        	
	            helplist.set(j + 1,helplist.get(j));
	            j--;
	        }
	        helplist.set(j + 1, hKey);
	    }
		
		//Por agora só dá sort por ano
			
	}
	

	private int getYear(AjudasDTO ajuda) {
		String data = ajuda.getDate();
		String [] div = data.split("/");
		return Integer.parseInt(div[2]);
	}
	


}
