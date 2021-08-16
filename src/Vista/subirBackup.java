package Vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controlador.Controlador;
import Modelo.Conexion;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class subirBackup extends JInternalFrame {
	private JTextField textField;
	Controlador controlador;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public subirBackup() {
		setBounds(100, 100, 449, 125);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		setTitle("Subir copia de seguridad");
		setClosable(true);
		JButton btnNewButton = new JButton("Seleccionar carpeta");
		btnNewButton.setIcon(new ImageIcon(subirBackup.class.getResource("/imagenes/folder.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser ch = new JFileChooser();
				FileNameExtensionFilter fil = new FileNameExtensionFilter("SQL","sql");
				ch.setFileFilter(fil);
				int se = ch.showOpenDialog(null);
				if (se==JFileChooser.APPROVE_OPTION) {
					String ruta=ch.getSelectedFile().getPath();
					textField.setText(ruta);
				}
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("Subir backup");
		btnNewButton_1.setIcon(new ImageIcon(subirBackup.class.getResource("/imagenes/warning.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ruta = textField.getText();
				String backup= "";
				
				if(ruta.trim().length()!=0) {
					try {
						backup = "cmd /c mysql -u root feria < "+"\""+ruta+"\"";
						Runtime rt = Runtime.getRuntime();
						rt.exec(backup);
						JOptionPane.showMessageDialog(null,"Importado con éxito");
						System.out.println("\""+ruta+"\"");
					} catch (Exception e2) {
						System.out.println(e2);
					}
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Seleccione la carpeta en donde se guardar\u00E1 el backup ");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(51)
									.addComponent(btnNewButton_1)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addContainerGap(35, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(55, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}
	public void dispose() {
    	controlador.setBackupBDD(null);
        super.dispose();
    }
	public Controlador getControlador() {
		return controlador;
	}
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
}
