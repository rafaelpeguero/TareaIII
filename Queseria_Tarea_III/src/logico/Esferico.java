package logico;

public class Esferico extends Queso {


	private String tipo = "Esférico";
	
	public Esferico(float precio_base, float precio_unitario, float radio, String nombre, int cantidad) {
		super(precio_base, precio_unitario, radio, nombre, cantidad);
		
	}

	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public float Volumen() {
		double volumen = 0;
		
		volumen =   (4/3) * ((Math.PI) * (Math.pow(getRadio(), 3) ) )  ;
		return (float) volumen;
	}
	
	public float Precio_Total() {
		double total = 0;
		total = getCantidad() * ( getPrecio_base() + getPrecio_unitario() ) * (Volumen()/100);
		return (float) total;
	}


}
