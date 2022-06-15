package pt.migrantmatcher.main;

import pt.migrantmatcher.facade.*;
import pt.migrantmatcher.facade.handlers.*;
import pt.migrantmatcher.facade.dto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Client{
	
	public static void main(String [] args) {
		
		
		//UC1
		System.out.println("-----REGISTING HELP FROM VOLUNTARY----");
		AjudasHandler ah = MigrantMatcher.getInstance().getAjudasHandler();
		System.out.println("Please introduce your phone number");
		
		ah.verificarUtilizador(918376458); //Exception number not recognized
		
		SMSDTO sms;
		System.out.println("Please indicate your help type:");
		Random r  = new Random ();
		if(r.nextBoolean()) { // Alojamento
			
			List<RegionDTO> regions = ah.numPessoasAlojamento(5,"09/09/21");
			
			System.out.println("Available reigions: \n");
			for (RegionDTO regionDTO : regions) {
				System.out.println(regionDTO.getName());
			}
			
			System.out.println("Please choose an available region.");
			int choice = r.nextInt(regions.size());                    //Simula escolha de regiao
				
			sms = ah.regiaoPaisAlojamento(regions.get(choice)); 
		}
		else { // Item
			
			sms = ah.descricaoItem("Batata frita: da boa","09/09/21");
		}
		
		System.out.println("Insert the code sent to your number to confirm the pending help.");
		ah.inserirCodigoUnico(sms.getCode());
		System.out.println("Your Help as been submitted! \n");
		
		
		
		//UC2
		System.out.println("-----REGISTING HELP REQUESTS FROM MIGRANT----");
		MigranteHandler mh = MigrantMatcher.getInstance().getMigranteHandler();
		System.out.println("Would you like to register solo or with a familiy?");
		
		if(r.nextBoolean()) { //Solo
			
			System.out.println("Please give state your name and phone number");
			mh.registarMigrante("Miguel",999888777);
			
		}
		else { //Family
			int familyNumber = 3;
			String [] familyNames = {"Maria" ,"João"}; 
			
			System.out.println("Please give state your name, phone number and the number of additional family members");
			
			mh.registarFamily(familyNumber,"Miguel",999888777);  
			
			for (int i = 0; i < familyNumber - 1; i++) { 
				System.out.println("Please add the names of the members one by one");
				mh.registerMember(familyNames[i]);
			}	
		}
		
		List<RegionDTO> regions = mh.pedirListaRegioes();
		System.out.println("Available reigions: \n" + regions);
		System.out.println("Please choose an available region.");
		int choice = r.nextInt(regions.size());
		
		List<AjudasDTO> helpList = mh.escolherRegiao(regions.get(choice)); //Ter em conta testes em que existem ajudas
		
		//Extensão 5a (Usa Observer)
		if(helpList.isEmpty()) {
			System.out.println("Oops! Currently there is no registered help offers in this region. Would you like to be notified on updates in the region?");
			mh.notifyMe(regions.get(choice));
		}
		
		//UC2 cont
		else {
			System.out.println("Available help offers: \n" + helpList);
			
			//Takes a random number of elements from the help list
			int numberOfHelps = r.nextInt(helpList.size());
			List<AjudasDTO> helpsChosen = new ArrayList<>();
			int helpIndex;
			
			for(int i = 0; i < numberOfHelps; i++) {
				 helpIndex = r.nextInt(helpList.size());
				 helpsChosen.add(helpList.get(helpIndex));
				 helpList.remove(helpIndex);
			}
			System.out.println("Pick the help offers you want to get");
			for (int i = 0; i < helpsChosen.size(); i++) {
				mh.escolherTipoDeAjuda(helpsChosen.get(i));
			}
			
			mh.confirmar();
			System.out.println("Your requests have been confirmed!");
		}
	
		
	}
}