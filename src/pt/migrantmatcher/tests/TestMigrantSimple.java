package pt.migrantmatcher.tests;

import static org.junit.jupiter.api.Assertions.*;

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

class TestMigrantSimple {

	@Test
	void testNoMigrant() {
		System.out.println("\n--------- NO CURRENT MIGRANT REGISTERED TEST------------");

		
		MigranteHandler mh = MigrantMatcher.getInstance().getMigranteHandler();
		
		assertThrows(NullMigrantException.class,() ->{
			mh.registerMember(null);;
		});	
		
		assertThrows(NullMigrantException.class,() ->{
			mh.escolherRegiao(null);
		});	
		
		assertThrows(NullMigrantException.class,() ->{
			mh.confirmar();
		});	
		
		assertThrows(NullMigrantException.class,() ->{
			mh.notifyMe(null);
		});	

		//Normal runtime ----------------------------------
				try {
					mh.confirmar();
				}catch (NullMigrantException e) {
					System.out.println(e.getMessage());
					
				}
				
				
	}
	
	@Test
	void testInvalidInput() {
		
		System.out.println("\n--------- INVALID HELP AND REGION TEST------------");
		
		
		MigranteHandler mh = MigrantMatcher.getInstance().getMigranteHandler();
		mh.registarMigrante("Teste", 8318318);
		
		assertThrows(WrongRegionException.class,() ->{
			mh.escolherRegiao(null);
		});
		//Normal runtime ----------------------------------
		try {
			List<AjudasDTO> a = mh.escolherRegiao(null);
			
		} catch (NullMigrantException | WrongRegionException e) {
			System.out.println(e.getMessage());
			
			
		}
		
		try {
			//No available Helps in correct region print test
			List<AjudasDTO> a = mh.escolherRegiao(new RegionDTO("Lisboa"));
			assertNull(a);
			
			//Couldnt add help request (wrong input/help doesnt exist)
			//Check print
			mh.escolherTipoDeAjuda(null);
			
		} catch (NullMigrantException | WrongRegionException e) {
			System.out.println(e.getMessage());
		}

	}
	

	@Test
	void testFamily() {
		
		System.out.println("\n--------- ADD FAMILY LIMIT TEST------------");

		MigranteHandler mh = MigrantMatcher.getInstance().getMigranteHandler();
		
		mh.registarMigrante("Teste", 8318318);
		assertThrows(NullMigrantException.class,() ->{
			mh.registerMember("TESTE 1 SHOULD NOT BE OK. DIDNT REGISTER AS FAMILY");
		});
		//Normal runtime ----------------------------------
		try {
			mh.registerMember("TESTE 1 SHOULD NOT BE OK. DIDNT REGISTER AS FAMILY");
		} catch (NullMigrantException e1) {
			System.out.println(e1.getMessage());
		}
		
		try {
			
			
			mh.registarFamily(1,"Teste", 8318318);
			mh.registerMember("TESTE 2 SHOULD BE OK SINCE FAMILY HAS 1 SLOT");
			mh.registerMember("TESTE 3 SHOULD NOT BE OK. NO MORE SLOTS (CHECK PRINT)");
				
		} catch (NullMigrantException e) {
			e.printStackTrace();
		}
		
		assertThrows(NullMigrantException.class,() ->{
			mh.registarFamily(0,"Teste", 8318318);
		});
		//Normal runtime ----------------------------------
			try {
				mh.registarFamily(0,"Teste", 8318318);
			} catch (NullMigrantException e) {
				System.out.println(e.getMessage());
			}
		
		assertThrows(NullMigrantException.class,() ->{
			mh.registarFamily(-1,"Teste", 8318318);
		});
		//Normal runtime ----------------------------------
			try {
				mh.registarFamily(-1,"Teste", 8318318);
			} catch (NullMigrantException e) {
				System.out.println(e.getMessage());
			}

	}
	@Test
	void testNotification() { //OBSERVER TEST (check prints)
		
		System.out.println("\n--------- REGION NOTIFICATION TEST------------");

		MigranteHandler mh = MigrantMatcher.getInstance().getMigranteHandler();
		
		try {
			mh.registarMigrante("João dos testes", 8318318);
			List<RegionDTO> l = mh.pedirListaRegioes();
			mh.escolherRegiao(l.get(0)); //Lisboa
			mh.notifyMe(l.get(0));       //Pedi notification para Lisboa
			
			mh.registarMigrante("Maria", 8318318);
			mh.escolherRegiao(l.get(1)); //Norte
			mh.notifyMe(l.get(1));       //Pedi notification para Norte
			
		} catch (NullMigrantException | WrongRegionException e) {
			System.out.println(e.getMessage());
		}
		
		//VOLUNTARY THAT UPDATES THE REGION
		System.out.println("");
		AjudasHandler ah = MigrantMatcher.getInstance().getAjudasHandler();
				
		int num = 1;
		String desc = "Carro velho";
		String date = "09/06/1999";
				
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
	}
	
}















