package Vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import Controlador.Controlador;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class buscarPrendaID extends JInternalFrame {

	Controlador controlador;
	private JTable table;
	private JTextField textField;
	

	public JTable getTable() {
		return table;
	}


	public void setTable(JTable table) {
		this.table = table;
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
	
	public buscarPrendaID() {
		setTitle("Buscador ID nombre de prendas");
		setBounds(100, 100, 1080, 263);
		setClosable(true);
		
		JScrollPane scrollPane = new JScrollPane();
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode() == KeyEvent.VK_ENTER && controlador.getBuscarPrendaID() != null)
			    {
			    	if (textField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "No puede estar vacio", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						controlador.getModelo().limpiarTabla(controlador.getBuscarPrendaID().getTable());
						controlador.getModelo().buscarPrendaID(controlador.getBuscarPrendaID().getTable(),textField.getText());
						controlador.getModelo().resizeColumnWidth(controlador.getBuscarPrendaID().getTable());
					}
				
			    }
			}
		});
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setIcon(new ImageIcon(buscarPrendaID.class.getResource("/imagenes/icono.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No puede estar vacio", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					controlador.getModelo().limpiarTabla(controlador.getBuscarPrendaID().getTable());
					controlador.getModelo().buscarPrendaID(controlador.getBuscarPrendaID().getTable(),textField.getText());
					controlador.getModelo().resizeColumnWidth(controlador.getBuscarPrendaID().getTable());
					
				}
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField, 144, 144, 144)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel = new JLabel("ID de la prenda a buscar");
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column){  
		          return false;  
		      }
		};
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo prenda","Nombre","Nombre proovedor","Codigo","Precio"
			}
		));
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

	}
	public void dispose() {
    	controlador.setBuscarPrendaID(null);
        super.dispose();
    }
}
