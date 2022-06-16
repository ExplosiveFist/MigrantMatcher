package pt.migrantmatcher.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;

import pt.migrantmatcher.exceptions.NullMigrantException;
import pt.migrantmatcher.exceptions.VoluntaryNumberException;
import pt.migrantmatcher.exceptions.WrongCodeException;
import pt.migrantmatcher.exceptions.WrongRegionException;
import pt.migrantmatcher.facade.MigrantMatcher;
import pt.migrantmatcher.facade.dto.AjudasDTO;
import pt.migrantmatcher.facade.dto.RegionDTO;
import pt.migrantmatcher.facade.dto.SMSDTO;
import pt.migrantmatcher.facade.handlers.AjudasHandler;
import pt.migrantmatcher.facade.handlers.MigranteHandler;

class TestVoluntarySimple {

	@Test
	void testInvalidNumber() {
		//Recognized numbers (918376458);(987654321);(999000999);
		
			//UC1
		System.out.println("\n---------UNRECOGNIZED VOLUNTARY TEST------------");
		AjudasHandler ah = MigrantMatcher.getInstance().getAjudasHandler();
			
			
		assertThrows(VoluntaryNumberException.class,() ->{
					ah.verificarUtilizador(918376459);
				});	
		//Normal runtime ----------------------------------
		try {
			ah.verificarUtilizador(918376459);
		} catch (VoluntaryNumberException e) {
			System.out.println(e.getMessage());;
		}
		//Normal runtime ---------------------------------
		
		
	}
	
	@Test
	void testNoVoluntary(){
		//Recognized numbers (918376458);(987654321);(999000999);
		//UC1
		System.out.println("\n----------NO CURRENT VOLUNTARY TEST------------");
		AjudasHandler ah = MigrantMatcher.getInstance().getAjudasHandler();

		assertThrows(VoluntaryNumberException.class,() ->{
			ah.numPessoasAlojamento(918376459,"aaaa");
		});	
		RegionDTO reg = new RegionDTO("test");
		assertThrows(VoluntaryNumberException.class,() ->{
			ah.regiaoPaisAlojamento(reg);
		});	
		assertThrows(VoluntaryNumberException.class,() ->{
			ah.descricaoItem("a","test");
		});
		assertThrows(VoluntaryNumberException.class,() ->{
			ah.inserirCodigoUnico("test");
		});
		//Normal runtime ----------------------------------
		try {
			ah.descricaoItem("a","test");
		} catch (VoluntaryNumberException e) {
			System.out.println(e.getMessage());;
		}
		//Normal runtime ---------------------------------
	}
	
	@Test
	void testsInvalidCode() {
		//Recognized numbers (918376458);(987654321);(999000999);
		
			//UC1
		System.out.println("\n---------INVALID CODE/SMS TEST------------");
		AjudasHandler ah = MigrantMatcher.getInstance().getAjudasHandler();
	
		try {
			ah.verificarUtilizador(918376458);
			//Wrong date format should return the sms as null with no help created
			SMSDTO sms = ah.descricaoItem("test", "wrong format date"); 
			assertNull(sms);
			assertThrows(WrongCodeException.class,() ->{
				ah.inserirCodigoUnico("test");
			});
			System.out.println("...........Testing wrong input...........");
			SMSDTO sms2 = ah.descricaoItem("test", "09/06/1999");
			assertNotNull(sms2);
			System.out.println("Correct code is " + sms2.getCode());
			assertThrows(WrongCodeException.class,() ->{
				ah.inserirCodigoUnico("test");
			});
			//Normal runtime ---------------------------------
				ah.inserirCodigoUnico("test");
			//Normal runtime ---------------------------------
				
		} catch (VoluntaryNumberException e) {
			System.out.println(e.getMessage());;
		} catch (WrongCodeException e) {
			System.out.println(e.getMessage());;
		}

	}
	

	@Test
	void testInvalidRegionHelp() {
		//Recognized numbers (918376458);(987654321);(999000999);
		
			//UC1
		System.out.println("\n---------INVALID REGION AND NULL HELP TEST------------");
		AjudasHandler ah = MigrantMatcher.getInstance().getAjudasHandler();
	
		try {
			ah.verificarUtilizador(987654321);
			//Nenhum Alojamento/Descrição foi registado para dar set da regiao
			SMSDTO sms = ah.regiaoPaisAlojamento(null);
			assertNull(sms);
			ah.descricaoItem("aaaaa", "09/06/1999");
			sms = ah.regiaoPaisAlojamento(null);
			assertNull(sms);
			sms = ah.regiaoPaisAlojamento(new RegionDTO("tokyo"));
			assertNull(sms);

		} catch (VoluntaryNumberException e) {
			System.out.println(e.getMessage());;
		}
	
	}
	
	@Test
	void testRegions() {
		//Recognized numbers (918376458);(987654321);(999000999);
		
			//UC1
		System.out.println("\n---------REGIONS TEST WITH HAPPY PATH------------");
		AjudasHandler ah = MigrantMatcher.getInstance().getAjudasHandler();

		try {
			ah.verificarUtilizador(987654321);
			List<RegionDTO> r = new ArrayList<RegionDTO>();
			r.add(new RegionDTO("Lisboa"));
			r.add(new RegionDTO("Norte"));
			r.add(new RegionDTO("Centro"));
			r.add(new RegionDTO("Algarve"));
			r.add(new RegionDTO("Alentejo"));
			r.add(new RegionDTO("Açores"));
			r.add(new RegionDTO("Madeira"));
			List<RegionDTO> reg = ah.numPessoasAlojamento(1,"09/06/1999");
			
			assertEquals(r.size(),reg.size());
			
			for (int i = 0; i < reg.size(); i++) {
				System.out.println(r.get(i).getName());
				assertEquals(r.get(i).getName(),reg.get(i).getName());
			}
			
			SMSDTO sms = ah.regiaoPaisAlojamento(r.get(0));
			ah.inserirCodigoUnico(sms.getCode());
			

		} catch (VoluntaryNumberException | WrongCodeException e) {
			System.out.println(e.getMessage());;
		}
	
	}
	@Test
	void testRegHelp() {
		//Checking if the helps were created and registered correctly
		System.out.println("\n--------- HELP REGISTRY TEST------------");
		AjudasHandler ah = MigrantMatcher.getInstance().getAjudasHandler();
		
		int num = 1;
		String desc = "Carro velho";
		String date = "09/06/1999";
		int numberOfHelps = 2;
		
		try {
			ah.verificarUtilizador(987654321);
			
			
			List<RegionDTO> reg = ah.numPessoasAlojamento(num,date);
			SMSDTO sms = ah.regiaoPaisAlojamento(reg.get(0));
			ah.inserirCodigoUnico(sms.getCode());
			sms = ah.descricaoItem(desc, date);
			ah.inserirCodigoUnico(sms.getCode());
			

		} catch (VoluntaryNumberException | WrongCodeException e) {
			System.out.println(e.getMessage());;
		}
		
		MigranteHandler mh = MigrantMatcher.getInstance().getMigranteHandler();
		
		mh.registarMigrante("Miguel",999888777);
		List<RegionDTO> regions = mh.pedirListaRegioes();
		try {
			List<AjudasDTO> ajudas = mh.escolherRegiao(regions.get(0));
			assertEquals(ajudas.size(),numberOfHelps);
			
			//First help registered check
			assertEquals(ajudas.get(0).getDate(), date);
			assertEquals(ajudas.get(0).getNum(), num);
			
			//Second help registered check
			assertEquals(ajudas.get(1).getDate(), date);
			assertEquals(ajudas.get(1).getDescDono(), desc);
			
		} catch (NullMigrantException | WrongRegionException e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
	

}
