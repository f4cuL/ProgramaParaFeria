import Controlador.Controlador;
import Modelo.Modelo;
import Vista.menuPrincipal;
import Vista.proovedores;

public class main {

	public static void main(String[] args) {
		//Declaración controlador modelo
		Controlador Controlador = new Controlador();
		Modelo Modelo = new Modelo();
		Modelo.setControlador(Controlador);
		
		//Declaración vistas
		menuPrincipal menuPrincipal = new menuPrincipal();
		
		//Set a controlador
		Controlador.setModelo(Modelo);
		Controlador.setMenuPrincipal(menuPrincipal);
		
		//Seteo controlador a vistas
		menuPrincipal.setControlador(Controlador);

	}

}
