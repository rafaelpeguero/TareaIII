package logico;

import java.io.Serializable;

public class Factura implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ID;
	private Cliente factura_cliente;
	private Inventario quesos_facturados;
	
	
	
	public Factura(String iD, Cliente factura_cliente, Inventario quesos_facturados) {
		super();
		ID = iD;
		this.factura_cliente = factura_cliente;
		this.quesos_facturados = quesos_facturados;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Cliente getFactura_cliente() {
		return factura_cliente;
	}

	public void setFactura_cliente(Cliente factura_cliente) {
		this.factura_cliente = factura_cliente;
	}

	public Inventario getQuesos_facturados() {
		return quesos_facturados;
	}

	public void setQuesos_facturados(Inventario quesos_facturados) {
		this.quesos_facturados = quesos_facturados;
	}

	
	
	//METODOS
	
	public float Total_factura() {
		float total = 0;
		for(Queso aux : quesos_facturados.getInventario()) {
			if(aux instanceof Esferico) {
				total += ( (Esferico) aux).Precio_Total();
			}
			if (aux instanceof Cilindrico) {
				total += ((Esferico)aux).Precio_Total();
			}
			if(aux instanceof CilindricoHueco) {
				total += ((CilindricoHueco)aux).Precio_Total();
			}
		}
		return total;
	}

}
