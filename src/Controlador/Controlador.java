package Controlador;

import Modelo.Modelo;
import Vista.*;

public class Controlador {
	
	private Modelo Modelo;
	private menuPrincipal menuPrincipal;
	private proovedores proovedores;
	private agregarProovedor agregarProovedor;
	private mostrarProovedorPrendas mostrarProovedorPrendas;
	private agregarPrenda agregarPrenda;
	private buscarPrendaNombre buscarPrendaNombre;
		
	
	
	public buscarPrendaNombre getBuscarPrendaNombre() {
		return buscarPrendaNombre;
	}
	public void setBuscarPrendaNombre(buscarPrendaNombre buscarPrendaNombre) {
		this.buscarPrendaNombre = buscarPrendaNombre;
	}
	public agregarPrenda getAgregarPrenda() {
		return agregarPrenda;
	}
	public void setAgregarPrenda(agregarPrenda agregarPrenda) {
		this.agregarPrenda = agregarPrenda;
	}
	public mostrarProovedorPrendas getMostrarProovedorPrendas() {
		return mostrarProovedorPrendas;
	}
	public void setMostrarProovedorPrendas(mostrarProovedorPrendas mostrarProovedorPrendas) {
		this.mostrarProovedorPrendas = mostrarProovedorPrendas;
	}
	public agregarProovedor getAgregarProovedor() {
		return agregarProovedor;
	}
	public void setAgregarProovedor(agregarProovedor agregarProovedor) {
		this.agregarProovedor = agregarProovedor;
	}
	public void setProovedores(proovedores proovedores) {
		this.proovedores = proovedores;
	}
	public Modelo getModelo() {
		return Modelo;
	}
	public void setModelo(Modelo modelo) {
		Modelo = modelo;
	}
	public menuPrincipal getMenuPrincipal() {
		return menuPrincipal; 
	}
	public void setMenuPrincipal(menuPrincipal menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
	}
	public proovedores getProovedores() {
		return proovedores;
	}
	public void setTest(proovedores test) {
		this.proovedores = test;
	}

}
