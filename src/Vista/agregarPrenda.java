package Vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import Controlador.Controlador;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class agregarPrenda extends JInternalFrame {
	private JTextField inputNombre;
	private JTextField inputPrecio;
	private Controlador Controlador;


	public JTextField getTextField() {
		return inputNombre;
	}

	public void setTextField(JTextField textField) {
		this.inputNombre = textField;
	}

	public JTextField getTextField_1() {
		return inputPrecio;
	}

	public void setTextField_1(JTextField textField_1) {
		this.inputPrecio = textField_1;
	}

	public Controlador getControlador() {
		return Controlador;
	}

	public void setControlador(Controlador controlador) {
		Controlador = controlador;
	}

	/**
	 * Create the frame.
	 */
	public agregarPrenda() {
		setBounds(100, 100, 337, 179);
		
		JLabel lblNewLabel = new JLabel("Nombre de prenda");
		setTitle("Agregar prenda");
		inputNombre = new JTextField();
		inputNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Precio");
		
		inputPrecio = new JTextField();
		inputPrecio.setColumns(10);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inputPrecio.getText().isEmpty() || inputNombre.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "NO PUEDE ESTAR VACIO EL NOMBRE O EL PRECIO", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						if(Integer.parseInt(inputPrecio.getText()) < 0)
						{
							JOptionPane.showMessageDialog(null, "NO PUEDE SER MENOR A 0 EL PRECIO", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}else{
							try {
								int precio=Integer.parseInt(inputPrecio.getText());
								int id=Controlador.getModelo().tomarIDporCodigo(Controlador.getModelo().tomarCodigoTabla(Controlador.getProovedores().getTable()));
								if (Controlador.getModelo().agregarPrenda(inputNombre.getText(),precio,id)) {
									Controlador.getModelo().limpiarTabla(Controlador.getMostrarProovedorPrendas().getTablaPrendas());
									Controlador.getModelo().listarPrendasPorNombreID(Controlador.getMostrarProovedorPrendas().getTablaPrendas(),Controlador.getModelo().tomarNombreTabla(Controlador.getProovedores().getTable()));
									dispose();
								};
							} catch (Exception e2) {
								// TODO: handle exception
								if (!inputPrecio.getText().isEmpty())
								{
								JOptionPane.showMessageDialog(null, "NO PUEDE CONTENER LETRAS EL PRECIO", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								}
							}
							
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "NO PUEDE CONTENER LETRAS EL PRECIO", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(inputPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton))
						.addComponent(inputNombre, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(inputNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(inputPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(60))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnNewButton)
							.addGap(39))))
		);
		getContentPane().setLayout(groupLayout);
		setClosable(true);

	}
	public void dispose() {
		Controlador.getMostrarProovedorPrendas().getBtnNewButton().setEnabled(true);
		Controlador.getMostrarProovedorPrendas().setClosable(true);
		super.dispose();
	}
}
