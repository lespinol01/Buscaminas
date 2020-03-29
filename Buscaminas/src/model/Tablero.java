package model;

import utiles.Utiles;

public class Tablero {

	private Casilla[][] casillas;

	public Tablero(int lado, int numeroBombas) {
		super();
		crearTablero(lado);
		colocarMinas(lado, numeroBombas);
	}

	public Casilla[][] getCasillas() {
		return casillas;
	}

	private void establecerMinasAlrededor(Coordenada posicionMinaCoordenada) {
		int posX = posicionMinaCoordenada.getPosX();
		int posY = posicionMinaCoordenada.getPosY();
		int lado = casillas.length;

		for (int i = posX - 1; i <= posX + 1; i++) {
			for (int j = posY - 1; j <= posY + 1; j++) {
				Coordenada casillasAlrededor = new Coordenada(i, j);
				if (!casillasAlrededor.equals(posicionMinaCoordenada) && isDentroLimites(casillasAlrededor)) {
					Casilla casillaAlrededorSeleccionada = getCasilla(casillasAlrededor);
					if (!casillaAlrededorSeleccionada.isMina()) {
						casillaAlrededorSeleccionada
								.setMinasAlrededor(casillaAlrededorSeleccionada.getMinasAlrededor() + 1);
					}
				}
			}
		}
	}

	private boolean isDentroLimites(Coordenada alrededor) {
		return alrededor.getPosX() >= 0 && alrededor.getPosX() < casillas.length && alrededor.getPosY() >= 0
				&& alrededor.getPosY() < casillas.length;
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
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas.length; j++) {
				casillas[i][j] = new Casilla();
			}
		}
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
		boolean retorno = false;
		int posX = coordenada.getPosX();
		int posY = coordenada.getPosY();

		if (getCasilla(coordenada).isVelada() && !getCasilla(coordenada).isMarcada()) {
			getCasilla(coordenada).setVelada(false);

			for (int i = posX - 1; i <= posY + 1; i++) {
				for (int j = posY - 1; j <= posY + 1; j++) {
					Coordenada coordenadas = new Coordenada(i, j);
					if (getCasilla(coordenada).getMinasAlrededor() == 0 && isDentroLimites(coordenadas)
							&& !getCasilla(coordenada).isMina() && !coordenada.equals(coordenadas)) {
						desvelarCasilla(coordenadas);
						retorno = true;
					}
				}
			}

		}

		return retorno;
	}

	public boolean marcarCasilla(Coordenada posicion) {
		if (getCasilla(posicion).isVelada() && !getCasilla(posicion).isMarcada()) {
			getCasilla(posicion).setMarcada(!getCasilla(posicion).isMarcada());
		}
		return false;
	}

}
