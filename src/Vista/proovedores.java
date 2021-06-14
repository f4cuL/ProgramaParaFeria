package Vista;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Controlador.Controlador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class proovedores extends JInternalFrame {
	
	Controlador Controlador;
	private JTable table;
	int contador = 0;
	JButton btnAgregarProovedor;
	JButton btnMpp;
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
		setBounds(100, 100, 744, 596);
		setVisible(true);
		setClosable(true);
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column){  
		          return false;  
		      }
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Nombre", "Codigo del proovedor", "Total a pagar"
			}
		));
		scrollPane.setViewportView(table);
		
		btnAgregarProovedor = new JButton("Agregar proovedor");
		btnAgregarProovedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				if (Controlador.getAgregarProovedor().getContador()==0)
				{
				agregarProovedor ap = new agregarProovedor();
				ap.setControlador(Controlador);
				Controlador.setAgregarProovedor(ap);
				Controlador.getModelo().centrarJIP(ap);
				Controlador.getMenuPrincipal().getDesktopPane().add(ap,0);
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
				table.setEnabled(false);
				if(table.getSelectionModel().isSelectionEmpty())
				{
					JOptionPane.showMessageDialog(null, "DEBES SELECCIONAR UN PROOVEDOR PRIMERO",
			                "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				mostrarProovedorPrendas mpp = new mostrarProovedorPrendas(Controlador);
				Controlador.setMostrarProovedorPrendas(mpp);
				Controlador.getModelo().centrarJIP(mpp);
				Controlador.getMenuPrincipal().getDesktopPane().add(mpp,0);
				try {
					mpp.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Controlador.getModelo().listarPrendasPorNombre(Controlador.getMostrarProovedorPrendas().getTablaPrendas(),Controlador.getModelo().tomarNombreTabla(table));
				btnMpp.setEnabled(false);
			}}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 728, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(44)
							.addComponent(btnAgregarProovedor, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnMpp)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMpp, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAgregarProovedor, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(162, Short.MAX_VALUE))
		);
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

