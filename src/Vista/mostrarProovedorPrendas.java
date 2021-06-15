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
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton_1))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_2)
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
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_2)
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
