package Vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import Controlador.Controlador;

public class buscarPrendaNombre extends JInternalFrame {

	Controlador controlador;
	

	public Controlador getControlador() {
		return controlador;
	}


	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}


	/**
	 * Create the frame.
	 */
	public buscarPrendaNombre() {
		setBounds(100, 100, 450, 300);

	}

}
