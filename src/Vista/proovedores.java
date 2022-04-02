package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import Controlador.Controlador;

public class proovedores extends JInternalFrame {

	Controlador Controlador;
	private JTable table;
	int contador = 0;
	JButton btnAgregarProovedor;
	JButton btnMpp;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel;

	public JButton getBtnMpp() {
		return btnMpp;
	}

	public void setBtnMpp(JButton btnMpp) {
		this.btnMpp = btnMpp;
	}

	public JButton getBtnAgregarProovedor() {
		return btnAgregarProovedor;
	}

	public void setBtnAgregarProovedor(JButton btnAgregarProovedor) {
		this.btnAgregarProovedor = btnAgregarProovedor;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	/**
	 * Launch the application.
	 */

	public Controlador getControlador() {
		return Controlador;
	}

	public void setControlador(Controlador controlador) {
		Controlador = controlador;
	}

	/**
	 * Create the frame.
	 */

	public proovedores() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		setTitle("Proovedores");
		setBounds(100, 100, 744, 630);
		setVisible(true);
		setClosable(true);

		JScrollPane scrollPane = new JScrollPane();

		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, },
				new String[] { "Nombre", "Codigo del proovedor", "Total a pagar" }));
		scrollPane.setViewportView(table);

		btnAgregarProovedor = new JButton("Agregar proovedor");
		btnAgregarProovedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Controlador.getAgregarProovedor().getContador() == 0) {
					agregarProovedor ap = new agregarProovedor();
					ap.setControlador(Controlador);
					Controlador.setAgregarProovedor(ap);
					Controlador.getModelo().centrarJIP(ap);
					Controlador.getMenuPrincipal().getDesktopPane().add(ap, 0);
					ap.toFront();
					btnAgregarProovedor.setEnabled(false);
					try {
						ap.setSelected(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		btnMpp = new JButton("Editar proovedor seleccionado");
		btnMpp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "DEBES SELECCIONAR UN PROOVEDOR PRIMERO", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					;
				} else {
					mostrarProovedorPrendas mpp = new mostrarProovedorPrendas(Controlador);
					Controlador.setMostrarProovedorPrendas(mpp);
					Controlador.getModelo().centrarJIP(mpp);
					Controlador.getMenuPrincipal().getDesktopPane().add(mpp, 0);
					btnMpp.setEnabled(false);
					table.setEnabled(false);
					setClosable(false);
					try {
						mpp.setSelected(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Controlador.getModelo().listarPrendasPorNombreEstadoPago(
							Controlador.getMostrarProovedorPrendas().getTablaPrendas(),
							Controlador.getModelo().tomarNombreTabla(table));
					Controlador.getModelo()
							.resizeColumnWidth(Controlador.getMostrarProovedorPrendas().getTablaPrendas());
				}
			}
		});

		btnNewButton = new JButton("Ordenar por orden alfab\u00E9tico");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.getModelo().listarProovedoresPorNombre(table);
			}
		});

		btnNewButton_1 = new JButton("Ordenar por c\u00F3digo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.getModelo().listarProovedores(table);
			}
		});

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(proovedores.class.getResource("/imagenes/clientes.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap()
										.addComponent(btnNewButton).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnNewButton_1).addGap(18).addComponent(lblNewLabel))
								.addGroup(groupLayout.createSequentialGroup().addGap(49)
										.addComponent(btnAgregarProovedor, GroupLayout.PREFERRED_SIZE, 173,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnMpp))
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 728, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
						.addComponent(btnNewButton_1).addComponent(lblNewLabel))
				.addGap(3).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE).addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMpp, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAgregarProovedor, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		getContentPane().setLayout(groupLayout);

	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void dispose() {
		Controlador.getMenuPrincipal().setContador(0);
		super.dispose();
	}
}
