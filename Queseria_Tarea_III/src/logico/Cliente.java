package logico;

import java.util.ArrayList;

public class Cliente {

	private String nombre;
	private String direccion;
	private String ID;
	private int telefono;
	private ArrayList <Factura> facturas;
	
	public Cliente(String nombre, String iD, String direccion , int telefono) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		ID = iD;
		this.telefono = telefono;
		facturas = new ArrayList<Factura>();
	}
	

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public ArrayList<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}
	
	
	//METODOS
	
	public void Annadir_Factura(Factura aux) {
		facturas.add(aux);		
	}
	public void Remover_Factura(Factura aux) {
		facturas.remove(aux);
	}
	
	
	
	
}
