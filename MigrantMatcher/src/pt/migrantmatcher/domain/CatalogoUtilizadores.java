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
	}
	
	/**
	 * Adds Migrant object to catalogs
	 * @param nome migrant's name
	 * @param telemovel telephone number
	 */
	public void addMigrante(String nome, int telemovel) {
		users.add(new Utilizador(telemovel));
		migrantes.add(new Migrante(nome, telemovel));
	}
	
	/**
	 * Adds Voluntario object to catalogs
	 * @param nome migrant's name
	 * @param telemovel telephone number
	 */
	public void addVoluntario(int codigo, int telemovel) {
		users.add(new Utilizador(telemovel));
		voluntarios.add(new Voluntario(codigo, telemovel));
	}
	
	
	public Migrante getMigrante(int telemovel) {
		for (Migrante m : migrantes) {
			
		}
		return null;
	}
}
