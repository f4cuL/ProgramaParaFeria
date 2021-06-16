package Vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controlador.Controlador;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class agregarProovedor extends JInternalFrame {
	private JTextField inputNombre;
	Controlador controlador;
	static int contador=0;
	private JTextField inptCodigo;
	
	public static int getContador() {
		return contador;
	}

	public static void setContador(int contador) {
		agregarProovedor.contador = contador;
	}

	public JTextField getTextField() {
		return inputNombre;
	}

	public void setTextField(JTextField textField) {
		this.inputNombre = textField;
	}

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	/**
	 * Create the frame.
	 */
	/**
	 * 
	 */
	public agregarProovedor() {
		getContentPane().setEnabled(false);
		setClosable(true);
		setBounds(100, 100, 255, 211);

		JButton btnAgregarProovedor = new JButton("Agregar");
		btnAgregarProovedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inputNombre.getText().isEmpty() || inptCodigo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "NO PUEDE ESTAR VACIO EL NOMBRE O EL CODIGO", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (controlador.getModelo().codigoRepetido(inptCodigo.getText())) {
						JOptionPane.showMessageDialog(null, "YA EXISTE UN PROOVEDOR CON ESE CODIGO", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					} else {
						if (JOptionPane.showConfirmDialog(null,
								"¿Estás seguro que deseas agregar a " + inputNombre.getText() + "?",
								"AVISO: Estás agrengado un nuevo proovedor", JOptionPane.YES_NO_OPTION,
								JOptionPane.WARNING_MESSAGE) == 0) {

							controlador.getModelo().agregarProovedor(inputNombre.getText(), inptCodigo.getText());
							controlador.getModelo().limpiarTabla(controlador.getProovedores().getTable());
							controlador.getModelo().listarProovedores(controlador.getProovedores().getTable());
							inputNombre.setText(null);
							dispose();

						}
					}
				}
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Agregando nuevo proovedor");
		lblNewLabel_1.setFont(new Font("Source Code Pro Light", Font.PLAIN, 11));
		lblNewLabel_1.setEnabled(false);
		
		inputNombre = new JTextField();
		inputNombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre del proovedor");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		inptCodigo = new JTextField();
		inptCodigo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Codigo proovedor");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(inptCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(10)
										.addComponent(inputNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addComponent(lblNewLabel))
								.addContainerGap(122, Short.MAX_VALUE))
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel_2)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnAgregarProovedor)
										.addPreferredGap(ComponentPlacement.UNRELATED)))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnCancelar)
								.addGap(183)))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addComponent(lblNewLabel_1)
					.addContainerGap(166, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addGap(11)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(inputNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addGap(5)
					.addComponent(inptCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAgregarProovedor)
						.addComponent(btnCancelar))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
		contador++;

	}
	public JTextField getInputNombre() {
		return inputNombre;
	}

	public void setInputNombre(JTextField inputNombre) {
		this.inputNombre = inputNombre;
	}

	public JTextField getInptCodigo() {
		return inptCodigo;
	}

	public void setInptCodigo(JTextField inptCodigo) {
		this.inptCodigo = inptCodigo;
	}

	public void dispose() {
    	contador=0;
    	controlador.getProovedores().btnAgregarProovedor.setEnabled(true);
        super.dispose();
    }
}
