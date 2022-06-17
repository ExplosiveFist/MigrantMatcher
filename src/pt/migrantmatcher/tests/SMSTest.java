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

class SMSTest {

	@Test
	void testSMSFinal() {
		//Checking if voluntarios receive sms from smsprovider upon migrant accepting help
		//Also checks if every help requested is saved!
		System.out.println("\n--------- SMS TEST------------");
		AjudasHandler ah = MigrantMatcher.getInstance().getAjudasHandler();
		int num = 1;
		String desc = "Dor de cabeça";
		String date = "09/06/1999";
		
		try {
			//VOLUNTARIO 1
			ah.verificarUtilizador(987654321);
			List<RegionDTO> reg = ah.numPessoasAlojamento(num,date);
			SMSDTO sms = ah.regiaoPaisAlojamento(reg.get(0)); //Lisboa
			ah.inserirCodigoUnico(sms.getCode());
			assertNotNull(sms);
			sms = ah.descricaoItem(desc, date);
			ah.inserirCodigoUnico(sms.getCode());
			assertNotNull(sms);
			
			//VOLUNTARIO 2
			ah.verificarUtilizador(999000999);
			ah.numPessoasAlojamento(num,date);
			SMSDTO sms1 = ah.regiaoPaisAlojamento(reg.get(0)); //Lisboa
			assertNotNull(sms1); 
			ah.inserirCodigoUnico(sms1.getCode());
			sms1 = ah.descricaoItem(desc, date);
			assertNotNull(sms1);
			ah.inserirCodigoUnico(sms1.getCode());
			
			//As ajudas escolhidas entre os voluntarios sao bastantes semelhantes
			//mas são diferenciadas pelo codigo gerado
		
		} catch (VoluntaryNumberException | WrongCodeException e) {
			System.out.println(e.getMessage());;
		}
		
		System.out.println("");
		MigranteHandler mh = MigrantMatcher.getInstance().getMigranteHandler();
		
		try {
			mh.registarMigrante("Cthulhu", 8318318);
			List<RegionDTO> l = mh.pedirListaRegioes();
			List<AjudasDTO> a = mh.escolherRegiao(l.get(0)); //Lisboa
			assertNotNull(a);
			
			for (AjudasDTO ajuda : a) {
				mh.escolherTipoDeAjuda(ajuda);
			}
			
			mh.confirmar(); //VER SMS NA CONSOLA. THERE SHOULD BE 4 SMS MSGS
			
		} catch (NullMigrantException | WrongRegionException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	

}
