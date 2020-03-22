package Modelo;

public class Casillas {
	private Coordenadas coordenadas;
	public boolean casillaMarcada;
	public boolean casillaVelada;
	
	public Casillas(Coordenadas coordenadas, boolean casillaMarcada, boolean casillaVelada) {
		super();
		this.coordenadas = coordenadas;
		this.casillaMarcada = casillaMarcada;
		this.casillaVelada = casillaVelada;
	}

	public Coordenadas getCoordenadas() {
		return coordenadas;
	}

	public boolean isCasillaMarcada() {
		return casillaMarcada;
	}

	public boolean isCasillaVelada() {
		return casillaVelada;
	}

	public void setCasillaMarcada(boolean casillaMarcada) {
		this.casillaMarcada = casillaMarcada;
	}

	public void setCasillaVelada(boolean casillaVelada) {
		this.casillaVelada = casillaVelada;
	}
	
	
}
