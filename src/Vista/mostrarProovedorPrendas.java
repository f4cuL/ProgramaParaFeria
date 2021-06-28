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
import java.beans.PropertyVetoException;

import javax.swing.SwingConstants;

public class mostrarProovedorPrendas extends JInternalFrame {
	Controlador controlador;
	private JTable tablaPrendas;
	private JButton btnNewButton;
	/**
	 * Launch the application.
	 */


	public JButton getBtnNewButton() {
		return btnNewButton;
	}


	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}


	/**
	 * Create the frame.
	 */
	public mostrarProovedorPrendas(Controlador controlador) {
		setBounds(100, 100, 778, 525);
		setControlador(controlador);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		JLabel lblNewLabel = new JLabel("<html>" +"Visualizando al proovedor <B>"+ controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()) + "</B> con el CODIGO  <B>" + controlador.getModelo().tomarCodigoTabla(controlador.getProovedores().getTable()) + "</B></html>") ;
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 17));
		
		btnNewButton = new JButton("Agregar prenda");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarPrenda aP = new agregarPrenda();
				aP.setVisible(true);
				controlador.getModelo().centrarJIP(aP);
				controlador.setAgregarPrenda(aP);
				aP.setControlador(controlador);
				controlador.getMenuPrincipal().getDesktopPane().add(aP,0);
				aP.toFront();
				setClosable(false);
				try {
					aP.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnNewButton.setEnabled(false);
				
			}
		});
		setTitle("Proovedor "+controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()));
		JButton btnNewButton_1 = new JButton("Cambiar estado de pago");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setForeground(new Color(0, 128, 128));
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
		
		JButton btnCambiarNombre = new JButton("Cambiar nombre proovedor");
		JButton btnCambiarCodigo = new JButton("Cambiar codigo");
		btnCambiarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = JOptionPane.showInputDialog("Ingrese nuevo nombre");
				if (nombre==null || nombre.equals("")){
					JOptionPane.showMessageDialog(null, "No se ha realizado ningún cambio", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					controlador.getModelo().cambiarNombre(nombre, controlador.getModelo().tomarCodigoTabla(controlador.getProovedores().getTable()));
					if (btnCambiarCodigo.isEnabled())
					{
					lblNewLabel.setText("<html> Visualizando al proovedor <B>"+ nombre + "</B> con el CODIGO  <B>" + controlador.getModelo().tomarCodigoTabla(controlador.getProovedores().getTable()) + "</B></html>");
					}else {
						lblNewLabel.setText("Cierre esta pestaña para que se efectuen los cambios");
					}
					dispose();
				}
			}
		});
		
		btnCambiarCodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = JOptionPane.showInputDialog("Ingrese nuevo codigo(Cambiar el codigo cerrará esta pestaña)");
				if (controlador.getModelo().codigoRepetido(codigo)){
					JOptionPane.showMessageDialog(null, "No se ha realizado ningún cambio, ya existe otro proovedor con ese codigo", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
				if (codigo==null || codigo.equals("")){
					JOptionPane.showMessageDialog(null, "No se ha realizado ningún cambio", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					controlador.getModelo().cambiarCodigo(codigo, controlador.getModelo().tomarCodigoTabla(controlador.getProovedores().getTable()));
					dispose();
				}
			}
		}
		});
		
		JButton btnNewButton_3 = new JButton("Cambiar estado de venta\r\n");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setForeground(new Color(0, 128, 128));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tablaPrendas.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "DEBES SELECCIONAR UNA PRENDA PRIMERO",
			                "ERROR", JOptionPane.ERROR_MESSAGE);				
				}
				else {
				controlador.getModelo().setVendidoTrueFalse(Integer.parseInt(controlador.getModelo().tomarIdTabla(tablaPrendas)));
				controlador.getModelo().limpiarTabla(tablaPrendas);
				controlador.getModelo().listarPrendasPorNombre(tablaPrendas,controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()));			
				}
			}
		});
		
		JButton btnNewButton_4 = new JButton("Cambiar nombre prenda");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tablaPrendas.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "DEBES SELECCIONAR UNA PRENDA PRIMERO",
			                "ERROR", JOptionPane.ERROR_MESSAGE);				
				}
				else
				{	
				String nombre = JOptionPane.showInputDialog("Ingrese nuevo nombre");
				if (nombre==null || nombre.equals("")){
					JOptionPane.showMessageDialog(null, "No se ha realizado ningún cambio", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else {
					controlador.getModelo().cambiarNombrePrenda(nombre, controlador.getModelo().tomarIdTabla(tablaPrendas));
					controlador.getModelo().limpiarTabla(tablaPrendas);
					controlador.getModelo().listarPrendasPorNombre(tablaPrendas,controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()));
				}

			}}
		});
		
		JButton btnNewButton_5 = new JButton("Cambiar precio prenda");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tablaPrendas.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "DEBES SELECCIONAR UNA PRENDA PRIMERO",
			                "ERROR", JOptionPane.ERROR_MESSAGE);				
				}else
				{	
				String nombre = JOptionPane.showInputDialog("Ingrese nuevo nombre");
				if (nombre==null || nombre.equals("")){
					JOptionPane.showMessageDialog(null, "No se ha realizado ningún cambio", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else {
					try {
						controlador.getModelo().cambiarPrecioPrenda(Integer.parseInt(nombre), controlador.getModelo().tomarIdTabla(tablaPrendas));
						controlador.getModelo().limpiarTabla(tablaPrendas);
						controlador.getModelo().listarPrendasPorNombre(tablaPrendas,controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()));					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "TIENE QUE SER UN NUMERO",
				                "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				
				}

			}
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnNewButton_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED, 347, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_2)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnCambiarNombre)
									.addGap(18)
									.addComponent(btnCambiarCodigo)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_1)
								.addComponent(btnNewButton_4))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_3)
								.addComponent(btnNewButton_5))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCambiarCodigo)
						.addComponent(btnNewButton)
						.addComponent(btnCambiarNombre))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_2)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		
		tablaPrendas = new JTable() {
			public boolean isCellEditable(int row, int column){  
		          return false;  
		      }
		};
		tablaPrendas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		tablaPrendas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre prenda", "Precio", "Estado pago", "Estado vendido", "ID"
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
		controlador.getProovedores().setClosable(true);
		controlador.getModelo().limpiarTabla(controlador.getProovedores().getTable());
		controlador.getModelo().listarProovedores(controlador.getProovedores().getTable());
		controlador.getProovedores().getTable().setEnabled(true);
		super.dispose();
	}
}
