package Modelo;

public class Tablero {
	private Casillas[][] casillas;
	private Dificultad dificultad;
	
	public Tablero(Casillas[][] casillas, Dificultad dificultad) {
		super();
		this.casillas = casillas;
		this.dificultad = dificultad;
	}

	public Casillas[][] getCasillas() {
		return casillas;
	}

	public Dificultad getDificultad() {
		return dificultad;
	}

	public void setDificultad(Dificultad dificultad) {
		this.dificultad = dificultad;
	}
	
	
	
	
	
	
}
