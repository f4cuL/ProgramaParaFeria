package Modelo;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controlador.Controlador;
import Modelo.Conexion;

public class Modelo {
	Conexion conexion;
	Controlador Controlador;

	public Modelo() {
		conexion = new Conexion();
	}

	public Controlador getControlador() {
		return Controlador;
	}

	public void setControlador(Controlador controlador) {
		Controlador = controlador;
	}

	public void centrarJIP(JInternalFrame JIP) {
		Dimension desktopSize = Controlador.getMenuPrincipal().getDesktopPane().getSize();
		Dimension FrameSize = JIP.getSize();
		JIP.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
		JIP.show();

	}

	public boolean traerProovedores() {
		String sql = "select p.nombre, pr.precio, sum(pr.precio) as suma from proovedores p left join prenda pr on pr.idProovedor = p.id where pr.estadoPago=0 or pr.estadoPago is NULL group by p.nombre order by p.nombre ASC";
		Connection con = conexion.getConexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("p.nombre"));
				System.out.println(rs.getFloat("suma"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return true;

	}

	public void limpiarTabla(JTable tabla) {
		for (int i = 0; i < tabla.getRowCount(); i++) {
			DefaultTableModel tabla2 = (DefaultTableModel) tabla.getModel();
			tabla2.removeRow(i);
			i = i - 1;
		}
	}

	public void listarProovedores(JTable tabla) {
		limpiarTabla(tabla);
		String sql = "select p.nombre, p.codigo from proovedores p group by p.nombre order by p.nombre ASC";
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = conexion.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			Object[] proovedor = new Object[3];
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				proovedor[0] = rs.getString("p.nombre");
				proovedor[1] = rs.getString("codigo");
				proovedor[2] = "$"+obtenerTotalPagar(rs.getString("codigo"));
				modelo.addRow(proovedor);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void listarPrendasPorNombre(JTable tabla, String nombre) {
		limpiarTabla(tabla);
		String sql = "select pr.id, pr.nombrePrenda, pr.precio, pr.estadoPago from prenda pr left join proovedores p on pr.idProovedor = p.id where p.nombre='"+nombre+"'";
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = conexion.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			Object[] prenda = new Object[4];
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				prenda[0] = rs.getString("pr.nombrePrenda");
				prenda[1] = "$" + rs.getFloat("pr.precio");
				int estadoPago = rs.getInt("pr.estadoPago");
				switch(estadoPago) {
				case 0: prenda[2] = "NO PAGO"; break;
				case 1: prenda[2] = "PAGADO"; break;
				}
				prenda[3] = rs.getInt("pr.id");
				modelo.addRow(prenda);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	} 

	public boolean agregarProovedor(String nombre,String codigo) {
		String sql="insert into proovedores(nombre,codigo) values('"+nombre+"','"+codigo+"')";
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ps = con.prepareStatement(sql);
			ps.execute(sql);
		}catch (Exception e){
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	public void test()
	{
		System.out.println("jola");
	}
	
	public String tomarNombreTabla(JTable tabla) {
		int row = tabla.getSelectedRow();	
		String nombre = tabla.getModel().getValueAt(row, 0).toString();		
		return nombre;
	}
	
	public String tomarCodigoTabla(JTable tabla){
		int row = tabla.getSelectedRow();	
		String codigo = tabla.getModel().getValueAt(row, 1).toString();	
		return codigo;
	}

	public String tomarIdTabla(JTable tabla){
		int row = tabla.getSelectedRow();	
		String id = tabla.getModel().getValueAt(row, 3).toString();
		return id;
	}

	public boolean setPagoTrueFalse(int id) {
		String sql="update prenda set estadoPago = NOT estadoPago where id="+id;
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ps = con.prepareStatement(sql);
			ps.execute(sql);
		}catch (Exception e){
			System.out.println(e);
			return false;
		}
		return true;
	}
	public int obtenerTotalPagar(String codigo) {
		String sql = "select sum(pr.precio) as suma from prenda pr inner join proovedores p on p.id=pr.idProovedor where p.codigo='"+codigo+"' and pr.estadoPago=0";
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = conexion.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			if(rs.next()){
				return rs.getInt("suma");	
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
	public void borrarPrendasIdProovedor(int id) {
		String sql= "delete from prenda where idProovedor="+id;
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ps = con.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int tomarIDporCodigo(String codigo) {
		String sql= "select id from proovedores where codigo='"+codigo+"'";
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				return rs.getInt("id");
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public void borrarProovedorPorId(int id) {
		String sql= "delete from proovedores where id="+id;
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ps = con.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
