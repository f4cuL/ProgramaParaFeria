package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JInternalFrame;

import Controlador.Controlador;
import Modelo.colorCelda;

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
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;

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
		setBounds(100, 100, 1236, 594);
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JLabel lblNewLabel_1 = new JLabel("Ordenamiento de prendas");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JLabel lblNewLabel_2 = new JLabel("Edici\u00F3n del proovedor");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JButton btnCambiarNombre = new JButton("Cambiar nombre proovedor");
		panel_1.add(btnCambiarNombre);
		JButton btnCambiarCodigo = new JButton("Cambiar codigo");
		panel_1.add(btnCambiarCodigo);
		
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
		
		JButton btnNewButton_2 = new JButton("Borrar proovedor");
		btnNewButton_2.setIcon(new ImageIcon(mostrarProovedorPrendas.class.getResource("/imagenes/warning.png")));
		panel_1.add(btnNewButton_2);
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
		
		JButton btnNewButton_6 = new JButton("Ordenar alfabeticamente");
		panel.add(btnNewButton_6);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.getModelo().listarPrendasPorNombre(tablaPrendas,controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()));
			}
		});
		btnNewButton_6.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnNewButton_6.setForeground(SystemColor.textHighlight);
		
		JButton btnNewButton_7 = new JButton("Ordenar por ID");
		panel.add(btnNewButton_7);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.getModelo().listarPrendasPorNombreID(tablaPrendas,controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()));
			}
		});
		btnNewButton_7.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnNewButton_7.setForeground(SystemColor.textHighlight);
		
		JButton btnNewButton_8 = new JButton("Ordenar por vendido sin pagar");
		panel.add(btnNewButton_8);
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.getModelo().listarPrendasPorNombreEstadoPago(tablaPrendas,controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()));
			}
		});
		btnNewButton_8.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnNewButton_8.setForeground(SystemColor.textHighlight);
		
		JButton btnNewButton_8_1 = new JButton("Ordenar por vendido y pagado");
		panel.add(btnNewButton_8_1);
		btnNewButton_8_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.getModelo().listarPrendasPorNombreEstadoPagoFull(tablaPrendas,controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()));
				
			}
		});
		btnNewButton_8_1.setForeground(SystemColor.textHighlight);
		btnNewButton_8_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		JButton btnNewButton_8_1_1 = new JButton("Ordenar por fecha pago");
		panel.add(btnNewButton_8_1_1);
		btnNewButton_8_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.getModelo().listarPrendasPorNombreFechaPago(tablaPrendas,controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()));
				
			}
		});
		btnNewButton_8_1_1.setForeground(SystemColor.textHighlight);
		btnNewButton_8_1_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		tablaPrendas = new colorCelda() {
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
				"Nombre prenda", "Precio", "Estado pago", "Estado vendido","ID","Fecha pago"
			}
		));
		scrollPane.setViewportView(tablaPrendas);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JLabel lblNewLabel_3 = new JLabel("Edici\u00F3n de prenda");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(mostrarProovedorPrendas.class.getResource("/imagenes/client.png")));
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\facue\\OneDrive\\Escritorio\\Cargas RENOVA\\Otras cosas\\clothes.png"));
		
		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setIcon(new ImageIcon(mostrarProovedorPrendas.class.getResource("/imagenes/clothes.png")));
		
		JButton btnNewButton_9 = new JButton("Borrar prenda\r\n");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tablaPrendas.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "DEBES SELECCIONAR UNA PRENDA PRIMERO",
			                "ERROR", JOptionPane.ERROR_MESSAGE);				
				}else {
					switch (JOptionPane.showConfirmDialog(null, "¿Estás seguro?", "Borrando prenda ",
			                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE)){
					case 0: controlador.getModelo().borrarPrenda(controlador.getModelo().tomarIdTabla(tablaPrendas));
					JOptionPane.showMessageDialog(null, "Borrado con éxito"); controlador.getModelo().limpiarTabla(tablaPrendas);
					controlador.getModelo().listarPrendasPorNombreEstadoPago(tablaPrendas,controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()));break;
			                case 1: JOptionPane.showMessageDialog(null, "No se ha realizado ningún cambio", "Error", JOptionPane.WARNING_MESSAGE);break;
						
					}
				

			}
			}
		}
		);
		btnNewButton_9.setForeground(Color.RED);
		btnNewButton_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnNewButton)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnNewButton_9)
										.addGap(24)
										.addComponent(lblNewLabel_5))
									.addComponent(lblNewLabel_1)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 826, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel_2)
										.addGap(159))
									.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 347, Short.MAX_VALUE)))
							.addComponent(scrollPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 1191, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 567, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_4)))
							.addPreferredGap(ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
							.addComponent(lblNewLabel_5_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_3)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_5_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnNewButton)
										.addComponent(btnNewButton_9))
									.addGap(14)
									.addComponent(lblNewLabel_1))
								.addComponent(lblNewLabel_5))
							.addGap(4)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))))
		);
		
		JButton btnNewButton_4 = new JButton("Cambiar nombre prenda");
		panel_2.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Cambiar precio prenda");
		panel_2.add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tablaPrendas.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "DEBES SELECCIONAR UNA PRENDA PRIMERO",
			                "ERROR", JOptionPane.ERROR_MESSAGE);				
				}else
				{	
				DefaultTableModel tm = (DefaultTableModel) tablaPrendas.getModel();
				String numeros[]=String.valueOf(tm.getValueAt(tablaPrendas.getSelectedRow(),1)).split("$");
				String nombre = JOptionPane.showInputDialog("<html>Ingrese nuevo precio, precio anterior:<b>"+String.valueOf(tm.getValueAt(tablaPrendas.getSelectedRow(),1)) + "</b></html>)");
				if (nombre==null || nombre.equals("")){
					JOptionPane.showMessageDialog(null, "No se ha realizado ningún cambio", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else {
					try {
						controlador.getModelo().cambiarPrecioPrenda(Integer.parseInt(nombre), controlador.getModelo().tomarIdTabla(tablaPrendas));
						controlador.getModelo().limpiarTabla(tablaPrendas);
						controlador.getModelo().listarPrendasPorNombreEstadoPago(tablaPrendas,controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()));					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "TIENE QUE SER UN NUMERO",
				                "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				
				}

			}
			}
		});
		JButton btnNewButton_1 = new JButton("Cambiar estado de pago");
		panel_2.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setForeground(Color.BLUE);
		
		JButton btnNewButton_3 = new JButton("Cambiar estado de venta\r\n");
		panel_2.add(btnNewButton_3);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setForeground(new Color(220, 20, 60));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tablaPrendas.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "DEBES SELECCIONAR UNA PRENDA PRIMERO",
			                "ERROR", JOptionPane.ERROR_MESSAGE);				
				}
				else {
				controlador.getModelo().setVendidoTrueFalse(Integer.parseInt(controlador.getModelo().tomarIdTabla(tablaPrendas)));
				controlador.getModelo().limpiarTabla(tablaPrendas);
				controlador.getModelo().listarPrendasPorNombreEstadoPago(tablaPrendas,controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()));			
				}
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tablaPrendas.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "DEBES SELECCIONAR UNA PRENDA PRIMERO",
			                "ERROR", JOptionPane.ERROR_MESSAGE);				
				}
				else {
				controlador.getModelo().setPagoTrueFalse(Integer.parseInt(controlador.getModelo().tomarIdTabla(tablaPrendas)));
				if (controlador.getModelo().fechaNull(Integer.parseInt(controlador.getModelo().tomarIdTabla(tablaPrendas)))){
					controlador.getModelo().setearFecha(Integer.parseInt(controlador.getModelo().tomarIdTabla(tablaPrendas)));
				}else {
					controlador.getModelo().setearFechaNull(Integer.parseInt(controlador.getModelo().tomarIdTabla(tablaPrendas)));
				}
				controlador.getModelo().limpiarTabla(tablaPrendas);
				controlador.getModelo().listarPrendasPorNombreEstadoPago(tablaPrendas,controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()));			
				}
			}
		});
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tablaPrendas.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "DEBES SELECCIONAR UNA PRENDA PRIMERO",
			                "ERROR", JOptionPane.ERROR_MESSAGE);				
				}
				else
				{	
				DefaultTableModel tm = (DefaultTableModel) tablaPrendas.getModel();
				String nombre = JOptionPane.showInputDialog("<html>Ingrese nuevo nombre para <b> "+ String.valueOf(tm.getValueAt(tablaPrendas.getSelectedRow(),0)) + "</b></html>",String.valueOf(tm.getValueAt(tablaPrendas.getSelectedRow(),0)));
				if (nombre==null || nombre.equals("")){
					JOptionPane.showMessageDialog(null, "No se ha realizado ningún cambio", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else {
					controlador.getModelo().cambiarNombrePrenda(nombre, controlador.getModelo().tomarIdTabla(tablaPrendas));
					controlador.getModelo().limpiarTabla(tablaPrendas);
					controlador.getModelo().listarPrendasPorNombreEstadoPago(tablaPrendas,controlador.getModelo().tomarNombreTabla(controlador.getProovedores().getTable()));
				}

			}}
		});
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
