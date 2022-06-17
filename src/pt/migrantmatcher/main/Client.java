package pt.migrantmatcher.main;

import pt.migrantmatcher.exceptions.NullMigrantException;
import pt.migrantmatcher.exceptions.VoluntaryNumberException;
import pt.migrantmatcher.exceptions.WrongCodeException;
import pt.migrantmatcher.exceptions.WrongRegionException;
import pt.migrantmatcher.facade.*;
import pt.migrantmatcher.facade.handlers.*;
import pt.migrantmatcher.facade.dto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Client{
	
	//Linear path with some random choices and multiple actions. Happy Path 
	
	public static void main(String [] args) {
		
		Random r  = new Random ();
		boolean rand = r.nextBoolean();
		
		try {
		//UC1
		System.out.println("\n-----REGISTING HELP FROM VOLUNTARIES----");
		
		AjudasHandler ah = MigrantMatcher.getInstance().getAjudasHandler();
		ah.verificarUtilizador(918376458);
		
		SMSDTO sms = null;

		if(rand) { // Alojamento voluntario 1
			
			List<RegionDTO> regions = ah.numPessoasAlojamento(5,"09/09/2021");
			
			System.out.println("Available reigions: \n");
			for (RegionDTO regionDTO : regions) {
				System.out.println(regionDTO.getName());
			}
			
			
			int choice = r.nextInt(regions.size());//Simula escolha de regiao
			sms = ah.regiaoPaisAlojamento(regions.get(choice)); 
			ah.inserirCodigoUnico(sms.getCode());
			
			regions = ah.numPessoasAlojamento(4,"09/09/2022");
			choice = r.nextInt(regions.size());
			sms = ah.regiaoPaisAlojamento(regions.get(choice)); 
			ah.inserirCodigoUnico(sms.getCode());
			
			
			regions = ah.numPessoasAlojamento(7,"01/09/2021");
			choice = r.nextInt(regions.size());
			sms = ah.regiaoPaisAlojamento(regions.get(choice)); 
			ah.inserirCodigoUnico(sms.getCode());
			
			regions = ah.numPessoasAlojamento(7,"01/09/2022");
			choice = r.nextInt(regions.size());
			sms = ah.regiaoPaisAlojamento(regions.get(choice)); 
			ah.inserirCodigoUnico(sms.getCode());
			
			regions = ah.numPessoasAlojamento(3,"01/01/2022");
			choice = r.nextInt(regions.size());
			sms = ah.regiaoPaisAlojamento(regions.get(choice)); 
			ah.inserirCodigoUnico(sms.getCode());
			
		}
		else { // Item voluntario 1
			
			sms = ah.descricaoItem("Batata frita: da boa","09/09/2021");
			ah.inserirCodigoUnico(sms.getCode());
			
			sms = ah.descricaoItem("Excalibur","09/09/1099");
			ah.inserirCodigoUnico(sms.getCode());
			
			sms = ah.descricaoItem("Dark Matter","09/09/9999");
			ah.inserirCodigoUnico(sms.getCode());
			
			sms = ah.descricaoItem("Pizza Slice","09/09/1999");
			ah.inserirCodigoUnico(sms.getCode());
			
			sms = ah.descricaoItem("Hello World program","09/01/2001");
			ah.inserirCodigoUnico(sms.getCode());
			
		}
		
		
		ah.verificarUtilizador(987654321);
		
		if(rand) { //Item voluntario 2
			
			sms = ah.descricaoItem("Carro velho","08/08/1888");
			ah.inserirCodigoUnico(sms.getCode());
			
			sms = ah.descricaoItem("Cama","09/09/2009");
			ah.inserirCodigoUnico(sms.getCode());
			
		}else {
			
			List<RegionDTO> regions = ah.numPessoasAlojamento(5,"09/09/2021");
			int choice = r.nextInt(regions.size());//Simula escolha de regiao
			sms = ah.regiaoPaisAlojamento(regions.get(choice)); 
			ah.inserirCodigoUnico(sms.getCode());
			
			regions = ah.numPessoasAlojamento(4,"09/09/2022");
			choice = r.nextInt(regions.size());
			sms = ah.regiaoPaisAlojamento(regions.get(choice)); 
			ah.inserirCodigoUnico(sms.getCode());
			
		}
		
		} catch (VoluntaryNumberException e) {
			System.out.println(e.getMessage());
		} //Exception number not recognized
			catch (WrongCodeException e) {
			System.out.println(e.getMessage());
		}
		
		
		//UC2
		try {
		System.out.println("\n---------REGISTING HELP REQUESTS FROM MIGRANT-------------");
		MigranteHandler mh = MigrantMatcher.getInstance().getMigranteHandler();
		
		if(r.nextBoolean()) { //Solo
			
			
			mh.registarMigrante("Miguel",999888777);
			
		}
		else { //Family
			int familyNumber = 3;
			String [] familyNames = {"Maria" ,"João"}; 
			
			mh.registarFamily(familyNumber,"Miguel",999888777);  
			
			for (int i = 0; i < familyNumber - 1; i++) { 
				
				mh.registerMember(familyNames[i]);
			}	
		}
		
		List<RegionDTO> regions = mh.pedirListaRegioes();
		int choice = r.nextInt(regions.size());
		
		List<AjudasDTO> helpList = mh.escolherRegiao(regions.get(choice)); //Ter em conta testes em que existem ajudas
		
			
		//Takes a random number of elements from the help list
		int numberOfHelps = r.nextInt(helpList.size());
		List<AjudasDTO> helpsChosen = new ArrayList<>();
		int helpIndex;
			
		for(int i = -1; i < numberOfHelps; i++) { // forces at least one choice
				 helpIndex = r.nextInt(helpList.size());
				 helpsChosen.add(helpList.get(helpIndex));
				 helpList.remove(helpIndex);
			}
		
		for (int i = 0; i < helpsChosen.size(); i++) {
			mh.escolherTipoDeAjuda(helpsChosen.get(i));
		}
			
		mh.confirmar();
		
		}catch (NullMigrantException e) {
			e.getMessage();
		} catch (WrongRegionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
}