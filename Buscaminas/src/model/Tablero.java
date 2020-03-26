package model;

import utiles.Utiles;

public class Tablero {

	private Casilla[][] casillas;

	public Tablero(int lado, int numeroBombas) {
		super();
		crearTablero(lado);
		colocarMinas(lado, numeroBombas);
	}

	private void establecerMinasAlrededor(Coordenada posicionMinaCoordenada) {
		int posX = posicionMinaCoordenada.getPosX();
		int posY = posicionMinaCoordenada.getPosY();
		int lado = casillas.length;

		for (int i = posX - 1; i <= posX + 1; i++) {
			for (int j = posY - 1; j <= posY + 1; j++) {
				Coordenada casillasAlrededor = new Coordenada(i, j);
				if (!casillasAlrededor.equals(posicionMinaCoordenada) && isDentroLimites(casillasAlrededor,lado)) {
					Casilla casillaAlrededorSeleccionada = getCasilla(casillasAlrededor);
					if (!casillaAlrededorSeleccionada.isMina()) {
						casillaAlrededorSeleccionada.setMinasAlrededor(casillaAlrededorSeleccionada.getMinasAlrededor() + 1);
					}
				}
			}
		}
	}
	
	private boolean isDentroLimites(Coordenada alrededor, int lado) {
		return alrededor.getPosX() >= 0 && alrededor.getPosX() < lado && alrededor.getPosY() >= 0
				&& alrededor.getPosY() < lado;
	}

	private void colocarMinas(int lado, int numeroBombas) {
		int tamano = 2;
		int posiciones[][] = new int[numeroBombas][tamano];
		damePosicionesAleatorias(lado, posiciones);

		for (int i = 0; i < posiciones.length; i++) {
			for (int j = 0; j < posiciones[i].length - 1; j++) {
				Coordenada coordenada = new Coordenada(posiciones[i][j], posiciones[i][j + 1]);

				if (getCasilla(coordenada).isMina()) {
					do {
						dameOtraPosicionAleatoria(lado, posiciones, i);
						coordenada = new Coordenada(posiciones[i][j], posiciones[i][j + 1]);
					} while (getCasilla(coordenada).isMina());
				}
				getCasilla(coordenada).setMina(true);
				establecerMinasAlrededor(coordenada);
			}
		}
	}

	private void dameOtraPosicionAleatoria(int lado, int[][] posiciones, int posicion) {
		int longitud = 2;
		for (int i = 0; i < longitud; i++) {
			posiciones[posicion][i] = Utiles.dameNumero(lado);
		}
	}

	private void damePosicionesAleatorias(int lado, int[][] posiciones) {
		for (int i = 0; i < posiciones.length; i++) {
			for (int j = 0; j < posiciones[i].length; j++) {
				posiciones[i][j] = Utiles.dameNumero(lado);
			}
		}
	}

	private void crearTablero(int lado) {
		this.casillas = new Casilla[lado][lado];
		// ahora hay que crear los objetos casilla
	}

	// TODO antes todo esto era private
	public Casilla getCasilla(Coordenada posicion) {
		return casillas[posicion.getPosX()][posicion.getPosY()];
	}

	private void setMina(Coordenada posicion, boolean bandera) {
		getCasilla(posicion).setMina(bandera);
	}

	private boolean isMina(Coordenada posicion) {
		return getCasilla(posicion).isMina();
	}

	public boolean desvelarCasilla(Coordenada coordenada) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean desmarcarCasilla(Coordenada posicion) {
		if (getCasilla(posicion).isVelada() && getCasilla(posicion).isMarcada()) {
			return getCasilla(posicion).isMarcada();
		}
		return false;
	}

	public boolean marcarCasilla(Coordenada posicion) {
		if (getCasilla(posicion).isVelada() && !getCasilla(posicion).isMarcada()) {
			return !getCasilla(posicion).isMarcada();
		}
		return false;
	}

}
