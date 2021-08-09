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
		frame.setForeground(Color.WHITE);
		frame.getContentPane().setEnabled(false);
		frame.setTitle("RENOVA VIP");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/fondo.png"));
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
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Test bdd");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controlador.getModelo().buscarNombre(controlador.getBuscarPrendaNombre().getTable(),"cheeky");
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Prendas");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Buscar prenda por nombre");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_2 = new JMenu("New menu");
		menuBar.add(mnNewMenu_2);
		
		JMenu menu = new JMenu("New menu");
		menuBar.add(menu);
		

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
