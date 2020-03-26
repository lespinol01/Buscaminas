package Control;

import model.Densidad;
import model.Dificultad;
import model.Tablero;
import utiles.Utiles;

public class ConfiguradorController {

	public Tablero cambiarCondiciones(Densidad densidad,Dificultad dificultad) {
		return new Tablero(dificultad.getLado(), Utiles.calculaMinas(dificultad.getLado(), densidad.getPorcentaje()));
	}
}
