package Vista;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import Controlador.Controlador;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.time.LocalDate;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class menuPrincipal {

	private Controlador controlador;
	private JFrame frame;
	private JDesktopPane desktopPane;
	private int contador=0;

	public Controlador getControlador() {
		return controlador;
	}

	public int getContador() { 
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */
	public menuPrincipal() {
		try { 
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(menuPrincipal.class.getResource("/imagenes/toolbar.png")));
		frame.setForeground(Color.WHITE);
		frame.getContentPane().setEnabled(false);
		frame.setTitle("RENOVA VIP");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/fondo1080.png"));
		Image image = icon.getImage();

		
		desktopPane = new JDesktopPane() {
			 protected void paintComponent(Graphics g) {
			        super.paintComponent(g);
			        g.setColor(Color.BLACK);
			        g.fillRect(0, 0, getWidth(), getHeight());
			        g.drawImage(image, 0, 0, null);
			    }
		};
		desktopPane.setForeground(Color.WHITE);
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Proovedores");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Lista de proovedores");
		mntmNewMenuItem_1.setIcon(new ImageIcon(menuPrincipal.class.getResource("/imagenes/clientes.png")));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contador == 0) {
					proovedores proovedores = new proovedores();
					controlador.setProovedores(proovedores);
					proovedores.setControlador(controlador);
					desktopPane.add(proovedores);
					controlador.getModelo().centrarJIP(proovedores);
					controlador.getModelo().listarProovedores(proovedores.getTable());
					proovedores.toFront();
					try {
						proovedores.setSelected(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					contador++; 
				}

			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Copia de seguridad\r\n");
		mntmNewMenuItem.setIcon(new ImageIcon(menuPrincipal.class.getResource("/imagenes/diskette.png")));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (controlador.getBackupBDD()==null)
				{
				backupBDD bDD = new backupBDD();
				bDD.setVisible(true);
				bDD.toFront();
				controlador.getModelo().centrarJIP(bDD);
				desktopPane.add(bDD);
				controlador.setBackupBDD(bDD);
				bDD.setControlador(controlador);
				try {
					bDD.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Subir copia de seguridad");
		mntmNewMenuItem_3.setEnabled(false);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (controlador.getSubirBackup()==null)
				{
				subirBackup sB = new subirBackup();
				sB.setVisible(true);
				sB.toFront();
				controlador.getModelo().centrarJIP(sB);
				desktopPane.add(sB);
				controlador.setSubirBackup(sB);
				sB.setControlador(controlador);
				try {
					sB.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}

		});
		mntmNewMenuItem_3.setIcon(new ImageIcon(menuPrincipal.class.getResource("/imagenes/warning.png")));
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("Prendas");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Buscar prenda por nombre");
		mntmNewMenuItem_2.setIcon(new ImageIcon(menuPrincipal.class.getResource("/imagenes/icono.png")));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (controlador.getBuscarPrendaNombre() == null)
				{
				buscarPrendaNombre bPN = new buscarPrendaNombre();
				bPN.setVisible(true);
				controlador.setBuscarPrendaNombre(bPN);
				desktopPane.add(bPN);
				bPN.toFront();
				bPN.setControlador(controlador);
				try {
					bPN.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				controlador.getModelo().centrarJIP(bPN);
			}
			else {
				
			}
			}});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Buscar prenda por id");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (controlador.getBuscarPrendaID() == null) {
				buscarPrendaID bPID = new buscarPrendaID();
				controlador.setBuscarPrendaID(bPID);
				desktopPane.add(bPID);
				bPID.setVisible(true);
				bPID.toFront();
				controlador.getModelo().centrarJIP(bPID);
				bPID.setControlador(controlador); 
				}
			}
		});
		mntmNewMenuItem_4.setIcon(new ImageIcon(menuPrincipal.class.getResource("/imagenes/icono.png")));
		mnNewMenu_1.add(mntmNewMenuItem_4);
		

		frame.setVisible(true);
		
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public void setDesktopPane(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}		
}
