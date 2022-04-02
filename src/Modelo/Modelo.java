package Modelo;

import java.awt.Component;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Controlador.Controlador;

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
		String sql = "select p.nombre, p.codigo from proovedores p where p.estado=0 group by p.nombre order by p.codigo ASC";
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
				proovedor[2] = "$" + obtenerTotalPagar(rs.getString("codigo"));
				modelo.addRow(proovedor);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void listarProovedoresPorNombre(JTable tabla) {
		limpiarTabla(tabla);
		String sql = "select p.nombre, p.codigo from proovedores p where p.estado=0 group by p.nombre order by p.nombre ASC";
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
				proovedor[2] = "$" + obtenerTotalPagar(rs.getString("codigo"));
				modelo.addRow(proovedor);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void listarPrendasPorNombre(JTable tabla, String nombre) {
		limpiarTabla(tabla);
		String sql = "select pr.id, pr.nombrePrenda, pr.precio, pr.estadoPago, pr.estadoVendido, pr.fechaPago from prenda pr left join proovedores p on pr.idProovedor = p.id where p.nombre='"
				+ nombre + "' order by pr.nombrePrenda ASC";
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = conexion.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			Object[] prenda = new Object[6];
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				prenda[0] = rs.getString("pr.nombrePrenda");
				prenda[1] = "$" + rs.getFloat("pr.precio");
				int estadoPago = rs.getInt("pr.estadoPago");
				switch (estadoPago) {
				case 0:
					prenda[2] = "NO PAGO";
					break;
				case 1:
					prenda[2] = "PAGADO";
					break;
				}
				int estadoVendido = rs.getInt("pr.estadoVendido");
				switch (estadoVendido) {
				case 0:
					prenda[3] = "NO VENDIDO";
					break;
				case 1:
					prenda[3] = "VENDIDO";
					break;
				}
				prenda[4] = rs.getInt("pr.id");
				if (rs.getDate("pr.fechaPago") != null) {
					String fecha = rs.getDate("pr.fechaPago").toString();
					String fechas[] = fecha.split("-");

					String fechaDMA = fechas[2] + "/" + fechas[1] + "/" + fechas[0];

					prenda[5] = fechaDMA;

				} else {
					prenda[5] = rs.getDate("pr.fechaPago");
				}

				modelo.addRow(prenda);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void listarPrendasPorNombreID(JTable tabla, String nombre) {
		limpiarTabla(tabla);
		String sql = "select pr.id, pr.nombrePrenda, pr.precio, pr.estadoPago, pr.estadoVendido, pr.fechaPago from prenda pr left join proovedores p on pr.idProovedor = p.id where p.nombre='"
				+ nombre + "' order by pr.id ASC";
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = conexion.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			Object[] prenda = new Object[6];
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				prenda[0] = rs.getString("pr.nombrePrenda");
				prenda[1] = "$" + rs.getFloat("pr.precio");
				int estadoPago = rs.getInt("pr.estadoPago");
				switch (estadoPago) {
				case 0:
					prenda[2] = "NO PAGO";
					break;
				case 1:
					prenda[2] = "PAGADO";
					break;
				}
				int estadoVendido = rs.getInt("pr.estadoVendido");
				switch (estadoVendido) {
				case 0:
					prenda[3] = "NO VENDIDO";
					break;
				case 1:
					prenda[3] = "VENDIDO";
					break;
				}
				prenda[4] = rs.getInt("pr.id");
				if (rs.getDate("pr.fechaPago") != null) {
					String fecha = rs.getDate("pr.fechaPago").toString();
					String fechas[] = fecha.split("-");

					String fechaDMA = fechas[2] + "/" + fechas[1] + "/" + fechas[0];

					prenda[5] = fechaDMA;

				} else {
					prenda[5] = rs.getDate("pr.fechaPago");
				}

				modelo.addRow(prenda);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void listarPrendasPorNombreEstadoPago(JTable tabla, String nombre) {
		limpiarTabla(tabla);
		String sql = "select pr.id, pr.nombrePrenda, pr.precio, pr.estadoPago, pr.estadoVendido, pr.fechaPago from prenda pr left join proovedores p on pr.idProovedor = p.id where p.nombre='"
				+ nombre + "' order by pr.estadoVendido=1 and estadoPago=0 DESC";
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = conexion.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			Object[] prenda = new Object[6];
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				prenda[0] = rs.getString("pr.nombrePrenda");
				prenda[1] = "$" + rs.getFloat("pr.precio");
				int estadoPago = rs.getInt("pr.estadoPago");
				switch (estadoPago) {
				case 0:
					prenda[2] = "NO PAGO";
					break;
				case 1:
					prenda[2] = "PAGADO";
					break;
				}
				int estadoVendido = rs.getInt("pr.estadoVendido");
				switch (estadoVendido) {
				case 0:
					prenda[3] = "NO VENDIDO";
					break;
				case 1:
					prenda[3] = "VENDIDO";
					break;
				}
				prenda[4] = rs.getInt("pr.id");
				if (rs.getDate("pr.fechaPago") != null) {
					String fecha = rs.getDate("pr.fechaPago").toString();
					String fechas[] = fecha.split("-");

					String fechaDMA = fechas[2] + "/" + fechas[1] + "/" + fechas[0];

					prenda[5] = fechaDMA;

				} else {
					prenda[5] = rs.getDate("pr.fechaPago");
				}

				modelo.addRow(prenda);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void listarPrendasPorNombreEstadoPagoFull(JTable tabla, String nombre) {
		limpiarTabla(tabla);
		String sql = "select pr.id, pr.nombrePrenda, pr.precio, pr.estadoPago, pr.estadoVendido, pr.fechaPago from prenda pr left join proovedores p on pr.idProovedor = p.id where p.nombre='"
				+ nombre + "' order by pr.estadoVendido=1 and estadoPago=1 DESC";
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = conexion.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			Object[] prenda = new Object[6];
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				prenda[0] = rs.getString("pr.nombrePrenda");
				prenda[1] = "$" + rs.getFloat("pr.precio");
				int estadoPago = rs.getInt("pr.estadoPago");
				switch (estadoPago) {
				case 0:
					prenda[2] = "NO PAGO";
					break;
				case 1:
					prenda[2] = "PAGADO";
					break;
				}
				int estadoVendido = rs.getInt("pr.estadoVendido");
				switch (estadoVendido) {
				case 0:
					prenda[3] = "NO VENDIDO";
					break;
				case 1:
					prenda[3] = "VENDIDO";
					break;
				}
				prenda[4] = rs.getInt("pr.id");
				if (rs.getDate("pr.fechaPago") != null) {
					String fecha = rs.getDate("pr.fechaPago").toString();
					String fechas[] = fecha.split("-");

					String fechaDMA = fechas[2] + "/" + fechas[1] + "/" + fechas[0];

					prenda[5] = fechaDMA;

				} else {
					prenda[5] = rs.getDate("pr.fechaPago");
				}

				modelo.addRow(prenda);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void listarPrendasPorNombreFechaPago(JTable tabla, String nombre) {
		limpiarTabla(tabla);
		String sql = "select pr.id, pr.nombrePrenda, pr.precio, pr.estadoPago, pr.estadoVendido, pr.fechaPago from prenda pr left join proovedores p on pr.idProovedor = p.id where p.nombre='"
				+ nombre + "' order by pr.fechaPago DESC";
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = conexion.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			Object[] prenda = new Object[6];
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				prenda[0] = rs.getString("pr.nombrePrenda");
				prenda[1] = "$" + rs.getFloat("pr.precio");
				int estadoPago = rs.getInt("pr.estadoPago");
				switch (estadoPago) {
				case 0:
					prenda[2] = "NO PAGO";
					break;
				case 1:
					prenda[2] = "PAGADO";
					break;
				}
				int estadoVendido = rs.getInt("pr.estadoVendido");
				switch (estadoVendido) {
				case 0:
					prenda[3] = "NO VENDIDO";
					break;
				case 1:
					prenda[3] = "VENDIDO";
					break;
				}
				prenda[4] = rs.getInt("pr.id");
				if (rs.getDate("pr.fechaPago") != null) {
					String fecha = rs.getDate("pr.fechaPago").toString();
					String fechas[] = fecha.split("-");

					String fechaDMA = fechas[2] + "/" + fechas[1] + "/" + fechas[0];

					prenda[5] = fechaDMA;

				} else {
					prenda[5] = rs.getDate("pr.fechaPago");
				}

				modelo.addRow(prenda);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public boolean agregarProovedor(String nombre, String codigo) {
		String sql = "insert into proovedores(nombre,codigo,estado) values('" + nombre + "','" + codigo + "',0)";
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ps = con.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public void test() {
		System.out.println("jola");
	}

	public String tomarNombreTabla(JTable tabla) {
		int row = tabla.getSelectedRow();
		String nombre = tabla.getModel().getValueAt(row, 0).toString();
		return nombre;
	}

	public String tomarCodigoTabla(JTable tabla) {
		int row = tabla.getSelectedRow();
		String codigo = tabla.getModel().getValueAt(row, 1).toString();
		return codigo;
	}

	public String tomarIdTabla(JTable tabla) {
		int row = tabla.getSelectedRow();
		String id = tabla.getModel().getValueAt(row, 4).toString();
		return id;
	}

	public boolean setPagoTrueFalse(int id) {
		String sql = "update prenda set estadoPago = NOT estadoPago where id=" + id;
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ps = con.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public boolean setVendidoTrueFalse(int id) {
		String sql = "update prenda set estadoVendido = NOT estadoVendido where id=" + id;
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ps = con.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public int obtenerTotalPagar(String codigo) {
		String sql = "select sum(pr.precio) as suma from prenda pr inner join proovedores p on p.id=pr.idProovedor where p.codigo='"
				+ codigo + "' and pr.estadoPago=0 and pr.estadoVendido=1";
		try {
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = conexion.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt("suma");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public void borrarPrendasIdProovedor(int id) {
		String sql = "delete from prenda where idProovedor=" + id;
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
		String sql = "select id from proovedores where codigo='" + codigo + "'";
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("id");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public void borrarProovedorPorId(int id) {
		String sql = "update proovedores set estado = not estado where id=" + id;
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

	public boolean cambiarNombre(String nombre, String codigo) {
		String sql = "update proovedores set nombre='" + nombre + "' where codigo='" + codigo + "'";
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ps = con.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public boolean cambiarNombrePrenda(String nombre, String id) {
		String sql = "update prenda set nombrePrenda='" + nombre + "' where id='" + id + "'";
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ps = con.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public boolean borrarPrenda(String id) {
		String sql = "delete from prenda where id=" + id;
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ps = con.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public boolean cambiarPrecioPrenda(int precio, String id) {
		String sql = "update prenda set precio='" + precio + "' where id='" + id + "'";
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ps = con.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public boolean cambiarCodigo(String nuevoCodigo, String codigo) {
		String sql = "update proovedores set codigo='" + nuevoCodigo + "' where codigo='" + codigo + "'";
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ps = con.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public boolean codigoRepetido(String codigo) {
		String sql = "select * from proovedores where codigo='" + codigo + "'";
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean nombreRepetido(String nombre) {
		String sql = "select * from proovedores where nombre='" + nombre + "'";
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean fechaNull(int id) {
		String sql = "select fechaPago from prenda where id=" + id;
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			Date fecha;
			while (rs.next()) {
				if (rs.getDate("fechaPago") == null) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean buscarNombre(JTable tabla, String palabra) {
		String sql = "select pr.nombrePrenda, p.codigo, p.nombre from prenda pr inner join proovedores p on p.id=pr.idProovedor where pr.nombrePrenda like'%"
				+ palabra + "%'";
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			Object[] proovedor = new Object[3];
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			while (rs.next()) {
				proovedor[0] = rs.getString("pr.nombrePrenda");
				proovedor[1] = rs.getString("p.nombre");
				proovedor[2] = rs.getString("p.codigo");
				modelo.addRow(proovedor);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean buscarPrendaID(JTable tabla, String palabra) {
		String sql = "select pr.id, pr.nombrePrenda, p.codigo, p.nombre, pr.precio from prenda pr inner join proovedores p on p.id=pr.idProovedor where pr.id like'%"
				+ palabra + "%' limit 1";
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ResultSet rs = null;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			Object[] proovedor = new Object[5];
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			while (rs.next()) {
				proovedor[0] = rs.getString("pr.id");
				proovedor[1] = rs.getString("pr.nombrePrenda");
				proovedor[2] = rs.getString("p.nombre");
				proovedor[3] = rs.getString("p.codigo");
				proovedor[4] = rs.getString("pr.precio");
				modelo.addRow(proovedor);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public boolean agregarPrenda(String nombre, int precio, int idProovedor) {
		String sql = "insert into prenda(id,nombrePrenda,precio,estadoPago,estadoVendido,idProovedor) values (NULL,'"
				+ nombre + "'," + precio + ",0,0," + idProovedor + ")";
		Connection con = conexion.getConexion();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean setearFecha(int id) {
		LocalDate Fecha = LocalDate.now();
		String sql = "update prenda set fechaPago= '" + Fecha + "' where id=" + id;
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ps = con.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public boolean setearFechaNull(int id) {
		LocalDate Fecha = LocalDate.now();
		String sql = "update prenda set fechaPago= null where id=" + id;
		try {
			Connection con = conexion.getConexion();
			PreparedStatement ps = null;
			ps = con.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public void resizeColumnWidth(JTable table) {
		// Se obtiene el modelo de la columna
		TableColumnModel columnModel = table.getColumnModel();
		// Se obtiene el total de las columnas
		for (int column = 0; column < table.getColumnCount(); column++) {
			// Establecemos un valor minimo para el ancho de la columna
			int width = 150; // Min Width
			// Obtenemos el numero de filas de la tabla
			for (int row = 0; row < table.getRowCount(); row++) {
				// Obtenemos el renderizador de la tabla
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				// Creamos un objeto para preparar el renderer
				Component comp = table.prepareRenderer(renderer, row, column);
				// Establecemos el width segun el valor maximo del ancho de la columna
				width = Math.max(comp.getPreferredSize().width + 1, width);

			}
			// Se establece una condicion para no sobrepasar el valor de 300
			// Esto es Opcional
			if (width > 300) {
				width = 300;
			}
			// Se establece el ancho de la columna
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

}
