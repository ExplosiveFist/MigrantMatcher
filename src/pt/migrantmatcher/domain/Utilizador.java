package pt.migrantmatcher.domain;


public class Utilizador {

	private int telemovel; 
	
	/**
	 * Class constructor for Utilizador class
	 * @param telemovel telephone number
	 */
	public Utilizador(int telemovel) {
		this.telemovel = telemovel;
	}
	
	/**
	 * Returns a user's telephone number
	 * @return - this.telemovel
	 */
	public int getTelephoneNumber() {
		return telemovel;
	}
	
	/**
	 * Verifies a user's telephone number
	 * @param telemovel telephone number
	 * @return - true if equals, false if not
	 */
	public boolean verificarUtilizador(int telemovel) {
		return this.telemovel == telemovel;
	}
	
}
