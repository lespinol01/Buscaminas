package Control;

import model.Coordenada;
import model.Tablero;

public class MarcadorController {
	Tablero tablero;

	public boolean marcarCasilla(int posX, int posY) {
		return tablero.marcarCasilla(new Coordenada(posX, posY));
	}
}
