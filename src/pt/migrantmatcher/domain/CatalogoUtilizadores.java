package pt.migrantmatcher.domain;

import java.util.List;
import java.util.ArrayList;


public class CatalogoUtilizadores {
	
    private List<Utilizador> users;
    private List<Voluntario> voluntarios;
    private List<Migrante> migrantes;

	/**
	 * Class constructor for Utilizadores catalog
	 */
	public CatalogoUtilizadores() {
		this.users = new ArrayList<Utilizador>();
		this.voluntarios = new ArrayList<Voluntario>();
		this.migrantes = new ArrayList<Migrante>();
		
		//Voluntarios com identificação
		addVoluntario(918376458);
		addVoluntario(987654321);
		addVoluntario(999000999);
		
	}
	
	/**
	 * Adds Migrant object to catalogs
	 * @param nome migrant's name
	 * @param telemovel telephone number
	 */
	public void addMigrante(String nome, int telemovel, int numFamiliares) {
		users.add(new Utilizador(telemovel));
		migrantes.add(new Migrante(nome, telemovel, numFamiliares));
	}
	
	/**
	 * Adds Voluntario object to catalogs
	 * @param nome migrant's name
	 * @param telemovel telephone number
	 */
	public void addVoluntario(int telemovel) {
		users.add(new Utilizador(telemovel));
		voluntarios.add(new Voluntario(telemovel));
	}
	
	
	public Migrante getMigrante(int telemovel) {
		for (Migrante m : migrantes) {
			if (telemovel == m.getTelephoneNumber()) {
				return m;
			}
		}
		return null;
	}
	
	public Voluntario getVoluntario(int telemovel) {
		for (Voluntario v : voluntarios) {
			if (telemovel == v.getTelephoneNumber()) {
				return v;
			}
		}
		return null;
	}
}
