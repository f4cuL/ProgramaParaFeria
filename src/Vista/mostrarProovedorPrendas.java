package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JInternalFrame;

import Controlador.Controlador;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mostrarProovedorPrendas extends JInternalFrame {
	Controlador controlador;
	private JTable tablaPrendas;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public mostrarProovedorPrendas(Controlador controlador) {
		setBounds(100, 100, 778, 517);
		setControlador(controlador);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		JLabel lblNewLabel = new JLabel("<html>" +"Visualizando al proovedor <B>"+ controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()) + "</B> con el CODIGO  <B>" + controlador.getModelo().tomarCodigoTabla(controlador.getProovedores().getTable()) + "</B></html>") ;
		lblNewLabel.setFont(new Font("MS Sans Serif", Font.PLAIN, 20));
		
		JButton btnNewButton = new JButton("Agregar prenda");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarPrenda aP = new agregarPrenda();
				aP.setVisible(true);
				controlador.getModelo().centrarJIP(aP);
				controlador.setAgregarPrenda(aP);
				aP.setControlador(controlador);
				controlador.getMenuPrincipal().getDesktopPane().add(aP,0);
				
			}
		});
		setTitle("Proovedor "+controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()));
		JButton btnNewButton_1 = new JButton("Cambiar estado de pago");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tablaPrendas.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "DEBES SELECCIONAR UNA PRENDA PRIMERO",
			                "ERROR", JOptionPane.ERROR_MESSAGE);				
				}
				else {
				controlador.getModelo().setPagoTrueFalse(Integer.parseInt(controlador.getModelo().tomarIdTabla(tablaPrendas)));
				controlador.getModelo().limpiarTabla(tablaPrendas);
				controlador.getModelo().listarPrendasPorNombre(tablaPrendas,controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()));			
				}
			}
		});
		
		JButton btnNewButton_2 = new JButton("Borrar proovedor");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setForeground(Color.RED);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			int opcion=JOptionPane.showConfirmDialog(null,"¿Estás seguro?, esta acción no se puede revertir, todas las prendas asociadas a este proovedor seran borradas.", "CUIDADO", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			if (opcion==0) {
				controlador.getModelo().borrarPrendasIdProovedor(controlador.getModelo().tomarIDporCodigo(controlador.getModelo().tomarCodigoTabla(controlador.getProovedores().getTable())));
				controlador.getModelo().borrarProovedorPorId(controlador.getModelo().tomarIDporCodigo(controlador.getModelo().tomarCodigoTabla(controlador.getProovedores().getTable())));
				dispose();
			}
			}
		});
		
		JButton btnCambiarNombre = new JButton("Cambiar nombre");
		btnCambiarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = JOptionPane.showInputDialog("Ingrese nuevo nombre");
				if (nombre==null || nombre.equals("")){
					JOptionPane.showMessageDialog(null, "No se ha realizado ningún cambio", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					controlador.getModelo().cambiarNombre(nombre, controlador.getModelo().tomarCodigoTabla(controlador.getProovedores().getTable()));
					lblNewLabel.setText("<html> Visualizando al proovedor <B>"+ nombre + "</B> con el CODIGO  <B>" + controlador.getModelo().tomarCodigoTabla(controlador.getProovedores().getTable()) + "</B></html>");
					btnCambiarNombre.setEnabled(false);
				}
			}
		});
		
		JButton btnCambiarCodigo = new JButton("Cambiar codigo");
		btnCambiarCodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = JOptionPane.showInputDialog("Ingrese nuevo codigo");
				if (controlador.getModelo().codigoRepetido(codigo)){
					JOptionPane.showMessageDialog(null, "No se ha realizado ningún cambio, ya existe otro proovedor con ese codigo", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
				if (codigo==null || codigo.equals("")){
					JOptionPane.showMessageDialog(null, "No se ha realizado ningún cambio", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					controlador.getModelo().cambiarCodigo(codigo, controlador.getModelo().tomarCodigoTabla(controlador.getProovedores().getTable()));
					lblNewLabel.setText("<html> Visualizando al proovedor <B>"+ controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()) + "</B> con el CODIGO  <B>" + codigo + "</B></html>");
					btnCambiarCodigo.setEnabled(false);
				}
			}
		}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_1))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCambiarNombre)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCambiarCodigo))
								.addComponent(btnNewButton))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(btnNewButton_1))
					.addGap(43)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_2)
						.addComponent(btnCambiarNombre)
						.addComponent(btnCambiarCodigo))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		
		tablaPrendas = new JTable();
		tablaPrendas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		tablaPrendas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre prenda", "Precio", "Estado pago", "ID"
			}
		));
		scrollPane.setViewportView(tablaPrendas);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
		setClosable(true);
	}


	public Controlador getControlador() {
		return controlador;
	}


	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}


	public JTable getTablaPrendas() {
		return tablaPrendas;
	}


	public void setTablaPrendas(JTable tablaPrendas) {
		this.tablaPrendas = tablaPrendas;
	}
	
	public void dispose() {
		controlador.getProovedores().btnMpp.setEnabled(true);
		controlador.getModelo().limpiarTabla(controlador.getProovedores().getTable());
		controlador.getModelo().listarProovedores(controlador.getProovedores().getTable());
		controlador.getProovedores().getTable().setEnabled(true);
		super.dispose();
	}
}
