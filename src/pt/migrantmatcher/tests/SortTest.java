package pt.migrantmatcher.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

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

class SortTest {

	@Test
	void testSort() { 
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream(new File("defaults.properties")));
			String strat =  prop.getProperty("sortstrategy");
			
			if(strat.equals("pt.migrantmatcher.strategies.SortByDate")) {
				//TEST SORT BY DATE (change in defaults.properties)
				System.out.println("------------SORT BY DATE TEST------------------");
				int numDeAjudas = 6;
				AjudasHandler ah = MigrantMatcher.getInstance().getAjudasHandler();
				try {
					ah.verificarUtilizador(987654321);
					SMSDTO sms = ah.descricaoItem("data 1", "02/02/2002");
					ah.inserirCodigoUnico(sms.getCode());
					sms = ah.descricaoItem("data 2", "02/02/1999");
					ah.inserirCodigoUnico(sms.getCode());
					sms = ah.descricaoItem("data 3", "22/02/2022");
					ah.inserirCodigoUnico(sms.getCode());
					sms = ah.descricaoItem("data 4", "02/02/2021");
					ah.inserirCodigoUnico(sms.getCode());
					sms = ah.descricaoItem("data 5", "22/02/2021");
					ah.inserirCodigoUnico(sms.getCode());
					sms = ah.descricaoItem("data 6", "09/09/1999");
					ah.inserirCodigoUnico(sms.getCode());
				} catch (VoluntaryNumberException | WrongCodeException e) {
					System.out.println(e.getMessage());;
				}
				
				MigranteHandler mh = MigrantMatcher.getInstance().getMigranteHandler();
				
				try {
					mh.registarMigrante("Schrodinger's cat", 8318318);
					List<RegionDTO> l = mh.pedirListaRegioes();
					List<AjudasDTO> al = mh.escolherRegiao(l.get(0)); 
					assertEquals(numDeAjudas,al.size());
					
					assertEquals(al.get(0).getDate(),"02/02/1999" );
					assertEquals(al.get(1).getDate(),"09/09/1999" );
					assertEquals(al.get(2).getDate(),"02/02/2002" );
					assertEquals(al.get(3).getDate(),"02/02/2021" );
					assertEquals(al.get(4).getDate(),"22/02/2021" );
					assertEquals(al.get(5).getDate(),"22/02/2022" );
					
					
				} catch (NullMigrantException | WrongRegionException e) {
					System.out.println(e.getMessage());
				}
			}
			else if(strat.equals("pt.migrantmatcher.strategies.SortByType")){
				
				System.out.println("------------SORT BY TYPE TEST-----------------");
				int numDeAjudas = 6;
				AjudasHandler ah = MigrantMatcher.getInstance().getAjudasHandler();
				try {
					ah.verificarUtilizador(987654321);
					
					SMSDTO sms = ah.descricaoItem("Item 1", "02/02/2002");
					ah.inserirCodigoUnico(sms.getCode());
					
					List<RegionDTO> r = ah.numPessoasAlojamento(1, "02/02/2021");
					sms = ah.regiaoPaisAlojamento(r.get(0));
					ah.inserirCodigoUnico(sms.getCode());
					
					sms = ah.descricaoItem("Item 2", "02/02/1999");
					ah.inserirCodigoUnico(sms.getCode());
					
					sms = ah.descricaoItem("Item 3", "22/02/2022");
					ah.inserirCodigoUnico(sms.getCode());
					
					r = ah.numPessoasAlojamento(2, "09/09/1999");
					sms = ah.regiaoPaisAlojamento(r.get(0));
					ah.inserirCodigoUnico(sms.getCode());
					
					r = ah.numPessoasAlojamento(3, "22/02/2021");
					sms = ah.regiaoPaisAlojamento(r.get(0));
					ah.inserirCodigoUnico(sms.getCode());
					
					
				} catch (VoluntaryNumberException | WrongCodeException e) {
					System.out.println(e.getMessage());;
				}
				
				MigranteHandler mh = MigrantMatcher.getInstance().getMigranteHandler();
				
				try {
					mh.registarMigrante("Schrodinger's cat", 8318318);
					List<RegionDTO> l = mh.pedirListaRegioes();
					List<AjudasDTO> al = mh.escolherRegiao(l.get(0)); 
					assertEquals(numDeAjudas,al.size());
					
					
					assertEquals(al.get(0).getType(),"Alojamento" );
					assertEquals(al.get(1).getType(),"Alojamento" );
					assertEquals(al.get(2).getType(),"Alojamento" );
					assertEquals(al.get(3).getType(),"Doação" );
					assertEquals(al.get(4).getType(),"Doação" );
					assertEquals(al.get(5).getType(),"Doação" );
					
					
				} catch (NullMigrantException | WrongRegionException e) {
					System.out.println(e.getMessage());
				}
				
			}
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
