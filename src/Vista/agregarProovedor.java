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
import javax.swing.border.BevelBorder;

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
		setBounds(100, 100, 255, 226);

		JButton btnAgregarProovedor = new JButton("Agregar");
		btnAgregarProovedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inputNombre.getText().isEmpty() || inptCodigo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "NO PUEDE ESTAR VACIO EL NOMBRE O EL CODIGO", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (controlador.getModelo().codigoRepetido(inptCodigo.getText()) || controlador.getModelo().nombreRepetido(inputNombre.getText()) ) {
						JOptionPane.showMessageDialog(null, "YA EXISTE UN PROOVEDOR CON ESE CODIGO O NOMBRE", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					} else {
						if (JOptionPane.showConfirmDialog(null,
								"¿Estás seguro que deseas agregar a " + inputNombre.getText() + "?",
								"AVISO: Estás agregando un nuevo proovedor", JOptionPane.YES_NO_OPTION,
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
		
		inputNombre = new JTextField();
		inputNombre.setColumns(10);
		setTitle("Nuevo proovedor");
		JLabel lblNewLabel = new JLabel("Nombre del proovedor");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		inptCodigo = new JTextField();
		inptCodigo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Codigo proovedor");
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JLabel lblNewLabel_1 = new JLabel("Agregando nuevo proovedor");
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Source Code Pro Light", Font.PLAIN, 11));
		lblNewLabel_1.setEnabled(false);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(inputNombre, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel_2))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(inptCodigo, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(btnAgregarProovedor)
					.addGap(31)
					.addComponent(btnCancelar))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(lblNewLabel)
					.addGap(6)
					.addComponent(inputNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(lblNewLabel_2)
					.addGap(6)
					.addComponent(inptCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAgregarProovedor)
						.addComponent(btnCancelar)))
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
