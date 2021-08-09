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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class backupBDD extends JInternalFrame {
	private JTextField textField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public backupBDD() {
		setBounds(100, 100, 449, 125);
		
		textField = new JTextField();
		textField.setColumns(10);
		setTitle("Copia de seguridad");
		setClosable(true);
		JButton btnNewButton = new JButton("Seleccionar carpeta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser ch = new JFileChooser();
				ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int se = ch.showSaveDialog(null);
				if (se==JFileChooser.APPROVE_OPTION) {
					String ruta=ch.getSelectedFile().getPath();
					textField.setText(ruta);
				}
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("Crear backup");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ruta = textField.getText();
				String nombre = "\\backup.sql";
				String backup= "";
				
				if(ruta.trim().length()!=0) {
					try {
						backup = "C:\\\\xampp\\\\mysql\\\\bin\\\\mysqldump --opt -u root -B feria -r "+"\""+ruta+nombre+"\"";
						Runtime rt = Runtime.getRuntime();
						rt.exec(backup);
						JOptionPane.showMessageDialog(null,"Backup creado con éxito");
						System.out.println("\""+ruta+nombre+"\"");
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
							.addContainerGap()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addComponent(btnNewButton_1)))
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
